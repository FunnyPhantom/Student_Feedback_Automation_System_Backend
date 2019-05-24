package ir.ac.sbu.ie.studentfeedback.Types;

import ir.ac.sbu.ie.studentfeedback.Entities.Util.CaseCategory;
import ir.ac.sbu.ie.studentfeedback.Entities.Util.CaseStatus;
import lombok.Data;

@Data
public class CaseInputType {
    private String title;
    private String description;
    private CaseCategory caseCategory;
    private Long selectedInspectorId;
}
