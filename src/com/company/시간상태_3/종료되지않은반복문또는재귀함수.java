package com.company.시간상태_3;

/*
    종료되지 않은 반복문 또는 재귀함수

    재귀함수의 횟수를 제어하지 않아 메모리나 프로그램 스택 등의 자원을 과다하게 사용하는 경우

    (영향)
    귀납조건(base case)이 없는 재귀함수는 무한 루프에 빠지게 되고, 자원고갈을 유발함으로써
    시스템의 정상적인 서비스를 제공할 수 없게 한다.

    (대응)
    재귀 호출시, 재귀 호출 횟수를 제한하거나, 초기값을 설정하여 재귀 호출을 제한한다.
 */
#include<stdlib.h>
#include<stdio.h>

int factorial(int i) {
    return i * factorial(i - 1); // 재귀함수의 횟수를 제어하지 않아 메모리나 프로그램 스택 자원을 과다하게 사용 가능

    // -> 귀납조건 설정 - 재귀 호출시 재귀 호출 횟수를 제한하거나, 초기값을 설정하여 재귀호출을 제한한다.
    if(i <= 1) {
        return 1;
    }
    return i * factorial(i - 1);
}

int main() {
    int i = 0;
    char szInput[256];

    print("숫자를 입력하세요");
    fgets(szInput, 256, stdin);
    i = ato(szInput);
    if(i <= 0) {
        fputs("0 보다 큰 수를 입력하세요.", stderr);
    } else {
        printf("%d!= %d", i, factorial(i));
    }
    return 0;
}
