package com.beta.replyservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
public class RestServiceApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	public RuleProcessor ruleProcessor;

	@Test
	public void testProcessV2Request_Success() throws Exception {
		Mockito.when(ruleProcessor.processRule("11", "kbzw9ru")).thenReturn("kbzw9ru");

		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/11-kbzw9ru")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data").value("kbzw9ru"));
	}

	@Test
	public void testProcessV2Request_InvalidRule() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/13-kbzw9ru")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid input"));
	}

}
