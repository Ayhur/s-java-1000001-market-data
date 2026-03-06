package com.inditex.marketdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Market Data service.
 * This class initializes and starts the Spring Boot application.
 */
@SpringBootApplication
public class MarketDataApplication {

	/**
	 * Main entry point for the application.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MarketDataApplication.class, args);
	}

}
