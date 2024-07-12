package br.com.luizalabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LabslogisticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabslogisticaApplication.class, args);
	}

}
