package com.company.보안기능_2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    취약한 비밀번호 허용

    비밀번호 조합규칙(영문, 숫자, 특수문자 등)이 미흡하거나 길이가 충분하지 않은 경우

    (영향)
    - 패스워드 무작위 대입으로 계정 정보가 노출될 수 있다.
    - 패스워드 추측 공격으로 계정 정보가 노출될 수 있다.

    (대응)
    - 비밀번호 설정 시 문자 구성 및 길이 조건을 설정한다.
        * 3가지 종류의 문자 구성시 8자리 이상 비밀번호 설정
        * 2가지 조류의 문자 구성시 10자리 이상 비밀번호 설정
    - 특정 정보 이용 및 패턴조건 제약
        * 한글, 영어 등의 사전적 단어 포함 금지
        * 널리 알려진 단어 사용금지
        * 사용자 ID와 연관 있는 단어 사용 금지
        * 3자가 쉽게 알수 있는 개인정보 포함 금지
        * 패턴이 존재하는 비밀번호 사용 금지
        * 숫자와 영문자를 비슷한 문자로 치환한 형태를 포함한 구성의 비멀번호 사용 금지
     - 기타
        * 이전 비밀번호와 연관성이 있는 단어 사용 금지
     - 한국인터넷진흥원의 '암호정책수립기준 설명서' 참조
 */
public class 취약한비밀번호허용 {

    public static void main(String[] args) {
        String id = request.getParameter("id");
        String pass = request.getParameter("pass"); // 취약하지 않은 비밀번호로 구성이 되었는지 확인 하지 않음
        // ->
        // 문자구성, 길이 조건을 검사, 특정 정보, 패턴조건 검사,
        Pattern pattern = Pattern.compile("((?=.*[a-zA-Z])(?=.*[0-9]).{10.})");
        Matcher matcher = pattern.matcher(pass);
        if(!matcher.matches()) {
            return "비밀번호 조합규칙 오류";
        }
        /////////////////////////////////////
        UserVO userVO = new UserVO(id, pass);
        ..
        String result = registDAO.regist(userVO);
    }
}

