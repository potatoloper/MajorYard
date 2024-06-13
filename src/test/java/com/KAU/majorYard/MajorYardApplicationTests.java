package com.KAU.majorYard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.InetAddress;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MajorYardApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testIpEndpoint() throws Exception {
		String hostIp = InetAddress.getLocalHost().getHostAddress();
		String accessIp = "127.0.0.1";

		mockMvc.perform(get("/ip")
						.header("x-forwarded-for", accessIp))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.hostIp", is(hostIp)))
				.andExpect(jsonPath("$.accessIp", is(accessIp)));
	}

}
