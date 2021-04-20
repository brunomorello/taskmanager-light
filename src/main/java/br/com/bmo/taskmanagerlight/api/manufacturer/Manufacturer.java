package br.com.bmo.taskmanagerlight.api.manufacturer;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String formalName;
	private String displayName;
	private String address;
	@Enumerated(EnumType.STRING)
	private ManufacturerStatus status = ManufacturerStatus.ACTIVE;
	
	public Manufacturer(Long id, String formalName, String displayName, String address) {
		this.id = id;
		this.formalName = formalName;
		this.displayName = displayName;
		this.address = address;
	}
	
	public Manufacturer(String formalName, String displayName, String address) {
		this.formalName = formalName;
		this.displayName = displayName;
		this.address = address;
	}
	
	public Manufacturer(String displayName) {
		this.displayName = displayName;
	}

	public Manufacturer() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormalName() {
		return formalName;
	}

	public void setFormalName(String formalName) {
		this.formalName = formalName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ManufacturerStatus getStatus() {
		return status;
	}

	public void setStatus(ManufacturerStatus status) {
		this.status = status;
	}
	
}
