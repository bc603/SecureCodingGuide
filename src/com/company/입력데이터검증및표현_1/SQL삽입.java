package com.company.입력데이터검증및표현_1;

import javax.management.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
    SQL삽입

    (원인) 데이터베이스(DB)와 연동하는 웹 애플리케이션에서 입력된 데이터에 대한 유효성 검증을
          하지 않고 입력값을 DB쿼리 일부로 사용하는 경우

    (영향)
    - 인증을 우회해서 시스템에 로그인할 수 있다.
    - 조작된 쿼리를 이용해 권한 없는 사용자가 DB 데이터를 유출하거나 수정, 삭제할 수 있다.
    - 저장 프로시저(stored procedure)를 호출하여 원격으로 식스템 명령어를 수행할 수 있다.

    (대응)
    - 정적 쿼리를 사용하여 쿼리문의 구조가 바뀌지 않도록 한다.
    - 동적 쿼리를 사용해야 하는 경우, 입력값에 쿼리문의 구조를 변경할 수 있는 문자열을 검사한 후 쿼리문 생성에 사용한다.
    - 웹 애플리케이션에서 사용하는 데이터베이스 사용자 권한을 최소화 한다
    - Internal Error(500) 오류 정보가 노출되지 않도록 한다.
 */
public class SQL삽입 {

    public static void main(String[] args) {
        //안전하지 않은 코드
        String gubun = request.getParameter("gubun");
        ..
        String sql = "select b_gubun "
                + " a.idx "
                + ", a.b_id "
                + ", date_format(a.w_date, '%y-%m-%d')"
                + ", a.pwd "
                + ", a.content "
                + ", b.idx "
                + ", a.security "
                + " from board a left outer join tall b on a.idx = b.b_idx "
                + " where b_gubun = '" + gubun + "'";
        Connection conn = db.getCon();
        Statement stmt = con.createStatement(sql);
        ResultSet rs = stmt.executeQuery(sql);

        //->
        // 정적 쿼리를 사용하여 쿼리문의 구조가 바뀌지 않도록 한다.
        String gubun = request.getParameter("gubun");
        String sql = "select b_gubun "
                + ", a.idx, "
                + ", a.b_id, "
                + ", date_format(a.w_date, '%Y-%m-%d')"
                + ", a.pwd "
                + ", a.content "
                + ", b.idx "
                + ", a.security "
                + " from board a left join outer join tall b on a.idx = b.b_id "
                + " where gubun = ?";
        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, gubun);
        ResultSet rs = pstmt.executeQuery();

        ////////////////////////////
        // MyBatis를 사용하는 경우
        where a.confirm_seq = ${confirmSeq}
        // -->
        // 정적 쿼리를 사용하여 쿼리문의 구조가 바뀌지 않도록 한다
        where a.confirm_seq = #{confirmSeq}

        // MyBatis를 사용하는 경우
        select * from tbl_board where title like '%${keyword}%' order by pos asc
        // -->
        // 정적 쿼리를 사용하여 쿼리문의 구조가 바뀌지 않도록 한다
        select * from tbl_board where title like '||#{keyword}||' order by pos asc

        ////////////////////////////
        // Hibernate를 사용하는 경우
        String name = request.getParameter("name");
        Query query = session.createQuery("from Student where studentName = '" + name + "'");
        // ->
        // 정적 쿼리를 사용하여 쿼리문의 구조가 바뀌지 않도록 한다
        String name = request.getParameter("name");
        Query query = session.createQuery("from Student where studentName = ?");
        query.setString(0, name); // #아님

        // Hibernate를 사용하는 경우
        String name = request.getParameter("name");
        Query query = session.createQuery("from Student whree studentName = :name");
        query.setParameter("name", name); //:name, setParameter() 사용

        // Hibernate를 사용하는 경우
        String name = reuqest.getParameter("name");
        Query query = session.createQuery("from Student where studentName = :name");
        query.setString("name", name); // :name, setString() 사용
    }
}
