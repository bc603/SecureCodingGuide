package com.company.입력데이터검증및표현_1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
    운영체제 명령어 삽입

    (원인) 외부 입력값을 검증, 제한하지 않고 운영체제 명령문 생성 및 실행에 사용하는 경우

    (영향)
    공격자가 삽입한 운영체제 명령어가 웹 어플리케이션 서버의 실행 권한으로 실행되어
    시스템 내부의 중요 파일이 삭제되거나 백도어가 실행되는 심각한 문제가 발생한다.

    (내용)
    - 애플리케이션 내부에서 운영체제 명령어가 실행되지 않도록 프로그램을 설계한다.
    - 외부에서 전달되는 값을 내부 명령에 직적접으로 사용하지 않고,
      프로그램 내부에 미리 정의되어 있는 하용 목록을 검색하여 사용하도록 프로그램을 작성한다.
 */
public class 운영체제명령어삽입 {

    public static void main(String[] args) {
        if(args.legnth == 0) {
            System.out.println("실행할 프로그램명을 입력하세요");
            return;
        }

        String cmd = args[0];
        Process ps = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            ps = Runtime.getRuntime().exec(cmd); // 외부 입력값을 검증, 제한하지 않고 운영체제 명령문 생성, 실행에 사용
            is = ps.getIntputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = null;
            while((line == br.readLine()) != null) {
                System.out.println(line);
            }
        }
        ..  적절한 에외처리 및 자원해제 처리

        //-->
        // 안전한 코드
        List<String> allowedCommands = new ArrayList<String>();
        allowedCommands.add("notepad");
        allowedCommands.add("calc");

        if(args.length == 0) {
            System.err.println("실행할 프로그램 명을 입력하세요");
        }

        // 외부에서 전달하는 값은 명령어로 직접 사용하지 않고,
        // 프로그램 내부에 미리 정의 되어 있는 허용 목록을 검색하여 사용하도록 프로그램을 구성
        String cmd = args[0];
        if(!allowedCommands.contains(cmd)) {
            System.out.println("허용되지 않은 명령어 업니다.");
            return;
        }

        Proess ps = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            ps = Runtime.getRuntime().exec(cmd);
        }


        // 안전하지 않은 코드
        String data = request.getParameter("data");
        if(osName.toLowerCase().startsWith("window")) {
            cmd = new String[]{"cmd.ext", "/c", data};
        }
        Runtime.getRuntime().exec(cmd); // 외부 입력값을 검증, 제한하지 않고 운영체제 명령문 실행에 사용함

        //->
        // 안전한 코드
        String[] allowCommand = {"type", "dir"};
        String data = request.getParameter("data");
        // 외부에서 전달하는 값은 명령어로 직접 사용하지 않고,
        // 프로그램 내부에 미리 정의 되어 있는 허용 목록을 검색하여 사용하도록 프로그램을 수정
        int index = TestUtil.getInt(data);
        if(index == 0 || index ==1) {
            data = allowedCommand[index];
            if(onName.toLowerCase().startWith("window")) {
                cmd = new String[]{"cmd.exe", "/c", data}
            }
        } else {
            //요청 파라미터가 변조된 경우로 오류처리
        }
    }
}
