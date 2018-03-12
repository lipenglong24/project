package com.lipenglong.lspring.annotation;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 注解bean扫描器
 * User: lipl
 * Date: 12-8-8
 * Time: 上午11:32
 * To change this template use File | Settings | File Templates.
 */
public class ComponentScanner {

    /**
     * 扫描包下的注解配置
     *
     * @param pack 包目录
     * @return 注解类列表
     */
    public List<String> scan(String pack) {
        List<String> classList = new ArrayList<String>();
        String packDirName = pack.replace(".", "/");
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader()
                    .getResources(packDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                if ("file".equals(url.getProtocol())) {
                    String packagePath = URLDecoder.decode(url.getFile(), "utf-8");
                    this.findClassFiles(packagePath, pack, classList);
                } else if ("jar".equals(url.getProtocol())) {
//                    暂时不做处理
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classList;
    }

    /**
     * 递归变量注解bean包路径，得到class列表
     *
     * @param packagePath
     * @param packN
     * @param classList
     */
    private void findClassFiles(String packagePath, String packN, List<String> classList) {
        File dirFile = new File(packagePath);
        if (dirFile.exists() && dirFile.isDirectory()) {
            File[] classFiles = dirFile.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    return pathname.isDirectory() ||
                            pathname.getName().endsWith(".class");
                }
            });

            for (File temp : classFiles) {
                if (temp.isDirectory()) {
                    findClassFiles(temp.getAbsolutePath(), packN + "."
                            + temp.getName(), classList);
                } else {
                    String className = temp.getName().substring(0,
                            temp.getName().length() - 6);
                    classList.add(packN + "." + className);
                }
            }
        }
    }
}
