package br.com.bmo.taskmanagerlight.externalsystem.accuweather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureMetricDTO {

	@JsonProperty(value = "Value")
	private String value;
	
	@JsonProperty(value = "Unit")
	private String unit;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "TemperatureMetricDTO [value=" + value + ", unit=" + unit + "]";
	}
	
}
