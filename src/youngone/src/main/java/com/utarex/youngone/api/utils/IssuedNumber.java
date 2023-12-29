package com.utarex.youngone.api.utils;

import java.security.SecureRandom;

public class IssuedNumber {
	public final static String Get(int length) {
		SecureRandom sr = new SecureRandom();
		StringBuilder res = new StringBuilder();
		for(int i=0;i<length;i++) {
			int d = (int)(sr.nextFloat() * 10) % 10;
			res.append(d);
		}
		return res.toString();
	}
}
