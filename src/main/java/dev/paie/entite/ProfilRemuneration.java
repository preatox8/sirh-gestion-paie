package dev.paie.entite;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ProfilRemuneration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;

	@ManyToMany
	@JoinTable(name = "PRO_CO_NON_IMPOSABLE",
		joinColumns = @JoinColumn(name = "ID_PRO", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "ID_CO", referencedColumnName = "id")
	)
	private List<Cotisation> cotisationsNonImposables;

	@ManyToMany
	@JoinTable(name = "PRO_CO_IMPOSABLE",
		joinColumns = @JoinColumn(name = "ID_PRO", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "ID_CO", referencedColumnName = "id")
	)
	private List<Cotisation> cotisationsImposables;

	@ManyToMany
	@JoinTable(name = "PRO_AV",
		joinColumns = @JoinColumn(name = "ID_PRO", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "ID_AV", referencedColumnName = "id")
	)
	private List<Avantage> avantages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Cotisation> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<Cotisation> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}

	public List<Cotisation> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<Cotisation> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

	public ProfilRemuneration() {
		super();
	}

}
