package ir.ac.sbu.ie.studentfeedback.configs;

import ir.ac.sbu.ie.studentfeedback.Controllers.UserController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JersyConfig extends ResourceConfig {

    public JersyConfig(){
        register(UserController.class);
    }
}
