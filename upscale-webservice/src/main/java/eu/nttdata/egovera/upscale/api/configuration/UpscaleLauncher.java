package eu.nttdata.egovera.upscale.api.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan({ "eu.nttdata.egovera.upscale.api", "eu.nttdata.egovera.upscale.service"})
@SpringBootApplication
public class UpscaleLauncher {

	public static void main(String[] args) {
		SpringApplication.run(UpscaleLauncher.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
					.addMapping("/**")
					.allowedOrigins("*");
			}
		};
	}
}
