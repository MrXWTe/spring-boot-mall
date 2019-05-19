import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {

    /**
     * 每次操作都需要创建一个连接，再关闭，性能低
     */
    @Test
    public void testJedis(){
        // 创建一个jedis对象，参数：host port
        Jedis jedis = new Jedis("192.168.43.126", 6379);
        // 直接使用jedis操作redis。所有jedis的命令都对应一个redis方法
        jedis.set("test123", "my first jedis test");
        System.out.println(jedis.get("k1"));
        // 关闭连接
        jedis.close();
    }


    /**
     * redis连接池
     */
    @Test
    public void testJedisPool(){
        // 创建一个连接池对象，两个参数 host port
        JedisPool pool = new JedisPool("192.168.43.126", 6379);
        // 从连接池获得一个连接，就是一个jedis对象
        Jedis jedis = pool.getResource();
        // 使用jedis操作redis
        jedis.set("test1234", "test1234");
        System.out.println(jedis.get("test1234"));
        // 关闭连接，每次使用完毕后关闭连接。连接池回收资源
        jedis.close();
        // 关闭连接池
        pool.close();
    }

    /**
     * jedis集群
     */
    @Test
    public void testJedisCluster(){
        // 创建一个JedisCluster对象，有一个参数nodes是一个set参数。set中包含若干个HostAndPort对象
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.43.126", 7001));
        nodes.add(new HostAndPort("192.168.43.126", 7002));
        nodes.add(new HostAndPort("192.168.43.126", 7003));
        nodes.add(new HostAndPort("192.168.43.126", 7004));
        nodes.add(new HostAndPort("192.168.43.126", 7005));
        nodes.add(new HostAndPort("192.168.43.126", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("test", "123");
        // 使用JedisCluster对象操作redis
        System.out.println(jedisCluster.get("test"));
        // 系统关闭之前，关闭JedisCluster对象
        jedisCluster.close();
    }

}
