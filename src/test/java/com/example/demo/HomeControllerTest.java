package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(locations = "file:src/main/webapp/WEB-INF/spring/dispatcher-config.xml")
public class HomeControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac; //コンテキストを用意

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testHomeController() throws Exception {
		final ResultActions result = mockMvc.perform(get("/"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("home"));
	}
}
