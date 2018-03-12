package com.lipenglong.lspring.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.lipenglong.lspring.context.entity.BeanEntity;
import com.lipenglong.lspring.context.entity.ParamEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * xml文件解析器
 * User: lipl
 * Date: 12-7-3
 * Time: 下午1:25
 * To change this template use File | Settings | File Templates.
 */
public class XMLParser {

    /**
     * 读取配置文件中的所有bean对象
     *
     * @param configLocation 配置文件
     * @return bean列表
     */
    public List<BeanEntity> getAllBeansConfigure(String configLocation) {
        List<BeanEntity> beanList = new ArrayList<BeanEntity>();
        try {
            File file = new File(configLocation);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(file);
            Element rootElement = doc.getRootElement();
            Element element;
            Element property;
            Element transaction;
            if (rootElement.getName().equals("beans")) {
                for (Iterator iterator = rootElement.elementIterator("bean"); iterator.hasNext(); ) {
                    element = (Element) iterator.next();

                    BeanEntity bean = new BeanEntity();
                    bean.setName(element.attributeValue("name"));
                    bean.setClassName(element.attributeValue("class"));

                    List<ParamEntity> params = new ArrayList<ParamEntity>();
                    for (Iterator it = element.elementIterator("property");
                         it.hasNext(); ) {
                        property = (Element) it.next();
                        ParamEntity param = new ParamEntity();
                        param.setParamName(property.attributeValue("name"));
                        param.setParamRef(property.attributeValue("ref"));
                        param.setParamValue(property.attributeValue("value"));
                        params.add(param);
                    }
                    bean.setParamList(params);

                    transaction = element.element("transaction");
                    if (transaction != null) {
                        List<String> methods = new ArrayList<String>();
                        for (Iterator it = transaction.elementIterator("method"); it.hasNext(); ) {
                            methods.add(((Element) it.next()).getTextTrim());
                        }
                        bean.setMethodList(methods);
                    }
                    beanList.add(bean);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    /**
     * 读取aop配置事务的package属性
     *
     * @param configLocation 配置文件
     * @return package属性
     */
    public String getAopPackageConfigure(String configLocation) {
        String aopPackage = null;
        try {
            File file = new File(configLocation);
            SAXReader reader = new SAXReader();
            Document doc = null;
            doc = reader.read(file);
            Element rootElement = doc.getRootElement();

            Element aop = rootElement.element("aop");
            if (aop != null) {
                aopPackage = aop.attributeValue("package");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return aopPackage;
    }

    /**
     * 读取配置的注解bean扫描包路径列表
     *
     * @param configLocation 配置文件
     * @return 注解bean包路径列表
     */
    public List<String> getAnnotationPackageConfigure(String configLocation) {
        List<String> list = new ArrayList<String>();
        try {
            File file = new File(configLocation);
            SAXReader reader = new SAXReader();
            Document doc = null;
            doc = reader.read(file);
            Element rootElement = doc.getRootElement();

            for (Iterator iterator = rootElement.elementIterator("component-scan");
                 iterator.hasNext(); ) {
                Element element = (Element) iterator.next();
                if (element != null) {
                    list.add(element.attributeValue("base-package"));
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return list;
    }

}
