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

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;

//TODO compléter la configuration
@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {

	@Autowired
	private CotisationService cotisationService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		
		Cotisation cotisation = new Cotisation("testajoutcode", "testajoutlibelle", new BigDecimal("1.52"), new BigDecimal("2.56"));
		
		// TODO sauvegarder une nouvelle cotisation
		cotisationService.sauvegarder(cotisation);
		
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister
		List<Cotisation> cotisationList = cotisationService.lister();
		assertThat(cotisationList.get(0).getCode(), equalTo("testajoutcode"));
		
		// TODO modifier une cotisation
		cotisation.setCode("testmodificationcode");
		cotisation.setLibelle("testmodificationlibelle");
		cotisation.setTauxPatronal(new BigDecimal("6.66"));
		cotisation.setTauxSalarial(new BigDecimal("7.77"));
		
		cotisationService.mettreAJour(cotisation);
		List<Cotisation> listModif = cotisationService.lister();
		assertThat(listModif.get(listModif.size()-1).getCode(), equalTo("testmodificationcode"));
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		List<Cotisation> cotisationListmodif = cotisationService.lister();
		assertThat(cotisationListmodif.get(cotisationList.size()-1).getCode(), equalTo("testmodificationcode"));
		
		
	}
}
