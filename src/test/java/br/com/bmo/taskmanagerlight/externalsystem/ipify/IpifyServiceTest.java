package br.com.bmo.taskmanagerlight.externalsystem.ipify;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.bmo.taskmanagerlight.externalsystem.ipify.domain.IpifyIP;

class IpifyServiceTest {

	@Test
	void shouldRetriveMyIP() throws Exception {
		IpifyService service = new IpifyService();
		IpifyIP myIp = service.getMyIp();
		
		assertEquals(myIp.getIp(), "189.120.77.134");
	}

}
