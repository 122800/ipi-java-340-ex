package com.ipiecoles.java.java340.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class CommercialTest {
	
	@Test
	public void testGetPrimeAnuelleWithCANull() {
		//Given
		Commercial commercial = new Commercial();
		commercial.setCaAnnuel(null);
		
		//When
		Double prime = commercial.getPrimeAnnuelle();
		
		//Then
		Assertions.assertThat(prime).isEqualTo(500d);
	}

}