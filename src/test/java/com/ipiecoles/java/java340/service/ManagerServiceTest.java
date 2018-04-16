package com.ipiecoles.java.java340.service;

import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ipiecoles.java.java340.model.Manager;
import com.ipiecoles.java.java340.model.Technicien;
import com.ipiecoles.java.java340.repository.ManagerRepository;
import com.ipiecoles.java.java340.repository.TechnicienRepository;

@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceTest {

	@InjectMocks
	public ManagerService managerService;
	
	@Mock public ManagerRepository managerRepository;
	@Mock public TechnicienRepository technicienRepository;
	
	@Test public void testAddTechnicien() {
		//Given
		final String MATRICULE = "T12345";
		final Long ID_MANAGER = 1L;
		Manager manager = new Manager("Dupond", "Jacques", "M12345", new LocalDate(), 2500d, new HashSet<>());
		Technicien technicien = new Technicien("Durand", "Jean", MATRICULE, new LocalDate(), 1500d, 3);
		
		Mockito.when(managerRepository.findOneWithEquipeById(ID_MANAGER)).thenReturn(manager);
		Mockito.when(technicienRepository.findByMatricule(MATRICULE)).thenReturn(technicien);
		Mockito.when(technicienRepository.save(Mockito.any(Technicien.class))).then(AdditionalAnswers.returnsFirstArg());
		Mockito.when(managerRepository.save(Mockito.any(Manager.class))).then(AdditionalAnswers.returnsFirstArg());
		
		//When
		managerService.addTechniciens(ID_MANAGER, MATRICULE);
		
		//Then
		Mockito.verify(managerRepository, Mockito.times(1)).findOneWithEquipeById(ID_MANAGER);
		Mockito.verify(technicienRepository, Mockito.times(1)).findByMatricule(MATRICULE);
		
		Assertions.assertThat(technicien.getManager()).isEqualTo(manager);
		
		Assertions.assertThat(manager.getEquipe()).hasSize(1);
		Assertions.assertThat(manager.getEquipe()).contains(technicien);
		
		ArgumentCaptor<Manager> managerCaptor = ArgumentCaptor.forClass(Manager.class);
		Mockito.verify(managerRepository).save(managerCaptor.capture());
		Assertions.assertThat(managerCaptor.getValue().getEquipe().toArray()[0]).isEqualTo(technicien);
	}
}