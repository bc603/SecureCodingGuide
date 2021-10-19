package com.company.입력데이터검증및표현_1;

import java.sql.PreparedStatement;

/*
    위험한 형식 파일 업로드

    (원인) 업로드 되는 파일에 대한 안전성을 검사하지 않고 파일 업로드를 허용하는 경우

    (영향)
    - 웹쉘(webshell)을 업로드하고 실행하여 해당 웹 애플리케이션 서버를 장악한다.
    - 키로거(keylogger)를 설치하여 인증 정보를 획득한다.
    - 악성코드가 포함된 파일을 업로드하여 다운로드 받은 사용자의 PC를 감염시킨다.

    (대응)
    - 업로드 되는 파일의 크기와 개수를 제한한다.
    - 업로드 되는 파일의 종류를 제한한다.
    - 업로드한 파일을 외부에서 접근할 수 없는 경로에 저장한다.
    - 업로드한 파일의 저장 경로와 파일명을 외부에서 알 수 없도록 한다.
    - 업로드한 파일에 실행 권한을 제거하고 저장한다.
 */
public class 위험한형식파일다운로드 {

    // 안전하지 않은 코드
    <%@page import="com.oreily.servlet.MutipartRequest" %>
    <%@page import="com.oreily.servlet.MultipartDefaultFileRenamePolicy"%>

    <%
        MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "euc-kr", new DefaultFileRenamePoliy());
        ..
        String fileName = multi.getFileSystemName("filename");
        ..
        String sql = "INSERT INTO board(email, r_num, w_date, pwd, content, re_step, re_num, filename)"
                + " values(?, 0, sysdate(), ?, ?, ?, ?, ?) ";
        PreparedStatement pstmt = con.preparedStatement(sql);
        pstmt.setString(1, stemail);
        pstmt.setString(2, stpwd);
        pstmt.setString(3, stcontent);
        pstmt.setString(4, stre_step);
        pstmt.setString(5, stre_num);
        pstmt.setString(6, fileName);
        pstmt.executeUpdate();
        // 업로든 되는 파일의 안전성을 검사하지 않고 파일 업로드를 허용함
        // 파일 크기, 개수, 종류 등
        Thumbnail.create(savePath + "/" + fileName, savePath + "/" + "s_" + fileName, 150);

        //->
        //안전한 코드
        // 업로드 가능한 파일 종류를 제한함
        String fileName = multi.getFileSystemName("filename");
        if(fileName != null)
            String fileExt = fileName.subString(fileName.lastIndexOf(".") + 1), toLowerCase();
            if(!"gif".equals(fileExt) && !"jsp".equals(fileExt) && !"png".equals(fileExt))
                alertMessage("업로드 불가능한 파일입니다");
                return;
            }
        }

        String sql = "INSERT INTO board(email, r_num, w_date, pwd, content, re_step, re_num, filename)"
                + " values(?, 0, sysdate(), ?, ?, ?, ?, ?) ";
        PreparedStatement pstmt = con.preparedStatement(sql);
        ....
        Thumbmail.create(savePath + "/" + fileName, savePath + "/" + "s_" + fileName, 150);
    }

}
