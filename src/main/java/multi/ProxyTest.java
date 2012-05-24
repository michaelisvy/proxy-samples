package multi;


import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests what happens when combining Caching and Transactions together
 * @author misvy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"config-data-access.xml", "config-cache.xml", "config-tx.xml"})
public class ProxyTest {
	
	private static Logger logger = Logger.getLogger(ProxyTest.class);

	@Autowired
	private AccountService accountService;

	
	
	@Test
	public void findAccountCached() {
		logger.info("class name: " + accountService.getClass().getName());
		Account account1 = accountService.findAccountCached(1);
		Account account2 = accountService.findAccountCached(1);
		// since the bean is cached, we expect to have the same address
		Assert.assertEquals(account1.toString(), account2.toString());
	}

	
	@Test
	public void transactionAccountCreation() {
		Account account = new Account();
		account.setId(10);
		account.setCashBalance(500);
		accountService.create(account);
	}

}
