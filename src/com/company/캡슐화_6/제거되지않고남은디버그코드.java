package com.company.캡슐화_6;

/*
    제거되지 않고 남은 디버그코드
    디버그 코드는 설정 등의 민감한 정보를 포함하고 있거나, 시스템을 제어하게 허용하는 부분을 담고 있을수 있다.
    만일 디버그 코드가 남겨진 채로 배포될 경우, 공격자가 식별 과정을 우회하거나 의도하지 않은 정보와 제어 정보가 노출될 수 있다.
 */
public class 제거되지않고남은디버그코드 {

    class Base64 {
        public static void main(String[] ars) {
            if (debug) {
                byte[] a = {(byte) 0xfc, (byte) 0x0f, (byte) 0xc0};
                byte[] b = {(byte)0x03, (byte) 0xf0, (byte) 0x3f};
                ...
            }
            // SW 배포 전, 반드시 디버그 코드를 확인, 삭제한다.
            // if(debug) { } 삭제
            // 추가로
            // 애플리케이션 제작시 디버그 용도로 개발한 main() 할수는 디버깅이 끝나면 삭제한다.
        }
        public void otherMethod() {...}
    }
}
