package cache.ehcache;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService {
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	@Cacheable(value="accounts", key="#id")
	public Account findAccountCached(long id) {
		Query query = entityManager.createQuery("from Account a where a.id=:id").setParameter("id", id);
		return (Account) query.getSingleResult();
	}
	
	@Override
	@Cacheable(value="accounts", key="#id")
	public Account findAccountCachedFiveSeconds(long id) {
		Query query = entityManager.createQuery("from Account a where a.id=:id").setParameter("id", id);
		return (Account) query.getSingleResult();
	}
	

	
	
	

}
