package arturnikytenko.jobtrack.models.company;

import arturnikytenko.jobtrack.models.job.Job;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long companyId;
    private String companyName;
    @OneToMany(
            mappedBy = "company",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Job> jobs;
    @PreRemove
    private void removeJobs() {
        for (Job job : jobs) {
            job.setCompany(null);
        }
    }
}
