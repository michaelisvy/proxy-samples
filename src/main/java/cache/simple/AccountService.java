package cache.simple;

import org.springframework.cache.annotation.Cacheable;

public interface AccountService {

	@Cacheable(value = "accounts", key = "#id")
	public abstract Account findAccountCached(long id);

	public abstract Account findAccountNotCached(long id);

}