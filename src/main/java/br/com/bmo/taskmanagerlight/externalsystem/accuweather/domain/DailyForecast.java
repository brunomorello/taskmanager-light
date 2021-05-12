package br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DailyForecast {

	private LocalDateTime date;
	
	private BigDecimal minimumTemperature;
	
	private BigDecimal maximumTemperature;
	
	private String temperatureUnit;

	public DailyForecast(LocalDateTime date, BigDecimal minimumTemperature, BigDecimal maximumTemperature,
			String temperatureUnit) {
		this.date = date;
		this.minimumTemperature = minimumTemperature;
		this.maximumTemperature = maximumTemperature;
		this.temperatureUnit = temperatureUnit;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public BigDecimal getMinimumTemperature() {
		return minimumTemperature;
	}

	public BigDecimal getMaximumTemperature() {
		return maximumTemperature;
	}

	public String getTemperatureUnit() {
		return temperatureUnit;
	}

	@Override
	public String toString() {
		return "DailyForecast [date=" + date + ", minimumTemperature=" + minimumTemperature + ", maximumTemperature="
				+ maximumTemperature + ", temperatureUnit=" + temperatureUnit + "]";
	}
	
}
