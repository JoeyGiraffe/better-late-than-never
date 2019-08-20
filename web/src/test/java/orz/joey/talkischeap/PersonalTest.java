package orz.joey.talkischeap;

import org.aspectj.apache.bcel.classfile.InnerClass;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.SerializationUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PersonalTest {

    @Test
    public void listFilterTest() {
        List<Person> people = Arrays.asList(new Person("SAN", 12), new Person("SI", 20), new Person("SI", 14), new Person("WU", 13));
        List<Person> chosen = people.stream().filter(person -> "SI".equals(person.getName()) || "WU".equals(person.getName())).filter(person -> person.getAge() < 15).collect(Collectors.toList());
        chosen.stream().map(Person::toString).forEach(System.out::println);
    }


    private class Person {
        private String name;
        private int age;

        Person() {}

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            System.out.println(name);
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            System.out.println(age);
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Test
    public void cloneTest() throws ClassNotFoundException {
        Person source = new Person("JIALI", 19);
        System.out.println(source);
        Object target = deepClone(source);
        System.out.println(target);

    }

    private Object deepClone(Object source) {
        try {
            //1.需要外部类的实例
            PersonalTest outerInstance = new PersonalTest();
            //2.需要外部类的Class
            Class<? extends PersonalTest> outerClazz = outerInstance.getClass();
            //2.需要内部类的Class
            Class<?> innerClazz = source.getClass();
            //3.获得内部类的构造器
            Constructor innerConstructor = innerClazz.getDeclaredConstructor(outerClazz);
            //4.把构造器设为可用
            innerConstructor.setAccessible(true);
            //5.利用构造器生成内部类实例
            Object innerInstance = innerConstructor.newInstance(outerInstance);


            Field[] fields = innerClazz.getDeclaredFields();
            for (Field field : fields) {
                //判断为外部类对象
                if (field.isSynthetic() && Modifier.isFinal(field.getModifiers())) continue;
                String name = field.getName();
                String getMethodName = "get" + capitalizeFirstLetter(name);
                String setMethodName = "set" + capitalizeFirstLetter(name);

                Method getMethod = innerClazz.getMethod(getMethodName);
                Method setMethod = innerClazz.getMethod(setMethodName, field.getType());
                Object val = getMethod.invoke(source);
                setMethod.invoke(innerInstance, val);
            }

            return innerInstance;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String capitalizeFirstLetter(String name) {
        char[] charArr = name.toCharArray();
        if (charArr[0] >= 'a' && charArr[0] <= 'z') {
            charArr[0] -= 32;
        }
        return String.valueOf(charArr);
    }


}