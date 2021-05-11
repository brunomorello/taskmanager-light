package br.com.bmo.taskmanagerlight.externalsystem.accuweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain.City;
import br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain.Forecast;
import br.com.bmo.taskmanagerlight.externalsystem.accuweather.dto.ForecastDTO;
import br.com.bmo.taskmanagerlight.externalsystem.ipify.IpifyService;
import br.com.bmo.taskmanagerlight.externalsystem.ipify.domain.IpifyIP;

@Service
public class AccuweatherService {

	@Autowired
	private IpifyService ipService;
	
	private RestTemplate restTemplate;
	
	public AccuweatherService(IpifyService ipService) {
		this.ipService = ipService;
		this.restTemplate = new RestTemplate();
	}
	
	public City getCityByIp(IpifyIP ip) throws Exception {
		
		String endpoint = "http://dataservice.accuweather.com/locations/v1/cities/ipaddress?apikey=5tAcIbSDmTyf6iMWuneaWPpzOFG46QN3&q=" + ipService.getMyIp().getIp();
		City city = restTemplate.getForObject(endpoint, City.class);
		return city;
	}

	public Forecast get5dayForecastByLocationKey(String key) {
		String endpoint = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/" + key + "?apikey=5tAcIbSDmTyf6iMWuneaWPpzOFG46QN3";
		ForecastDTO forecastDTO = restTemplate.getForObject(endpoint, ForecastDTO.class);
		return forecastDTO.parse();
	}
}
