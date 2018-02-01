package dev.paie.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;

import static org.assertj.core.api.Assertions.*;

public class JeuxDeDonneesTest {
	private ClassPathXmlApplicationContext context;
	private BulletinSalaire bulletin1;

	@Before
	public void onSetup() {
		context = new ClassPathXmlApplicationContext("jdd-config.xml");
		bulletin1 = context.getBean("bulletin1", BulletinSalaire.class);
	}

	@Test
	public void test_primeExceptionnelle() {

		// Sans assertj
		// assertThat(bulletin1.getPrimeExceptionnelle(), equalTo(new BigDecimal("1000");

		// Avec assertj
		BigDecimal primeExceptionnelle = bulletin1.getPrimeExceptionnelle();
		String primeExceptionnelleResult = "1000";
		assertThat(primeExceptionnelle).isEqualTo(primeExceptionnelleResult);
	}

	@Test
	public void test_employe() {

		// Sans assertj
		// assertThat(bulletin1.getRemunerationEmploye().getMatricule(), equalTo("M01"));

		// Avec assertj
		String matricule = bulletin1.getRemunerationEmploye().getMatricule();
		String matriculeResult = "M01";
		assertThat(matricule).isEqualTo(matriculeResult);
	}

	@Test
	public void test_entreprise() throws Exception {

		// Sans assertj
		/*assertThat(bulletin1.getRemunerationEmploye().getEntreprise().getSiret(), equalTo("80966785000022"));
		  assertThat(bulletin1.getRemunerationEmploye().getEntreprise().getDenomination(), equalTo("Dev Entreprise"));
		  assertThat(bulletin1.getRemunerationEmploye().getEntreprise().getCodeNaf(), equalTo("6202A"));*/
		 

		// Avec assertj
		String siret = bulletin1.getRemunerationEmploye().getEntreprise().getSiret();
		String siretResult = "80966785000022";
		assertThat(siret).isEqualTo(siretResult);

		String denomination = bulletin1.getRemunerationEmploye().getEntreprise().getDenomination();
		String denominationResult = "Dev Entreprise";
		assertThat(denomination).isEqualTo(denominationResult);

		String codeNaf = bulletin1.getRemunerationEmploye().getEntreprise().getCodeNaf();
		String codeNafResult = "6202A";
		assertThat(codeNaf).isEqualTo(codeNafResult);
	}

	@Test
	public void test_cotisationsNonImposables() {
		
		//sans assertj
		/*List<Cotisation> cotisationsNonImposables = bulletin1.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables();
		Stream.of("EP01", "EP02", "EP03", "EP04", "EP05", "EP06", "EP07", "EP12", "EP19", "EP20", "EPR1", "E900",
				"EP28", "EP37")
				.forEach(code -> assertTrue("verification code " + code,
						cotisationsNonImposables.stream().filter(c -> c.getCode().equals(code)).findAny().isPresent()));*/
		
		//avec assertj
		List<Cotisation> cotisationsNonImposables = bulletin1.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		Stream.of("EP01", "EP02", "EP03", "EP04", "EP05", "EP06", "EP07", "EP12", "EP19", "EP20", "EPR1", "E900","EP28", "EP37").forEach(code -> assertThat(
							cotisationsNonImposables.stream()
			                .filter(c -> c.getCode().equals(code)).findAny().isPresent())
			                .isTrue());
	}

	@Test
	public void test_cotisationImposables() {
		
		//sans assertj
		/*List<Cotisation> cotisationsImposables = bulletin1.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsImposables();
		Stream.of("SP01", "SP02").forEach(code -> assertTrue("verification code " + code,
				cotisationsImposables.stream().filter(c -> c.getCode().equals(code)).findAny().isPresent()));*/
		
		//avec assertj
		List<Cotisation> cotisationsImposables = bulletin1.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables();
		Stream.of("SP01", "SP02").forEach(code -> assertThat(
					cotisationsImposables.stream()
	                .filter(c -> c.getCode().equals(code)).findAny().isPresent())
	                .isTrue());
	}

	@Test
	public void test_grade() {

		// Sans assertj
		/*assertThat(bulletin1.getRemunerationEmploye().getGrade().getNbHeuresBase(), equalTo(new BigDecimal("151.67")));
		  assertThat(bulletin1.getRemunerationEmploye().getGrade().getTauxBase(), equalTo(new BigDecimal("11.0984")));*/
		 

		// Avec assertj
		BigDecimal nbrHeureBase = bulletin1.getRemunerationEmploye().getGrade().getNbHeuresBase();
		String nbrHeureBaseResult = "151.67";
		assertThat(nbrHeureBase).isEqualTo(nbrHeureBaseResult);

		BigDecimal tauxBase = bulletin1.getRemunerationEmploye().getGrade().getTauxBase();
		String tauxBaseResult = "11.0984";
		assertThat(tauxBase).isEqualTo(tauxBaseResult);
	}

	@After
	public void onExit() {
		context.close();
	}
}