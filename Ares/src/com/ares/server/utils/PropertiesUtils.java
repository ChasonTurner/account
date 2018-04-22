package com.ares.server.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by bruce.zhan.pc@outlook.com on 2015/5/13.
 * 读取资源文件，获取相应内容
 */
public class PropertiesUtils {

    private static final Logger logger = Logger.getLogger(PropertiesUtils.class);

    //资源文件名
    private String fileName;

    //构造函数
    public PropertiesUtils(String fileName) {
        this.fileName = fileName;
    }

    //初始化，读取资源文件
    private Properties init(String fileName) {

        Properties prop = new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (in == null) {
            logger.error(fileName + "文件不存在。");
            throw new RuntimeException(fileName + "文件不存在。");
        }

        try {
            prop.load(in);
        } catch (IOException e) {
            logger.error("读取" + fileName + "文件出错。");
            throw new RuntimeException("读取" + fileName + "文件出错。");
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("关闭InputStream流出错。");
                throw new RuntimeException("关闭InputStream流出错。");
            }
        }

        return prop;
    }

    //获取资源相应内容
    public String getString(String key) {

        Properties prop = init(this.fileName);
        if (prop.containsKey(key)) {
            return prop.getProperty(key);
        }

        logger.error("没有找到" + key + "相应的值，请检查" + key + "的写法是否正确。");
        throw new RuntimeException("没有找到" + key + "相应的值，请检查" + key + "的写法是否正确。");
    }
}
