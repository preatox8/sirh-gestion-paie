package dev.paie.web.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired EntrepriseRepository entrepriseRepository;
	@Autowired ProfilRemunerationRepository profilRemunerationRepository;
	@Autowired GradeRepository gradeRepository;
	@Autowired RemunerationEmployeRepository remunerationEmployeRepository;

		
	@RequestMapping(method = RequestMethod.GET,path = "/creer")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		
		List<Entreprise> listEntreprise = entrepriseRepository.findAll();
		List<ProfilRemuneration> listProfilRemuneartion = profilRemunerationRepository.findAll();
		List<Grade> listGrade = gradeRepository.findAll();

		mv.setViewName("employes/creerEmploye");
		
		mv.addObject("entreprise", listEntreprise);
		mv.addObject("profil", listProfilRemuneartion);
		mv.addObject("grade", listGrade);

		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public String submit(@RequestParam(value="matricule") String matricule, @RequestParam(value="entreprise") Integer entreprise, @RequestParam(value="profil") Integer profil, @RequestParam(value="grade") Integer grade) {
		
		Entreprise entreprise1 = new Entreprise();
		entreprise1.setId(entreprise);
		
		ProfilRemuneration profil1 = new ProfilRemuneration();
		profil1.setId(profil);
		
		Grade grade1 = new Grade();
		grade1.setId(grade);
		
		ZonedDateTime dateheurecrea = ZonedDateTime.now();
		String dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss").format(dateheurecrea);
		
		RemunerationEmploye employe = new RemunerationEmploye();
		employe.setMatricule(matricule);
		employe.setEntreprise(entreprise1);
		employe.setProfilRemuneration(profil1);
		employe.setGrade(grade1);
		employe.setDateHeureCreation(dateformat);
		
		remunerationEmployeRepository.save(employe);
		
		return "redirect:/mvc/employes/creer";
	}
	
	@RequestMapping(method = RequestMethod.GET,path = "/lister")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		List<RemunerationEmploye> listEmploye = remunerationEmployeRepository.findAll();
		mv.setViewName("employes/listerEmploye");
		mv.addObject("listemploye", listEmploye);
		return mv;
	}
	
}
