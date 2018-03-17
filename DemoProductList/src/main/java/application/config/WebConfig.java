package application.config;

import application.data.service.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Created by HoangDuyHao on 1/21/2018.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LogManager.getLogger(WebConfig.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ThymeleafProperties properties;

    @Value("${spring.thymeleaf.templates_root:}")
    private String templatesRoot;

    @Bean
    public ITemplateResolver defaultTemplateResolver(){
        logger.debug("run defaultTemplateResolver");

        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(properties.getSuffix());
        resolver.setPrefix(templatesRoot);
        resolver.setTemplateMode(properties.getMode());
        resolver.setCacheable(properties.isCache());
        return resolver;
    }

    @Bean
    public ProductService getProductService(){
        return new ProductService();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        logger.debug("run defaultTemplateResolver");

        registry.addResourceHandler(
                "/webjars/**",
                "/static/img/**",
                "/static/css/**",
                "/static/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:static/img/",
                        "classpath:static/css/",
                        "classpath:static/js/");
    }
}
