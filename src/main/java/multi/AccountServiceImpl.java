package multi;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountServiceImpl  implements AccountService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Cacheable(value="accounts", key="#id")
	public Account findAccountCached(long id) {
		Query query = entityManager.createQuery("from Account a where a.id=:id").setParameter("id", id);
		return (Account) query.getSingleResult();
	}
	
	@Override
	@Transactional
	public void create(Account account) {
		entityManager.persist(account);
	}

}
