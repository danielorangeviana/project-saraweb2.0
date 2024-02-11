package com.br.saraweb20.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.saraweb20.entities.Book;
import com.br.saraweb20.entities.Celebrant;
import com.br.saraweb20.entities.Term;
import com.br.saraweb20.entities.User;
import com.br.saraweb20.entities.enums.ReligiousTitle;
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
	private CelebrantRepository celebrantRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private TermRepository termRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Daniel Orange", "123.456.678-98", "orangedwcov", "123456");
		User u2 = new User(null, "Dryele Ribeiro", "345.123.567-09", "dryelewdar", "654321");
		User u3 = new User(null, "Deivson Correa", "098.765.432-12", "deivsondcc", "1234");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Celebrant c1 = new Celebrant(null, "Jerominho da Silva Santos", ReligiousTitle.CAPELAO);
		Celebrant c2 = new Celebrant(null, "Pedro Alves da Cunha", ReligiousTitle.PADRE);
		
		celebrantRepository.saveAll(Arrays.asList(c1, c2));
		
		Book b1 = new Book(null, 1, 15);
		Book b2 = new Book(null, 1, 16);
		Book b3 = new Book(null, 1, 17);
		
		bookRepository.saveAll(Arrays.asList(b1, b2, b3));
		
		Term t1 = new Term(null, 150);
		Term t2 = new Term(null, 151);
		Term t3 = new Term(null, 152);
		
		termRepository.saveAll(Arrays.asList(t1, t2, t3));
	}
}
