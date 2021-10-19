package com.company.입력데이터검증및표현_1;

/*
    신뢰되지 않는 URL 주소로 자동 접속 연결

    (원인) 사용자로부터 입력받은 값을 검증하지 않고 외부 사이트로 자동 연결(redirect)할 사이트의 주소로 사용하는 경우

    (영향) 공격자는 외부 입력값을 조작하여 사용자를 피싱(phising), 크로스사이트스크립트(XSS)와 같은 공격에 노출시킬 수 있다

    (대응)
    - 자동 연결에 사용할 외부 사이트의 URL과 도메인을 화이트리스트(whitelist)로 관리한다.
    - 사용자 입력값을 자동 연결할 사이트의 주소로 사용하는 경우 화이트 리스트 내의 값인지 확인하고 사용한다.
 */
public class 신뢰되지않은URL주로자동접속연결 {

    //안전하지 않은 코드
    String id = utils.nvl((String)session.getValue("id"));
    String bn = utils.nvl(request.getParameter("gubun"));
    String rd = utils.nvl(request.getParameter("redirect"));

    //->
    // 안전한 코드
    // 사용자의 입력값을 화이트 리스트로 관리
    // 사용자 입력값을 자동 연결할 사이트의 주소로 사용하지 않고 화이트 리스트 내의 값인지 확인하고 사용한다.
    String allowedUrl[] = { "/main.do", "/login.jsp", "list.do"};
    String id = utils.nvl(request.getParameter("redirect"));
    try {
        rd = allwoedUrl[Integer.parseInt(id)];
    } catch(NumberFormatException e) {
        return "잘못된 접근입니다";
    } catch(ArrayIndexOutOfBoundsException e) {
        return "잘못된 입력입니다";
    }

    if(id.length() > 0) {
        String sql = "select level from customer where customer_id = ?";
        conn = db.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        rs = pstmt.executeQuery();
        rs.next();
        if("0".equals(rs.getString(1)) && "01AD".equals(bn)) {
            response.sendRedirect(rd);
            return;
        }
    } else {
        if("01AD".equals(bn)) {
            response.sendDirect(rd); //사용자로부터 입력받은 값을 검증하지 않고 자동 연결할 사이트의 주소 사용
                                    // 피싱사이트나 크로스사이트스크립트 공경에 노출될수 있음
            return;
        }
    }
}
