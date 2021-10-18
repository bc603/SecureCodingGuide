package com.company.코드오류_5;

/*
    부적절한 자웒 해제
    메모리, 파일, DB연결과 같은 자원들은 사용 후 반드시 자원을 해제해야 하는데
    부적절한 흐름처리나 코드누락으로 인해 자원해체가 실행되지 않는 경우

    1. 해제하지 않은 자원(Heap Memory, Socket, DB Connection 등)이 고갈되어 더 이상
       할당할수 없는 상태가 된다.
    2. 자원 해제 코드가 잘못된 루틴에 있어서 시스템에 발생할수 있다.

    -> 유한한 자원은 반드시 반환되도록 제어문이나 예외처리가 되도록 작성한다.
       자바의 경우 반환 코드는 finally 블록에 기술하여 반드시 실행되도록 한다.
 */
public class 부적절한자원해제 {

    public class ListOfNumnbers {
        private List<Integer> list;
        private static final SIZE = 10;;
        public ListOfNumbers() {
            list = new ArrayList<Integer>(SIZE);
            for (int i = 0; i < SIZE; i++) {
                list.add(new Integer(i));
            }

            public void writeList() {
                PrintWriter out = null;
                try {
                    out = new PrintWriter(new FileWriter("OutFile.txt"));
                    for(int i = 0; i < SIZE; i++) {
                        out.println("Value at:" + i + " = " + list.get(i));
                    }
                    if(out != null) { out.close(); }
                }
                catch(IndexOutOfBoundsException e) { ... }
                catch(IOException e) { .. }
            }

            // 자바의 경우 반환코드는 finally 블록에 기술하여 반드시 실행되도록 한다.
            finally {
                if (out != null) { out.close(); }
            }
        }

        public static void main(String[] args) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
                Result rs = stmt.exeucteQuery();
                rs.close();
                pstmt.close();
                conn.close();
            } catch(SQLException e) {
                logger.error("ERROR-01:" + e.getMessage());
            }

            // 유한한 자원은 반드시 반환하도록 제어문이나 예외처리가 되도록 작성한다.
            // Java의 경우 반환코드는 finally블록에 기술하여 반드시 실행되록 한다.
            try {
                PreparedStatement sptmt = conn.prepareStatement(sqlSelect);
                ResultSet rs = pstmt.exeucuteQuery();
            } catch(SQLException e) {
                logger.error("Error-01" + e.getMesasge);
                rs.close();
                pstmt.close();
                conn.close();
            }

            // 안전한 코드
            // -> 유한한 자원은 반드시 반환하도록 제어문이나 예외처리가 되도록 작성한다.
            // java의 경우 반환ㄷ코드는 finally 블록에 기술하여 반드시 실행되도록 한다.
            try {
                PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
                ResultSet rs = pstmt.executeQuery();
            } catch(SQLExceptio e) {
                logger.error("ERROR-01:" + e.getMessage());
            } finally {
                if (rs != null) {
                    try { rs.close(); } catch(SQLExcepeion e) {..}
                }
                if (pstmt != null) {
                    try { pstmt.close(); } catch(SQLException e) { ..}
                }
                if (conn != null) {
                    try { conn.close(); } catch(SQLException e) { ... }
                }
            }
        }
    }
}
