package jp.co.toyota.sato.youth.skills18.repositories;

import jp.co.toyota.sato.youth.skills18.entities.DeliveryScheduleDetail;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryScheduleDetailRepository extends ListCrudRepository<DeliveryScheduleDetail, Integer> {
    List<DeliveryScheduleDetail> findAllByDeliveryScheduleIdAndActualTimeIsNull(int deliveryScheduleId);

    int countByDeliveryScheduleId(int deliveryScheduleId);

    Optional<DeliveryScheduleDetail> findByDeliveryIdAndDeliveryScheduleId(String deliveryId, int deliveryScheduleId);
    List<DeliveryScheduleDetail> findAllByDeliveryScheduleId(int deliveryScheduleId);
}
