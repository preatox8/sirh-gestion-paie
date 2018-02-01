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

import dev.paie.config.DataSourceMySQLConfig;
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
		
		// TODO sauvegarder un nouveau grade
		//gradeService.sauvegarder(nouveauGrade);
		
		// TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		List<Grade> gradeList = gradeService.lister();
		assertThat(gradeList.get(0).getCode(), equalTo("testajoutcode"));
		
		// TODO modifier un grade
		nouveauGrade.setCode("testmodificationcode");
		gradeService.sauvegarder(nouveauGrade);
		List<Grade> listModif = gradeService.lister();
		assertThat(listModif.get(listModif.size()-1).getCode(), equalTo("testmodificationcode"));
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		List<Grade> gradeListmodif = gradeService.lister();
		assertThat(gradeListmodif.get(gradeList.size()-1).getCode(), equalTo("testmodificationcode"));
		
	}
}