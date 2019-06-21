package ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse;

import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureStatus;
import lombok.Getter;
import org.springframework.lang.Nullable;

public final class LoginProcedureResponse extends BooleanProcedureResponse {
    @Getter
    private String authorizationToken;

    private LoginProcedureResponse(ProcedureStatus p, @Nullable FailReason f, @Nullable String authorizationToken) {
        super(p, f);
        this.authorizationToken = authorizationToken;
    }

    private static LoginProcedureResponse buildLoginProcedureResponse(ProcedureStatus p, FailReason f, String authorizationToken) {
        return new LoginProcedureResponse(p, f, authorizationToken);
    }

    public static LoginProcedureResponse buildFailedResponse(FailReason f) {
        return buildLoginProcedureResponse(ProcedureStatus.FAILED, f, null);
    }

    public static LoginProcedureResponse buildSuccessResponse(String authorizationToken) {
        return buildLoginProcedureResponse(ProcedureStatus.SUCCESS, null, authorizationToken);
    }
}
