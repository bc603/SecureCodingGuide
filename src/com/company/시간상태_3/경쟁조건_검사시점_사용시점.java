package com.company.시간상태_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 시간 및 상태
 * 동시 또는 거의 동시 수행을 지원하는 병렬 시스템, 하나 이상의 프로세스가 동작되는 환경에서 시간 및 상태를 부적절하게 관리하여
 * 발생할 수 있는 보안약점
 */

/*
    병렬 실행 환경의 응용 프로그램에서 자원을 검사하는 시점과 자원을 사용하는 시점의 상태가 달라 오동작을 일으키는 경우

    (영향)
    프로그램이 교착 상태에 빠지거나 경쟁(공유) 자원의 변조나 삭제 및 기타 동기화 오류 등이 발생할 수 있다

    (대응)
    - 경쟁(공유) 자원(예:파일)을 여러 스레드가 접근하여 사용할 경우, 동기화 구문(synchronized)을 이용하여
      한번에 하나의 스레드만 접근할 수 있도록 프로그램을 작성한다
 */
public class 경쟁조건_검사시점_사용시점 {
    class FileMgmtThread extends Thread {
        // -> 동기화 구문 synchronized를 이요하여 한번에 하나의 스레드만 접근할 수 있도록 프르그램을 작성한다.
        private static final String SYNC = "SYNC";
        private String manageType = "";
        public FileMgmtThread(String type) {
            manageType = type;
        }

        public void run() {
            // -> 동기화 구문 synchronized를 이용하여 한번에 하나의 스레드만 접근할 수 있도록 프로그램을 작성한다
            synchronized (SYNC) { // 동기화 구문 추가
                try {
                    if (manageType.equals("READ")) {
                        File f = new File("Test_367.txt");
                        if (f.exists()) {
                            BufferedReader br = new BufferedReader(new FileReader(f));
                            br.close();
                        } else if (manageType.equals("DELETE")) {
                            File f = new File("Test_367.txt");
                            if (f.exists()) {
                                f.delete();
                            } else { ...}
                        }
                    }
                } catch (IOException e) { ...}
            }
        }
    }

    public class CWE367 {
        public static void main(String[] args) {
            FileMgmtThread fileAccess = new FileMgmtThread("READ");
            FileMgmtThread fileDelete = new FileMgmtThread("DELETE");
            fileAccess.start(); // 경쟁(공유) 자원(파일)을 여러 스레드가 접근하여 프로그램이 교착상태에 빠질수 있음
            fileDelete.start(); //
        }
    }


}
