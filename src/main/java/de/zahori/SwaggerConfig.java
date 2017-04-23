package de.zahori;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.base.Predicate;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class}) // to enable @RepositoryRestResource
public class SwaggerConfig {          
	
	/**
	 * You can add multiple Beans with different selectors.
	 * @return
	 */
    @Bean
    public Docket api() { 
       Docket docket = new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
//          .apis(RequestHandlerSelectors.any())              
//          .apis(RequestHandlerSelectors.withClassAnnotation(RepositoryRestResource.class))              
//          .apis(RequestHandlerSelectors.basePackage("de.zahori.model") )
//          .paths(PathSelectors.any())    
          .paths(labelPaths())        
          .build();
       
       return docket;
    }
    
	private Predicate<String> labelPaths() {
        return or(
                regex("/label.*"),
                regex("/api/label.*")
        );
    }
    
	
	// TODO: Figure out how to change swagger-ui path
//	private class pathConfiguration extends WebMvcConfigurerAdapter{
//		
//		@Override
//		public void addViewControllers(ViewControllerRegistry registry) {
//		    registry.addRedirectViewController("/swagger/v2/api-docs", "/v2/api-docs");
//		    registry.addRedirectViewController("/swagger/configuration/ui", "/configuration/ui");
//		    registry.addRedirectViewController("/swagger/configuration/security", "/configuration/security");
//		    registry.addRedirectViewController("/swagger/swagger-resources", "/swagger-resources");
//		    registry.addRedirectViewController("/swagger", "/documentation/swagger-ui.html");
//		    registry.addRedirectViewController("/swagger/", "/documentation/swagger-ui.html");
//		}
//
//		@Override
//		public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		    registry
//		        .addResourceHandler("/swagger/**").addResourceLocations("classpath:/META-INF/resources/");
//		}
//	}
    
}
