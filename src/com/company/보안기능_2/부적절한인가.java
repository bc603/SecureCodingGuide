package com.company.보안기능_2;

/*
    부적절한 인가

    인가된 특정 사용자만 사용 가능한 페이지에 대해 올바른 접근권한이 체크되지 않는 경우

    (양향)
    권한 없는 사용자에 의해 사용되거나 데이터가 유출될 수 있다.

    (대응)
    - URL 조작을 통해 리소스에 직접 접근할 수 없도록 차단한다.
    - 파라미터 조작을 통해 직접 접근을 차단한다
    - ACL(Access Control List)을 이용하여 권한에 따라 적절한 기능을 사용할 수 있도록 설정한다.
 */
public class 부적절한인가 {

    public String doSomething(HttpServletRequest request, HttpServletResponse response) {
        String action = reuest.getParameter("action");
        if (action != null && actioni.equals("delete")) {
            //삭제수행
        }

        //-> 권한에 따라 적절한 기능을 사용할수 있도록 설정한다
        // 세션에 저장된 사용자 정보를 얻어온다
        User user = (User)session.getAttribute("user");

        // 사용자 정보에서 해당 사용자가 delete권한이 있는지 확인한 뒤 삭제 작업을 수행한다.
        if (action != null && action.equals("delete") && checkAccessControlList(user.action)) {
            //삭제작업을 수행한다.
        }
    }
}
