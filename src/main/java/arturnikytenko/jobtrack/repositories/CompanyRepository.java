package arturnikytenko.jobtrack.repositories;

import arturnikytenko.jobtrack.models.company.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Company findByCompanyName(String name);
}
