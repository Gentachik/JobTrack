package arturnikytenko.jobtrack.services;

import arturnikytenko.jobtrack.models.Stage;
import arturnikytenko.jobtrack.models.company.Company;
import arturnikytenko.jobtrack.models.job.Job;
import arturnikytenko.jobtrack.models.job.JobCreateDTO;
import arturnikytenko.jobtrack.repositories.CompanyRepository;
import arturnikytenko.jobtrack.repositories.JobRepository;
import arturnikytenko.jobtrack.repositories.StageRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final StageRepository stageRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository, StageRepository stageRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.stageRepository = stageRepository;
    }

    public List<Job> getJobs() {
        List<Job> jobs = new ArrayList<>();

        for (Job job : jobRepository.findAll()) {
            jobs.add(job);
        }

        return jobs;
    }

    public Job getJob(Long id) {
        return this.jobRepository.findJobByJobId(id);
    }

    public Job addNewJob(JobCreateDTO jobDTO) {
        Company company = this.companyRepository.findByCompanyName(jobDTO.getCompanyName());
        if (company == null) {
            company = new Company();
            company.setCompanyName(jobDTO.getCompanyName());
            company = companyRepository.save(company);
        }

        Job job = convertJobDTOToJob(jobDTO);
        job.setCompany(company);
        Stage stage = createFirstStage();
        stage.setJob(job);
        job.setStages(new ArrayList<>());
        job.getStages().add(stage);
        job = jobRepository.save(job);
        stageRepository.save(stage);
        return job;
    }

    private Stage createFirstStage() {
        Stage stage = new Stage();
        stage.setStageName("Applied");
        stage.setDateOfStageAdding(new Date());
        return stage;
    }

    private Job convertJobDTOToJob(JobCreateDTO dto) {
        Job job = new Job();
        job.setJobName(dto.getJobName());
        job.setLink(dto.getLink());
        job.setField(dto.getField());
        job.setCountry(dto.getCountry());
        job.setCity(dto.getCity());
        job.setMethodOfApplication(dto.getMethodOfApplication());
        job.setWithCoverLetter(dto.isWithCoverLetter());
        job.setSalaryInUSD(dto.getSalaryInUSD());
        job.setRequiredLanguages(dto.getRequiredLanguages());
        job.setRequiredSkills(dto.getRequiredSkills());
        return job;
    }
}
