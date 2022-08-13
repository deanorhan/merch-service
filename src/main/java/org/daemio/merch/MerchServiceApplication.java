package org.daemio.merch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MerchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerchServiceApplication.class, args);
	}

}
