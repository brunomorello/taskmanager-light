package br.com.bmo.taskmanagerlight.api.manufacturer;

import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

public class ManufacturerView {

	private String id;
	private String formalName;
	private String displayName;
	private String address;
	private String status;
	
	public ManufacturerView(String id, String formalName, String displayName, String address, String status) {
		this.id = id;
		this.formalName = formalName;
		this.displayName = displayName;
		this.address = address;
		this.status = status;
	}
	
	public ManufacturerView(Manufacturer manufacturer) {
		this.id = manufacturer.getId().toString();
		this.formalName = manufacturer.getFormalName();
		this.displayName = manufacturer.getDisplayName();
		this.address = manufacturer.getAddress();
		this.status = manufacturer.getStatus().toString();
	}
	
	public ManufacturerView() { }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
