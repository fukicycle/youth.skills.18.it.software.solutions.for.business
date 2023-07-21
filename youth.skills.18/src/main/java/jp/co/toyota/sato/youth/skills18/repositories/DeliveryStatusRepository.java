package jp.co.toyota.sato.youth.skills18.repositories;

import jp.co.toyota.sato.youth.skills18.entities.DeliveryStatus;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface DeliveryStatusRepository extends ListCrudRepository<DeliveryStatus, Integer> {
    List<DeliveryStatus> findAllByDeliveryId(String id);
}
