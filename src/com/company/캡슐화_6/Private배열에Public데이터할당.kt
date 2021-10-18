package com.company.캡슐화_6

/*
    Private 배열에 public 데이터 할당
    public으로 선언된 데이터 또는 메소드의 인자를 private 배열에 저장하는 경우
    -> private 배열을 외부에서 직접 접근하여 값을 수정하는 것이 가능한다.
 */
class Private배열에Public데이터할당 {

    public class SetPublicArrayToPriavteArray {
        private String[] datas;
        public void setDatas(String[] datas) {
            this.datas = datas;
        }
        public SetPublicArrayToPrivateArray() { this.datas = new String[]{"100", "90", "70", "60"}}; }
        public void print() { System.out.println(Arrays.toString(this.datas)); }

        public static void main(String[] args)
        {
            SetPublicArrayToPriavteArray innerData = new SetPublicArrayToPriavteArray();
            innerData.print();

            String[] outerData = new String[] {"10", "20", "30"};
            innerData.setDatas(outerData);
            innerData.print();
            System.out.println(Arrays.toString(outerData));

            // private 배열을 직접접근하여 값을 수정하는 것이 가능한다.
            outerData[1] = "xx"; //취약점
            innerData.print();
            System.out.println(Arrays.toString(outerDatas));

            // -> 입력된 public 배열의 reference가 아닌, 배열의 '값'을 private 배열에 할당하여
            // private 멤버로서의 접근권한을 유지
            private String[] datas;
            public void setDatas(String[] datas) {
                if(datas != null) {
                    this.datas = new String[datas.length};
                for(int i = 0; i < datas.length; i++) {
                    this.datas[i] = datas[i];
            }
        }
    }
}