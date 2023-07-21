package jp.co.toyota.sato.youth.skills18.repositories;

import jp.co.toyota.sato.youth.skills18.entities.Employee;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends ListCrudRepository<Employee, Integer> {
    Optional<Employee> findByUsernameAndPassword(String username, String password);
}
