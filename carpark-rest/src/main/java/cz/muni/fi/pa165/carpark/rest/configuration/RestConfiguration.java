package cz.muni.fi.pa165.carpark.rest.configuration;

import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by karelfajkus on 03/12/2016.
 */
@Configuration
@Import(ServiceConfiguration.class)
@EnableWebMvc
public class RestConfiguration extends WebMvcConfigurerAdapter {


}
