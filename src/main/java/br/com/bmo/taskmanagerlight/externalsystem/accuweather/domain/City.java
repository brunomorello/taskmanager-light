package br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

	@JsonProperty(value = "Version")
	private String version;
	
	@JsonProperty(value = "Key")
	private String key;
	
	@JsonProperty(value = "LocalizedName")
	private String localizedName;
	
	@JsonProperty(value = "PrimaryPostalCode")
	private String primaryPostalCode;
	
	@JsonProperty(value = "Country")
	private Country country;
	
	@JsonProperty(value = "AdministrativeArea")
	private AdministrativeArea administrativeArea;
	
	@JsonProperty(value = "GeoPosition")
	private GeoPosition geoPosition;
	
	public City() {	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLocalizedName() {
		return localizedName;
	}
	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}
	public String getPrimaryPostalCode() {
		return primaryPostalCode;
	}
	public void setPrimaryPostalCode(String primaryPostalCode) {
		this.primaryPostalCode = primaryPostalCode;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public AdministrativeArea getAdministrativeArea() {
		return administrativeArea;
	}
	public void setAdministrativeArea(AdministrativeArea administrativeArea) {
		this.administrativeArea = administrativeArea;
	}
	public GeoPosition getGeoPosition() {
		return geoPosition;
	}
	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}
	@Override
	public String toString() {
		return "City [version=" + version + ", key=" + key + ", localizedName=" + localizedName + ", primaryPostalCode="
				+ primaryPostalCode + ", country=" + country + ", administrativeArea=" + administrativeArea
				+ ", geoPosition=" + geoPosition + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((administrativeArea == null) ? 0 : administrativeArea.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((geoPosition == null) ? 0 : geoPosition.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((localizedName == null) ? 0 : localizedName.hashCode());
		result = prime * result + ((primaryPostalCode == null) ? 0 : primaryPostalCode.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		City other = (City) obj;
		if (administrativeArea == null) {
			if (other.administrativeArea != null)
				return false;
		} else if (!administrativeArea.equals(other.administrativeArea))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (geoPosition == null) {
			if (other.geoPosition != null)
				return false;
		} else if (!geoPosition.equals(other.geoPosition))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (localizedName == null) {
			if (other.localizedName != null)
				return false;
		} else if (!localizedName.equals(other.localizedName))
			return false;
		if (primaryPostalCode == null) {
			if (other.primaryPostalCode != null)
				return false;
		} else if (!primaryPostalCode.equals(other.primaryPostalCode))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}
