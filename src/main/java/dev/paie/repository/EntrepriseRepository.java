package dev.paie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {
	
	List<Entreprise> findAll();
}
