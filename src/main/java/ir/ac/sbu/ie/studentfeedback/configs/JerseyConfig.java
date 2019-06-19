package ir.ac.sbu.ie.studentfeedback.configs;


import ir.ac.sbu.ie.studentfeedback.WebServices.*;
import ir.ac.sbu.ie.studentfeedback.WebServices.Filters.CorsFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        this.register(EmployeeWebService.class);
        this.register(StudentWebService.class);
//        this.register(CorsFilter.class);
    }
}
