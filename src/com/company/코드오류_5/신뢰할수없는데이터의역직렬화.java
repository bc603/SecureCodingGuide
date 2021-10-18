package com.company.코드오류_5;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import java.io.ByteArrayInputStream;

/*
    신뢰할 수 없는 데이터의 역직렬화

    송신자가 네트워크를 이용하여 직렬화된 정보를 수신자에게 전달하는 과정에서
    공격자가 전송 또는 지정된 스트림을 조작할 수 있는 경우에는 신뢰할 수 없는 역직렬화를 이용

    (영향) 무결성 침해, 원격 코드 실행, 서비스 거부 공격 등이 발생할 수 있다.

    ->
    신뢰할 수 없는 데이터를 역직렬화 하지 않도록 응용프로그램을 구성한다.
    민감정보 또는 중용정보를 전송 시 암호화 통신을 적용하지 않는 경우, 송신 측에서 서명을 추가하고 수신 측에서 서명을 확인하여
    데이터의 무결성을 검증한다.
    신뢰할 수 있는 데이터의 식별을 위해 역직렬화 대상의 직렬화 데어터가 사전에 검증된 클래스에만 포함하는 지 검증하거나, (화이트리스트 구성?)
    제한된 실행 권한을 구성하여 역직렬화 코드를 실행한다.
 */
public class 신뢰할수없는데이터의역직렬화 {

    public static void main(String[] args) throws IOException, ClasNotFoundException {
        // map 생성
        SerializableMap<String, Integer> map = buildMap();

        //map 직렬화
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data"));
        out.writeObject(map);
        out.close();

        // map 역직렬화
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data"));
        map = (SerializableMap<String, Integer>)in.readObject();

        // -> 송신측에서 서명을 추가하고 수신측에서 서명을 확인하고 데이터의 무결성을 검증한다.
        sealedMap = (SealedObject) in.readObject();
        in.close();

        // 객체를 추출
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        signedMap = (SignedObject) sealedMap.getObject(cipher);

        // 서명값 검증 과정에서 불일치 시 예외를 리턴하고, 일치 시 map 값을 읽음
        if (!signedMap.verify(kp.getPublish(), sig)) {
            throw new GeneralSecurityException("Map failed verification");
        }
        map = (SerializableMap<String, Integer>) sigendMap.getObject();
    }


    //안전하지 않은 코드
    class DeserializeExample {
        public static Object deserialize(byte[] buffer) throws IOException, ClassNotFoundException {
            Object ret = null;
            try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer)) {
                try(ObjectInputStream ois = new ObjectInputStream(bais)) {
                    ret = ois.readObject();
                }
            }
            return ret;
        }
    }

    //안전한 코드
    /*
        1. 전송할 stream 생성할때 화이트 리스트를 입력받는다.
        2. 전송받았을때 역직렬화 대상 클래스의 이름이 화이트 리스트에 있는지 확인한다.
        3. 화이트리스트에 없는 역직렬화 데이터의 경우 에외 발생
     */
    public class WhiteListedObjectInputStream extends ObjectInputStream {
        public Set<String> whiteList;
        //WhiteLitedObjectInputStream를 생성할 때 화이트 리스트를 입력뱓는다.
        public WhiteListedObjectInputStream(InputStream inputStream, Set<String> wl) throws IOException {
            super(inputstream);
            whiteList = wl;
        }

        @Override
        protected Class<?> resolveClass(ObjectStreamClass cls) throws IOException, ClassNotFoundExceptioin {
            //ObejctStreamClass의 클래스명이 화이트리스트에 있는지 확인
            if(!whiteList.contains(cls.getName())) {
                throw new InvaliedClassException("UnExcepted serialized class", classgetName());
            }
        }

        @RequestMapping(value = "/upload", method= RequestMethod.POST)
        public Student upload(@RequestParam("file") MultipartFile multipartFile) throws ClassNotFoundException, IOExeption {
            Student student = null;
            File targetFile = new File("/temp/" + multipartFile.getOriginalFileName());
            //역직렬화 대상 클래스 이름의 화이트리스트 생성
            Set<String> whitelist = new HashSet<String>(Arrays.asList(new String[]{ "Student")}));

            try (InputStream fileStream = multipartFile.getInputStream()) {
                try(WhiteListdObjectInputStream ois = new WhiteListObjectInputStream(fileStream, whilteList)) {
                    //화이트리스트에 없는 역직력화 데이터의 경우 예외 발생
                    student = (Student) ois.readObject();
                }
            }
            return student;
        }
    }


    // 안전하지 않은 코드
    conn.addr = self.receiver_socket.accept();
    data = conn.recv(1024);
    return Pickle.loads(data);

    // 안전한 코드
    // 클라이언트코드
    picked_data = pickle.dumpts(data)
    digest = hmac_new('shared-key', pickled_data, hashlib.sha1).hexdigest()
    header = '%s' %(digest)
    conn_send(header + '' + pickled_data)
    // 서버코드
    conn,addr = self.receiver_socket.accept()
    data = conn.recv(1024)
    recvd_digest.pickled_data = data.split('')
    new digest = hmac.new('shared-key', pickled_data, hashlib.sha1).hexdigest()
    if recvd_digest != new digest:
        print 'Integrity check failed'
    else:
        unpickled_data = pickle.loads(pickled_data)
}
