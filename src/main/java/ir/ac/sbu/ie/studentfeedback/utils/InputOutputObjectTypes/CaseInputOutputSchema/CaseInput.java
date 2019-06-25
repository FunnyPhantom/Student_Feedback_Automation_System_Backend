package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.CaseInputOutputSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.util.CaseType;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

public class CaseInput {
    @Setter
    @Getter
    private String title;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private CaseType caseType;
    @Setter
    @Getter
    private Long selectedEmployeeId;


    public static boolean isBlankEntry(CaseInput input) {
        return input == null || Strings.isBlank(input.title) || Strings.isBlank(input.description) || input.caseType == null || input.selectedEmployeeId == null;
    }
}
