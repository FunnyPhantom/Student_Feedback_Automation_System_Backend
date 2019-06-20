package ir.ac.sbu.ie.studentfeedback.WebServices;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.EmployeeLogicBean;
import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.EmployeeInput;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;
import java.net.URI;
import java.util.List;

@Named
@Path("/employee")
public class EmployeeWebService {

    private final EmployeeLogicBean employeeLogicBean;

    @Autowired
    public EmployeeWebService(EmployeeLogicBean employeeLogicBean) {
        this.employeeLogicBean = employeeLogicBean;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response registerEmployee(EmployeeInput employeeInput) {
        RegisterUserResponse e = employeeLogicBean.registerEmployee(employeeInput);
        System.out.println(e);
        if (e == RegisterUserResponse.SUCCESS) {
            return Response.created(null).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getFailReason()).build();
        }
    }


    // todo: delete this method
    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getEmpl(@PathParam("id") Long id) {
        return Response.ok().entity(employeeLogicBean.getEmployee(id)).build();
    }


}
