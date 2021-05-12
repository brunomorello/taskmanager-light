package br.com.bmo.taskmanagerlight.externalsystem.accuweather.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain.DailyForecast;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyForecastDTO {

	@JsonProperty(value = "Date")
	private String date;

	@JsonProperty(value = "Temperature")
	private TemperatureDTO temperature;

	public DailyForecastDTO() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public TemperatureDTO getTemperature() {
		return temperature;
	}

	public void setTemperature(TemperatureDTO temperature) {
		this.temperature = temperature;
	}

	public DailyForecast parse() {
		return new DailyForecast(LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME),
				new BigDecimal(temperature.getMinimum().getValue()),
				new BigDecimal(temperature.getMaximum().getValue()), temperature.getUnit());
	}

}
