package com.company.에러처리_4;

/**
    에러를 처리하지 않거나, 불충분하게 처리하여 에러정보에 중요정보(시스템 등)가 포함될 때 발생할 수 있는 보안약점
 */

/*
    오류메세지 정보노출
    오류메세지나 스택 정보에서 시스템 내부구조가 포함되어 민감한 정보, 디버깅 정보가 노출 되는 경우

    (영향)
    - 공격자는 에러 메세지를 통해 프로그램 실행 중 상세한 오류 정보를 얻을 수 있으며, 이 정보는 공격에 활용될 수 있다.
    - 오류 정보를 통해 시스템, 관리자, DB정보 등 중요정보가 노출되어, 공격자가 해당 정보를 추가적으로 공격에 활용할 수 있다.

    (대응)
    - 에러 메세지는 에러 상황에 대해 간단한 에러 상태나, 에러 상태 문자열을 정의하여 지정된 로그 파일에 로깅한다.
    - 예외 발생시 오류와 관련된 시스템 정보가 유출되지 않도록 getMessage()사용을 금한다
    - 시스템 설정을 통해 오류가 발생하는 경우 디폴트 오류 페이지가 사용자에게 전달될 수 있도록 환경설정을 한다
 */
public class 오류메세지정보노출 {

    public static void main(String[] args) {

        try {
            rd = new BufferReader(new FileReader(new File(filename)));
        } catch(IOExcpeion e) {
            e.printStackTrace(); // 상세한 오류 정보를 얻을 수 있으며, 공격에 활용 가능
        }

        // ->
        try {
            rd = new BufferedReader(new FileReader(new File(fileName)));
        } catch(Exception e) {
            logger.error("ERROR-01: 파일 열기 에러"); //간단한 에러상태나, 문자열을 정의하여 지정된 로그파일에 로깅
        } finally {
            try {
                rd.close();
            } catch(IOException e) {
                logger.error("ERROR-02:파일 닫기 에러"); //getMessage() 사용을 하지 않는다.
            }
        }
    }
}
