package com.company.보안기능_2;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;

/*
    하드코드된 중요정보

    프로그램 코드 내부에 비밀번호를 하드코딩하는 경우
    암호화에 사용되는 키를 소스코드에 하드코딩하여 암호화를 수행하는 경우

    (영향)
    - 하드코드된 비밀번호를 내부 인증에 사용하거나 외부 컴포넌트와 통신에 사용하는 경우 관리자 정보가 노출될 수 있다.
    - 하드코드된 비밀번호가 인증 실패 등을 야기하는 경우 시스템 관리자가 원인을 파익하는 것이 어려울수 있다.
    - 실행파일이 유출되는 경우 역컴파일을 통해 암호화 키가 유출될 수 있으며, 이렇게 유출된 키는 암호화 된 중요정보를
      복호화하는데 사용할 수 있다.

    (대응)
    - 비밀번호는 암호화하여 별도의 파일이나 DB에 안전하게 저장하여 사용해야 한다.
    - SW 설치 시 사용하는 디폴트 패스워드 대신, "최초 로그인" 모드를 두어 사용자가 패스워드를 직접 입력하도록 설게해야 한다.
    - 암호화 처리에 사용되는 키는 암호화하여 안전한 별도의 위치에 저장해야 한다.
 */
public class 하드코드된중요정보 {

    public class MemberDAO {
        private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
        private static final String URL = "jdbc:oracle:thin:@192.168.0.3.:1521:ORCL";
        private static final String USER = "SCOTT"; //DB ID
        private static final String PASS = "TIGER"; //DB PWD //취약함
        // -> 하드코드된 비밀번호는 노출의 위험성이 있으니 삭제
        //private static final String PASS = "TIGER";
        Member_list mList;

        public MemberDAO() {}

        public MemberDAO(Member_list mList) {
            this.mList = mList;
        }

        public Connection getConn() {
            Connection con = null;
            try {
                Class.forName(DRIVER);
                ////////////////////////////////////
                // -> 비밀번호는 암호화하여 별도의 파일이나 DB에 안전하게 보관한다
                String PASS = props.getProperty("EncryptPswd"); //설정파일에 암호화된 패스워드 저장
                byte[] decryptedPswd = cipher.doFinal(PASS.getBytes());
                PSS = new String(decryptedPswd); // 복호화하여 사용
                ////////////////////////////////////
                con = DriverManager.getConnection(URL, USER, PASS);
            } catch(Exceptio e) {
                log.error(e.getMessage());
            }
            return con;
        }
    }



    //하드코딩된 암호화 키
    public String encryptString(String usr) {
        String key = "*234234lkd(9)DE"; // 암,복호화에 사용되는 암호화 키가 소스코드에 하드코딩되어 있음
        //->
        /////////////////////////////////////
        //String key = "*234234lkd(9)DE"; // 하드코딩된 암호화 키 삭제
        String key = getPassword("./password.ini"); //암호화키는 별도의 파일에서 암호화된 상태로 로딩
        key = decrpt(key); //연결을 위해 복호화하여 사용
        /////////////////////////////////////

        if(key != null) {
            byte[] bToEncrypt = usr.getBytes("UTF-8");
            SecretKeySpec sKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
            byte[] bCipherText = aesCipher.doFinal(bTOEncrypt);
            return String(bCipherText);
        }
    }
}
