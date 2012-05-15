package transaction;


import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionProxyTest {
	
	private static Logger logger = Logger.getLogger(TransactionProxyTest.class);

	
	
	@Test
	public void noTransaction() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction/config-data-access.xml");
		AccountServiceNoImpl accountService = (AccountServiceNoImpl) applicationContext.getBean(AccountServiceNoImpl.class);
		logger.info(accountService.getClass().getName());
		applicationContext.close();		
	}
	
	@Test
	public void transactionCGLIB() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction/config-data-access.xml", "transaction/config-tx.xml");
		AccountServiceNoImpl accountService = (AccountServiceNoImpl) applicationContext.getBean(AccountServiceNoImpl.class);
		String accountServiceClassName = accountService.getClass().getName();
		logger.info(accountServiceClassName);
		Assert.assertTrue(accountServiceClassName.contains("EnhancerByCGLIB"));
		applicationContext.close();
	}
	
	@Test
	public void transactionDynamicProxy() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction/config-data-access.xml", "transaction/config-tx.xml");
		AccountServiceInterface accountService = (AccountServiceInterface) applicationContext.getBean(AccountServiceInterface.class);
		String accountServiceClassName = accountService.getClass().getName();
		logger.info(accountServiceClassName);
		Assert.assertTrue(accountServiceClassName.startsWith("$Proxy"));
		applicationContext.close();
	}
	
	@Test
	public void accountCreationDynamicProxy() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction/config-data-access.xml", "transaction/config-tx.xml");
		AccountServiceInterface accountService = (AccountServiceInterface) applicationContext.getBean(AccountServiceInterface.class);
		Account account = new Account();
		account.setId(10);
		account.setCashBalance(500);
		accountService.create(account);
		applicationContext.close();		
	}

}
