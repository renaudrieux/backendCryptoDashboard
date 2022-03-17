package com.example.demo;

import com.example.demo.coinGeckoApi.CoinGeckoApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Bean
	public CoinGeckoApiClient getCoinGeckoApiClient(){
		return new CoinGeckoApiClient();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
