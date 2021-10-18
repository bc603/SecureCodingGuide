package com.company.캡슐화_6;

import java.util.Arrays;

/*
    Public 메소드로 부터 반환된 Private 배열
    private으로 선언된 배열을 public 으로 선언된 메소도를 통해 반환하는 경우,
    그 배열의 레퍼런스가 외부에 공개되어 외부에서 배열이 수정될수 있다.
 */
public class Public메소드로부터반환된Private배열 {

    public class GetPrivateArrayByPublichMethod {
        private String[] colors;
        // 배열 주소값이 외부에 공개되어 권한이 없는 사용자가 캡슐화된 중요 데이터를 직접적으로 수정하는것이 가능한다.
        public String[] getColors() {
            return this.colors;
        }
        public getPrivateArrayByPublicMethod() {
            this.colors = new String[] { "red", "orange", "yellow", "green", "blue"};
        }
        public void print() { System.out.pritln(Array.toString(this.colors))}

        public static void main(String[] args) {
            GetPrivateArrayByPublichMethod innerData = new GetPrivateArrayByPublichMethod();
            innerData.print();
            String[] outterData = innerData.getColors(); //취약점
            // 배열 주소값이 외부에 공개되어 권한이 없는 사용자가 캡슐화된 중요 데이터를 직접적으로 수정하는것이 가능한다.
            outterData[1] = "blue"; //취약점
            System.out.println(Arrays.toString(outterData));
            innerData.print();
            // -> private로 선언된 배열을 public 메소드를 통해 변환하지 않도록 하고
            // 필요한 경우에는 배열의 복제본을 반환하거나, 수정을 제어하는 별도의 public 메소드를 선언하여 사용한다.
        }

        //-> 수정
        // 배열의 복제본을 반환
        private String[] colors;
        public String[] getColors() {
            String[] safeArray = null;
            if(this.colors != null) {
                safeArray = new String[this.colors.length];print();
                for(int i = 0; i < this.colors.length; i++) {
                    safeArray[i] = this.colors[i];
                }
            }
        }
    }
}
