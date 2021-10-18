package com.company.API오용_7;

/*
 1.7 API요옹 보안약점
 취약한 API 사용
 */
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

public class 취약한API {

    public class U245 extends javax.servlet.http.HttpServlet {
        private Connection cons;
        public void dbConnection(String url, String user, String pw) {
            try {
                conn = DriverManger.getConnection(url, user, pw); //취약점
                // 취약한 API 사용
                // J2EE 애플리케이션 컨테이너에서 제공하는 자원연결관리를 사용하지 않고 직접제작하는 경우
                // 에러를 유발할수 있기 때문에 J2EE 표준에서 금지하고 있다
                // -> J2EE 애플리케이션이 컨테이너에서 제공하는 연결관리 기능을 사용한다.
                private static final String CONNECT_STRING = "jdbc:orl:orcl";
                InitialContext ctx = new InitialCcontext();
                DataSource datasource = (DataSource)ctx.lookup(CONNECT_STRINTG);
                conn = datasource.getConnection();
            } catch(SQLException e) {
                ....
            }
        }
    }

    public classs S245 extends javax.servlet.http.HttpServlet {
        private Socket socket;
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
            try {
                // 취약한 API 사용
                // J2EE 애플리케이션이 프레임워크 메소드 호출을 사용하지 않고, 소켓을 직접 사용하는 경우
                // 채널보안, 에러처리, 세션 관리 등 다양한 고려사항이 필요하다.
                socket = new Socket("kisa.or.kr", 8080); //취약점
                // 소켓을 직접 사용하는 대신 프레임워크에서 제공하는 메소드 호출을 사용한다.
                // ->
                ObjectOutputStream oos = null;
                ObjectInputStream ois = null;
                try {
                    URL url = new URL("http://127.0.0.1:8080/DataServlet");
                    URLConnection urlCon = url.openConnection();
                    urlConn.setDoOutput(true);
                }
            } catch(UnknownHostException e) {
                ..
            }
        }
    }

    public class U382 extends HttpServlet {
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOExceptio {
            try {
                do_something(logger);
            } catch(IOExcepion ase) {
                System.exit(1);
                // 취약한 API 사용
                // J2EE 응용프로그램에서 System.exit()의 사용은 컨테이너까지 종료시킨다
                // -> J2EE 프로그램에서 System.exit()를 사용하지 않는다.
                // System.exit(1);
            }
        }
    }
}
