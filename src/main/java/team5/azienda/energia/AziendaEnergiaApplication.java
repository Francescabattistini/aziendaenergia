package team5.azienda.energia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import team5.azienda.energia.servicies.ProvinciaService;

@SpringBootApplication
public class AziendaEnergiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AziendaEnergiaApplication.class, args);
	}

}
