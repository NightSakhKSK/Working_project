package com.utarex.youngone.api.iot.listener;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.utarex.youngone.api.user.entity.Command;
import com.utarex.youngone.api.user.repository.UserRepo;
import com.utarex.youngone.api.user.service.UserService;
import com.utarex.youngone.api.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utarex.youngone.api.device.entity.DeviceStatusIoT;
import com.utarex.youngone.api.device.repository.DeviceRepo;
import com.utarex.youngone.aws.utils.CommandLineUtils;

import software.amazon.awssdk.crt.CRT;
import software.amazon.awssdk.crt.CrtResource;
import software.amazon.awssdk.crt.CrtRuntimeException;
import software.amazon.awssdk.crt.mqtt.MqttClientConnection;
import software.amazon.awssdk.crt.mqtt.MqttClientConnectionEvents;
import software.amazon.awssdk.crt.mqtt.MqttMessage;
import software.amazon.awssdk.crt.mqtt.QualityOfService;
import software.amazon.awssdk.iot.iotjobs.model.RejectedError;

@Component
public class IoTListener implements ApplicationListener<ApplicationEvent>, Runnable	 {

	/** 작업을 수행할 thread */
    private Thread thread;
    private boolean isShutdown = false;

    private String ca_file = "C:\\Users\\utarex\\Desktop\\IOTServerPerm\\root-CA.crt";
    private String cert = "C:\\Users\\utarex\\Desktop\\IOTServerPerm\\IoTServer.cert.pem";
    private String key = "C:\\Users\\utarex\\Desktop\\IOTServerPerm\\IoTServer.private.key";

//    private String ca_file = "/home/ec2-user/perm/root-CA.crt";
//    private String cert = "/home/ec2-user/perm/IoTServer.cert.pem";
//    private String key = "/home/ec2-user/perm/IoTServer.private.key";

	// When run normally, we want to exit nicely even if something goes wrong
    // When run from CI, we want to let an exception escape which in turn causes the
    // exec:java task to return a non-zero exit code
    static String ciPropValue = System.getProperty("aws.crt.ci");
    static boolean isCI = ciPropValue != null && Boolean.valueOf(ciPropValue);

	static CommandLineUtils cmdUtils;

	static void onRejectedError(RejectedError error) {
        System.out.println("Request rejected: " + error.code.toString() + ": " + error.message);
    }

