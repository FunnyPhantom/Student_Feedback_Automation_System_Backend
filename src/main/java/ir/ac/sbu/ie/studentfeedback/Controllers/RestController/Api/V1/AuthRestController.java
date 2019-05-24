//package ir.ac.sbu.ie.studentfeedback.Controllers.RestController.Api.V1;
//
//import ir.ac.sbu.ie.studentfeedback.Entities.User;
//import ir.ac.sbu.ie.studentfeedback.Entities.Util.UserType;
//
//import javax.inject.Named;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import java.util.ArrayList;
//import java.util.Map;
//
//@Named("AuthController")
//@Path("auth")
//public class AuthRestController {
//
//    @Path("login")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Map login(Map credentials) {
//        //todo: impl
//        return credentials;
//    }
//
//    @Path("register")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Map register(Map credentials) {
//        //todo: impl
//        return  credentials;
//    }
//
//    @Path("get_user")
//    @GET
//    @Consumes(MediaType.WILDCARD)
//    @Produces(MediaType.APPLICATION_JSON)
//    public User getUser(@HeaderParam("auth") String auth) {
//        //todo: impl
//        return new User(2,"mamad", "mamad@mamadestan.com", UserType.STUDENT, new ArrayList<>(), new ArrayList<>());
//    }
//
//}
