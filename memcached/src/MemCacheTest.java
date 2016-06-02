import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemCacheTest {
    private static MemCachedClient mcc = new MemCachedClient();

    static {
        SockIOPool pool = SockIOPool.getInstance();
        String[] servers = {"localhost:11211", "localhost:11221"};
        Integer[] weights = {1, 1};
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
        save("age", "1");
        save("sex", "man");
        save("address", "china");
        System.out.println(get("name"));
        System.out.println(get("age"));
        System.out.println(get("sex"));
        System.out.println(get("address"));
    }
}