    /*
     * When called during a CI run, throw an exception that will escape and fail the exec:java task
     * When called otherwise, print what went wrong (if anything) and just continue (return from main)
     */
    static void onApplicationFailure(Throwable cause) {
        if (isCI) {
            throw new RuntimeException("BasicPubSub execution failure", cause);
        } else if (cause != null) {
            System.out.println("Exception encountered: " + cause.toString());
        }
    }

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ApplicationStartedEvent) {
			System.out.println("IOT Start Listener");
			//IOT 서비스 시작
			if (thread == null) {
	            thread = new Thread(this, "Daemon thread for background task");
//	            thread.setDaemon(true);
	        }
	        if (!thread.isAlive()) {
	            thread.start();
	        }
		} else if (event instanceof ContextClosedEvent) {
			System.out.println("IOT Stop Listener");
			//IOT 서비스 종료
			System.out.println ("== DaemonListener.contextDestroyed has been called. ==");
	        this.isShutdown = true;
	        try
	        {
	            thread.join();
	            thread = null;
	        }
	        catch (InterruptedException ie)
	        {
	            ie.printStackTrace();
	        }
		}
	}

	MqttClientConnection connection = null;

	@Override
	public void run() {
		while(!this.isShutdown) {
			subscribe();
		}
	}

	@Autowired
    DeviceRepo deviceRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

	public void subscribe() {
		// TODO Auto-generated method stub
		String[] args = {"--endpoint","a2jf8v1rtgh15g-ats.iot.ap-northeast-2.amazonaws.com"
				        ,"--client_id","iot-server"
				        ,"--topic","services/*"
				        ,"--ca_file",ca_file
				        ,"--cert",cert
				        ,"--key",key};
		cmdUtils = new CommandLineUtils();
        cmdUtils.registerProgramName("PubSub");
        cmdUtils.addCommonMQTTCommands();
        cmdUtils.addCommonTopicMessageCommands();
        cmdUtils.registerCommand("key", "<path>", "Path to your key in PEM format.");
        cmdUtils.registerCommand("cert", "<path>", "Path to your client certificate in PEM format.");
        cmdUtils.registerCommand("client_id", "<int>", "Client id to use (optional, default='test-*').");
        cmdUtils.registerCommand("port", "<int>", "Port to connect to on the endpoint (optional, default='8883').");
        cmdUtils.registerCommand("count", "<int>", "Number of messages to publish (optional, default='10').");
        cmdUtils.sendArguments(args);

        MqttClientConnectionEvents callbacks = new MqttClientConnectionEvents() {
            @Override
            public void onConnectionInterrupted(int errorCode) {
                if (errorCode != 0) {
                    System.out.println("Connection interrupted: " + errorCode + ": " + CRT.awsErrorString(errorCode));
                }
            }

            @Override
            public void onConnectionResumed(boolean sessionPresent) {
                System.out.println("Connection resumed: " + (sessionPresent ? "existing session" : "clean session"));
            }
        };

        try {

            connection = cmdUtils.buildMQTTConnection(callbacks);
            if (connection == null)
            {
                onApplicationFailure(new RuntimeException("MQTT connection creation failed!"));
            }

            CompletableFuture<Boolean> connected = connection.connect();
            try {
                boolean sessionPresent = connected.get();
                System.out.println("Connected to " + (!sessionPresent ? "new" : "existing") + " session!");
            } catch (Exception ex) {
                throw new RuntimeException("Exception occurred during connect", ex);
            }

            CompletableFuture<Integer> subscribed = connection.subscribe("services/info", QualityOfService.AT_LEAST_ONCE, (message) -> {
                String payload = new String(message.getPayload(), StandardCharsets.UTF_8);
                DeviceStatusIoT dsf = null;
				try {
					dsf = (new ObjectMapper()).readValue(payload, DeviceStatusIoT.class);
					if(dsf != null && dsf.getMcId() != null) {
						deviceRepo.pushInfo(dsf);
                        // alarm_e : [ on | off ]
                        // alarm_c : USER_CALL | 0000 | ...
                        if (dsf.getAlarm_e() != null && dsf.getAlarm_e().equalsIgnoreCase("on")) {
                            String userId = userRepo.getUserIdFromMcId(dsf.getMcId());
                            userRepo.insEmergency(userId, dsf.getAlarm_c());
                        }

                        //
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("id", "server_01");
                        jsonObject.addProperty("runtime", sdf.format(new Date()));
                        jsonObject.addProperty("type", "response");

                        // MC_ID 를 비교하여 publish
                        JsonArray jsonArray = new JsonArray();
                        for (int i = userService.getCommandToPublish().size() - 1; i >= 0; i--) {
                            Command cmd = userService.getCommandToPublish().get(i);

                            if (dsf.getMcId().equals(cmd.getMcId())) {

                                // Action 처리
                                JsonObject jsonAction = new JsonObject();
                                jsonAction.addProperty("action", cmd.getAction()); // INIT | UPDATE
                                jsonAction.addProperty(cmd.getValue(), "on"); // 예: "u_carbon_r"

                                //
                                jsonArray.add(jsonAction);

                                // 메시지 처리 후 메시지 큐에서 제거
                                userService.getCommandToPublish().remove(i);
                            }
                        }

                        jsonObject.add("", jsonArray);

                        Gson gson = new Gson();
                        String json = gson.toJson(jsonObject);
                        System.out.println(json);


                        // publish : 보낼 메시지가 있을 경우에만
                        final String mcId = dsf.getMcId();
                        if (jsonArray.size() > 0) {
                            new Thread(() -> {
                                String topic = "devices/" + mcId;
                                try {
                                    CompletableFuture<Integer> published = connection.publish(new MqttMessage(topic, json.getBytes(), QualityOfService.AT_LEAST_ONCE, false));
                                    published.get();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }
					}
				} catch (Exception e) {
					System.out.println("========= Error" + e.getMessage());
				}
				if(dsf != null) System.out.println("MESSAGE: " + dsf.toString());
            });

            subscribed.get();


//            String test = "{\"id\":\"server_01\",\"runtime\":\"2022-11-16T11:59:50\",\"type\":\"response\",\"\":[{\"action\":\"INIT\",\"u_cold_r\":\"on\"}]}";
//            CompletableFuture<Integer> published = connection.publish(new MqttMessage("devices/", test.getBytes(), QualityOfService.AT_LEAST_ONCE, false));
//            published.get();


            int count=0;
            while(!this.isShutdown && count < 6000) {
            	Thread.sleep(100);
            	count++;
            }

            CompletableFuture<Void> disconnected = connection.disconnect();
            disconnected.get();

            // Close the connection now that we are completely done with it.
            connection.close();
        } catch (CrtRuntimeException | InterruptedException | ExecutionException ex) {
            onApplicationFailure(ex);
        }

        CrtResource.waitForNoResources();
	}
}
