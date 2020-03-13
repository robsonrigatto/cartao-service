package br.com.rr.mastertech.cartao;

import br.com.rr.mastertech.cartao.configuration.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
@EnableCircuitBreaker
@EnableHystrix
public class CartoesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartoesServiceApplication.class, args);
	}

}
