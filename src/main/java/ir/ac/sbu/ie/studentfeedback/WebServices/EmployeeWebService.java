package ir.ac.sbu.ie.studentfeedback.WebServices;

import ir.ac.sbu.ie.studentfeedback.BusinessLayer.InputValidationLogics;
import ir.ac.sbu.ie.studentfeedback.BusinessLayer.RegisterUserBean;
import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.EmployeeInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Named
@Path("/employee")
public class EmployeeWebService {

    private final RegisterUserBean registerUserBean;

    @Autowired
    public EmployeeWebService(RegisterUserBean registerUserBean) {
        this.registerUserBean = registerUserBean;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response registerEmployee(EmployeeInput employeeInput) {
        Employee e = registerUserBean.registerEmployee(employeeInput);
        System.out.println(e.getId());
        return Response.created(URI.create("employee/" + e.getId())).build();
    }




}
