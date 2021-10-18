package com.company.보안기능_2;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/*
    취약한 암호화 알고리즘 사용

    중요정보를 암/복호화 할때 취약함 암호 알고리즘을 사용하는 경우

    (영향)
    외부로 유출된 중요 정보가 복호화되어 노출될 수 있다.

    (대응)
    - 자체적인 암호화 알고리즘을 개발해서 사용하는 것은 안전하지 않으므로,
      학계 및 업계에서 이미 검증된 표준화된 알고리즘을 사용한다.
    - 취약하다고 알려진 DES, RC5 등의 알고리즘 대신 3TDEA, AES, SEED, LEA, RSA, SHA2, SHA3
      등의 안전한 암호 알고리즘을 사용하여 암복호화 작업을 수행한다.
 */
public class 취약한암호화알고리즘사용 {

    public class CryptoUtils {
        public byte[] encrypt(byte[] msg, Key k) {
            byte[] rslt = null;
            try {
                Cipher c = Cipher.getInstance("DES"); //취약함 암호화 알고리즘을 사용
                // ->
                // 학계 및 업계에서 이미 검증된 표준화된 암호화 알고리즘을 사용한다.
                Cipher c = Cipher.getInstance("AES/CBC/PCKS5Padding");
                c.init(Cipher.ENCRYPT_MODE, k);
                rslt = c.update(msg);
            } catch(InvalidKeyException e) {
                System.err.print("Exception occured");
            } catch(NoSuchAlgorithmException e) {
                System.err.print("Excption occured");
            } catch(NoSuchPaddingException e) {
                System.err.print("Exception occured");
            }
            return rslt;
        }
    }

}
