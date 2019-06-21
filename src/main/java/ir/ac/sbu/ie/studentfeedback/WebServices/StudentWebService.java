package ir.ac.sbu.ie.studentfeedback.WebServices;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.EmployeeLogicBean;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.StudentRegisterInput;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@CrossOrigin(origins = {"*"}, allowedHeaders = "*", exposedHeaders = "*")
@Named
@Path("/student")
public class StudentWebService {

    private final EmployeeLogicBean employeeLogicBean;

    @Autowired
    public StudentWebService(EmployeeLogicBean employeeLogicBean) {
        this.employeeLogicBean = employeeLogicBean;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response registerStudent(StudentRegisterInput studentInput) {
        return Response.serverError().build();
    }

}
