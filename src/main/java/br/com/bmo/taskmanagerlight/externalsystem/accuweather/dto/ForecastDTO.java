package br.com.bmo.taskmanagerlight.externalsystem.accuweather.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain.DailyForecast;
import br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain.Forecast;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDTO {

	@JsonProperty(value = "Headline")
	private HeadlineDTO headline;
	
	@JsonProperty(value = "DailyForecasts")
	private List<DailyForecastDTO> dailyForecasts;

	public HeadlineDTO getHeadline() {
		return headline;
	}

	public void setHeadline(HeadlineDTO headline) {
		this.headline = headline;
	}

	public List<DailyForecastDTO> getDailyForecasts() {
		return dailyForecasts;
	}

	public void setDailyForecasts(List<DailyForecastDTO> dailyForecasts) {
		this.dailyForecasts = dailyForecasts;
	}
	
	public Forecast parse() {
		List<DailyForecast> dailyForecast = new ArrayList<>();
		dailyForecasts.forEach(d -> dailyForecast.add(d.parse()));
		return new Forecast(headline.parse(), dailyForecast);
	}
}
