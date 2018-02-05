package dev.paie.service;

import java.time.Year;
import java.util.Map;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {
	
	@PersistenceContext private EntityManager em;
	
	
	@Override
	@Transactional
	public void initialiser() {
		
		try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JeuxDeDonneesConfig.class)) {
						
			Map<String, Cotisation> cotisation = context.getBeansOfType(Cotisation.class);
			Map<String, Grade> grade = context.getBeansOfType(Grade.class);
			Map<String, Entreprise> entreprise = context.getBeansOfType(Entreprise.class);
			Map<String, ProfilRemuneration> profilRemuneration = context.getBeansOfType(ProfilRemuneration.class);

			
			IntStream.rangeClosed(1, 12).forEach(i -> {
				Periode p = new Periode();
				p.setDateDebut(Year.now().atMonth(i).atDay(1));
				p.setDateFin(Year.now().atMonth(i).atEndOfMonth());
				em.persist(p);
			});
			
			cotisation.forEach((k,v)->{
				em.persist(v);
			});
			
			grade.forEach((k,v)->{
				em.persist(v);
			});
			
			entreprise.forEach((k,v)->{
				em.persist(v);
			});
			
			profilRemuneration.forEach((k,v)->{
				em.persist(v);
			});
			
					
		}catch(Exception e) {
			System.out.println("erreur : " + e);
		}
		

	}

}
