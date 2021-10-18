package com.company.보안기능_2;

import java.io.File;
import java.io.IOException;

/*
    중요한 자원에 대한 잘못된 권한 설정

    소프트웨어가 중요한 보안관련 자원에 대해 읽기 또는 수정 권한을 의도하지 않게 허가하는 경우

    (영향)
    인가되지 않은 사용자가 자원을 사용할 수 있다.

    (대응)
    - 설정 파일, 실행파일, 라이브러리 등은 SW 관리자만 읽고 쓸 수 있도록 설정한다.
    - 허가 받지 않은 사용자가 중요한 자원에 접근 가능한지 검사한다.
 */
public class 중요한자원에대한잘못된권한설정 {

    public static void main(String[] args) {

        try {
            ..
            File file = new File("/home/setup/system.ini");
            file.setExecutable(true, false); // 중요한 보안관련 자원에 대해 읽기 또는 쓰기 허용
            file.setReadable(true, false);
            file.setWritable(ture, false);
            // ->
            file.setExecutable(false);
            file.setReadable(true); // 읽기만 허용
            file.setWritable(false);


            if (file.createNewFile()) {
                System.out.println("File is created");
            } else {
                System.out.println("File already exists");
            }
        } catch(IOException e) {
            ...
        }
    }
}
