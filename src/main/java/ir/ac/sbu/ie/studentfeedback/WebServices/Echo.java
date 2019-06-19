package ir.ac.sbu.ie.studentfeedback.WebServices;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Named
@Path("/echo")
public class Echo {

    @GET
    public String echo() {
        return "hi!";
    }

}
