package jp.co.toyota.sato.youth.skills18.controllers;

import jp.co.toyota.sato.youth.skills18.entities.*;
import jp.co.toyota.sato.youth.skills18.models.DeliverymanMenuView;
import jp.co.toyota.sato.youth.skills18.models.DeliverymanPickupView;
import jp.co.toyota.sato.youth.skills18.models.DeliverymanScheduleView;
import jp.co.toyota.sato.youth.skills18.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("deliveryman")
public class DeliverymanController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;
    @Autowired
    private DeliveryScheduleRepository deliveryScheduleRepository;
    @Autowired
    private DeliveryScheduleDetailRepository deliveryScheduleDetailRepository;
    @Autowired
    private TruckRepository truckRepository;
    @Autowired
    private DeliveryStatusTypeRepository deliveryStatusTypeRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DeliveryStatusRepository deliveryStatusRepository;

    @GetMapping("menu")
    public String getMenu(Model model, int id) {
        Employee employee = employeeRepository.findById(id).orElse(new Employee());
        List<DeliverySchedule> deliverySchedules = deliveryScheduleRepository.findAllByEmployeeIdAndEstimatedDate(employee.getId(), LocalDate.now());
        List<DeliveryType> deliveryTypes = deliveryTypeRepository.findAll();
        List<DeliverymanMenuView> items = new ArrayList<>();
        DeliverySchedule totalMin = deliverySchedules.stream().min(Comparator.comparing(DeliverySchedule::getEstimatedStartTime)).orElse(new DeliverySchedule());
        for (DeliveryType deliveryType : deliveryTypes) {
            var allItems = deliverySchedules.stream().filter(a -> a.getDeliveryTypeId() == deliveryType.getId()).toList();
            DeliverySchedule min = allItems.stream().min(Comparator.comparing(DeliverySchedule::getEstimatedStartTime)).orElse(new DeliverySchedule());
            boolean isValid = totalMin.getId() == min.getId() && totalMin.getId() != 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            items.add(new DeliverymanMenuView(
                    min.getId(),
                    deliveryType.getId(),
                    deliveryType.getName(),
                    min.getEstimatedStartTime() == null ? LocalTime.MAX : min.getEstimatedStartTime(),
                    String.join(",", allItems.stream().map(a -> formatter.format(a.getEstimatedStartTime())).toList()),
                    allItems.size(),
                    isValid ? getView(deliveryType.getId(), min.getId()) : "",
                    isValid
            ));
        }
        items.sort(Comparator.comparing(DeliverymanMenuView::getMinTime));
        model.addAttribute("items", items);
        return "deliveryman_menu";
    }

    private String getView(int typeId, int deliveryScheduleId) {
        return switch (typeId) {
            case 1 -> "/deliveryman/pickup?deliveryScheduleId=" + deliveryScheduleId;
            case 2 -> "/deliveryman/delivery";
            case 3 -> "/deliveryman/transport";
            case 4 -> "/deliveryman/collect";
            case 5 -> "/deliveryman/regular";
            default -> "";
        };
    }

    @PostMapping("next/pickup")
    public String nextPickup(Model model, String currentDeliveryId, int deliveryScheduleId) {
        DeliveryScheduleDetail deliveryScheduleDetail = deliveryScheduleDetailRepository.findByDeliveryIdAndDeliveryScheduleId(currentDeliveryId, deliveryScheduleId).orElseThrow();
        deliveryScheduleDetail.setActualTime(LocalTime.now());
        deliveryScheduleDetailRepository.save(deliveryScheduleDetail);
        DeliveryStatus deliveryStatus = new DeliveryStatus(0, currentDeliveryId, LocalDateTime.now(), 2, null);
        deliveryStatusRepository.save(deliveryStatus);
        System.out.println("Done!:" + currentDeliveryId);
        return pickup(model, deliveryScheduleId);
    }

    @GetMapping("pickup")
    public String pickup(Model model, int deliveryScheduleId) {
        DeliverySchedule deliverySchedule = deliveryScheduleRepository.findById(deliveryScheduleId).orElse(new DeliverySchedule());
        if (deliverySchedule.getActualStartTime() == null && deliverySchedule.getActualDate() == null) {
            deliverySchedule.setActualStartTime(LocalTime.now());
            deliverySchedule.setActualDate(LocalDate.now());
            deliveryScheduleRepository.save(deliverySchedule);
        }
        List<DeliveryScheduleDetail> deliveryScheduleDetails = deliveryScheduleDetailRepository.findAllByDeliveryScheduleIdAndActualTimeIsNull(deliveryScheduleId);
        DeliverymanPickupView deliverymanPickupView = new DeliverymanPickupView();
        if (deliveryScheduleDetails.size() == 0) {
            //finish process
            deliverySchedule.setActualEndTime(LocalTime.now());
            deliveryScheduleRepository.save(deliverySchedule);
            return "redirect:/deliveryman/menu?id=" + deliverySchedule.getEmployeeId();
        }
        Delivery delivery = deliveryRepository.findById(deliveryScheduleDetails.get(0).getDeliveryId()).orElse(new Delivery());
        List<DeliverymanScheduleView> items = new ArrayList<>();
        for (DeliveryScheduleDetail deliveryScheduleDetail : deliveryScheduleDetails) {
            Delivery delivery1 = deliveryRepository.findById(deliveryScheduleDetail.getDeliveryId()).orElse(new Delivery());
            items.add(new DeliverymanScheduleView(deliveryScheduleDetail.getId(), delivery1.getSenderAddress(), deliveryScheduleDetail.getDeliveryOrder(), deliveryScheduleDetail.getEstimatedTime()));
        }
        items.sort(Comparator.comparing(DeliverymanScheduleView::getEstimatedTime));
        //set value

        deliverymanPickupView.setDeliveryScheduleId(deliveryScheduleId);
        deliverymanPickupView.setItems(items);
        deliverymanPickupView.setTruck(truckRepository.findById(deliverySchedule.getTruckId()).orElse(new Truck()).getName());
        deliverymanPickupView.setType(deliveryTypeRepository.findById(deliverySchedule.getDeliveryTypeId()).orElse(new DeliveryType()).getName());
        deliverymanPickupView.setDelivery(delivery);
        int total = deliveryScheduleDetailRepository.countByDeliveryScheduleId(deliveryScheduleId);
        deliverymanPickupView.setTotal(total);
        deliverymanPickupView.setFinished(total - deliveryScheduleDetails.size());
        model.addAttribute("view", deliverymanPickupView);
        return "deliveryman_pickup";
    }
}

