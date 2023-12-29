// [global > global.js 설명]
// 1. 모든 컴포넌트가 공유할 수 있는 글로벌 영역 입니다

// [설정 수행 실시]
///*
export default {

    install(Vue) {
        
        Vue.config.globalProperties.$getEmergencyMessageFromCode = function(code) {
            let message = code;
            switch(code) {
                case '0':   message = '긴급 호출 발생'; break;
                case '1':   message = '냉수 온도 70℃ 이상 에러'; break;
                case '2':   message = '냉수 온도 -40℃ 이상 에러'; break;
                case '3':   message = '탄산 온도 70℃ 이상 에러'; break;
                case '4':   message = '탄산 온도 -40℃ 이상 에러'; break;
                case '5':   message = '온수 온도 150℃ 이상 에러'; break;
                case '6':   message = '온수 온도 -20℃ 이상 에러'; break;
                case '7':   message = '냉각  에러'; break;
                case '8':   message = '누수 에러'; break;
                case '9':   message = '만수 에러'; break;
                case '10':  message = '히터 에러'; break;
                case '11':  message = '콤프레샤 에러'; break;
                case '12':  message = '부스터 펌프 에러'; break;
                case '13':  message = '냉수탱크 UVC 수명 경과 경고'; break;
                case '14':  message = '탄산탱크 UVC 수명 경과 경고'; break;
                case '15':  message = '얼음저장고 UVC 수명 경과 경고'; break;
                case '16':  message = '음료출수부 UVC 수명 경과 경고'; break;
                case '17':  message = '얼음출수부 UVC 수명 경과 경고'; break;
                case '18':  message = '시럽1 부족 경고'; break;
                case '19':  message = '시럽2 부족 경고'; break;
                case '20':  message = '시럽3 부족 경고'; break;
                case '21':  message = '시럽4 부족 경고'; break;
                case '22':  message = '탄산가스 부족 경고'; break;
                case '23':  message = '핫 스위치 경고'; break;
                case 'USER_CALL': message = '긴급호출'; break;
            }
            return message;
        }        
    }
}
//*/