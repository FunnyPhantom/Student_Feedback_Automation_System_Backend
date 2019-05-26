package ir.ac.sbu.ie.studentfeedback.Controllers.RestController.Api.V1;

import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import ir.ac.sbu.ie.studentfeedback.Filters.AuthFilter;
import ir.ac.sbu.ie.studentfeedback.Services.AuthService;
import ir.ac.sbu.ie.studentfeedback.Services.CaseService;
import ir.ac.sbu.ie.studentfeedback.Services.util.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static ir.ac.sbu.ie.studentfeedback.Filters.AuthFilter.AUTHORIZATION_HEADER;
import static ir.ac.sbu.ie.studentfeedback.Services.util.ServiceResponseMapKeys.KEY_PAYLOAD_MY_CASES;
import static ir.ac.sbu.ie.studentfeedback.Services.util.ServiceResponseMapKeys.KEY_SERVICE_STATUS;

@Named("CasesRestController")
@Path("cases")
public class CasesRestController {

    private final CaseService caseService;
    @Autowired
    public CasesRestController(CaseService caseService) {
        this.caseService = caseService;
    }


    @GET
    @Path("/")
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyCases(@HeaderParam(AUTHORIZATION_HEADER) String bearerToken) {
        Map<String, Object> serviceAns =  caseService.getUserCasesByToken(bearerToken);
        if (serviceAns.get(KEY_SERVICE_STATUS).equals(ServiceStatus.FAILED)) {
            return Response.status(400).entity(serviceAns).build();
        } else {
            return Response.ok(serviceAns.get(KEY_PAYLOAD_MY_CASES)).build();
        }
    }

}
