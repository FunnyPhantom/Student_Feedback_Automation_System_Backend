package ir.ac.sbu.ie.studentfeedback.WebService;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.AdminLogicBean;
import ir.ac.sbu.ie.studentfeedback.Entities.Admin;
import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRole;
import ir.ac.sbu.ie.studentfeedback.WebService.Filters.Headers;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.IdSchema.EmployeeIdSchema;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.IdSchema.UserIdSchema;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.AdminRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.UserLoginInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.AdminInputOutputSchema;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.BooleanProcedureResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.EmployeeListProcedureResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.LoginProcedureResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.StudentListProcedureResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
@Path("/admin")
public class AdminWebService {

    private final AdminLogicBean adminLogicBean;

    @Autowired
    public AdminWebService(AdminLogicBean adminLogicBean) {
        this.adminLogicBean = adminLogicBean;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response registerAdmin(AdminRegisterInput adminRegisterInput) {
        BooleanProcedureResponse procedureResponse = adminLogicBean.registerAdmin(adminRegisterInput);
        if (procedureResponse.hasFailed()) {
            return Response.status(400).entity(procedureResponse).build();
        } else {
            return Response.status(201).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response loginAdmin(UserLoginInput loginInput) {
        LoginProcedureResponse procedureResponse = adminLogicBean.loginAdmin(loginInput);
        if (procedureResponse.hasFailed()) {
            return Response.status(400).entity(procedureResponse).build();
        }
        return Response.ok().header(Headers.AUTHORIZATION_HEADER, procedureResponse.getAuthorizationToken()).build();
    }

    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get_current")
    public Response getAdmin(@HeaderParam(Headers.AUTHORIZATION_HEADER) String authToken) {
        try {
            Admin admin = adminLogicBean.getAdminByAuthorizationToken(authToken);
            return Response.ok().entity(new AdminInputOutputSchema(admin)).build();
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.status(401).build();
        }
    }

    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pending_user_list/{userRole}")
    public Response getUserList(@PathParam("userRole") String userRole, @HeaderParam(Headers.AUTHORIZATION_HEADER) String authHeader) {
        UserRole requestedRole = null;
        try {
            requestedRole = UserRole.valueOf(userRole.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return Response.status(400).build();
        }
        if (requestedRole == UserRole.STUDENT) {
            StudentListProcedureResponse studentListProcedureResponse = adminLogicBean.getListOfAllPendingStudent(authHeader);
            if (studentListProcedureResponse.hasFailed()) {
                if (studentListProcedureResponse.getFailReason() == FailReason.AUTH_TOKEN_INVALID) {
                    return Response.status(401).build();
                }
                return Response.status(400).entity(studentListProcedureResponse).build();
            }
            return Response.ok().entity(studentListProcedureResponse.getStudentList()).build();
        }
        if (requestedRole == UserRole.EMPLOYEE) {
            EmployeeListProcedureResponse employeeListProcedureResponse = adminLogicBean.getListOfAllPendingEmployees(authHeader);
            if (employeeListProcedureResponse.hasFailed()) {
                if (employeeListProcedureResponse.getFailReason() == FailReason.AUTH_TOKEN_INVALID) {
                    return Response.status(401).build();
                }
                return Response.status(400).entity(employeeListProcedureResponse).build();
            }
            return Response.ok().entity(employeeListProcedureResponse.getEmployeeList()).build();
        }
        return Response.status(400).entity("entity not available").build();
    }

    @PUT
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/validate_user/{userRole}/{userId}")
    public Response validateUser(@PathParam("userRole") String userRole,
                                 @PathParam("userId") Long id,
                                 @HeaderParam(Headers.AUTHORIZATION_HEADER) String authToken) {
        try {
            UserRole requestedRole = UserRole.valueOf(userRole.toUpperCase());
            if (requestedRole == UserRole.ADMIN) {
                throw new IllegalArgumentException("admins cannot be validated");
            }
            UserIdSchema userIdentifier = new UserIdSchema() {
                @Override
                public UserRole getUserRole() {
                    return requestedRole;
                }
            };
            userIdentifier.setId(id);
            BooleanProcedureResponse procedureResponse = adminLogicBean.validateUser(authToken, userIdentifier);
            if (procedureResponse.hasFailed()) {
                return Response.status(400).entity(procedureResponse).build();
            }

            return Response.ok().build();


        } catch (IllegalArgumentException e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

}
