package ir.ac.sbu.ie.studentfeedback.configs;


import ir.ac.sbu.ie.studentfeedback.WebService.AdminWebService;
import ir.ac.sbu.ie.studentfeedback.WebService.EmployeeWebService;
import ir.ac.sbu.ie.studentfeedback.WebService.StudentWebService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        this.register(EmployeeWebService.class);
        this.register(StudentWebService.class);
        this.register(AdminWebService.class);
//        this.register(CorsFilter.class);
    }
}
