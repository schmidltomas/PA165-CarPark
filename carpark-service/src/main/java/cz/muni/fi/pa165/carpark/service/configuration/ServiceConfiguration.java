package cz.muni.fi.pa165.carpark.service.configuration;

import cz.muni.fi.pa165.carpark.persistence.configuration.PersistenceConfiguration;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.*;

/**
 * Created by karelfajkus on 05/11/2016.
 */
@Configuration
@Import(PersistenceConfiguration.class)
@ComponentScan(value = "cz.muni.fi.pa165.carpark.service")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }

}
