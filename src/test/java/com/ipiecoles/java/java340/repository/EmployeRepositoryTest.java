package com.ipiecoles.java.java340.repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.JUnit4;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ipiecoles.java.java340.model.Commercial;
import com.ipiecoles.java.java340.model.Employe;

@RunWith(Enclosed.class)
public class EmployeRepositoryTest {
	
	public static Commercial emp1, emp2, emp3, emp4, emp5, empMinus, empAvg, empPlus;
	
	@RunWith(Parameterized.class)
	public static class testFindEmployesPlusRichesParameterized {

		public static LocalDate curDate = new LocalDate();
		
		@Parameter(0) static public List<Employe> employesInDb;
		@Parameter(1) static public List<Employe> expectedList;
		@Parameter(2) static public int expectedSize;
		
		@Parameters(name="This batch of employes in DB returns {2}")
		public static Collection<Object[]> data() {
			
			List<Employe> initialList1 = Arrays.asList(
				emp1 = new Commercial("MyName", "Prenom", "matricule", curDate,  				500d, 0d, 0),
				emp2 = new Commercial("MyName2", "Prenom2", "matricule2", curDate,	  			1000d, 0d, 0),
				emp3 = new Commercial("MyName3", "Prenom3", "matricule3", curDate,  			2000d, 0d, 0),
				
				empMinus = new Commercial("MyNameMinus", "PrenomMinus", "matricule3", curDate,	2099d, 0d, 0),
				empAvg = new Commercial("MyNameAvg", "PrenomAvg", "matricule3", curDate,		2100d, 0d, 0),
				empPlus = new Commercial("MyNamePlus", "PrenomPlus", "matricule3", curDate,		2101d, 0d, 0),
				
				emp4 = new Commercial("MyName4", "Prenom4", "matricule4", curDate,				3000d, 0d, 0),
				emp5 = new Commercial("MyName5", "Prenom5", "matricule5", curDate,				4000d, 0d, 0)
				// average salary 2100
			);
			List<Employe> expectedList1 = Arrays.asList(emp4, emp5, empPlus);
			
			return Arrays.asList(new Object[][] {
				{initialList1, expectedList1, 5},
				//{initialList2, expectedList2, 5},
				//{initialList3, expectedList3, 5}
			});
		}
		
		@Test public void testSubRunner() {
			JUnitCore jUnitCore = new JUnitCore();
			Request r = Request.aClass(testSubClassWithSpring.class);
			Result result = jUnitCore.run(r);
			
			System.out.printf("%n%n%n%n+ + + + + test ran: %s, Failed: %s%n",
		                result.getRunCount(), result.getFailureCount());
			
			for(Failure failure : result.getFailures()) {
				System.out.println("+ + + " + failure.toString());
			}
			System.out.printf("%n%n%n%n");
			//JUnitCore.runClasses(SubClassWithSpring.class);
		}
		
		@DataJpaTest
		@RunWith(SpringRunner.class)
		public static class testSubClassWithSpring {
		
			@Autowired public EmployeRepository employeRepository;
		
			@Before public void setup() {
				
				for(Employe e : employesInDb) {
					employeRepository.save(e);
				}
			}
			
			@Test public void testFindEmployesPlusRiches() {
				//Given
				//When
				List<Employe> richest = employeRepository.findEmployePlusRiches();
				
				//Then
				for(Employe e : expectedList) {
					Assertions.assertThat(richest).contains(e);
				}
				
				Assertions.assertThat(richest).hasSize(expectedSize);
				Assertions.assertThat(1).isEqualTo(0);
			}
		}
	}
}
