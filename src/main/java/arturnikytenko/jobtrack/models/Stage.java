package arturnikytenko.jobtrack.models;

import arturnikytenko.jobtrack.models.job.Job;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;

import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class Stage {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long stageId;
    private String stageName;
    private Date dateOfStageAdding;
    @ManyToOne
    @JoinColumn(
            name = "job_id"
    )
    private Job job;
}

