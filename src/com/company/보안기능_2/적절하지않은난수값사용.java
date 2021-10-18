package com.company.보안기능_2;

import java.security.SecureRandom;
import java.util.Random;

/*
    적절하지 않은 난수값 사용

    예측 가능한 난수를 발생시키는 취약한 API를 사용하는 경우

    (영향)
    공격자가 SW에서 생성되는 다음 숫자를 예상하여 시스템을 공격하는 것이 가능한다.

    (대응)
    세션 아이디, 암호화 키 등 보안결정을 위한 값을 생성하고 보안결정을 수행하는 경우,
    java.security.SecureRandom 클래스를 사용
 */
public class 적절하지않은난수값사용 {

    public static void main(String[] args) {

        Random number = new Random(123L); // 예측가능한 난수를 발생시키는 취약한 API사용
        /////////////////////////////
        // -> 예측 불가능한 난수를 발생시키는 안전한 API 사용
        SecureRandom number = new SecureRandom.getInstanceStrong();
        /////////////////////////////
        //...
        for (int i = 0; i < 20; i++) {
            //0 ~ 20의 난수값으로 생성
            int n = number.nextInt(21);
            System.out.println(n);
        }
    }
}
