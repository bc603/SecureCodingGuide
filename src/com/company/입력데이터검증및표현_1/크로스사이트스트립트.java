package com.company.입력데이터검증및표현_1;

/*
    크로스사이트 스트크립트

    (원인) 외부에서 입력된 값이나, DB에 저장되어 있는 값을 사용하여
          동적인 웹페이지를 생성하는 웹 애플리케이션에서 입력값에 대한 충분한 검증없이 입력값을 사용하는 경우

    (영향)
    - 클라이언트(브라우저)에서 악성 코드가 실행되어 사용자 PC를 좀비화할 수 있다.
    - 사용자를 피싱 사이트로 접속하게 만들어 사용자의 중요 정보를 탈취할 수 있다.
    - 사용자의 쿠키 정보 노출로 세션 하이재킹과 같은 공격이 실행될 수 있다.

    (대응)
    - 입력값에 대해 정규식을 이용하여 정확하게 허용되는 패턴의 데이타만 입력되도록 한다.
    - 서버로 들어오는 모든 요청에 대해 XSS필터를 적용하여 안전한 값만 전달되어 사용되도록 한다.
    - 출력값에 대해 HTML 인코딩을 적용하여 스크립트가 동작되지 않도록 한다.
    - 출력값에 대해 XSS Filter를 적용하여 안전하지 않은 입력값(< > ' " &)에 대해 HTML인코딩을 적용하여 출력한다.
 */
public class 크로스사이트스트립트 {

    //안전하지 않은 코드
    <% String customerID = reuqest.getParameter("id"); %>
    요청한 사용자: <%=customerID%>
    처리결과 : ${m_content}

    //->
    // 방법1. 서블릿에서 출력값에 HTML 인코딩 적용
    String clearData = input.replaceAll("<", "&lt").replaceAll(">", "&gt"); //replaceAll()로 인코딩
    out.println(cleanData);

    // 방법2. JSP에서 출력값에 JSTL HTML 인코딩 함수 적용
    <textarea name="content">${fn.escapeXml(model.content)}) //escapeXML()을 통해 인코딩

    // 방법3. JSP에서 출력값에 JSTL core 출력 포맷을 상요하여 텍스트로 처리
    <textarea name="content"><c:out value="${model.content}"></textarea> // c:out을 사용하여 텍스트로 처리

    // 방법4. 잘 만들어진 외부 XSSFilter 라이브러리를 활용하여 출력값에 대해 필터링을 적용
    XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
    out.append(filter.doFilter(data)); //XssFilter 라이브러리를 활용
}
