package ir.ac.sbu.ie.studentfeedback.Services.util;

import ir.ac.sbu.ie.studentfeedback.Types.CaseInputType;
import ir.ac.sbu.ie.studentfeedback.Types.LoginInputType;
import ir.ac.sbu.ie.studentfeedback.Types.RegisterInputType;


import javax.inject.Named;


@Named("AuthValidator")
public class AuthValidator {


    public AuthStatus validateRegisterEntry(RegisterInputType registerEntry) {
        if (isNullOrEmpty(registerEntry.getUsername())) {
            return AuthStatus.USERNAME_EMPTY;
        }
        if (isNullOrEmpty(registerEntry.getPassword())) {
            return AuthStatus.PASSWORD_EMPTY;
        }
        if (isNullOrEmpty(registerEntry.getSid())) {
            return AuthStatus.SID_EMPTY;
        }

        return AuthStatus.VALID;
    }

    public AuthStatus validateLoginEntry(LoginInputType loginEntry) {
        if(isNullOrEmpty(loginEntry.getUsername())) {
            return AuthStatus.USERNAME_EMPTY;
        }
        if (isNullOrEmpty(loginEntry.getPassword())) {
            return AuthStatus.PASSWORD_EMPTY;
        }
        CaseInputType caseInputType = new CaseInputType();
        return AuthStatus.VALID;

    }

    private boolean isNullOrEmpty(String s){
        if (s == null) {
            return true;
        } else {
            return s.trim().equals("");
        }
    }

}
