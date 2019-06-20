package ir.ac.sbu.ie.studentfeedback.WebServices;

import ir.ac.sbu.ie.studentfeedback.BusinessLayer.RegisterUserBean;
import ir.ac.sbu.ie.studentfeedback.Entities.Student;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.StudentInput;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

//@CrossOrigin(origins = {"*"}, allowedHeaders = "*", exposedHeaders = "*")
@Named
@Path("/student")
public class StudentWebService {

    private final RegisterUserBean registerUserBean;

    @Autowired
    public StudentWebService(RegisterUserBean registerUserBean) {
        this.registerUserBean = registerUserBean;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response registerStudent(StudentInput studentInput) {
        Student s = registerUserBean.registerStudent(studentInput);
        return Response.created(URI.create("student/" + s.getId())).build();
    }

}
