package dev.paie.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;

//TODO compléter la configuration
@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	@Autowired
	private GradeService gradeService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		
		Grade nouveauGrade = new Grade("testajoutcode", new BigDecimal("155.50"), new BigDecimal("1.62"));
		
		gradeService.sauvegarder(nouveauGrade);
		
		List<Grade> gradeList = gradeService.lister();
		assertThat(gradeList.get(gradeList.size()-1).getCode(), equalTo("testajoutcode"));
		
		nouveauGrade.setCode("testmodificationcode");
		nouveauGrade.setId(1);
		gradeService.mettreAJour(nouveauGrade);
		List<Grade> listModif = gradeService.lister();
		assertThat(listModif.get(listModif.size()-1).getCode(), equalTo("testmodificationcode"));
		
		List<Grade> gradeListmodif = gradeService.lister();
		assertThat(gradeListmodif.get(gradeListmodif.size()-1).getCode(), equalTo("testmodificationcode"));
		
	}
}