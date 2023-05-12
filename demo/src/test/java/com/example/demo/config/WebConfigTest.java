package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.mockito.Mockito.*;

class WebConfigTests {

	@Test
	void corsConfigurer() {
		CorsRegistry corsRegistry = mock(CorsRegistry.class);
		WebConfig webConfig = new WebConfig();
		WebMvcConfigurer webMvcConfigurer = webConfig.corsConfigurer();
		webMvcConfigurer.addCorsMappings(corsRegistry);

		verify(corsRegistry, times(1)).addMapping("/**");
	}
}