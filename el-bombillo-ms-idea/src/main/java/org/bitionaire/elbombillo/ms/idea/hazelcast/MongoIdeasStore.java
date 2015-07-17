package org.bitionaire.elbombillo.ms.idea.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MapLoaderLifecycleSupport;
import com.hazelcast.core.MapStore;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.ms.idea.model.Idea;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author netdevfighter
 */
@Slf4j
public class MongoIdeasStore implements MapStore<String, Idea>, MapLoaderLifecycleSupport {

    private Mongo mongo;
    private DBCollection collection;

    @Override
    public void init(HazelcastInstance hazelcastInstance, Properties properties, String mapName) {

    }

    @Override
    public void destroy() {

    }


    @Override
    public void store(String key, Idea value) {

    }

    @Override
    public void storeAll(Map<String, Idea> map) {

    }

    @Override
    public void delete(String key) {

    }

    @Override
    public void deleteAll(Collection<String> keys) {

    }

    @Override
    public Idea load(String key) {
        return null;
    }

    @Override
    public Map<String, Idea> loadAll(Collection<String> keys) {
        return null;
    }

    @Override
    public Set<String> loadAllKeys() {
        return null;
    }
}
