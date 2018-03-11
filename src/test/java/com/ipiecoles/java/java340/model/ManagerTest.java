package com.ipiecoles.java.java340.model;

import java.util.Arrays;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class ManagerTest {
	
	@RunWith(Parameterized.class)
	public static class testSetSalaireParameterized {
	
		@Parameter(0) public Double salaire;
		@Parameter(1) public Integer tailleEquipe;
		@Parameter(2) public Double expectedSalaire;
		
		@Parameters(name="Un salaire de {0} avec une équipe de {1} devrait être {2}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{2100d, 0, 2730d},
				{2000d, 5, 3600d}
			});
		}
		
		@Test
		public void testSetSalaire() {
			//Given
			Manager manager = new Manager();
			
			for(int i=0; i<tailleEquipe; i++) {
				manager.ajoutTechnicienEquipe(
					new Technicien("technicien"+i, null, null, null, null, null)
				);
			}
			
			manager.setSalaire(salaire);
			
			//When
			Double result = manager.getSalaire();
			
			//Then
			Assertions.assertThat(result).isEqualTo(expectedSalaire);
		}
	}

	public static class testAugmenterSalaireManual {
		
		private Manager manager;
		private Double initialSalaire = 1000D;
		
		@Before public void init() {
			manager = new Manager();
			manager.setSalaire(initialSalaire);
		}
		
		@Test public void testAugmenterSalaire_zero() {
			//Given
			manager.augmenterSalaire(0D);
			
			//When
			Double newSalaire = manager.getSalaire();
			
			//Then
			Assertions.assertThat(newSalaire).isEqualTo(1300D);
		}
		
		@Test public void testAugmenterSalaire_double() {
			//Given
			manager.augmenterSalaire(1D);
			
			//When
			Double newSalaire = manager.getSalaire();
			
			//Then
			Assertions.assertThat(newSalaire).isEqualTo(2600D);
		}
		
		@Test public void testAugmenterSalaire_byHalf() {
			//Given
			manager.augmenterSalaire(0.5D);
			
			//When
			Double newSalaire = manager.getSalaire();
			
			//Then
			Assertions.assertThat(newSalaire).isEqualTo(1950D);
		}
		
		@Test public void testAugmenterSalaire_byNull() {
			//Given
			manager.augmenterSalaire(null);
			
			//When
			Double newSalaire = manager.getSalaire();
			
			//Then
			Assertions.assertThat(newSalaire).isEqualTo(initialSalaire);
			// It would maybe be better to expect an EmployeException? The manager should complain.
		}
		
		@Test public void testAugmenterSalaire_byNegativeAmount() {
			//Given
			manager.augmenterSalaire(-0.5D);
			
			//When
			Double newSalaire = manager.getSalaire();
			
			//Then
			Assertions.assertThat(newSalaire).isEqualTo(initialSalaire);
			// We expect to rather use a semantically named method "reduireSalaire".
			// Probably should expect an EmployeException.
		}
	}
	
	public static class testGetPrimeAnnuelleManual {
		
		// the year is variable and will otherwise causes tests to fail next year
		public Double yearlyCoefficient = LocalDate.now().getYear() * 0.5;
		
		@Test public void testGetPrimeAnnuelleTailleEquipe_0() {
			//Given
			Manager manager = new Manager();
			
			//When
			Double primeAnnuelle = manager.getPrimeAnnuelle();
			
			//Then
			Assertions.assertThat(primeAnnuelle).isEqualTo(yearlyCoefficient + 0);
		}
		@Test public void testGetPrimeAnnuelleTailleEquipe_1() {
			//Given
			Manager manager = new Manager();
			manager.ajoutTechnicienEquipe(new Technicien("Smith", "John", null, null, null, null));
			
			//When
			Double primeAnnuelle = manager.getPrimeAnnuelle();
			
			//Then
			Assertions.assertThat(primeAnnuelle).isEqualTo(yearlyCoefficient + 250d);
		}
		@Test public void testGetPrimeAnnuelleTailleEquipe_2() {
			//Given
			Manager manager = new Manager();
			manager.ajoutTechnicienEquipe(new Technicien("Smith", "John", null, null, null, null));
			manager.ajoutTechnicienEquipe(new Technicien("Smith", "Sally", null, null, null, null));
			
			//When
			Double primeAnnuelle = manager.getPrimeAnnuelle();
			
			//Then
			Assertions.assertThat(primeAnnuelle).isEqualTo(yearlyCoefficient + 500);
		}
	}
	
}