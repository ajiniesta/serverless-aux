package com.iniesta.sless.infra;

import com.iniesta.sless.conf.Configurator;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class RedisCache {

    private final Configurator conf;
    private final Jedis jedis;

    public RedisCache(Configurator conf) {
        this.conf = conf;
        this.jedis = new Jedis(
                conf.get("redis-host"),
                conf.getInt("redis-port",6379)
        );
    }

    public void set(String key, String value){
        SetParams params = new SetParams();
        params.ex(60*60*2);
        jedis.set(key, value, params);
    }

    public String get(String key){
         return jedis.get(key);
    }
}
