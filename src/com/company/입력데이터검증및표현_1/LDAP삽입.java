package com.company.입력데이터검증및표현_1;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;

/*
    LDAP 삽입

    (원인) 응용 프로그램이 사용자의 입력을 검증하지 않고 LDAP 필터문 생성에 사용하는 경우

    (영향) 공격자가 LDAP 필터문을 조작하여 정보를 유출하거나 조작할 수 있다.

    (대응)
    - DN(Distinguish Name)과 필터에 사용되는 사용자 입력값에는 LDAP 필터에 사용되는
      특수문자( () ; , *, ! & | = )가 포함되지 않도록 한다.
    - 특수문자를 사용해야 하는 경우에는 특수문자가 실행명령이 아닌 일반 문자로 인식되도록 처리한다.
 */
public class LDAP삽입 {

    private void searchRecord(String userSN, String userPassword) throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.dapCtxFactory");
        try {
            DirContext ctx = new InitialDirContext(env);
            SearchControls sc = new SearchControls();
            String[] attributeFilter = {"cn", "mail"};
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
            // 외부 입력값을 LDAP 필터문 생성에 사용 -> 공격자가 LDAP 필터문을 조작하여 정보를 유출하거나 조작할수 있다
            /////////////////////////////////
            // --> 안전한 코드
            // DN과 필터에 사용되는 사용자 입력값에는 LDAP 필터에 사용되는 특수문자( ( ) ; , * ! & | =)가 포함되지 않도록 한다
            if(!userSN.matches("[\\w\\s]") || !userPassword.matches("[\\w]")) {
                throws new IllegalArgumentException("Invalid Input");
            }
            /////////////////////////////////
            String base = "dc=example.dc=com";
            String filter = "(&(sn=" + userSN + ")(userPassword=" + userPassword + "))";
            NamingEnumeration<?> results = ctx.search(base, filter, sc);
            while(results.hasMore()) {
                SearchResult sr = (SearchResult) results.next();
                ....
            }
        } catch(NamingException e) {
            ....
        }
    }
}
