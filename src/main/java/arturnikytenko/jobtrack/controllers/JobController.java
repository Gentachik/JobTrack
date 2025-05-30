package arturnikytenko.jobtrack.controllers;

import arturnikytenko.jobtrack.models.job.Job;
import arturnikytenko.jobtrack.models.job.JobCreateDTO;
import arturnikytenko.jobtrack.services.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping({"/api/job"})
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping({"/add"})
    public ResponseEntity<Void> addNewJob(@RequestBody JobCreateDTO jobDTO) {
        Job savedJob = jobService.addNewJob(jobDTO);
        if (savedJob != null)
            return ResponseEntity.created(URI.create("/api/job/" + savedJob.getJobId())).build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Job>> getJobs() {
        List<Job> jobs = jobService.getJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Job> getJob(@PathVariable String id) {
        Job job = jobService.getJob(id);
        if (job == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(job);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        boolean res = jobService.deleteJobById(id);
        if (res)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}
