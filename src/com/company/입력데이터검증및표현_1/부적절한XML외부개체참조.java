package com.company.입력데이터검증및표현_1;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
    부적절한 XML 외부 개체 참조

    (원인) XML 문서에는 DTD(Document Type Definition)를 포함할 수 있으며, DTD는 XML엔티티(Entity)를 정의한다.
          부적절한 XML 외부개체 참조 보안약점은 서버에서 XML 외부 엔티티를 처리할 수 있도록 설정된 경우에 발생할 수 있다.

    (영향)
    - 취약한 XML parser가 외부 값을 참조하는 XML 값을 처리할 때, 공격자가 삽입한 공격 구문이 동작되어
      서버 파일 접근, 불필요한 자원 사용, 인증 우회, 정보 노출 등이 발생할 수 있다.

    (대응)
    - 로컬 정적 DTD를 사용하고, 외부에서 전송된 XML문서에 포함된 DTD를 완전하게 비활성화 한다.
    - 비활성화를 할 수 없는 경우에는 외부 엔티티 및 외부 문서 유형 선언을 각 파서(parser)에 맞는 고유한 방식으로 비활성화 한다.
 */
public class 부적절한XML외부개체참조 {

    //안전하지 않읔 코드
    public void unmarshal(File receivedXml) throws JAXBException, ParserConfigurationException, SAXException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNameSpaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(receivedXml); // 입력받은 receivedXml을 이용하여 Document를 생성한다
        Student employee = (Student) jaxbUnmarshaller.unmashall(document); //document를 이용하여 마샬링을 수행한다.
                                                    // 취약한 XML parser가 외부 값을 참조하느 XML값을 처리할때
                                                    // 공격자가 삽입한 공격 구문이 동작되어 서버 파일 접근, 불필요한 자원 사용, 인증 우회, 정보 노출 등이 발생할 수 있다.

        receivedXml
        <?xml version="1.0" encoding="ISO-8859-1"?>
        <!DOCTYPE foo [
        <!ELEMENT foo ANY>
        <!ENTITY xxe SYSTEM "file:///etc/passwd">]<foo>%xxxe;</foo> //서버파일에 접근할수 있다
    }

    //->
    // 안전한 코드
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    // XML 파서가 doctype을 정의하지 못하도록 설정
    dbf.setFeature("http://apache.org/xml/featuresdisallow-doctype-decl", true);
    // 외부 일반 엔티티를 포함하지 않도록 설정
    dbf.setFeature("http://apache.org/xml/sax/features/external-general-entities", false);
    // 외부 DTD 비활성화
    dbf.setFeature("http://apache.org/xml/feature/nonvalidation/load-external-dtd", false);
    // XInclude를 사용하지 않음
    dbf.setXIncludedAware(false);
    // 생성된 파서가 엔티티 참조 노드를 확장하지 않도록 함
    dbf.setExpandEntityReference(false);

    DocumentBuilder db = dbf.newDocumentBuilder();
    Document document = db.parse(receivedXml);
    Model model = (Model) u.unmarshall(document); // Student가 아닌 Model

    //->
    // 안전한 코드
    // DocumentBuilderFactory
    // 각파서의 고유한 방식으로 외부 엔티티 및 외부 문서 유형 선언을 비활성화 한다.
    String xml = "xxe.xml";
    DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
    df.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); //외부 DTD 비활성화
    df.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); //외부 SCHEMA 비활성화
    DocumentBuilder builder = df.newDocumentBuilder();
    Document docuemnt = builder.newDocument();
    DOMSource domSource = new DomSource(document);


    //->
    // 안전한 코드
    // SAXParserFactory
    // 각 파서의 고유한 방색으로 외부 엔티티 및 외부 문서 유형 선언을 비활성화 한다.
    String xml = "xxe.xml";
    SaxHandler handler = new SaxHandler();
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parser = factory.newSAXParser();
    parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
    parser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

    //-> 안전한 코드
    // SchemaFactory
    // 각 파서의 고유한 방식으로 외부 엔티티 및 외부 문서 유형 선언을 비활성화 한다.
    SchemaFactory factory = SchemaFactory.newInstance("XML_Constants.W3C_XML_SCHEMA_NS_URI");
    factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
    factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
    Schema schema = factory.newSchema(Source);

    //-> 안전한 코드
    // XMLInputFactory
    // 각 파서의 고유한 방식으로 외부 엔티티 및 외부 문서 유형 선언을 비활성화 한다.
    XMLInputFactory factory = XMLInputFactory.newFactory();
    factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
    factory.setProperty(XMLInputFactory.SUPPORTINTG_EXTERNAL_ENTITIES, flase);

    XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("xxe.xml"));

}
