package ir.ac.sbu.ie.studentfeedback.Services.util;

import ir.ac.sbu.ie.studentfeedback.Types.LoginInputType;
import ir.ac.sbu.ie.studentfeedback.Types.RegisterInputType;

import javax.inject.Named;


@Named("authValidator")
public class AuthValidator {


    public AuthStatus validateRegisterEntry(RegisterInputType registerEntry) {
        if (registerEntry.getUsername() == null) {
            return AuthStatus.USERNAME_EMPTY;
        }
        if (registerEntry.getPassword() == null) {
            return AuthStatus.PASSWORD_EMPTY;
        }
        if (registerEntry.getSid() == null) {
            return AuthStatus.SID_EMPTY;
        }

        return AuthStatus.VALID;
    }

    public AuthStatus validateLoginEntery(LoginInputType loginEntry) {
        if(loginEntry.getUsername() == null) {
            return AuthStatus.USERNAME_EMPTY;
        }
        if (loginEntry.getPassword() == null) {
            return AuthStatus.PASSWORD_EMPTY;
        }
        return AuthStatus.VALID;

    }

}
