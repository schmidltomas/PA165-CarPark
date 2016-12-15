package cz.muni.fi.pa165.carpark.angular.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by karelfajkus on 10/12/2016.
 */
public class SpringInitializerConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AngularAppConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/pa165/rest/*"};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
}
