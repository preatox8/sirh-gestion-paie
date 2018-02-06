package dev.paie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Periode;


public interface PeriodeRepository extends JpaRepository<Periode, String>{

	List<Periode> findAll();
}
