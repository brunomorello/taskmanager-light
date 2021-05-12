package br.com.bmo.taskmanagerlight.externalsystem.accuweather;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain.City;
import br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain.Forecast;
import br.com.bmo.taskmanagerlight.externalsystem.ipify.IpifyService;
import br.com.bmo.taskmanagerlight.externalsystem.ipify.domain.IpifyIP;

class AccuweatherServiceTest {
	
	private static IpifyService ipifyService = new IpifyService();
	
	private AccuweatherService accuweatherService = new AccuweatherService();
	
	private static IpifyIP myIp;
	
	@BeforeAll
	public static void setup() throws Exception {
		myIp = ipifyService.getMyIp();
	}
	
	@Test
	void shouldReturnCityLocationByIP() throws Exception {
		City cityByIp = accuweatherService.getCityByIp(myIp);
		
		assertNotNull(myIp);
		assertEquals("Pari", cityByIp.getLocalizedName());
	}
	
	@Test
	void shouldReturn5DayForecastByCity() throws Exception {
		City cityByIp = accuweatherService.getCityByIp(myIp);
		Forecast forecast = accuweatherService.get5dayForecastByLocationKey(cityByIp.getKey());
		
		assertNotNull(forecast);
		System.out.println(forecast);
	}

}
