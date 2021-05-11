package br.com.bmo.taskmanagerlight.externalsystem.ipify.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpifyIP {

	private String ip;

	public IpifyIP() {}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}
	
	@Override
	public String toString() {
		return "IP{ ip='" + ip + "' }";
	}
}
