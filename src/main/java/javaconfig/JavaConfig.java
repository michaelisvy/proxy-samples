package javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

	@Bean
	public AccountService accountService() {
		System.out.println(this.getClass().getName());
		return new AccountService();
	}

}
