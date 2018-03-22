package com.iacteam2.webshop;

import com.iacteam2.generated.Address;
import com.iacteam2.webshop.soap.PaymentsClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.net.MalformedURLException;

@SpringBootApplication
@EnableJpaAuditing
public class WebshopApplication {

	public static void main(String[] args) throws MalformedURLException{
		SpringApplication.run(WebshopApplication.class, args);

		// All that is needed to make request
		PaymentsClient pc = new PaymentsClient();
		Address ad = new Address();
		int code = pc.makePayment("Timo", ad, 10.1F);
	}
}
