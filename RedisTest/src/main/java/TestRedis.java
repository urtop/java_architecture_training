import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Mark Tao on 2016/9/10 17:58.
 */


public class TestRedis {

    @Test
    public void test() {
        Jedis jedis = new Jedis("1.1.1.2", 6379);
        List<String> list = jedis.mget("name", "age");
        list.forEach(System.out::println);

    }

}

