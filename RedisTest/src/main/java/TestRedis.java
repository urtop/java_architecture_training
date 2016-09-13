import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastjsonSockJsMessageCodec;
import entity.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by Mark Tao on 2016/9/10 17:58.
 */


public class TestRedis {

    @Test
    public void test() {
        Jedis jedis = new Jedis("1.1.1.2", 6379);
//        List<String> list = jedis.mget("name", "age");
//        list.forEach(System.out::println);
        final String SYS_USER_TAB = "SYS_USER_TAB";
        final String SYS_USER_TAB_SEL_AGE_25 = "SYS_USER_TAB_SEL_AGE_25";
        final String SYS_USER_TAB_SEL_SEX_m = "SYS_USER_TAB_SEL_SEX_m";
        final String SYS_USER_TAB_SEL_SEX_w = "SYS_USER_TAB_SEL_SEX_w";

        Set<String> userAges = jedis.smembers(SYS_USER_TAB_SEL_AGE_25);

        for (String id : userAges) {
           String result =  jedis.hget(SYS_USER_TAB,id);
            System.out.println(result);

        }
        String u1id = UUID.randomUUID().toString();
        User u1 = new User(u1id, "z1", "20", "w");
        jedis.sadd(SYS_USER_TAB_SEL_SEX_w, u1id);


        String u2id = UUID.randomUUID().toString();
        User u2 = new User(u2id, "z2", "25", "m");
        jedis.sadd(SYS_USER_TAB_SEL_SEX_m, u2id);
        jedis.sadd(SYS_USER_TAB_SEL_AGE_25, u2id);


        String u3id = UUID.randomUUID().toString();
        User u3 = new User(u3id, "z3", "20", "m");
        jedis.sadd(SYS_USER_TAB_SEL_SEX_m, u3id);


        String u4id = UUID.randomUUID().toString();
        User u4 = new User(u4id, "z4", "21", "m");
        jedis.sadd(SYS_USER_TAB_SEL_SEX_m, u4id);


        String u5id = UUID.randomUUID().toString();
        User u5 = new User(u5id, "z5", "29", "w");
        jedis.sadd(SYS_USER_TAB_SEL_SEX_w, u5id);
        jedis.sadd(SYS_USER_TAB_SEL_AGE_25, u5id);


        Map<String, String> map = new HashMap<>();
        map.put(u1id, JSON.toJSONString(u1));
        map.put(u1id, JSON.toJSONString(u2));
        map.put(u3id, JSON.toJSONString(u3));
        map.put(u4id, JSON.toJSONString(u4));
        map.put(u5id, JSON.toJSONString(u5));

        jedis.hmset("SYS_USER_TAB", map);


    }

}

