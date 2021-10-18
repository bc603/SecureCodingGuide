package com.company.보안기능_2;

import java.security.MessageDigest;

/*
    솔트없이 일방향 해쉬 함수 사용

    일방향 해쉬(one way hash) 함수를 사용할때 솔트(salt)를 적용하지 않은 경우

    (영향)
    공격자가 레인보우 테이블과 같이 가능한 모든 해쉬 값을 미리 계산한 후 전수 조사를 통해 원문을 추출

    (대잉)
    해쉬값을 생성할는 경우 반드시 솔트를 적용한다
 */
public class 솔트없이일방향해쉬함수사용 {

    //안전하지 않은 코드
    public String getPasswordHash(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte[] byteData = md.digest(); //일방향 해쉬함수 사용시 솔트값을 적용하지 않았음
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    //-> 안전한 코드
    public String getPasswordHash(String password, byte[] salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        md.update(salt); // 해쉬값을 생성할때는 반드시 솔트랎을 적용한다.

        byte[] byteData = md.digest(); //일방향 해쉬함수 사용시 솔트값을 적용하지 않았음
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
