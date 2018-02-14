package com.ipiecoles.java.java340.model;

import java.util.Arrays;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.ipiecoles.java.java340.exception.EmployeException;

@RunWith(Parameterized.class)
public class CommercialAnneeParameterizedTest {
	
	public static LocalDate now = LocalDate.now();
	public static LocalDate yesterday = now.minusDays(1);
	public static LocalDate tomorrow = now.plusDays(1);
	
	@Parameter(0) public LocalDate dateEmbaucheInput;
	@Parameter(1) public LocalDate dateEmbaucheExpected;
	
	@Parameters(name="input dateEmbauche {0} returns {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{now, 			now			},
			{yesterday, 	yesterday	},
			{tomorrow, 		null		}
		});
	}
	
	@Test
	public void testSetDateEmbauche() {
		//Given
		Employe commercial = new Commercial();
				
		//When
		try {
			commercial.setDateEmbauche(dateEmbaucheInput);
			
			//Then
		} catch (EmployeException e) {
			Assertions.assertThat(e.getMessage()).isEqualTo("La date d'embauche ne peut être postérieure à la date courante");
		} finally {
			Assertions.assertThat(commercial.getDateEmbauche()).isEqualTo(dateEmbaucheExpected);
		}
		
	}	
}