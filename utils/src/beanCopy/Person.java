package beanCopy;

import java.util.Date;
import java.util.Map;

public class Person {
    private int id;
    private String name;
    private int age;
    private Date birthday;
    private Map map;

    public Person() {
    }

    public Person(int id, String name, int age, Date birthday, Map map) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", map=" + map +
                '}';
    }
}
