package ir.ac.sbu.ie.studentfeedback.Controllers.RestController.Api.V1;

import com.sun.net.httpserver.Headers;
import io.swagger.models.parameters.HeaderParameter;
import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Entities.Util.UserType;
import ir.ac.sbu.ie.studentfeedback.Services.AuthService;
import ir.ac.sbu.ie.studentfeedback.Services.util.AuthStatus;
import ir.ac.sbu.ie.studentfeedback.Services.util.ServiceResponseMapKeys;
import ir.ac.sbu.ie.studentfeedback.Types.LoginInputType;
import ir.ac.sbu.ie.studentfeedback.Types.RegisterInputType;
import ir.ac.sbu.ie.studentfeedback.Types.StudentType;
import org.springframework.beans.factory.annotation.Autowired;
import ir.ac.sbu.ie.studentfeedback.Services.util.ServiceStatus;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ir.ac.sbu.ie.studentfeedback.Services.util.ServiceResponseMapKeys.*;

@Named("AuthController")
@Path("auth")
public class AuthRestController {

    private final AuthService authService;
    @Autowired
    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }


    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.WILDCARD)
    public Response login(LoginInputType loginEntry) {
        Map<String, Object>  serviceAns = authService.login(loginEntry);
        System.out.println("##Login");
        System.out.println("serviceAns = " + serviceAns);
        if (serviceAns.get(KEY_SERVICE_STATUS) == (ServiceStatus.FAILED)){
            return badResponseBuilder(serviceAns).build();
        } else {
            return Response.ok().header("Authorization", serviceAns.get(KEY_USER_TOKEN)).build();
        }
    }

    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.WILDCARD)
    public Response register(RegisterInputType registerEntry) {
        Map<String, Object> serviceAns = authService.register(registerEntry);
        System.out.println("##Register");
        System.out.println("serviceAns = " + serviceAns);
        if (serviceAns.get(KEY_SERVICE_STATUS) == ServiceStatus.FAILED) {
            return badResponseBuilder(serviceAns).build();
        } else {
            return Response.ok("registered Successfully").build();
        }

    }

    @Path("get_user")
    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@HeaderParam("Authorization") String auth) {
        Map<String, Object> serviceAns = authService.getUser(auth);
        System.out.println("##GetUser");
        System.out.println("serviceAns = " + serviceAns);
        if (serviceAns.get(KEY_SERVICE_STATUS) == ServiceStatus.FAILED){
            return badResponseBuilder(serviceAns).build();
        } else {
            if(serviceAns.get(KEY_USER_TYPE) == UserType.STUDENT) {
                return Response.ok(StudentType.buildStudentType(((User) serviceAns.get(KEY_SAVED_USER)))).build();
            } else
                return Response.ok(serviceAns.get(KEY_SAVED_USER)).build();
        }
    }

    private static final String KEY_REASON = "reason";
    private Response.ResponseBuilder badResponseBuilder(Map<String, Object> reason) {
        Map<String, Object> body = new HashMap<>();
        if(reason.get(KEY_AUTH_STATUS) != null) {
            body.put(KEY_REASON, reason.get(KEY_AUTH_STATUS));
        } else if (reason.get(KEY_CASE_SERVICE_STATUS) != null) {
            body.put(KEY_REASON, reason.get(KEY_CASE_SERVICE_STATUS));
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(body).type(MediaType.APPLICATION_JSON);
    }

}
