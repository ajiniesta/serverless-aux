package com.iniesta.sless.conf;

public class Configurator {

    public String get(String key){
        return System.getenv(String.valueOf(key));
    }

    public String get(String key, String defaultValue){
        String fromConf = get(key);
        if(fromConf==null || fromConf.trim().isEmpty()){
            return defaultValue;
        }
        return fromConf;
    }

    public int getInt(String key){
        return Integer.parseInt(System.getenv(String.valueOf(key)));
    }

    public int getInt(String key, int defaultValue){
        return Integer.parseInt(get(String.valueOf(key), String.valueOf(defaultValue)));
    }
}
