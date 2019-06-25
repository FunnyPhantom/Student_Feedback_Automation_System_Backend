package ir.ac.sbu.ie.studentfeedback.WebService;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.StudentLogicBean;
import ir.ac.sbu.ie.studentfeedback.Dao.EmployeeDao;
import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.Entities.Student;
import ir.ac.sbu.ie.studentfeedback.Entities.util.CaseStatus;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.CaseInputOutputSchema.CaseInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.StudentRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.UserLoginInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.StudentInputOutputSchema;
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
import java.util.Optional;

import static ir.ac.sbu.ie.studentfeedback.WebService.Filters.Headers.AUTHORIZATION_HEADER;

//@CrossOrigin(origins = {"*"}, allowedHeaders = "*", exposedHeaders = "*")
@Named
@Path("/student")
public class StudentWebService {

    private final StudentLogicBean studentLogicBean;

    @Autowired
    public StudentWebService(StudentLogicBean studentLogicBean) {
        this.studentLogicBean = studentLogicBean;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response registerStudent(StudentRegisterInput studentInput) {
        BooleanProcedureResponse procedureResponse = studentLogicBean.registerStudent(studentInput);
        if (procedureResponse.hasFailed()) {
            return Response.status(400).entity(procedureResponse).build();
        }
        return Response.status(201).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response loginStudent(UserLoginInput loginInput) {
        LoginProcedureResponse procedureResponse = studentLogicBean.loginStudent(loginInput);
        if (procedureResponse.hasFailed()) {
            if (procedureResponse.getFailReason() == FailReason.USER_NOT_YET_VALIDATED) {
                return Response.status(401).entity(procedureResponse).build();
            }
            return Response.status(400).entity(procedureResponse).build();
        }
        return Response.ok().header(AUTHORIZATION_HEADER, procedureResponse.getAuthorizationToken()).build();
    }

    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get_current")
    public Response getCurrentStudent(@HeaderParam(AUTHORIZATION_HEADER) String authHeader) {
        try {
            Student s = studentLogicBean.getStudentByAuthToken(authHeader);
            return Response.ok().entity(new StudentInputOutputSchema(s)).build();
        } catch (AuthenticationException e) {
            return Response.status(401).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new_case")
    public Response createNewCase(@HeaderParam(AUTHORIZATION_HEADER) String authToken, CaseInput caseInput) {
        if (CaseInput.isBlankEntry(caseInput)) {
            return Response.status(400).entity("blank entry").build();
        }
        try {
            Case c = studentLogicBean.createNewCase(authToken, caseInput);
            return Response.ok().entity(c).build();
        } catch (AuthenticationException e) {
            return Response.status(401).build();
        } catch (NotFoundException e) {
            return Response.status(400).entity("wrong employee id").build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/my_cases")
    public Response getMyCases(@HeaderParam(AUTHORIZATION_HEADER) String authToken) {
        try {
            List<Case> caseList = studentLogicBean.getStudentCasesByAuthToken(authToken);
            return Response.ok().entity(caseList).build();
        } catch (AuthenticationException e) {
            return Response.status(401).build();
        }
    }


}
