package arturnikytenko.jobtrack.models.job;

import arturnikytenko.jobtrack.models.Stage;
import arturnikytenko.jobtrack.models.company.Company;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long jobId;
    @Column(
            nullable = false
    )
    private String jobName;
    @Column(
            nullable = false
    )
    private String link;
    @Column(
            nullable = false
    )
    private String field;
    private boolean isResult;
    @Column(
            nullable = false
    )
    private String country;
    private String city;
    @Column(
            nullable = false
    )
    private String methodOfApplication;
    @Column(
            nullable = false
    )
    private boolean withCoverLetter;
    private double salaryInUSD;
    @ElementCollection
    private List<String> requiredLanguages;
    @ElementCollection
    private List<String> requiredSkills;
    @ManyToOne
    @JoinColumn(
            name = "company_id"
    )
    private Company company;
    @OneToMany(
            mappedBy = "job",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Stage> stages;
}
