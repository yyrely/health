package com.chuncongcong.health.common.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Swagger配置类
 * @author HU
 * @date 2021/3/5 11:50
 */

@EnableOpenApi
@Configuration
public class Swagger3Config implements WebMvcConfigurer {

	/**
	 * ture 启用Swagger3.0， false 禁用（生产环境要禁用）
	 */
	private Boolean swaggerEnabled = true;

	//生成接口信息，包括标题、联系人等
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("health-接口文档")
				.version("1.0")
				.build();
	}

	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				// 是否开启
				.enable(swaggerEnabled)
				.select()
				// 扫描的路径使用@Api的controller
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				// 指定路径处理PathSelectors.any()代表所有的路径
				.paths(PathSelectors.any())
				.build();
	}
}
