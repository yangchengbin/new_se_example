package beanCopy;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanCopy {

    public static Object copyBean(Object src, Object target) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        if (src == null || target == null) {
            return target;
        }
        BeanInfo srcInfo = Introspector.getBeanInfo(src.getClass());
        BeanInfo tarInfo = Introspector.getBeanInfo(target.getClass());
        PropertyDescriptor[] srcPts = srcInfo.getPropertyDescriptors();
        PropertyDescriptor[] targetPts = tarInfo.getPropertyDescriptors();
        for (PropertyDescriptor srcPt : srcPts) {
            for (PropertyDescriptor targetPt : targetPts) {
                if (!"class".equals(srcPt.getName()) && srcPt.getName().equals(targetPt.getName()) && srcPt.getPropertyType().getName().equals(targetPt.getPropertyType().getName())) {
                    targetPt.getWriteMethod().invoke(target, srcPt.getReadMethod().invoke(src, null));
                }
            }
        }
        return target;
    }

    public static void main(String[] args) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "hello");
        Person person = new Person(1, "jack", 2, new Date(), map);
        System.out.println(person);
        People people = new People();
        people = (People) copyBean(person, people);
        System.out.println(people);
    }
}
