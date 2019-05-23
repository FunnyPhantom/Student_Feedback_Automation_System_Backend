package ir.ac.sbu.ie.studentfeedback.Controllers;


import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Services.ServiceStatus;
import ir.ac.sbu.ie.studentfeedback.Services.UserService;


import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Named("userController")
@Path("users")
public class UserController {
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Long, User> getAllUsers() {
        return userService.getAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user){
//        System.out.println(user);
        return userService.createUser(user);
    }

    @Path("{userId}")
    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") Long id) {
        return userService.getUser(id);
    }

    @Path("{userId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User editUser(@PathParam("userId") Long id, User user) {
        return userService.editUser(id, user);
    }
    @Path("{userId}")
    @DELETE
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceStatus deleteUser(@PathParam("userId") Long id) {
        return userService.deleteUser(id);
    }
}
