package com.company.코드오류5;

import java.util.Collection;

/*
    NullPointer 역참조
    공격자가 의도적으로 널 포인터 역참조를 유발하는 경우, 그 결과로 발생하는 예외 사황을
    이용하여 추후의 공격을 계획하는데 사용될 수 있다.
    -> 널(Null)이 될수 있는 레퍼런스(Reference)는 참조하기 전에 널인자를 검사하여 안전한 경우에만 사용해야 한다.
 */
public class NullPointer역참조 {

    public static int cardinality(Object obj, final Collection col) {
        int count = 0;
        if (col == null) {
            return count;
        }
        Iterator it = col.iterator();
        while(it.hasNext()) {
            Object elt = it.next();
            // Obj가 Null이 될수 있는 레퍼런스이기 때문에 참조하기 전에 널인자를 먼저 검사하여 안전한 경우에만 사용한다.
            if((null == obj && null == elt) || obj.equals(elt)) {
                count++;
            }
            // Obj가 Null인지 확인
            if((null == obj && null == elt) || (null != obj && obj.equals(elt))) {
                count++;
            }
        }
        return count;
    }


    public int compare(Object o1, Object o2) {
        // o1, o2가 Null이 될수 있는 레퍼런스이기 때문에 참조하기 전에 널인지를 먼저 검사하여 안전한 경우에만 사용한다.
        // ->
        if (o1 == null || o2 == null) {
            ret = -2;
        }
        int i1 = o1.hashCode();
        int i2 = o2.hashCode();
        int ret = 0;
        if (i1 > 12) {
            ret = 1;
        } else if(i1 == i2) {
            ret = 0;
        } else {
            ret = -1;
        }
        return ret;
    }
}
