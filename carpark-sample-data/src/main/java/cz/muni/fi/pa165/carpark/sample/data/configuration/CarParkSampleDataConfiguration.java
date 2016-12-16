/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carpark.sample.data.configuration;

import cz.muni.fi.pa165.carpark.sample.data.SampleDataLoadingFacade;
import cz.muni.fi.pa165.carpark.sample.data.SampleDataLoadingFacadeImpl;
import cz.muni.fi.pa165.carpark.service.configuration.ServiceConfiguration;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Jakub Kriz
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class CarParkSampleDataConfiguration extends WebMvcConfigurerAdapter {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(CarParkSampleDataConfiguration.class);

    @Autowired
    private SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws Exception {
        LOGGER.debug("Inserting sample data for project.");
        sampleDataLoadingFacade.loadData();
    }
}
