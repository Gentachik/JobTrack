package arturnikytenko.jobtrack.repositories;

import arturnikytenko.jobtrack.models.job.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {
    Job findJobByJobId(Long jobId);
}
