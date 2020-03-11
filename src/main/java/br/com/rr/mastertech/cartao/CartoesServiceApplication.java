package br.com.rr.mastertech.cartao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CartoesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartoesServiceApplication.class, args);
	}

}
