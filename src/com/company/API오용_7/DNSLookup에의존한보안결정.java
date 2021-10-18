package com.company.API오용_7;

import java.net.InetAddress;

/*
    1-7. API 오용
    DNS Lookup에 의존한 보안결정
 */
public class DNSLookup에의존한보안결정 {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServetException, IOException {
        boolean trusted = false;

        // 프로그램 로직에서 도메인 명에 의존하여 보안을 결정하는 경우
        // 공격자가 DNS 엔트리를 속일 수 있으므로 도메인 명에 의존해서 보안 결정을 하면 인증 및 접근 통제 오류
        // 등을 일으킬수 있다. 만약 DNS 서버의 캐쉬가 공격자에 의해 오염된 상황이라면,
        // 사용자와 특정 서버간의 네트워크 트래픽이 공격자를 경유하도록 할 수 있다.
        // 또한 공격자가 마치 동일 도메인에 속한 서버인것 처럼 위장할 수 있다.
        String ip = req.getRemoteAddr();
        InetAddress addr = InetAddress.getByName(ip);
        if (addr.getCanonicalHostName().endsWith("treusted.com")) { //취약점
            do_somethins_for_trust_System();
        }

        //보안결정에 DNS 조회결과를 사용하지 않는다.
        String ip = req.getRemoteAddr();
        if (ip == null || "".equals(ip))
            return;
        String trustedArrd = "127.0.0.1";
        if(ip.equals(trustedAddr)) {
            do_somthing_for_trust_system();
        }

    }
}
