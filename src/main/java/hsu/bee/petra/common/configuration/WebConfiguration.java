package hsu.bee.petra.common.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import hsu.bee.petra.common.argumentresolver.UserArgumentResolver;
import hsu.bee.petra.common.interceptor.AuthenticationInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

	private final AuthenticationInterceptor authenticationInterceptor;
	private final UserArgumentResolver userArgumentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(userArgumentResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor)
			.excludePathPatterns("/")
				.excludePathPatterns("/mainpage/**")
				.excludePathPatterns("/attraction/**")
			.excludePathPatterns("/api/**")
			.excludePathPatterns("/login")
			.excludePathPatterns("/schedule/")
				.excludePathPatterns("/attraction/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/**")
//			.allowedOrigins("http://15.165.93.55:3000");
				.allowedOrigins("*");

	}
}
