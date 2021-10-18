package com.company.보안기능_2;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

/*
    무결성 검사 없는 코드 다운로드

    (원인) 원격으로부터 소스 코드나 실행파일을 다운로드하여 사용할 때, 다운로드 받은 리소스에 무결성 검사를 수행하지 않고 사용하는 경우

    (영향)
    악성코드가 삽입된 소스코드를 실행하거나, 변조된 데이터가 사용되어 의도하지 않은결과를 유발할 수 있다.

    (내용)
    - 원격으로부터 다운로드 받은 파일에 대해서는 무결성 검증을 위한 해쉬값을 첨부하거나, 안전한 암호화 기법을 이용하여 암호화하여 전송한다.
    - 다운로드한 파일에 대해 무결정 검증을 수행한 뒤 취소 권한으로 실행한다.
 */
public class 무결성검사없는코드다운로드 {

    public static void main(String[] args) {

        //안전하지 않은 코드
        //URLClassLoader를 사용하여 원격의 파일을 다운로드 한다.
        //다운로드 대상 파일에 대한 무결성 검사를 수행하지 않을 경우,
        //파일변조 등으로 피해가 발생할수 있다
        URL[] classURLs = new URL[]{ new URL("file:subdir/")};
        URLClassLoader loader = new URLClassLoader(classURLs);
        Class loadedClass = Class.forName("LoadMe", ture, loader);

        //-> 안전한 암호화 기법을 이용하여 암호화 하여 전송한다
        //////////////////////////////////
        //서버의 개인키로 utils.jar 파일을 암호화
        //////////////////////////////////
        String jarFile = "./downalod/util.jar";
        Byte[] loadFile = FileManager.getBytes(jarFile);
        loadFile = encrypt(loadFile, privateKey);
        //////////////////////////////////
        //jarFileName으로 암호화된 파일을 생성한다.
        //////////////////////////////////
        FileManager.createFile(loadFile, jarFileName);

        //////////////////////////////////
        //서버의 공개키로 다운로드 받은 utils.jar 파일을 복호화
        //////////////////////////////////
        URL[] classURLs = new URL[]{new URL("http://filesave.com/dowload/utils.jar")};
        URLConnection conn = classURLs.openConnection();
        InputStream is = conn.getInputStream();
        //////////////////////////////////
        // 입력스트림을 읽어 서버의 jarFile명으로 파일을 출력한다.
        //////////////////////////////////
        FileOutputStream fos = new FileOutputStream(new File(jarFile));
        while(is.read(buf) != -1) {...}
        byte[] loadFile = FileManager.getBytes(jarFile);
        loadFile = decrypt(loadFile, jarFile);
        //////////////////////////////////
        // 복호화된 파일을 생성한다
        //////////////////////////////////
        FileManager.createFile(loadFile, jarFile);
        URLClassLoader loader = new URLClassLoader(classURLs);
        Class loadedClass = Class.forName("MyClass", ture, loader);


    }
}
