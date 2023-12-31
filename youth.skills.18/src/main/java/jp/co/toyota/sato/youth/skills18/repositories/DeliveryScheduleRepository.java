package jp.co.toyota.sato.youth.skills18.repositories;

import jp.co.toyota.sato.youth.skills18.entities.Delivery;
import jp.co.toyota.sato.youth.skills18.entities.DeliverySchedule;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryScheduleRepository extends ListCrudRepository<DeliverySchedule, Integer> {
    List<DeliverySchedule> findAllByEmployeeIdAndEstimatedDateAndActualDateIsNull(int employeeId, LocalDate estimatedDate);

    List<DeliverySchedule> findAllByEmployeeIdAndEstimatedDate(int employeeId, LocalDate estimatedDate);

    List<DeliverySchedule> findAllByEmployeeIdAndActualDateIsNotNull(int employeeId);
}
