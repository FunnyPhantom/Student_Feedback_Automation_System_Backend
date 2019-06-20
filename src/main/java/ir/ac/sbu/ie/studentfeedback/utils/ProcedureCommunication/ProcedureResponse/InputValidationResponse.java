package ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse;

import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureStatus;
import lombok.ToString;

@ToString
public final class InputValidationResponse extends BooleanProcedureResponse {
    private InputValidationResponse(ProcedureStatus p, FailReason f) {
        super(p, f);
    }

    public static InputValidationResponse buildFailedValidationResponse(FailReason f) {
        return new InputValidationResponse(ProcedureStatus.FAILED, f);
    }

    public static final InputValidationResponse SUCCESS = new InputValidationResponse(ProcedureStatus.SUCCESS, null);
    public static final InputValidationResponse FAILED__BLANK_ENTRY = buildFailedValidationResponse(FailReason.BLANK_ENTRY);
    public static final InputValidationResponse FAILED__USERNAME_EXISTS = buildFailedValidationResponse(FailReason.USERNAME_EXISTS);
    public static final InputValidationResponse FAILED__STUDENT_ID_EXISTS = buildFailedValidationResponse(FailReason.STUDENT_ID_EXISTS);
}