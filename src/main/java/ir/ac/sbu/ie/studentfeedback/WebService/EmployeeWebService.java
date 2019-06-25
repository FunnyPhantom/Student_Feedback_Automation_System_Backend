package ir.ac.sbu.ie.studentfeedback.WebService;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.EmployeeLogicBean;
import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.EmployeeRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.UserLoginInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.EmployeeBriefSchema;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.EmployeeInputOutputSchema;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.BooleanProcedureResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.LoginProcedureResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static ir.ac.sbu.ie.studentfeedback.WebService.Filters.Headers.AUTHORIZATION_HEADER;

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
        if (e == BooleanProcedureResponse.SUCCESS) {
            return Response.created(null).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getFailReason()).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response loginEmployee(UserLoginInput loginInput) {
        LoginProcedureResponse response = employeeLogicBean.loginEmployee(loginInput);
        if (response.hasFailed()) {
            if (response.getFailReason() == FailReason.USER_NOT_YET_VALIDATED) {
                return Response.status(Response.Status.FORBIDDEN).entity(response).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
        return Response.ok().header(AUTHORIZATION_HEADER, response.getAuthorizationToken()).build();
    }

    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get_current")
    public Response getEmployee(@HeaderParam(AUTHORIZATION_HEADER) String authHeader) {
        try {
            Employee e = employeeLogicBean.getEmployeeWithAuthToken(authHeader);
            return Response.ok().entity(new EmployeeInputOutputSchema(e)).build();
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/my_assigned_cases")
    public Response getResponsibleCases(@HeaderParam(AUTHORIZATION_HEADER) String authHeader) {
        try {
            List<Case> list = employeeLogicBean.getEmployeeCasesWithAuthToken(authHeader);
            return Response.ok().entity(list).build();
        } catch (AuthenticationException e) {
            return Response.status(401).entity(e.getMessage()).build();
        }
    }

    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get_brief_employee_list")
    public Response getBriefListOfEmployees() {
        try {
            List<EmployeeBriefSchema> list = employeeLogicBean.getListOfEmployeesAndTheirJobTitles();
            return Response.ok().entity(list).build();
        } catch (Exception e) {
            System.err.println("unexpected exception occured.");
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
