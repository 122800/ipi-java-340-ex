package com.ipiecoles.java.java340.model;

import java.util.Arrays;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ManagerTest {
	
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