package ir.ac.sbu.ie.studentfeedback.configs;


import ir.ac.sbu.ie.studentfeedback.Controllers.RestController.Api.V1.AuthRestController;
import ir.ac.sbu.ie.studentfeedback.Controllers.RestController.Api.V1.CasesRestController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JersyConfig extends ResourceConfig {

    public JersyConfig(){
        register(AuthRestController.class);
        register(CasesRestController.class);
    }
}
