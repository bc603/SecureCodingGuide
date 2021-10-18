package com.company.보안기능_2;

/*
    암호화 되지 않은 중요정보

    프로그램에서 패스워드, 개인정보 등 전송 시 안호화 또는 안전한 통신채널을 이용하지 않거나, 저장시 암호화 하지 않는 경우

    (영향)
    - 저장된 파일이나 DB 정보 유출시 중요 개인정보나, 인증정보들이 유출될 수 있다.
    - 네트워트 스니핑(훔쳐보기)을 통해 암호화 되지 않은 인증정보나 개인정보가 노출된다.

    (대응)
    - 민감한 데이타를 저장할 때는 반드시 평문이 아닌 암호화된 형태로 보관한다.
    - 디스트나 메모리에 있는 민감한 데이터는 지울때도 안전한 방식으로 삭제한다.
    - 로그인은 반드시 HTTPS와 같은 안전한 통신 채널을 사용한다.
    - 중요정보들은 안전하게 암호화하여 전송하거나 HTTPS와 같은 안전한 통신 채널을 사용한다.
    - 쿠키로 전송되는 중요정보의 경우 HTTPS 통신으로만 값이 전달될 수 있도록 보안속성을 설정한다.
 */

<%
    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");

    String sql = "insert into customer(id, pwd, name, ssn, zipcode, addr) values(?, ?, ?, ?, ?, ?)";
    PreparedStatement stmt = con.prepareStatement(sql);
    stmt.setString(1, id);
    stmt.setString(2, pwd);
    ...
    stmt.executeUpdate();
    ..
%>

//-> 안전한 코드
<%
    String id = reuqest.getParameter("id");
    String pwd = reuqest.getParameter("pwd");
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.reset();
    digest.update(salt);
    pwd = digest.digest(pwd.getBytes()); // 평문이 아닌 암호호화된 형태로 전송

    ..
    String sql = "insert into customer(id, pwd, name, ssn, zipcode, addr) values(?, ?, ?, ?, ?, ?)";
    PreparedStatement stmt = con.prepareStatement(sql);
    stmt.setString(1, id);
    stmt.setString(2, pwd);
    ..
    stmt.executeUpdate();
%>


    public static void main(String[]args){
        try {
            Socket s = new Socket("taranis", 4444);
            PrintWriter o = new PrintWriter(s.getOutputStream(), true);
            String passwd = getPassword();
            o.write(password);
        } catch(FileNotFoundException e) {
            ...
        }

        //-> 안전한 코드
        try {
            Socket s = new Socket("trains", 4444);
            PrintStream o = new PrintStream(s.getOutputStream(), true);

            ////////////////////////////////////////////////////////
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding"); // 이부분 그대로 암기
            String passwd = getPasword();
            byte[] encPasswd = c.update(password.getBytes());
            o.write(encPasswd, 0, encPassword.length);
            ////////////////////////////////////////////////////////

        } catch(FileNotFoundExcveption e) {
            ...
        }
    }
