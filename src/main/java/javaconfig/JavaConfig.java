package javaconfig;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
	
	private static Logger logger = Logger.getLogger(JavaConfig.class);

	@Bean
	public AccountService accountService() {
		logger.info("current class is of type: " +this.getClass().getName());
		return new AccountService();
	}
}
