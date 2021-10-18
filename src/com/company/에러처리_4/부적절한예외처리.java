package com.company.에러처리_4;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    여러 개의 명령문에 대해 하나의 try 블록을 설정하고, 모든 예외에 대해 하나의 방식으로 예외를 처리하는 경우

    (영향)
    - 각각의 상황에 대해 적절한 대응을 할 수 없어, 부적절한 리소스 관리로 시스템이 중지될 수 있다.
    - 예외 상황에 대한 정확한 정보가 로깅되지 않아 사후 처리에 어려움이 발생할 수 있다

    (대응)
    각각의 예외 상황에 대해 적절한 예외 처리를 수행할 수 있도록 코드를 작성한다.

 */
public class 부적절한예외처리 {

    public class ErrorHandling {
        public static void main(String[] args) {
            BufferedReader reader = null;
            try {
                URL url = new URL("http://example.com/");
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = reader.readLine();
                SimpleDateFormat format = new SimpleDataFormat("MM/DD/YY");
                Date date = format.parse(line);
            } catch(Exception e) {
                System.err.println("Exception :" + e.getMessage()); // 모든 예외의 대하 하나의 방식으로 예외를 처리
                // 각각의 상황에 대해 적절한 대응을 할 수 없어, 부적절한 리소스 관리로 시스템이 중지 될 수 있다.
                // 예외 상황에 대한 정확한 정보가 로깅되지 않아 사후 처리에 어려움이 발핼 할 수 있다.
            } finally {
                if (reader != null) {
                    try { reader.close(); } catch(IOException ex) { ... }
                }
            }

            // -->
            try {

            } catch(MalformedURLException e) {
                System.err.println("MalformedURLException:" + e.getMesssage());
            } catch(IOException e) {
                System.err.println("IOException:" + e.getMessage());
            } catch(ParseException e) {
                System.err.println("ParseEveption:" + e.getMessage());
            } finally {
                if (reader != null) {
                    try { reader.cloase() } catch(IOException e) { .... }
                }
            }


            // 안전하지 않은 코드
            try {
                File file = new File(data);
                FileWriter out = new FileWriter(file);
                out.write("write test");
                out.close();
            } catch(Exception e) {
                logger.error("파일처리오류가 발생함");
            }

            //-->
            try {
                out = new FileWriter(file);
            } catch(IOException e) {
                logger.error("ERROR-01:파일열기오류");
            }
            try {
                out.write("write test");
            } catch(IOException e) {
                logger.error("ERROR-02:파일쓰기오류");
            } finally {
                try {
                    out.close();
                } catch(IOException e) {
                    logger.error("ERROR-03:파일닫기 오류");
                }
            }
        }
    }
}
