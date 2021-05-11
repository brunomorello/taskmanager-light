package br.com.bmo.taskmanagerlight.externalsystem.accuweather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureDTO {

	@JsonProperty(value = "Minimum")
	private TemperatureMetricDTO minimum;
	
	@JsonProperty(value = "Maximum")
	private TemperatureMetricDTO maximum;

	public TemperatureMetricDTO getMinimum() {
		return minimum;
	}

	public void setMinimum(TemperatureMetricDTO minimum) {
		this.minimum = minimum;
	}

	public TemperatureMetricDTO getMaximum() {
		return maximum;
	}

	public void setMaximum(TemperatureMetricDTO maximum) {
		this.maximum = maximum;
	}

	@Override
	public String toString() {
		return "TemperatureDTO [minimum=" + minimum + ", maximum=" + maximum + "]";
	}

	public String getUnit() {
		return minimum.getUnit();
	}
	
}
