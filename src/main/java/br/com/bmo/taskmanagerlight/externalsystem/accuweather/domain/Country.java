package br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

	@JsonProperty(value = "ID")
	private String id;
	
	@JsonProperty(value = "LocalizedName")
	private String localizedName;
	
	@JsonProperty(value = "EnglishName")
	private String englishName;
	
	public Country() {	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocalizedName() {
		return localizedName;
	}
	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((englishName == null) ? 0 : englishName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localizedName == null) ? 0 : localizedName.hashCode());
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
		Country other = (Country) obj;
		if (englishName == null) {
			if (other.englishName != null)
				return false;
		} else if (!englishName.equals(other.englishName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localizedName == null) {
			if (other.localizedName != null)
				return false;
		} else if (!localizedName.equals(other.localizedName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Country [id=" + id + ", localizedName=" + localizedName + ", englishName=" + englishName + "]";
	}
	
}