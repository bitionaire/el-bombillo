package org.bitionaire.elbombillo.tag.database;


import org.bitionaire.elbombillo.tag.PropertyLoader;
import redis.clients.jedis.Jedis;


/**
 * This an Adapter for the Jedis framework.
 * Using this you can work with the Redis in-memory database
 * (@see <a href="http://redis.io/">redis.io</a>).
 */
public class JedisAdapter {
    protected static Jedis dbInstance;

    public static Jedis getInstance() {
        if (dbInstance == null) {
            PropertyLoader prop = new PropertyLoader();
            dbInstance = new Jedis(prop.getDatabaseHost());
            return dbInstance;
        } else {
            return dbInstance;
        }
    }
}
