package com.company.보안기능_2;

import java.io.File;
import java.security.CodeSigner;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/*
    부적절한 전자서명 확인

    전자서명을 검증하지 않거나, 검증절차가 부적절한 경우

    (영향)
    위변조된 파일을 통해 악성코드에 감염될 수 있다.

    (대응)
    - 전자서명을 포함하는 파일을 사용할 때는 항상 전자서명을 확인하여야 한다.
    - 이 경우, 전자서명 파일의 출처 등을 확인하여 신뢰할 수 없는 곳에서
      생성된 파일을 사용하지 않도록 한다.
 */
public class 부적절한전자서명확인 {

    public static void main(String[] args) {

        File f = new File(downloadFilePath);
        JarFile jf = new JarFile(f); //서명파일을 검증하지 않거나 검증절차가 부적절하다
        //->
        //////////////////////////////////////////
        JarFile jf = new JarFile(f, true); // boolean verify
        Enumeration<JarEntry> ens = jf.entries();
        while(ens.hasMoreElements()) {
            JarEntry en = ens.nextElement();
            if(!en.isDirectory()) {
                if(en.toString().equals(path)) {
                    byte[] data = readAll(jar.getInputStream(en), en.getSize());
                    CodeSigner[] signers = en.getCodeSigners(); // 전자서명을 사용하고 확인, 신뢰성 확인
                    ....
                }
            }
        }
        //////////////////////////////////////////

        @RequestMapping(value = "/upload", method = RequestMethod.POST)
        public Student upload(@RequestParam("file") MutltiPartFile multipartFile) throws IllegaStateException, IOExcption {
            File f = new File(multipartFile.getOriginalFileName());
            mutipartFile.transferTo(f);
            JarFile jf = new JarFile(f, true);
            ...... //전자서명을 검증하는 코드 없음
            // -->
            //전사서명을 검증한다.
            Enumeration<JarEntry> ens = jf.entries();
            while(ens.hasMoreElements()) {
                JarEntry en = ens.nextElement();
                if(!en.isDirectory()) {
                    if(en.toString().equals(path)) {
                        byte[] data = readAll(jar.getInputStream(en), en.getSize());
                        CodeSigner[] signers = en.getCodeSigners();
                        if(signer != null && signers.length != 0) {
                            .... // 전자서명 확인
                        }
                    }
                }
            }

        }
    }
}
