package com.company.입력데이터검증및표현_1;

/*
    보안기능 결정에 사용되는 부적절한 입력값

    (원인) 응용프로그램이 쿠키, 환경변수, 히든필드와 같은 입력값에 대한 신뢰를 전제로 보호 메커니즘을 구현하는 경우

    (영향) 공격자가 입력값을 조작하여 보호 매커니즘을 우회하거나 권한 상승을 시도하여 허가되지 않은 시스템의 기능을 사용할수 있다.

    (대응)
    - 상태 정보, 민감한 데이터, 사용자 세션 정보와 같으 중요한 정보는 서버에 저장하고 보안 절차도 서버에서 실행한다.
    - 사용자 권한, 인증 여부 등 보안 결정에 사용하는 값은 사용자 입력값을 사용하지 않고 내부 세션 값을 활용한다.
 */
public class 보안기능결정에사용되는부적절한입력값 {

    //위험한 코드
    // 응용프로그램이 쿠키, 환경변수, 히든필드와 같은 입력값에 대한 신뢰를 전제로 보호 매케니즘을 구현
    <input type="hidden" name="price" value="10000"/>
    <br/>품목 : HDTV
    <br/>수량 : <input type="hidden" name="quantity"/>개
    <br/><input type="submit" value="구입" />
    ...
    try {
        price = request.getParameter("price");
        // ->
        // 안전한 코드
        // 상태 정보, 민감한 데이터, 사용자 세션 정보와 같은 중요한 정보를 서버에 저장하고
        // 보안절차도 서버에서 실행한다
        price = CONST_HDTV_UNIT_PRICE;
        quantity = request.getParameter("quantity");
        total = quantity * Float.parseFloat(price);
    } catch(Exception e) {
        .....
    }
}
