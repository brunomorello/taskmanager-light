package br.com.bmo.taskmanagerlight.api.manufacturer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

public class ManufacturerForm {

	private String formalName;
	@NotBlank @NotNull
	private String displayName;
	private String address;
	
	public ManufacturerForm(String formalName, @NotBlank @NotNull String displayName, String address) {
		this.formalName = formalName;
		this.displayName = displayName;
		this.address = address;
	}
	
	public ManufacturerForm() {}

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

	public Manufacturer parse() {
		return new Manufacturer(formalName, displayName, address);
	}
	
}
