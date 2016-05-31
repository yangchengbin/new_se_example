package test;

import bean.Fruit;
import util.ExcelUtil;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<Fruit>();
        fruits.add(new Fruit("苹果", 2.01f));
        fruits.add(new Fruit("桔子", 2.05f));
        String templateFileName = "template.xls";
        String resultFileName = "e:/fruit.xls";
        new ExcelUtil().createExcel(templateFileName, fruits, resultFileName);
    }
}
