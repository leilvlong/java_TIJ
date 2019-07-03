package com.github.java03.xmltest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.*;


/*
记录一个坑:
    涉及到数据库连接的xml配置
原因:
    在xml获取ContactJdbc对象时 连接数据库也需要花费很多资源
    所以在xml中将ContactJdbc放在第一行 让它加载完成才加载下面的xml,
    在不涉及到数据库连接操作时
    而程序书写上下文无论怎么改动也不会出空指针异常更验证我的猜想
 */
public class GetCoantactProperty {
    private static GetCoantactProperty gp = null;

    private GetCoantactProperty() {
    }

    private static Map<String,Object> contactProperty = new HashMap<>();
    static {
        InputStream is = GetCoantactProperty.class.getClassLoader().getResourceAsStream("bean.xml");
        SAXReader reader = new SAXReader();
        try {
            Document read = reader.read(is);
            List<Element> list =  read.selectNodes("//bean");
            for (Element element : list) {

                System.out.println("正在加载" + element.attributeValue("id"));

                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                Object obj = Class.forName(className).newInstance();
                contactProperty.put(id,obj);
            }
        } catch (Exception e) {
            throw new RuntimeException("获取对象异常");
        }
    }

    public Object getBean(String key){
        System.out.println("获取对象: "+ key + ": " + contactProperty.get(key));
        return contactProperty.get(key);
    }

    public static synchronized GetCoantactProperty getGCP(){
        if(gp == null ){
            gp = new GetCoantactProperty();
        }
        return gp;
    }

    public static void main(String[] args) {
        GetCoantactProperty gcp = GetCoantactProperty.getGCP();
        gcp.getBean("contactService");
        gcp.getBean("contactJdbc");
        gcp.getBean("contactTest");

    }
}
