package com.example.demo.app.welcome;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import mockit.Tested;

@SpringJUnitWebConfig(locations = "file:src/main/webapp/WEB-INF/spring/dispatcher-config.xml")
public class HomeControllerTestWithJMockit {

	private MockMvc mockMvc;

	@Tested(availableDuringSetup=true)
	HomeController homeController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@Test
	public void testHomeController() throws Exception {
		final ResultActions result = mockMvc.perform(get("/"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("welcome/home"));
	}
}
