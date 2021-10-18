package com.company.보안기능_2;

import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

/*
    부적절한 인증서 유효성 검증(11)

    인승저를 확인하지 않거나 인증서 확인 절차를 적절하게 수행하지 않음

    (영향)
    악의적인 호스트에 연결되거나 신뢰할수 없는 호스트에서 생성된 데이터를 수신할 수 있다.

    (대응)
    - 인증서를 사용하기 전에 인승저의 유효성을 확인한다
    - 인증서의 Common Name과 실제 호스트가 일치하는지,
      신뢰된 발급기관(CA, RootCA)의 서명 여부,
      인증서의 유효기간(begin date, end date),
      인증서의 해지여부(revoked),
      안전한 암호화 알고리즘 사용여부
      확인 등을 통해 유효한 인증서인지 검증하는 절차를 구현하여야 한다.
 */
public class 부적절한인증서유효성검증 {

    public static void main(String[] args) {
        //안전하지 않은코드
        if((cert = SSL_get_peer_certificate(ssl) && host))
            foo = SSL_get_verify_result(ssl);
        if((X509_V_OK ==foo) || X509_V_ERR_SELF_SIGNED_CERT_IN_CHAIN==foo))
            //자체서명된 인증서일수 있음

        //안전하지 않은 코드
        cert = SSL_get_peer_certificate(ssl);
        if(cert && (SSL_get_verify_result(ssl) == X509_V_OK)) {
            //CN을 확인하지 않았지만 신뢰하고 진행한다. 이럴 경우 공격자가 CommonName을
            //www.attack.com으로 설정하여 중간자 공격에 사용할 경우 데이터가 중간에서 복호화되고 있음을
            //탐지하지 못한다.
        }
    }

    //-> 안전한 코드
    // 인증서와 실제 호스트 일치확인,등 등
    private boolean verifySignature(X509Certificate toVerify, X509Certificate signingCert) {
        //검증하려는 호스트 인증서(toVerify)와 CA인증서(signingCert)의 DN(Distinguished Name)이 일치하는지 여부를 확인
        if(!toVerify.getIssuerDN().equals(signingCert.getIssuerDN()))
            return false;
        try {
            //호스트 인증서가 CA인증서로 서명되어 있는지 확인한다.
            toVerify.verify(signingCert.getPublicKey());
            //호스트 인증서가 유효기간이 만료되었는지 확인한다.
            toVerify.checkValidity();
            return true;
        } catch(GeneralSecurityException verifyFailed) {
            return false;
        }
    }
}
