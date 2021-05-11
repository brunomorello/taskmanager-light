package br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdministrativeArea {

	@JsonProperty(value = "ID")
	private String id;
	
	@JsonProperty(value = "LocalizedName")
	private String LocalizedName;
	
	@JsonProperty(value = "EnglishName")
	private String EnglishName;
	
	@JsonProperty(value = "CountryID")
	private String countryID;
	
	public AdministrativeArea() {	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocalizedName() {
		return LocalizedName;
	}
	public void setLocalizedName(String localizedName) {
		LocalizedName = localizedName;
	}
	public String getEnglishName() {
		return EnglishName;
	}
	public void setEnglishName(String englishName) {
		EnglishName = englishName;
	}
	public String getCountryID() {
		return countryID;
	}
	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}
	@Override
	public String toString() {
		return "AdministrativeArea [id=" + id + ", LocalizedName=" + LocalizedName + ", EnglishName=" + EnglishName
				+ ", countryID=" + countryID + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EnglishName == null) ? 0 : EnglishName.hashCode());
		result = prime * result + ((LocalizedName == null) ? 0 : LocalizedName.hashCode());
		result = prime * result + ((countryID == null) ? 0 : countryID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AdministrativeArea other = (AdministrativeArea) obj;
		if (EnglishName == null) {
			if (other.EnglishName != null)
				return false;
		} else if (!EnglishName.equals(other.EnglishName))
			return false;
		if (LocalizedName == null) {
			if (other.LocalizedName != null)
				return false;
		} else if (!LocalizedName.equals(other.LocalizedName))
			return false;
		if (countryID == null) {
			if (other.countryID != null)
				return false;
		} else if (!countryID.equals(other.countryID))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
