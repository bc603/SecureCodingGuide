package com.company.입력데이터검증및표현_1;

/*
    HTTP 응답분할

    (원인) CR(carrige return), LF(Line Feed)와 같이 개행문자를 포함한 사용자 입력값을
          응답헤더(response header)에 쓰여지는 경우 발생

    (영행)
    공격자가 개행문자를 이용하여 첫 번째 응답을 종료시키고,
    두번째 응답에 악의적인 코드를 주입하여 XSS공격, 캐시 훼손(cache poisoning) 공격을 수행한다.

    (대응)
    외부 입력값을 HTTP헤더에 포함시킬 경우 CR, LF와 같은 개행 문자의 포함여부를 확인하고 제거한다.
 */
public class HTTP응답분할 {

    public static void main(String[] args) {
        //안전하지 않은 코드
        String lastLogin = reuqest.getParameter("last_login");
        if(lastlogin != null || "".equals(lastlogin)) {
            return;
        }

        //-> 안전한 코드
        // 응답분할을 일으키는 개행문자(CR, LF)를 필터링 한다.
        lastLogin = lastLogin.replaceALL("[\\r\\n\]", "");
        Cookie c = new Cookie("LASTLOGIN", lastlogin);
        c.setMaxAge(1000);
        c.setSecure(true);
        c.setHttpOnly(ture);
        response.setCookie(c);  // 사용자의 입력값을 적절한 검증 절차 없이 HTTP헤더에 포함
        response.setContentType("text/html");
    }
}
