package raf.si.racunovodstvo.preduzece;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// For testing purpose
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PreduzeceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreduzeceApplication.class, args);
	}

}
