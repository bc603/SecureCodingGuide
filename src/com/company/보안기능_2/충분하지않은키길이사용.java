package com.company.보안기능_2;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/*
    충분하지 않은 키 길이 사용

    데이터의 기밀성, 무결성을 보장하기 위해 암호화에 사용되는 키의 길이가 충분하지 않은 경우

    (영향)
    - 길이가 짧은 키를 사용하는 것은 암호화 알고리즘을 취약하게 만들수 있다.
    - 검증된 암호화 알고리즘을 사용하더라도, 키 길이가 충분히 길지 않으면 짧은 시간안에 키를 찾아낼수 있고
      이를 이용해 공격자가 암호화된 데이터나 패스워드를 복호화할수 있게 된다.

     (대응)
     - AES, ARIA, SEED와 같은 대칭키 암호화 알고리즘은 128bit이상의 키를 사용한다.
     - RSA와 같은 공개키 암호화 알고리즘은 2048bit 이상의 키를 사용한다.

 */
public class 충분하지않은키길이사용 {

    public static final String ALGORITHM = "RSA"; //공개키
    public static final String PRIVATE_KEY_FILE = "C:/keys/private.key";
    public static final String PUBLIC_KEY_FILE = "C:/keys/public.key";

    public static void generateKey() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024); // 취약점 RSA는 공개키이기 때문에 2048bit 이상의 키길이가 안전하다
            // ->
            keyGen.initialize(2048); // 2048 bit 이상의 키를 사용한다
            final KeyPair key = keyGen.generateKeyPair();
            ...
            File privateKeyFile = new File(PRIVATE_KEY_FILE);
            File publicKeyFile = new File(PUBLIC_KEY_FILE);
        } catch(Exceptio e) {
            ...
        }
    }
}
