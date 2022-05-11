package com.bustracking.backend;

import com.bustracking.backend.service.FirebaseInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		FirebaseInitializer fb = new FirebaseInitializer();
		try {
			fb.initDB();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
