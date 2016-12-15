package cz.muni.fi.pa165.carpark.angular.configuration;

import cz.muni.fi.pa165.carpark.rest.configuration.RestConfiguration;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by karelfajkus on 10/12/2016.
 */
@EnableWebMvc
@Configuration
@Import(RestConfiguration.class)
public class AngularAppConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private BeanFactory beanFactory;
}
