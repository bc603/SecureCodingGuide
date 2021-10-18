package com.company.코드오류5;

/*
    C나 C++ 프로그램 처럼 메모리 해제를 개발자가 수행해야 하는 경우 해제된 자원을 해제하거나
    헤제된 자원을 사용하는 경우

    해제한 메모리를 참조하게 되면 예상치 못한 값 또는 코드를 실행하게 되어 오류나
    비정상 실행이 될수 있다.

    -> 동적으로 할당된 메모리를 해제한 후 그 메모리를 참조하고 있던 포인터를 참조 추적이나 형 변환,
       수식에서의 피연산자 등을 사용하여 해제된 메모리에 접근하지 않도록 한다.
    -> 메모리 해제 후 포인터에 NULL값을 저장하거나 다른 적절한 값을 저장하면 예상치 못한 코드의 실행을 막을수 있다.
 */
public class 해제된자원사용 {

    int main(int argc, const char* argv[]) {
        char *temp;
        temp = (char*)malloc(BUFFER_SIZE);
        if(!temp) {
            /* 에러 처리코드 생략 */
        }

        free(temp); //메모리 해제
        ..
        stmcpy(temp, argv[1], BUFFER_SIZE-1); //해제된 메모리를 참조

        // -->
        stmcpy(temp, argv[1], BUFFER_SIZE-1); //메모리 해제전에 자원을 사용한다.
        free(temp);
    }


    int main(int argc, const char* argv[]) {
        char* data;
        int data_type;
        if (data_type == val_1) { free(data); }
        ..
        if (data_type == val_2) { free(data); }

        // -->
        char* data;
        int data_type;
        if(data_type == val_1) {
            free(data);
            data = NULL; //메모리 해제후 포인터에서 NULL값을 저장하거나 다른 적절한 값을 저장하여 예상치 못한 코드의 실행을 막음
        }
        ...
        if(data_type == val_2) {
            free(data);
            data = NULL; //메모리 해제후 포인터에서 NULL값을 저장하거나 다른 적절한 값을 저장하여 예상치 못한 코드의 실행을 막음
        }
    }
}
