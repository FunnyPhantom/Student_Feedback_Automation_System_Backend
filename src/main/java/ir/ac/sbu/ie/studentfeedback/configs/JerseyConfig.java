package ir.ac.sbu.ie.studentfeedback.configs;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        packages("ir.ac.sbu.ie.studentfeedback.Controllers");
        packages("ir.ac.sbu.ie.studentfeedback.Filters");
    }
}
