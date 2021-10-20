package com.company.입력데이터검증및표현_1;

import java.util.Calendar;

/*
    포맷스트링 삽입

    (원인) printf(), ftprintf(), sprintf()와 같이 포맷 스트링을 사용하는 함수를 사용하는 경우,
          외부로부터 입력된 값을 검증하지 않고 입출력 함수의 포맷문자열로 그대로 사용하는 경우

    (영향) 포맷 문자열을 이용하여 취약함 프로세스를 공격하거나 메모리 내용을 읽거나 쓸 수 있으며,
          리턴 주소를 조작하여 임의의 코드를 실행하거나 데이터 표현에 관한 문제를 유발할 수 있다.

    (대응)
    - 포맷 스트링을 사용하는 함수를 사용할 때, 사용자 입력값을 포맷 문자열로 바로 사용하거나
      포맷 스트링 생성에 사용하지 않는다.
    - 포맷 스트링에 표현되어 있는 인자와 매개 변수의 개수를 일치시킨다.
 */
public class 포맷스트링삽입 {

    public static void main(String[] args) {

        //안전하지 않은 코드
        Calendar validDate = Calendar.getInstance();
        validDate.set(2018, Calendar.OCTOBER, 14);

        if(args.length <= 0) {
            System.out.println("Please Input Data (YYYY-MM-DD)");
            return;
        }

        // 사용자 입력값을 포맷 문자열로 바로 사용함
        System.out.printf(
                args[0] + " did not match! HINT: It was issued on %1$terd of some month", validDate
        );
        //-> 안전한 코드
        /////////////////////////////
        // 사용자 입력값을 포맷 문자열로 바로 사용하지 않고, 포맷 스트링에 표현되어있는 인자와 매개 변수의 개수를 일치시킨다.
        System.out.println(
                "%s did not match!HINT: issued on %2$terd of some month", args[0], validDate
        );
    }

    //안전하지 않은 코드 ??????
    void incorrect_password(const char* user) {
        static const char msg_format[] = "%s cannot be authenticated.\n";
        size_t len = strlen(user) + sizeof(msg_format);
        char *msg = (char*)malloc(len);
        if(msg == NULL) {
            /* 오류 처리 */
        }
        int ret = snprintf(msg, len, msg_format, user);
        if(ret < 0 || ret >= len) {
            /* 오류 처리 */
        }

        fprintf(stderr, msg); // 포맷스트링???
        // ->
        // 안전한 코드
        //fprintf(stderr, msg); // 포맷스트링???
        if(fputs(msg, stderr) == EOF) {
            /* 오류처리 */
        }
        free(msg);
        msg = null;
    }
}
