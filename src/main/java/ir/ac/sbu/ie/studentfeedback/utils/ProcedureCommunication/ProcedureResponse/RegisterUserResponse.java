package ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse;

import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureStatus;
import lombok.ToString;

@ToString
public final class RegisterUserResponse extends BooleanProcedureResponse {
    private RegisterUserResponse(ProcedureStatus p, FailReason f) {
        super(p, f);
    }

    public static final RegisterUserResponse SUCCESS = new RegisterUserResponse(ProcedureStatus.SUCCESS, null);

    public static RegisterUserResponse buildFailedResponse(FailReason f) {
        return new RegisterUserResponse(ProcedureStatus.FAILED, f);
    }
}
