package dev.paie.web.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {
	
	@Autowired PeriodeRepository periodeRepository;
	@Autowired RemunerationEmployeRepository remunerationEmployeRepository;
	@Autowired BulletinSalaireRepository bulletinSalaireRepository;
	@Autowired CalculerRemunerationService calculSimple;

	
	@RequestMapping(method = RequestMethod.GET,path = "/creer")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		
		List<Periode> listPeriode = periodeRepository.findAll();
		List<RemunerationEmploye> listRemunerationEmploye = remunerationEmployeRepository.findAll();
		
		mv.setViewName("bulletins/creerBulletin");
		
		mv.addObject("periode", listPeriode);
		mv.addObject("employe", listRemunerationEmploye);


		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/creer")
	@Secured({"ROLE_ADMINISTRATEUR"})
	public String submit(@RequestParam(value="periode") Integer periode, @RequestParam(value="matricule") Integer employe, @RequestParam(value="prime") String prime) {
		
		Periode periode1 = new Periode();
		periode1.setId(periode);
		
		RemunerationEmploye employe1 = new RemunerationEmploye();
		employe1.setId(employe);
		
		BigDecimal prime1 = new BigDecimal(prime);
		
		BulletinSalaire bulletin = new BulletinSalaire();
		bulletin.setPeriode(periode1);
		bulletin.setPrimeExceptionnelle(prime1);
		bulletin.setRemunerationEmploye(employe1);
		
		bulletinSalaireRepository.save(bulletin);
		
		return "redirect:/mvc/bulletins/creer";
	}
	
	@RequestMapping(method = RequestMethod.GET,path = "/lister")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView listerBulletin() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("bulletins/listerBulletin");
		mv.addObject("listcalcul", calculSimple.calculerTousLesBulletins());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET,path = "/visualiser")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	@Transactional
	public ModelAndView visualiserBulletin(@RequestParam Integer id) {
		ModelAndView mv = new ModelAndView();
		
		BulletinSalaire bull = bulletinSalaireRepository.findOne(id);
		mv.setViewName("bulletins/visualiserBulletin");
		
		mv.addObject("bull", bull);
		mv.addObject("calcul", calculSimple.calculer(bull));
		return mv;
	}
}
