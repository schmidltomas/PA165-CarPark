package cz.muni.fi.pa165.carpark.rest.configuration;

import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by karelfajkus on 03/12/2016.
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(value = "cz.muni.fi.pa165.carpark.rest")
public class RestConfiguration extends WebMvcConfigurerAdapter {
}
