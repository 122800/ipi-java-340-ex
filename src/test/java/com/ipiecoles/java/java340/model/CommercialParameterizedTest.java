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
public class CommercialParameterizedTest {
	
	@Parameter(value = 0)
	public Integer performance;
	@Parameter(value = 1)
	public Note expectedNote;
	
	@Parameters(name = "Performance {0} est {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{0, Note.INSUFFISANT},
			{50, Note.INSUFFISANT},
			{100, Note.PASSABLE},
			{150, Note.BIEN},
			{200, Note.TRES_BIEN},
			{null, null},
			{600, null}
		});
	}
	
	@Test
	public void testEquivalenceNote() {
		//Given
		Commercial commercial = new Commercial();
		commercial.setPerformance(performance);
		
		//When
		Note note = commercial.equivalenceNote();
		
		//Then
		Assertions.assertThat(note).isEqualTo(expectedNote);
	}
	
}