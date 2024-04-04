package com.example.demo.app.welcome;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

/*@SpringJUnitWebConfig(locations = "file:src/main/webapp/WEB-INF/spring/dispatcher-config.xml")*/
@SpringJUnitWebConfig(locations = "file:src/test/spring/dispatcher-config.xml")
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
		result.andExpect(view().name("welcome/home"));
	}

	@Test
	public void message() throws Exception {
		// TemplateResolverに設定値をセット
		var resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(wac);
		resolver.setTemplateMode("HTML");
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".html");
		// TemplateEngineにTemplateResolverの値をセット
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(resolver);

		// ThymeLeafで使用するテキスト生成
		Context ctx = new Context();
		String hello = "テキストのテスト";
		ctx.setVariable("message", hello);

		// ThymeLaefの結果出力（templates配下のwelcome/home.htmlを読み込む）
		String result = templateEngine.process("welcome/home", ctx);
		System.out.println(result);
		// ThymeLaefの結果出力にセットした文字列があるか
		assertThat(result, is(containsString(hello)));
	}
}
