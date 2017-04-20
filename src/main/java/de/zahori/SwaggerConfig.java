package de.zahori;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.base.Predicate;

import springfox.documentation.builders.PathSelectors;
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
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
//          .apis(RequestHandlerSelectors.any())              
//          .apis(RequestHandlerSelectors.withClassAnnotation(CollectionResourceMapping.class))              
//          .apis(RequestHandlerSelectors.basePackage("de.zahori.model") )
          
          .paths(PathSelectors.any())                          
//          .paths(labelPaths())                          
          .build();                                           
    }
    
    @SuppressWarnings("unchecked")
	private Predicate<String> labelPaths() {
        return or(
                regex("/label.*"),
                regex("/labels.*"),
                regex("/api/labels.*")
        );
    }
    
    
}
