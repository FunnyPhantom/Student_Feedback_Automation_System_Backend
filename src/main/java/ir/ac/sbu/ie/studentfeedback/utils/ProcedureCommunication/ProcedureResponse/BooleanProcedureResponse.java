package ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse;

import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureStatus;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ToString
public class BooleanProcedureResponse extends ProcedureResponse {
    protected BooleanProcedureResponse(ProcedureStatus p, @Nullable FailReason f) {
        super(p, f);
    }

    public static BooleanProcedureResponse buildProcedureResponse(ProcedureStatus p, FailReason f) {
        return new BooleanProcedureResponse(p, f);
    }

    public final static BooleanProcedureResponse SUCCESS = new BooleanProcedureResponse(ProcedureStatus.SUCCESS, null);
}
