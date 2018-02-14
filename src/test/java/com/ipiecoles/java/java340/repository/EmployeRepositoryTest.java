package com.ipiecoles.java.java340.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ipiecoles.java.java340.model.Commercial;
import com.ipiecoles.java.java340.model.Employe;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeRepositoryTest {
	@Autowired
	EmployeRepository employeRepository;
	
	Commercial emp1, emp2, emp3;
	
	@Before
	public void setUp() {
		emp1 = employeRepository.save(new Commercial("MyName", "Prenom", "matricule", new LocalDate(),  14000d, 0d, 0));
		emp2 = employeRepository.save(new Commercial("MyName2", "Prenom2", "matricule2", new LocalDate(),  14000d, 0d, 0));
		emp3 = employeRepository.save(new Commercial("MyName3", "Prenom3", "matricule3", new LocalDate(),  14000d, 0d, 0));
	}
	
	@Test
	public void testFindByNomOrPrenomAllIgnoreCase() {
		//Given
		
		//When
		List<Employe> results = employeRepository.findByNomOrPrenomAllIgnoreCase("MyName");
		
		//Then
		Assertions.assertThat(results).hasSize(1);
		Assertions.assertThat(results).contains(emp1);
	}
	
}
