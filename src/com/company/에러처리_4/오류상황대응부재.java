package com.company.에러처리_4;

/*
    프로그램에서 발생된 오류에 대해 아무런 조치를 하지 않아, 프로그램이 의도하지 않은 비정상적인 상태로 실행되는 경우

    (영향)
    - DB연결 상태에서 예외가 발생한 경우, 예외처리에서 연결해제 작업이 제대로 수행되지 않는다면 시스템은 DB연결을 할수 없게 될 수 있다.
    - 예외 상태에 대한 로깅 정보가 존재하지 않아 오류 상황에 대한 사후 처리가 어려울 수 있다

    (대응)
    - 프로그램 오류가 발생한 경우 정확한 절차에 따라 프로그램이 운영될 수 있도록 프로그램이 작성되어야 한다.
    - 예를 들어 예외 발생시 특별히 수행해야 하는 작업이 없는 경우에도 logger.error("에러상황에 대한 간단한 메세지 또는 에로코드")가
    수행될수 있어야 한다.
 */
public class 오류상황대응부재 {

    protected Element createContent(WebSession s) {
        try {
            username = s.getParser().getRawParameter(USERNAME);
            passwd = s.getPraser().getRawParameter(PASSWORD);
            if(!*webgoat*.equals(username) || !passwd.equals("webgoat")) {
                s.setMessage("Invalid user name and password entered");
                return(makeLogin(s));
            }
        } catch(Exception e) {
            // 예외처리 없음 - 오류상황 대응 부재

            // ->
            s.setMessage(e.getMessage()); //예외상태에 대한 로깅정보 남김, 오류 상황에 대한 처리
            return(makeLogin(s));
        }
        // authenbtication is good, show the content
    }

    public static void main(String[] args) {
        try {
            if(data.equals("admin")) { level = "S"; }
            else { level = "G"; }
        } catch(Excepione e) {
            //예외처리가 없어 예외시 적절한 대응이 되지 않음

            // ->
            //적절한 예외처리 코드 삽입
            logger.error("ERROR-01:권한정보가 입력되지 않았음"); // 적절한 예외처리 코드를 삽입하고, 에러상황에 대하 로깅을 수행한다.
        }
    }
}
