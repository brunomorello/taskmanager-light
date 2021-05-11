package br.com.bmo.taskmanagerlight.externalsystem.ipify;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bmo.taskmanagerlight.externalsystem.ipify.domain.IpifyIP;

@Service
public class IpifyService {
	
	private RestTemplate restTemplate;
	
	public IpifyService() {
		restTemplate = new RestTemplate();	
	}

	public IpifyIP getMyIp() throws Exception {
		return restTemplate.getForObject("https://api.ipify.org/?format=json", IpifyIP.class);
	}
	
}
