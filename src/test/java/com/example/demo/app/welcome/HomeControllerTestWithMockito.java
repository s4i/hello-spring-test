package com.example.demo.app.welcome;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@SpringJUnitWebConfig(locations = "file:src/main/webapp/WEB-INF/spring/dispatcher-config.xml")
@SpringJUnitWebConfig(locations = "file:src/test/spring/dispatcher-config.xml")
@ExtendWith(MockitoExtension.class)
public class HomeControllerTestWithMockito {

	private MockMvc mockMvc;

	@InjectMocks
	HomeController homeController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@Test
	public void testHomeController() throws Exception {
		var result = mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("welcome/home"))
				.andReturn();

		// コンテキスト
		var content = result.getModelAndView().getModel();
		System.out.println(content);
		assertThat(content.get("message").toString(), containsString("hello springframework." + "[こんにちは春]"));
		assertThat(content.get("time").toString(), containsString(new SimpleDateFormat("yyyy/MM/dd").toString()));
	}
}
