package thread;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDao {
	private Object value;
	private HashMap cache = new HashMap();
	//读写锁 ----->    myisam里面的表锁
	private ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
	public  Object get(Object key){
		rrwl.readLock().lock();
		try{
			value = cache.get(key);
			if(value==null){
				rrwl.readLock().unlock();
				rrwl.writeLock().lock();
				try{
					if(value==null){
					   value = "...";
					   cache.put(key, value);
					}
				}finally{
					rrwl.writeLock().unlock();
					rrwl.readLock().lock();
				}
			}
		}finally{
			rrwl.readLock().unlock();
		}
		return value;
	}

}
