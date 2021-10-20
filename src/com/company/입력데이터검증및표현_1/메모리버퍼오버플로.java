package com.company.입력데이터검증및표현_1;

/*
    메모리 버퍼 오버플로

    (원인) 버퍼를 이용해서 메모리를 사용할 때, 버퍼의 크기 보다 큰 데이터를 버퍼에 기록하는 경우
          버퍼의 경계를 넘어 다른 메모리 영역을 침범하게 되는 경우

    (영향) 메모리 상의 중요한 정보를 읽거나 시스템 충돌을 발생시키거나, 리턴 주소를 조작하여 임이의 코드가 실행되도록 조작할 수 있다.

    (대응)
    - 메모리 버퍼를 사용할 경우 버퍼의 범위를 넘어서 데이터를 접근하지 못하도록 버퍼의 크기와
      사용하는 인덱스 값을 정확하게 사용한다.
    - 외부로부터 입력 받은 값을 기준으로 버퍼나 버퍼에 기록할 데이터의 크기를 결정할 경우
      그 값이 타당한지 검사한 후 사용한다.
    - 문자열 저장을 위해 메모리 버퍼를 사용하는 경우 널(NULL) 문자로 종료한다.
 */
public class 메모리버퍼오버플로 {

    // 안전하지 않은 코드
    typedef struct _charvoid {
        char x[16];
        void *y;
        void *z;
    } charvoid;

    void badCode() {
        charvoid cv_struct;
        cv_struct_y = (void*) SRC_STR;
        printLine((char*)cv_struct.y);

        //버퍼의 크기보다 큰 데이터를 버퍼에 기록할 수 있음
        memcpy(ct_struct.x, SRC_STR, sizeof(cv_struct)); //안전하지 않은 코드
        printLine((char*)cv_struct.x);
        printLine((char*)cv_struct.y);

        //->
        // 버퍼의 크기보다 큰 데이터를 버퍼에 기록하지 않게 버퍼에 기록할 데이터의 크기를 검사
        memcpy(cv_struct.x, SRC_STR, sizeof(cv_struct.x));
        cv_struct.x[sizeof(cv_struect.x)/sizeof((char))-1] = '\0';
    }
}
