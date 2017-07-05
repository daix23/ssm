package com.code.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-7-4
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesUtil {

        private static final Log log = LogFactory.getLog(PropertiesUtil.class);
        private static Properties env = new Properties();

        static {
            try {
                //PropertiesHelper.class.getResourceAsStream("env.properties"); // /com/cici/conf/env.properties
                //ClassLoader.getSystemResourceAsStream("env.properties");
                InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("comm-config.properties");
                env.load(is);
                is.close();
            } catch (Exception e) {
                log.error(e);
            }
        }

        /**
         * 取属性值
         * @param key
         * @return
         */
        public static String getProperty(String key){
            return env.getProperty(key);
        }

        /**
         * 设置属性值
         * @param key
         * @param value
         */
        public static void setProperty(String key, String value){
            try{
                File file = new File(PropertiesUtil.class.getClassLoader().getResource(".").getPath()+File.separator+"comm-config.properties");
                FileOutputStream outStream = new FileOutputStream(file);
                env.setProperty(key, value);
                //写入properties文件
                env.store(outStream, null);
            } catch (Exception ex) {
                log.error(ex);
            }
        }

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            System.out.println(PropertiesUtil.getProperty("lucene.path"));
            //System.out.println(PropertiesUtil.class.getClassLoader().getResource(".").getPath());
        }

}
