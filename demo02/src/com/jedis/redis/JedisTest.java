package com.jedis.redis;

import redis.clients.jedis.Jedis;

/*
Jedis连接
 */
public class JedisTest {
    public static void main(String[] args) {
        fun();
    }

    public static void fun(){
        Jedis jd = new Jedis("localhost",6379);
        jd.auth("Lei749581");
        String set = jd.set("uname", "leilvlong");
        System.out.println(jd.get("uname"));
        jd.close();
        System.out.println(set);
    }
}
