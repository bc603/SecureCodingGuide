package com.company.입력데이터검증및표현_1;

/*
    정수형 오버플로

    (원인)
    - 사용자 입력값을 정수형으로 사용할 때 입력값의 크기를 검증하지 않을 경우 발생
    - 정수값이 증가하면서 허용된 가장 큰 값보다 더 커져서 실제 저장되는 값은 아주 작은 수이거나 음수로 되는 현상

    (영향)
    - 사용자 입력값이 반목문 제어, 메모리 할당, 메모리 복사 등을 위한 조건으로 사용되는 경우
      정수형 오버플로가 발생하면 시스템 오류 발생, 임의 명령실행, 시스템 점령 등의 보안상 문제를 유발

    (대응)
    - 언어, 플랫폼 별 정수 타입의 범위를 확인하여 사용한다.
    - 정수형 변수를 연산에 사용하는 경우 결과값의 범위를 결과값의 범위를 체크한다.
    - 외부 입력값을 동적으로 할당하여 사용하는 경우 변수의 값 범위를 검사하여 적절한 범위 내에 존재하는 값인지를 확인한다.
 */
public class 정수형오버플로 {

    public static void main(String[] args) {
        // 안전하지 않은 코드
        String msg_str = "";
        String tmp = request.getParameter("slf_msg_param_num");
        tmp = StringUtil.isNullTrim(tmp);
        if( tmp.equals("0")) {
            msg_str = PropertyUtil.getValue(msg_id);
        } else {
            int param_ct = Integer.parseInt(tmp);
            // 사용자 입력값을 정수형으로 사용하면서 입력값의 크기를 검증하지 않음
            // 사용자 입력값을 메모리 할당을 위한 조건으로 사용하면서 정수형 오버플로가 발생 가능성으로
            // 시스템 오류 발생 가능성

            //->
            // 안전한 코드
            if(param_ct < 0) {
                // 외부 입력값을 동적으로 할당하여 사용하는 경우
                // 변수의 값 범위를 검사하여 범위 내에 존재하는 값인지를 확인한다.
                throw new Exception();
            }
            String[] strArr = new String[param_ct];
        }
    }
}
