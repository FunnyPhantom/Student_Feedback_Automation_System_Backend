package ir.ac.sbu.ie.studentfeedback.Controllers;

import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import ir.ac.sbu.ie.studentfeedback.Filters.AuthFilter;
import ir.ac.sbu.ie.studentfeedback.Services.AuthService;
import ir.ac.sbu.ie.studentfeedback.Services.CaseService;
import ir.ac.sbu.ie.studentfeedback.Services.util.ServiceStatus;
import ir.ac.sbu.ie.studentfeedback.Types.CaseInputType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static ir.ac.sbu.ie.studentfeedback.Filters.AuthFilter.AUTHORIZATION_HEADER;
import static ir.ac.sbu.ie.studentfeedback.Services.util.ServiceResponseMapKeys.*;

@Named("CasesRestController")
@Path("issuing_cases")
public class IssuingCasesRestController {

    private final CaseService caseService;
    @Autowired
    public IssuingCasesRestController(CaseService caseService) {
        this.caseService = caseService;
    }


    @GET
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.WILDCARD)
    public Response createCase(@HeaderParam(AUTHORIZATION_HEADER) String bearerToken, CaseInputType caseEntry) {
        Map<String, Object> serviceAns = caseService.createCase(bearerToken, caseEntry);
        if (serviceAns.get(KEY_SERVICE_STATUS).equals(ServiceStatus.FAILED)) {
            return Response.status(400).entity(serviceAns).build();
        } else
            return Response.created(URI.create("issuing_cases/" + ((Case) serviceAns.get(KEY_SAVED_CASE)).getId())).build();
    }

    @GET
    @Path("/{caseId}")
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCaseById(@HeaderParam(AUTHORIZATION_HEADER) String bearerToken, @PathParam("caseId") Long id) {
        System.out.println("#caseId rest hit.");
        Map<String, Object> serviceAns = caseService.getUserCaseById(bearerToken, id);
        System.out.println("after service");
        if (serviceAns.get(KEY_SERVICE_STATUS).equals(ServiceStatus.FAILED)) {
            System.out.println("req failed");
            return Response.status(400).entity(serviceAns).build();
        } else {
            System.out.println("req acc");
            System.out.println(serviceAns.get(KEY_PAYLOAD_MY_CASES));
            return Response.ok().entity(serviceAns.get(KEY_PAYLOAD_MY_CASES)).build();
        }
    }
}
