package cache.ehcache;


public interface AccountService {

	public abstract Account findAccountCached(long id);

	public abstract Account findAccountCachedFiveSeconds(long id);

}