package ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.util;

import ir.ac.sbu.ie.studentfeedback.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Named
public class AuthorizationTokenGenerator {

    private Base64.Encoder encoder;

    public AuthorizationTokenGenerator() {
        encoder = Base64.getEncoder();
    }


    public String generateAuthorizationToken(User u) {
        String s = u.getUserRole().toString() + u.getUsername() + u.getPassword();
        return "Bearer " + new String(encoder.encode(s.getBytes(StandardCharsets.UTF_8)));
    }
}


