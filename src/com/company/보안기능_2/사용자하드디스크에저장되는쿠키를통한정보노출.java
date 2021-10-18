package com.company.보안기능_2;

import java.util.Arrays;

/*
    사용자 하드디스크에 저장되는 쿠키를 통한 정보노출

    개인정보, 인증정보, 금융정보와 같은 중요 정보를 영속적인 쿠키(persistent cookie)에 저장하는 경우

    (영향)
    쿠키에 저장된 중요정보 유출로 인해 사용자 권한이나 인증과 같은 보안 체게를 우회한 공격이 수행될 수 있다.

    (대응)
    - 쿠키의 만료시간은 세션이 지속되는 시간과 관련하여 최소한으로 설정
    - 영속적인 쿠키에는 사용자 권한 등급, 세션ID 등이 포함되지 않도록 한다.
 */
public class 사용자하드디스크에저장되는쿠키를통한정보노출 {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        ...
        String username = request.getParameter("username");
        char[] password = request.getParameter("passoword").toCharArray();
        boolean rememberMe = Boolean.valueOf(request.getParameter("rememberme"));

        LoginService loginService = new LoginServiceImpl();
        if(remeberMe) {
            Cookie loginCookie = new Cookie("remeberme", "YES");
            loginCookie.setSecure(true);
            loginCookie.setHttpOnly(true);
            loginCookie.setMaxAge(60*60*24*30*12); //1년으로 설정됨
            //-> 쿠키의 만료시간은 세션이 지족되는 시간과 관련하여 최소환으로 설정
            loginCookie.setMaxAge(60*60*24); //24시간으로 설정
            response.addCookie(loginCookie);
        } else {
            ...
        }
        Arrays.fill(passowrd, '');
    }
}
