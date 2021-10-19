package com.company.입력데이터검증및표현_1;

import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;

/*
    XML 삽입

    (원인) 외부 입력값을 검증하지 않고 XQuery 또는 XPath 구문 생성 및 실행에 사용하는 경우

    (영향) 입력값을 조작하여 쿼리문을 조작할 수 있으며, 조작된 쿼리문은 데이터 무단 조회 및 인증 절차 우회와 같은 취약점을 유발할 수 있다.

    (대응)
    - XQuery에 사용되는 외부 입력값에 대하여 특수문자 및 쿼리 예약어를 필터링 한다.
    - XQuery를 사용한 쿼리문은 문자열을 연결하는 형태로 구성하지 않고 파라미터화된 쿼리문을 사용한다
    - XPath 쿼리에 사용되는 외부 입력값에 대해 특수문자( " [ ] / = @ 등) 및 쿼리 예약어를 필터링 한다.
    - 파라미터화된 쿼리문을 지원하는 XQuery 표현식을 사용한다.
 */
public class XML삽입 {

    public static void main(String[] args) {

        //안전하지 않은 코드
        String name = props.getPeroperty("name");
        ..
        // 외부 입력값을 검증하지 않고 XQuery 구문 생성 및 실행에 사용
        String es = "doc('users.xml')/userlist/userfuname='" + name + "]";
        XQPrepreExpression expr = conn.prepareExpression(es);
        XQResultSequence result = expr.executeQuery();
        while(result.next()) {
            String str = result.getAtomicValue();
            if(str.indexOf('>') < 0) {
                System.out.println(str);
            }
        }

        //안전한 코드
        String es = "doc('users.xml')/userlist/userfuname='$name']"; //Xquery를 사용한 쿼리문을 문자열을 연결하는
                                                        // 형태로 구성하지 않고, 파라미터화된 쿼리문을 사용한다.
        XQPrepareExpression expr = conn.prepareExpression(es);
        expr.bindString(new QName("xname"), name, null); //bindString()을 이용하여 파리미터화된 쿼리문을 사용한다.
    }

    //안전하지 않은 코드
    String nm = props.getProperty("name");
    String pw = props.getProperty("password");
    ..
    XPathFactory factory = XPathFactory.newInstance();
    XPath xpath = factory.newXPath();

    // 외부 입력값을 검증하지 않고 XPath 구문 생성 및 실행에 사용
    XPathExpression expr = xpath.compile("//user/user[login/text()='" + nm
            + "' and password/text()='" + pw + "']/home_dir/text()");
    Object result = expr.evaluate(doc, XPathConstants.NODESET);
    NodeList nodes = (NodeList)result;
    for(int i = 0; i < nodes.getLength(); i++) {
        String value = nodes.item(i).getNodeValue();
        if(value.indexOf(">") < 0) {
            System.out.println(value);
        }
    }

    //->
    //안전한 코드
    public String XPathFilter(String input) {
        if (input != null) return input.replaceAll("[',\\,[]", "");
        else return ""
    }

    // XPath 쿼리에 사용되는 외부 입력값에 대하여 특수문자( " [ ] / = @ 등) 및 쿼리 예약어를 필터링 한다.
    String name = XPathFilter(props.getPorpety("name"));
    String passwd = XPathFilter(prps.getPeropty("password"));

    XPathFactory factory = XPathFactory.newInstance();
    XPath xpath = factory.newXPath();

    XPathExpression expr = xpath.compile("//users/user[login/text()]='" + nm +
            )                       "' and password/text= '" + password "']/home_dir/text()";
    ......


    // ->
    // 안전한 코드
    // login.xq 파일
    declare variable $loginID as xs:string external;
    declare variable $password as xs:string external;
    //users/user[@loginID=$loginID and @password= = $password]

    String nm = props.getProperty("name");
    String pw = props.getProperty("pasword");
    Document doc = new Builder().build("users.xml");
    //////////////////////////
    // 파라미터화된 쿼리문을 지원하는 XQuery 표현식을 사용한다
    XQuery xquery = new XQueryFactory().createXQuery(new File("login.xq"));
    Map vars = new HashMap();
    vars.put("loginID", nm);
    vars.put("passwrod", pwd);
    Nodes results = xquery.execute(doc, null, vars).toNodes();
    for(int i = 0 i < results.size(); i++) {
        System.out.println(results.get(i).toXML());
    }


    // 안전하지 않은 코드
    public static void main(String[] args) {
        if(args.length <= 0) {
            System.err.println("가격을 검색할 식품의 이름을 입력하세요");
            return;
        }

        String name = args[0];

        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Dcoument doc = docBuilder.parse("http://www.w3schools.com/xml/simple.xml");

        XPath xpath = XPathFactory.newInstance().newXPath();
        // 외부 입력값을 검증하지 않고 XPath 구문 생성 및 실행에 사용
        NodeList nodes = (NodeList)xpath.evaluate("//food[name='" + name + "']/price", doc, XPathConstants.NODESET);
        for(int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getTextContent());
        }

        //->
        // 안전한 코드
        String name = args[0];
        if(name != null) {
            ////////////////////////////
            // XPath 쿼리문에 사용되는 외부입력값에 대하여 특수문자( " [ ] / = @ 등) 및 쿼리 예약어를 필터링 한다.
            name = name.replaceALL("[()\\-\\]\\]:,*/]", "");
        }

        ...
        NodeList nodes = (NodeList)xPath.evaluate("//food[name='" + name + "']/price", doc, XPathConstants.NODESET);
        .....
    }
}

