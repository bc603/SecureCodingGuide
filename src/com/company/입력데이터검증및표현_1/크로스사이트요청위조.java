package com.company.입력데이터검증및표현_1;

import java.util.UUID;

/*
    크로스사이트 요청 위조

    (원인) 웹 서버로 전달된 요청이 공격자가 심어 놓은 자동화된 코드를 통한 요청인지,
          실제 페이지에서 사용자가 일으킨 정상적인 요청인지를 확인하지 않고 처리하는 경우

    (영향)
    - 미리 작성해 둔 공격코드를 이용하여 희생자의 권한으로 요청이 처리되도록 한다.
    - 게시판 자동 글쓰기, 자동 회원 가입, 다른 사용자의 권한을 도용한 중요 기능 실행 등의 공격이 수행될 수 있다

    (내용)
    - 입력화면 폼 작성시 GET 방식 대신 POST방식을 사용한다.
    - 입력화면 폼과 해당 입력을 처리하는 프로그램 사이에 CSRF 토큰을 사용하여, 공격자가 직접적인 URL 조작을 통해서
      시스템의 기능을 사용할 수 없도록 한다.
    - 중요 기능에 대해서는 추가적인 인증이나 다중매체 인증방식을 적용한다.
 */
public class 크로스사이트요청위조 {

    public static void main(String[] args) {
        // 안전하지 않은 코드
        // 어떤 형태의 요청이든지 기본적으로 CSRF 취약점을 가질수 있다.

        //->
        // 안전한 코드
        // 입력화면이 요청되었을때, 임이의 토큰을 생성한 후 세션에 저장한다.
        session.setAttribute("SESSION_CSRF_TOKEN", UUID.randomUUID().toString());

        // 입력화면에 임의의 토큰을 HIDDEN 핉드항목의 값으로 설정해 서버로 전달되도록 한다.
        <input type="hidden" name="param_csrf_token" value="${SESSION_CSRF_TOKEN}" />

        // 요청 파라미터와 세션에 저장된 토큰을 비교해서 일치하는 경우에만 요청을 처리한다.
        String pToken = request.getParameter("param_csrf_token");
        String sToken = (String) session.agetAttribute("SESSION_CSRF_TOKEN");
        if(pToken != null && pToken.equals(sToken)) {
            //일치하는 토큰이 존재하는 경우 -> 정상처리
        } else {
            //토큰이 없거나 값이 일치하지 않는 경우 -> 오류 메세지 출력
        }
    }
}