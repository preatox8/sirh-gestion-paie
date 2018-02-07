package dev.paie.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired private PaieUtils paieUtils;
	@Autowired private BulletinSalaireRepository bulletinSalaireRepository;
	
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		ResultatCalculRemuneration result = new ResultatCalculRemuneration();
		
		
		//SALAIRE_BASE = GRADE.NB_HEURES_BASE * GRADE.TAUX_BASE
		BigDecimal salaireBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase().multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase());
		
		//SALAIRE_BRUT = SALAIRE_BASE + PRIME_EXCEPTIONNELLE
		BigDecimal salaireBrut = salaireBase.add(bulletin.getPrimeExceptionnelle());
		
		String salaireBrutString = paieUtils.formaterBigDecimal(salaireBrut);
		result.setSalaireBrut(salaireBrutString);
		
		//TOTAL_RETENUE_SALARIALE = SOMME(COTISATION_NON_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		BigDecimal totalRetenuSalariale = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().stream()
                .filter(c -> c.getTauxSalarial()!=null)
                .map(c -> c.getTauxSalarial().multiply(new BigDecimal(result.getSalaireBrut())))
                .reduce((a,b) -> a.add(b)).get();
		
		//TOTAL_COTISATIONS_PATRONALES = SOMME(COTISATION_NON_IMPOSABLE.TAUX_PATRONAL*SALAIRE_BRUT)
		BigDecimal totalCotisationsPatronales = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().stream()
				.filter(c ->c.getTauxPatronal()!=null)
				.map(c -> c.getTauxPatronal().multiply(new BigDecimal(result. getSalaireBrut())))
				.reduce((a, b) -> a.add(b)).get();
		
		//NET_IMPOSABLE = SALAIRE_BRUT - TOTAL_RETENUE_SALARIALE
		BigDecimal netImposable = salaireBrut.subtract(totalRetenuSalariale);
		
		//NET_A_PAYER = NET_IMPOSABLE - SOMME(COTISATION_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		BigDecimal netAPayer = netImposable.subtract(
				bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables().stream()
	                .filter(c -> c.getTauxSalarial()!=null)
	                .map(c -> c.getTauxSalarial().multiply(new BigDecimal(result.getSalaireBrut())))
	                .reduce((a,b) -> a.add(b)).get());

		
		String netImposableString = paieUtils.formaterBigDecimal(netImposable);
		result.setNetImposable(netImposableString);
		
		String totalCotisationsPatronalesString = paieUtils.formaterBigDecimal(totalCotisationsPatronales);
		result.setTotalCotisationsPatronales(totalCotisationsPatronalesString);
		
		String totalRetenuSalarialeString = paieUtils.formaterBigDecimal(totalRetenuSalariale);
		result.setTotalRetenueSalarial(totalRetenuSalarialeString);
		
		
		
		String salaireBaseString = paieUtils.formaterBigDecimal(salaireBase);
		result.setSalaireDeBase(salaireBaseString);
		
		String netAPayerString = paieUtils.formaterBigDecimal(netAPayer);
		result.setNetAPayer(netAPayerString);
		
		return result;
	}

	@Override
	@Transactional
	public Map<BulletinSalaire, ResultatCalculRemuneration> calculerTousLesBulletins() {
		
		Map<BulletinSalaire, ResultatCalculRemuneration> listcalcul = new HashMap<>();
		
		List<BulletinSalaire> listbulletin = bulletinSalaireRepository.findAll();
		
		for(BulletinSalaire i: listbulletin ) {
			listcalcul.put(i, this.calculer(i));
		}
		return listcalcul;
	}

}
