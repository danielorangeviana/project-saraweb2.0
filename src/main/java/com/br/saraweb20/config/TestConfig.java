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
import com.br.saraweb20.entities.Term;
import com.br.saraweb20.entities.User;
import com.br.saraweb20.entities.enums.ReligiousTitle;
import com.br.saraweb20.repositories.BaptismDataRepository;
import com.br.saraweb20.repositories.BookRepository;
import com.br.saraweb20.repositories.CelebrantRepository;
import com.br.saraweb20.repositories.TermRepository;
import com.br.saraweb20.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private CelebrantRepository celebrantRepository;
	
	@Autowired
	private BaptismDataRepository baptismDataRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		User u1 = new User(null, "Daniel Orange", "123.456.678-98", "orangedwcov", "123456");
		User u2 = new User(null, "Dryele Ribeiro", "345.123.567-09", "dryelewdar", "654321");
		User u3 = new User(null, "Deivson Correa", "098.765.432-12", "deivsondcc", "1234");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Book b1 = new Book(null, 1, 15);
		Book b2 = new Book(null, 1, 16);
		Book b3 = new Book(null, 1, 17);
		
		bookRepository.saveAll(Arrays.asList(b1, b2, b3));
		
		Term t1 = new Term(null, 150, b1);
		Term t2 = new Term(null, 151, b1);
		Term t3 = new Term(null, 152, b2);
		Term t4 = new Term(null, 153, b3);
		
		termRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
		
		Celebrant c1 = new Celebrant(null, "Jerominho da Silva Santos", ReligiousTitle.CAPELAO);
		Celebrant c2 = new Celebrant(null, "Pedro Alves da Cunha", ReligiousTitle.PADRE);
		
		celebrantRepository.saveAll(Arrays.asList(c1, c2));
				
		BaptismData bd1 = new BaptismData(null, "Sofia Alcantara de Orange Viana", LocalDate.parse("23-03-2023", format), "Daniel Orange", "Dryele Alcantara", "Thiago Ribeiro", "Marcela Gomes", LocalDate.parse("21-06-2023", format));
		BaptismData bd2 = new BaptismData(null, "Mateus Alcantara de Orange Viana", LocalDate.parse("07-06-2023", format), "Daniel Orange", "Dryele Alcantara", "Thiago Ribeiro", "Marcela Gomes", LocalDate.parse("15-08-2023", format));
		BaptismData bd3 = new BaptismData(null, "Mateus Alcantara de Orange Viana", LocalDate.parse("10-08-2023", format), "Daniel Orange", "Dryele Alcantara", "Thiago Ribeiro", "Marcela Gomes", LocalDate.parse("05-12-2023", format));
		
		baptismDataRepository.saveAll(Arrays.asList(bd1, bd2, bd3));
	}
}
