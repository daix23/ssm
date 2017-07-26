package com.code.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: wbz
 * Date: 12-4-25
 * Time: 下午12:32
 * To change this template use File | Settings | File Templates.
 */
public class Config {
    static final Logger log = Logger.getLogger(Config.class);
    private Properties props = null;
    private String configname;
    private static HashMap<String, Config> configs = new HashMap<String, Config>();

    private Config() {

    }
    public int getCount(){
        if(props == null)
            return 0;
        return props.size();
    }
    public String getProperty(int i ){
        String[] keys = props.keySet().toArray(new String[0]);
        return getProperty(keys[i]);
    }
    public String[] getAllKeys(){
        return props.keySet().toArray(new String[0]);
    }
    public static Config getConfig(String configFilename) {
        if (!configs.containsKey(configFilename)) {
            Config config = new Config();
            config.configname = configFilename;
            Properties prop = new Properties();
            InputStreamReader is;
            try {
                //is = new InputStreamReader(Config.class.getClassLoader().getResourceAsStream(configFilename), "UTF-8");
                is = new InputStreamReader((InputStream)Config.class.getClassLoader().getResource(configFilename).getContent(),"UTF-8");
                prop.load(is);
                config.props = prop;
                is.close();
                configs.put(configFilename, config);
                return config;
            } catch (UnsupportedEncodingException e1) {
                log.error(e1.getMessage());
                log.error(e1.getStackTrace());
            } catch (IOException e) {
                log.error(e.getMessage());
                log.error(e.getStackTrace());
            }
            return null;
        } else {
            return configs.get(configFilename);
        }
    }
    public void setProperty(String key,String value) throws FileNotFoundException,IOException {
        props.setProperty(key,value);
        InputStreamReader fileReader = new InputStreamReader(new FileInputStream(Config.class.getClassLoader().getResource(configname).getFile()),"UTF-8");
        BufferedReader reader=new BufferedReader(fileReader);
        String line = reader.readLine();
        String content = "";
        boolean set = false;
        while(line != null){
            if(line.replace(" ", "").startsWith(key + "=")){
                line = key+"="+value;
                set = true;
            }
            content += line + "\n";
            line = reader.readLine();
        }
        if(!set){
            content += key+"="+value;
        }
        fileReader.close();
        reader.close();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.class.getClassLoader().getResource(configname).getFile()),"UTF-8"));
        bufferedWriter.write(content);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
    public static void refresh(String configfilename) {
        Config config = getConfig(configfilename);
        if(config != null)
            config.props = null;
        configs.remove(configfilename);
    }
    public static void refresh(){
        configs.clear();
    }
    public String getProperty(String key) {
        return props.getProperty(key);
    }
}
