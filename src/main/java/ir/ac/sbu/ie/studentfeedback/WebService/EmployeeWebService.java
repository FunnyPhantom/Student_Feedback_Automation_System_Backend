package ir.ac.sbu.ie.studentfeedback.WebService;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.EmployeeLogicBean;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.EmployeeRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.BooleanProcedureResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response registerEmployee(EmployeeRegisterInput employeeInput) {
        BooleanProcedureResponse e = employeeLogicBean.registerEmployee(employeeInput);
        System.out.println(e);
        if (e == BooleanProcedureResponse.SUCCESS) {
            return Response.created(null).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getFailReason()).build();
        }
    }

}
