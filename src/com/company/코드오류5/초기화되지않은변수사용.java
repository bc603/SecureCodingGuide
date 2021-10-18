package com.company.코드오류5;

/*
    초기화 되지 않은 변수 사용
    C언에서 스택 메모리에 저장되는 지역변수는 생성시 자동으로 초기화 되지 않는데 이 변수를 사용하는 경우

    초기화 되지 않은 스택 메모리 영역의 변수는 이전 함수에서 사용되었던 내용을 포함하고 있다.
    공격자는 이러한 약점을 이용하여 메모리에 저장되어 있느 값을 읽거나 특정 코드를 실행할 수 있다.

    모든 변수를 사용전에 반드시 초기값을 할당 한다.
 */
public class 초기화되지않은변수사용 {

    int main(int argc, const char* argv[]) {
        int x, y; //초기화 되지 않은 스택 메모리 영역의 변수는 이전 함수에서 사용되었던 내용을 포함하고 있다.
        switch(position) {
            case 0: x = base_position; y = base_position; break;
            case 1: x = base_position + i; y = base_position - i; break;
            default: x = 1; break;
        }
        setCursorPosition(x, y);

        // --> 모든 변수를 사용전에 반드시 초기값을 할당 한다.
        int x = 1, y = 1;
    }
}
