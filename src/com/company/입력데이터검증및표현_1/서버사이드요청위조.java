package com.company.입력데이터검증및표현_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/*
    서버사이드 요청 위조

    (원인)
    - 적절한 검증절차를 거치지 않은 사용자 입력값을 서버간의 요청에 사용
    - 외부에 노출된 웹 서버에 취약한 애플리케이션이 존재하는 경우 공격자는 URL 또는 요청문을 위조하여
      접근통제를 우회하는 방식으로 비정상적인 동작을 유도할 수 있다

    (영향)
    외부에서 내부의 신뢰된 네트워크에 있는 데이터를 획득

    (대응)
    - 식별할 수 있는 범위 내에서 사용자의 입력 값을 다른 시스템의 서비스 호출에 사용하는 경우,
      사용자의 입력 값을 화이트리스트 방식으로 필터링한다.
    - 사용자가 지정하는 무작위의 URL을 받아들여야 한다면 내부의 URL을 블랙리스트로 지정하여 필터링 한다.
    - 또한 동일한 내부 네트워크에 있더라도 기기 인증, 접근권한을 확인하여 요청이 이루어질 수 있도록 한다.
 */
public class 서버사이드요청위조 {

    // 안전하지 않은 코드
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        URL url = new URL(req.getParameter("url")); //사용자 입력값으로 URL을 받음
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    }

    // -> 안전한 코드
    public class Connect {
        //key, value 형식으로 URL의 리스트를 작성
        /////////////////////
        // 식별할수 있는 범위 내에서 사용자의 입력 값을 받아 다른 시스템의 서비스 호출에 사용하는 경우
        // 사용자의 입력 값을 화이트리스트 방식으로 필터링 한다.
        /////////////////////
        private Map<String, URL> urlMap;

        protected void goGet(HttpServletRequest req, HttpServletResponse res) throws IOExceptio {
            //사용자에게 urlMap의 key를 입력받아, urlMap에서 URL값을 참조
            URL url = urlMap.get(request.getParameter("url"));
            //urlMap에서 참조한 값으로 Connection을 만들어 접속
            HttpURLConnection conn = (HttUrlConnection) url.openConnection();
        }
    }


    // 안전하지 않은 코드
    public class ConnectProperties {
        FileReader newFile = new FileReader("File.properties");
        Properties properties = new Properties();
        properties.load(newFile);

        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
            URL url = new URL(properties.getProperties("connectUrl"));
            HttpUrlConnection conn = (HttpURLConnection)url.openConnection();
        }

        //->
        // 입력되는 프로퍼티 값을 검증하는 메소드 추가
        // 프로퍼티의 값을 화이트 리스트와 대조하여 필터링한다.
    }


    // 안전하지 않은 코드
    private String getRemoteContent(String id) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        return FileCopyUtils.copytoString(in);
    }

    public String getConnection(String inUrl) throws Exception {
        try {
            String str = getRemoteContent(inUrl);
            // 적절한 검증절차를 거치지 않은 입력값을 서버간의 요청에 사용
            // ->
            // url을 검증하는 메소드 추가 필요
            str = str.replace("<head>", "<head><base href='" + inUrl + "' />" +
                    "<base target='_blank'/><script>top.studio.startPageFragmentLoaded();</script>");
            return str;
        } catch(Exception e) {
            return "";
        }
    }


    // 안전하지 않은 코드
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // 적절한 검즐 절차를 거치지 않은 사용자 입력값을 서버간의 요청에 사용
        String host = request.getParameter("host");
        // ->
        // 입력되는 host값을 검증하는 메소드 추가.

        byte[] bytes[] = getImage(host, defaultBytes);
        if(bytes != null) {
            writeBytesToStream(bytes, response);
        }
    }

    private byte[] getImage(String host, byte[] defaultImage) {
        byte[] bytes = getImage("http://" + host + "/favicon.ico");
    }
    // /getFavicon?host=192.168.176.1.:8080/secrets.txt?


    // 안전하지 않은 코드
    <?php
        require_once("./htmlpurifier/library/HTMLPurifier.includes.php");
        $purifier = new HTMLPurifier();

        $ch = curl_init();
        $url = $_GET['url'];
        $urlInfo = parse_url($url); //URL생성
        $scheme = $urlInfo['scheme'];
        $host = $urlInfo['host'];
        $ip = gethostbyname($host);

        curl_setop($ch, CURLOPT_URL, $url);
        curl_setop($ch, CURLOPT_FOLLOWLOCASTIOn, false);
        curl_setop($ch, CURLOPT_RETURNTRANSFER, true);

        $html = curl.exec($ch);
        echo $purifier->purify($html)

    //->
    // 안전한 코드
    $url = $_GET['url'];
    ...
    if($ip === "169.254.169.254") { //퍼블릭클라우드 메타데이터 서비스 IP를 검증
        die("Invalied Host");
    } else if($sheme !== 'http' && $schema !== 'https' ) { // HTTP, HTTPS 스킴체크
        die("Invalied scheme");
    }
    ....
}
