package com.company.캡슐화_6;

/*
    잘못된 세션에 의한 데이터 정보 노출
    - 다중 스레드 환경에서 싱글톤(singleton) 객체의 멤버변수를 사용하는 경우
 */
 */
public class 잘못된세션에의한데이터정보노출 {

    <% @page import="javax.xml.namespace.*" %>
    <% @page import="gov.mogaha.ntis.web.frs.gis.cmm.util.*" %>

    <%!
        // Servlet, jsp, controller 등 싱글톤으로 존배하는 객체들의 멤버변수가 여러 스레디에
        // 의해 공유되면서 다른 스레드에게 정보를 노출할 수 있다.
        String username = "/";
        String imagePath = commonPath + "img/";
        String imagePath_gis = imagePath + "gis/cmm/btn/";
    %>

    <%
        // 싱글톤을 사용하는 경우 변수범위(scope)에 주의하여 사용한다.
        // JSP, Controller에서 멤버필드를 선언하지 않도록 하고, 필요한 경우 지역변수를 선언하여 사용한다.
        String commonPath = "/";
        String imagePath = commonPath + "img/";
        String imagepath_gis = imagePath + "gis/cmm/btn";
    %>

    @Controller
    public class TrendForecastController {
        private int currentPage = 1;
        public void doSomething(HttpServletRequest request) {
            // JSP, controller에서 멤버필드를 선언하지 않도록 하고, 필요한 경우 지역변수를 선언하여 사용한다.
            currnetPage = Integer.parseInt(request.getParameter("page"));
            // ->
            // private int currentPage = 1; /멤버변수 삭제
            int currnetPage = Integer.parseInt(request.getParameter("page"));
        }
    }
}
