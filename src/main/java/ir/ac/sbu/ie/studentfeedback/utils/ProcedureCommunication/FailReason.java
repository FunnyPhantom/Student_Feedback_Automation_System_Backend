package ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication;

public enum FailReason {

//    // not-used, kept for legacy dependency
//    BLANK_FIRSTNAME,
//    BLANK_LASTNAME,
//    BLANK_USERNAME,
//    BLANK_PASSWORD,
//    BLANK_JOBTITLE,
//    BLANK_STUDENTID,
//    // end of legacy


    BLANK_ENTRY,

    USERNAME_EXISTS,
    STUDENT_ID_EXISTS,

    USERNAME_NOT_FOUND,
    PASSWORD_NOT_MATCH,

    AUTH_TOKEN_INVALID,
    USER_ID_NOT_FOUND,


    SERVER_ERROR,


}
