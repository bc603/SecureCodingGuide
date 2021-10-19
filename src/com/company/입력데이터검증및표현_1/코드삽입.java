package com.company.입력데이터검증및표현_1;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/*
    코드삽입

    공격자가 소프트웨어의 의도된 동작을 변경하도록 임의 코드를 삽입하여 소프트웨어가 비정삭적으로 동작

    (영향) 의도하지 않은 코드를 실행하여 권한을 탈취하거나, 인증 우회, 시스템 명령어 실행

    (대응)
    - 동적코드를 실행할 수 있는 함수를 사용하지 않음
    - 동적 코드 필요 시, 실행 가능한 동적한 코드를 입력 값으로 받지 않도록,
      외부 입력값에 대하여 화이트리스트 방식으로 구현,
      유효한 문자만 포함하도록 동적 코드에 사용되는 사용자 입력값을 필터링
 */
public class 코드삽입 {

    public class CodeInjectionController {
        @RequestMapping(value = "/execute", method = RequestMethod.GET)
        public String execte(@RequestParameter("src") string src) throws ScriptException {
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scrintEngine = scriptEngineManager.getEngineByName("javascript");
            String retValue = (String)scriptEngine.eval(src); // eval()를 통해 외부 입력값인 src를 eval 함수로 실행하고 있다
            return retValue;

            //->
            // 정규식을 이용하여 특수문자 입력시 예외를 발생시킨다
            if(src.matched("[\\w]*") == false) {
                throw new IllegalArgumentException();
            }
            ///////////////////////////////////////
            ....
        }
    }

    // php의 경우 vul.php
    $myvar = 'someValue';
    $x = $_GET['arg'];
    eval('$myvar=l',$x, ';'); // eval()를 통해 외부 입력값이 myvar를 함수로 실행
    // 삽입코드의 예
    /vul.php?arg=1;phpinfo()
    /vul.php?arg=1;system(*uname -a")"
    /vul.php?arg=1;system("cat /etc/passwd")
    /vul.php?arg=1;system("ps -ef"))
    // 입력값에 명령어로 실행될수 있는 코드를 사입하여 시스템 명령어 실행 가능

    //->
    $myvar = 'varname';
    $x = $_GET['arg'];
    $x = preg_replace("/[w\\^a-z0-9]/1", "", $x); // prev_replace() 통해 입력값을 필터링
    eval('\$myvar = \$x,"); // 유효한 문자만 포함하도록 동적 코드에 사용되는 사용자 입력값을 필터링


    class ACC {
        public static void executeScript(final String name) throws ScriptException {
            ScriptEngineManager manger = new ScriptEngineManager();
            ScriptEngine engine = manger.getEngineByName("javascript");
            engine.eval("print(" + name + ")"); // eval()을 이용해서 변수 name 값을 표시한다.
        }

        @RequestMapping(value = "/print", method = RequestMethod.POST)
        public void print(@RequestParam("name") String name) throws ScriptException {
            ACC.execte(name); // 사용자 입력값인 name을 이용해서 eval을 사용하는 함수를 호출한다.
        }

        //->
        // 안전한 코드의 부분
        if (!Filter.filterScript(name)) {
            throw new IllegalArgumentException(); //Filter를 통해 유효한 문자만 포함하도록 동적코드에 사용되는 사용자 입력값 필터링
        }
        ////////////////////////////////////

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine manager.getEngineByName("javascript");
        engine.eval("print(" + name + ")");
    }
}
