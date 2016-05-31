import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemCacheTest {
    protected static MemCachedClient mcc = new MemCachedClient();

    static {
        SockIOPool pool = SockIOPool.getInstance();
        String[] servers = {"192.168.0.4:11211"};
        Integer[] weights = {1};
        pool.setServers(servers);
        pool.setWeights(weights);
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setSocketConnectTO(0);
        pool.initialize();
    }

    public static void save(String key, Object object) {
        mcc.set(key, object);
    }

    public static Object get(String key) {
        return mcc.get(key);
    }

    public static void main(String[] args) {
        save("name", "hello");
        System.out.println(get("name"));
    }
}
