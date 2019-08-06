package de.akull.geoloc.config;

import static org.apache.cxf.Bus.DEFAULT_BUS_ID;

import de.akull.geoloc.core.GeolocationPort;
import de.akull.geoloc.soap.GeolocationWebservice;
import javax.xml.ws.Endpoint;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;

@EnableWs
@Configuration
public class WebServiceConfig {

    private static final String URL_MAPPING = "/services/*";
    private static final String ADDRESS = "/geoloc";

    @Bean
    public GeolocationWebservice geolocationWebService(GeolocationPort service) {
        return new GeolocationWebservice(service);
    }

    @Bean
    public ServletRegistrationBean wsDispatcherServlet() {
        CXFServlet cxfServlet = new CXFServlet();
        return new ServletRegistrationBean(cxfServlet, URL_MAPPING);
    }

    @Bean(name = DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint geolocationEndpoint(SpringBus springBus, GeolocationWebservice controller) {
        EndpointImpl endpoint = new EndpointImpl(springBus, controller);
        endpoint.publish(ADDRESS);
        return endpoint;
    }
}