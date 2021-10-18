package com.company.보안기능_2;

/*
    반복된 인증시도 제한 기능 부재

    인증을 위한 기능 구현시 인증시도 횟수를 적절한 로직으로 제한하지 않은 경우

    (영향)
    인증시도 횟수를 제한하지 않는 경우 공격자는 툴을 이용하여 무작위 대입 인증시도를 통해 계정 접근 권한을 얻을 수 있다.

    (대응)
    - 인증시도 횟수를 제안하여 제한된 횟수를 초과하는 인증시도에 대해 인증시도 시간 간격을
      설정하여 툴을 이용한 인증시도 공격을 최대한 어렵게 만든다.
    - 중요 시스템에 대해서는 인승시도 횟수 제한을 초과한 계정에 대해서는 계정 잠금 정책을 적용하여 공격이 불가능하게 한다
 */
public class 반복된인증시도제한기능부재 {

    // 안전하지 않은 코드
    int validateUser(char *host, int port) {
        int socket = openSocketConnect(host, port);
        if (socket < 0 ) {
            print("Unable to open socket connection");
            return(FAIL);
        }
        
        int isValidUser = 0;
        char nm[NAME_SIZE];
        char pw[PSWD_SIZE];
        while(isValidUser == 0) {
            if(getNextMsg(socket, nm, NAME_SIZE) > 0) {
                if(getNextMsg(socket, pw, PSWD_SIZE) > 0) {
                    isValidUser = AuthenticateUser(nm, pw);
                }
            }
        }
    }
    
    // ->
    // 안전한 코드 - 인증시도 회수 제한을 설정
    int validateUser(char *host, int port) {
        ...
        int count = 0;
        while((isValidUser==0) && (count<MAX_ATTEMPTS)) { //인증시도 횟수 제한을 설정
            count++;
        }
        if(isValidUser) {
            return(SUCCESS);
        } else {
            return(FAIL);
        }
    }
}
