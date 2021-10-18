package com.company.보안기능_2;

/*
    소프트웨어 개발자가 편의를 위해 주석문 안에 남겨둔 시스템 주요정보를 제거하지 않은 경우

    (영향)
    소스코드에 포함된 중요정보(ID, 패스워드, 시스템정보, 실행환경 등)는 역컴파일(decompile)을 통해
    쉽게 유출될수 있으며, 이런 정보를 활용하여 공격자는 시스템을 공격할 수 있다.

    (대응)
    - 주석문에는 ID, 패스워드 등 보안과 관련된 내용을 기입하지 않는다.
    - 프로그램 개발 시 주석문으로 남겨둔 사용자 계정이나 패스워드 등의 정보는
 */
public class 주석문안에포함된시스템주요정보 {

    //안전하지 않은 코드
    public void daoTest() throws Exception {
        //dbsample : 84ddfkdfkasdlf490sf
        //암호화전,후 : 134545admin_01, akdf9080973802840234sfsf
        String pwd = "akdf9080973802840234sfsf";
        String pwd1 = ARIAEnging.decAria(pwd);
        System.out.println(pwd1);
    }

    //-> 안전한 코드
    public void daoTest() throws Exception {
        String pwd = "akdf9080973802840234sfsf"; // 개발자의 편의를 위해 주석문 안에 남겨둔 시스템 주요 정보 제거
        String pwd1 = ARIAEnging.decAria(pwd);
        System.out.println(pwd1);
    }
}
