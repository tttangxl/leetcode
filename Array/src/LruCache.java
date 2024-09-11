import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实现lru缓存  核心思路  双向链表
 * 访问值的时候将当前节点数据放到链表末尾  区数据总从最后一个节点取数据
 */
public class LruCache extends LinkedHashMap<Integer,Integer> {
    private int cap;
    public LruCache(int cap){
        super(cap,0.75f,true);
        this.cap= cap;
    }


    public Integer put(Integer key,Integer value){
        return super.put(key,value);
    }


    public Integer get(Integer key){
        return super.getOrDefault(key,-1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> entry){
        return size()>cap;
    }

    public static void main(String[] args) {
        LruCache cache = new LruCache(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);

        System.out.println(cache.get(1));

    }
}
