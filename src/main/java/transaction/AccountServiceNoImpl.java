package transaction;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceNoImpl {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Account find(long id) {
		Query query = entityManager.createQuery("from Account a where a.id=:id").setParameter("id", id);
		return (Account) query.getSingleResult();
	}
	
	@Transactional
	public void create(Account account) {
		entityManager.persist(account);
	}

}
