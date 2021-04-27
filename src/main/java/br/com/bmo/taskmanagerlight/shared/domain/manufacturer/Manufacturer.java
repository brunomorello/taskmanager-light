package br.com.bmo.taskmanagerlight.shared.domain.manufacturer;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bmo.taskmanagerlight.api.manufacturer.ManufacturerStatus;

@Entity
@Table(name = "manufacturers")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((formalName == null) ? 0 : formalName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manufacturer other = (Manufacturer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (formalName == null) {
			if (other.formalName != null)
				return false;
		} else if (!formalName.equals(other.formalName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
}
