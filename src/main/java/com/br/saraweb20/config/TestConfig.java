package com.br.saraweb20.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.saraweb20.entities.BaptismData;
import com.br.saraweb20.entities.Book;
import com.br.saraweb20.entities.Celebrant;
import com.br.saraweb20.entities.User;
import com.br.saraweb20.entities.enums.ReligiousTitle;
import com.br.saraweb20.entities.enums.UserRoles;
import com.br.saraweb20.repositories.BaptismDataRepository;
import com.br.saraweb20.repositories.BookRepository;
import com.br.saraweb20.repositories.CelebrantRepository;
import com.br.saraweb20.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CelebrantRepository celebrantRepository;
	
	@Autowired
	private BaptismDataRepository baptismDataRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		User u1 = new User(null, "Daniel Orange", "123.456.678-98", "123456", UserRoles.ADMIN);
		User u2 = new User(null, "Dryele Ribeiro", "345.123.567-09", "654321", UserRoles.USER);
		User u3 = new User(null, "Deivson Correa", "098.765.432-12", "1234", UserRoles.USER);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Book b1 = new Book(null, 1, 15);
		Book b2 = new Book(null, 1, 16);
		Book b3 = new Book(null, 1, 17);
		
		bookRepository.saveAll(Arrays.asList(b1, b2, b3));
		
		Celebrant c1 = new Celebrant(null, "Jerominho da Silva Santos", ReligiousTitle.CAPELAO);
		Celebrant c2 = new Celebrant(null, "Pedro Alves da Cunha", ReligiousTitle.PADRE);
		
		celebrantRepository.saveAll(Arrays.asList(c1, c2));
				
		BaptismData bd1 = new BaptismData(null, 102L, "Sofia Alcantara de Orange Viana", LocalDate.parse("23-03-2023", format), "Daniel Orange", "Dryele Alcantara", "Thiago Ribeiro", "Marcela Gomes", LocalDate.parse("21-06-2023", format), b1, c1);
		BaptismData bd2 = new BaptismData(null, 105L, "Mateus Alcantara de Orange Viana", LocalDate.parse("07-06-2023", format), "Daniel Orange", "Dryele Alcantara", "Thiago Ribeiro", "Marcela Gomes", LocalDate.parse("15-08-2023", format), b2, c1);
		BaptismData bd3 = new BaptismData(null, 108L, "Mateus Alcantara de Orange Viana", LocalDate.parse("10-08-2023", format), "Daniel Orange", "Dryele Alcantara", "Thiago Ribeiro", "Marcela Gomes", LocalDate.parse("05-12-2023", format), b3, c2);
		
		baptismDataRepository.saveAll(Arrays.asList(bd1, bd2, bd3));
	}
}
