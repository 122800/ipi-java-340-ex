package com.ipiecoles.java.java340.service;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ipiecoles.java.java340.repository.EmployeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeServiceTest {

	@InjectMocks
	private EmployeService employeService;
	
	@Mock
	private EmployeRepository employeRepository;
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindByMatriculeNotFound() {
		//Given
		Mockito.when(employeService.findByMatricule("C12345")).thenReturn(null);
		
		//When
		employeService.findByMatricule("C12345");
		
		//Then exception
		
	}
	
}