package cache.ehcache;


public interface AccountService {


	public abstract Account findAccountCached(long id);

}