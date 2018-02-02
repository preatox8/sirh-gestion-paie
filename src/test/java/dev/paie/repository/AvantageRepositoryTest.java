package dev.paie.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		Avantage nouveauAvantage = new Avantage("testajoutcode", "testajoutnom", new BigDecimal("100"));
		
		// TODO sauvegarder un nouvel avantage
		avantageRepository.save(nouveauAvantage);

		// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		avantageRepository.findOne(nouveauAvantage.getId());
		assertThat(nouveauAvantage.getCode(), equalTo("testajoutcode"));

		
		// TODO modifier un avantage
		avantageRepository.findOne(nouveauAvantage.getId());
		nouveauAvantage.setCode("modifcode");
		nouveauAvantage.setNom("modifnom");
		nouveauAvantage.setMontant(new BigDecimal("300"));
		avantageRepository.save(nouveauAvantage);
		assertThat(nouveauAvantage.getCode(), equalTo("modifcode"));

		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
		avantageRepository.findOne(nouveauAvantage.getId());
		assertThat(nouveauAvantage.getCode(), equalTo("modifcode"));
		
		List<Avantage> findByCode = avantageRepository.findByCode("modifcode");
		assertThat(nouveauAvantage.getCode(), equalTo("modifcode"));

}}
