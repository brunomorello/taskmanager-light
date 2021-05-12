package br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain;

import java.util.List;

public class Forecast {

	private Headline headline;
	private List<DailyForecast> dailyForecast;

	public Forecast(Headline headline, List<DailyForecast> dailyForecast) {
		this.headline = headline;
		this.dailyForecast = dailyForecast;
	}

	public Headline getHeadline() {
		return headline;
	}

	public List<DailyForecast> getDailyForecast() {
		return dailyForecast;
	}

	@Override
	public String toString() {
		return "Forecast [headline=" + headline + ", dailyForecast=" + dailyForecast + "]";
	}
	
}
