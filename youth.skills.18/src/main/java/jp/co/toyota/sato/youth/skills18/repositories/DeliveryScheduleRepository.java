package jp.co.toyota.sato.youth.skills18.repositories;

import jp.co.toyota.sato.youth.skills18.entities.DeliverySchedule;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryScheduleRepository extends ListCrudRepository<DeliverySchedule, Integer> {
    List<DeliverySchedule> findAllByEmployeeIdAndEstimatedDate(int employeeId, LocalDate estimatedDate);
}
