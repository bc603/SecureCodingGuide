package com.company.입력데이터검증및표현_1;

import java.io.*;

/*
    경로 조작 및 자원 삽입

    (원인) 검증되지 않은 외부 입력값을 파일 및 서버의 시스템 자원에 접근하거나 식별에 이용하는 경우 발생

    (영향)
    - 공격자는 입력값을 조작하여 시스템이 보호하는 자원에 임의로 접근할 수 있다.
    - 공격자는 허용되지 않는 권한을 획득하거나 인증이나 인가를 우회하여 설정에 관계된 파일을 열람, 변경, 실행할 수 있다.

    (대응)
    - 외부 입력값을 지원의 식별자로 사용하는 경우, 철저히 검증을 수행한 후 사용한다.
    - 사용자별 사용 가능한 자원을 사전에 리스트로 정의하여 사용범위를 제한한다.
    - 파일을 사용하는 경우, 파일명에 경로순회공격 위험이 있는 문자( .. / \ " )를 제거하는 필터를 이용한다.
 */
public class 경로조작및자원삽입 {

    <%@page language="java" pageEncoding="UTF-8"%>
    <%@page import="java.io.*"%>
    <%
        String fileName = request.getParameter("P");
        if(fileNaem == null "".equals(fileName)) fileName = "dummy.txt";
        BufferedInputStream bis = null;
        BufferedOutputSTream bos = null;
        FileInputStream fis = null;
        try {
            fileName = fileName.replaceAll("\n", "").replaceAll("\r", "");
            // ->
            // 안전한 코드
            //////////////////////////////////////
            // 파일을 사용하는 경우, 파일명에 경로순회공격 위험이 있는 문자(.. / \ ")를 제거하는 필터를 이용한다.
            fileName = fileName.replaceAll("\\", "").replaceAll("/", "").replaceAll("\\\\", "");
            fis = new FileInputStream("C:/datas/" + fileName);
            //////////////////////////////////////
            response.setHeader("Content-Description", "attachment:filename="+fileName+";");
            ...
            byte[] buffer = new byte[1024];
            fis = new FileInputStream("C:/datas/" + fileName);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream((response.getOuputStream()));
            int read;
            while(read == bis.read(buffer, 0, 1024) != -1) {
                bos.write(buffer, 0, read);
            }
        } catch(Exception e)
        } finally { ... }
    }


    // 안전하지 않은 코드
    public class ShowHelp {
        private final static String safeDir = "c:\\help_files\\";

        public static void main(String[] args) throws IOException {
            if(args.length == 0) {
                System.err.print("도움말을 입력하세요");
                return;
            }

            String helpFile = args[0];
            // 사용자로부터의 외부 입력값을 내부 자원에 접근하는 것에 이용
            // 경로순회공격 위험이 있는 문자를 필터링 하지 않음
            try(BufferedReader br = new BufferedReader(new FileReader(safeDir + helpFile))) {
                String line;
                while((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }


    }
}
