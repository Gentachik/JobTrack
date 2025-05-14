package arturnikytenko.jobtrack.models.job;

import lombok.Data;

import java.util.List;

@Data
public class JobCreateDTO {
    private String jobName;
    private String link;
    private String field;
    private String country;
    private String city;
    private String methodOfApplication;
    private String companyName;
    private boolean withCoverLetter;
    private double salaryInUSD;
    private List<String> requiredLanguages;
    private List<String> requiredSkills;
}
