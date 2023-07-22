package jp.co.toyota.sato.youth.skills18.controllers;

import jp.co.toyota.sato.youth.skills18.entities.*;
import jp.co.toyota.sato.youth.skills18.models.DeliverymanMenuView;
import jp.co.toyota.sato.youth.skills18.models.DeliverymanOtherView;
import jp.co.toyota.sato.youth.skills18.models.DeliverymanPickupAndDeliveryView;
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
    @Autowired
    private OfficeDeliveryRepository officeDeliveryRepository;
    @Autowired
    private OfficeRepository officeRepository;

    @GetMapping("menu")
    public String getMenu(Model model, int id) {
        Employee employee = employeeRepository.findById(id).orElse(new Employee());
        List<DeliverySchedule> deliverySchedules = deliveryScheduleRepository.findAllByEmployeeIdAndEstimatedDateAndActualDateIsNull(employee.getId(), LocalDate.now());
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
            case 2 -> "/deliveryman/delivery?deliveryScheduleId=" + deliveryScheduleId;
            case 3 -> "/deliveryman/transport?deliveryScheduleId=" + deliveryScheduleId;
            case 4 -> "/deliveryman/collect?deliveryScheduleId=" + deliveryScheduleId;
            case 5 -> "/deliveryman/regular?deliveryScheduleId=" + deliveryScheduleId;
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
        System.out.println("Pickup!:" + currentDeliveryId);
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
        deliveryScheduleDetails.sort(Comparator.comparing(DeliveryScheduleDetail::getDeliveryOrder));
        DeliverymanPickupAndDeliveryView deliverymanPickupAndDeliveryView = new DeliverymanPickupAndDeliveryView();
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
        //set value
        deliverymanPickupAndDeliveryView.setDeliveryScheduleId(deliveryScheduleId);
        deliverymanPickupAndDeliveryView.setItems(items);
        deliverymanPickupAndDeliveryView.setTruck(truckRepository.findById(deliverySchedule.getTruckId()).orElse(new Truck()).getName());
        deliverymanPickupAndDeliveryView.setType(deliveryTypeRepository.findById(deliverySchedule.getDeliveryTypeId()).orElse(new DeliveryType()).getName());
        deliverymanPickupAndDeliveryView.setDelivery(delivery);
        int total = deliveryScheduleDetailRepository.countByDeliveryScheduleId(deliveryScheduleId);
        deliverymanPickupAndDeliveryView.setTotal(total);
        deliverymanPickupAndDeliveryView.setFinished(total - deliveryScheduleDetails.size());
        model.addAttribute("view", deliverymanPickupAndDeliveryView);
        return "deliveryman_pickup";
    }


    @PostMapping("next/delivery")
    public String nextDelivery(Model model, String currentDeliveryId, int deliveryScheduleId) {
        DeliveryScheduleDetail deliveryScheduleDetail = deliveryScheduleDetailRepository.findByDeliveryIdAndDeliveryScheduleId(currentDeliveryId, deliveryScheduleId).orElseThrow();
        deliveryScheduleDetail.setActualTime(LocalTime.now());
        deliveryScheduleDetailRepository.save(deliveryScheduleDetail);
        DeliveryStatus deliveryStatus = new DeliveryStatus(0, currentDeliveryId, LocalDateTime.now(), 7, null);
        deliveryStatusRepository.save(deliveryStatus);
        System.out.println("Delivery!:" + currentDeliveryId);
        return pickup(model, deliveryScheduleId);
    }

    @GetMapping("delivery")
    public String delivery(Model model, int deliveryScheduleId) {
        DeliverySchedule deliverySchedule = deliveryScheduleRepository.findById(deliveryScheduleId).orElse(new DeliverySchedule());
        //start work
        if (deliverySchedule.getActualStartTime() == null && deliverySchedule.getActualDate() == null) {
            deliverySchedule.setActualStartTime(LocalTime.now());
            deliverySchedule.setActualDate(LocalDate.now());
            deliveryScheduleRepository.save(deliverySchedule);
        }
        List<DeliveryScheduleDetail> deliveryScheduleDetails = deliveryScheduleDetailRepository.findAllByDeliveryScheduleIdAndActualTimeIsNull(deliveryScheduleId);
        deliveryScheduleDetails.sort(Comparator.comparing(DeliveryScheduleDetail::getDeliveryOrder));
        DeliverymanPickupAndDeliveryView deliverymanPickupAndDeliveryView = new DeliverymanPickupAndDeliveryView();
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
            items.add(new DeliverymanScheduleView(deliveryScheduleDetail.getId(), delivery1.getDestinationAddress(), deliveryScheduleDetail.getDeliveryOrder(), deliveryScheduleDetail.getEstimatedTime()));
        }
        //set value
        deliverymanPickupAndDeliveryView.setDeliveryScheduleId(deliveryScheduleId);
        deliverymanPickupAndDeliveryView.setItems(items);
        deliverymanPickupAndDeliveryView.setTruck(truckRepository.findById(deliverySchedule.getTruckId()).orElse(new Truck()).getName());
        deliverymanPickupAndDeliveryView.setType(deliveryTypeRepository.findById(deliverySchedule.getDeliveryTypeId()).orElse(new DeliveryType()).getName());
        deliverymanPickupAndDeliveryView.setDelivery(delivery);
        int total = deliveryScheduleDetailRepository.countByDeliveryScheduleId(deliveryScheduleId);
        deliverymanPickupAndDeliveryView.setTotal(total);
        deliverymanPickupAndDeliveryView.setFinished(total - deliveryScheduleDetails.size());
        model.addAttribute("view", deliverymanPickupAndDeliveryView);
        return "deliveryman_delivery";
    }

    @PostMapping("done/transport")
    public String doneTransport(Model model, int deliveryScheduleId) {
        DeliverySchedule deliverySchedule = deliveryScheduleRepository.findById(deliveryScheduleId).orElseThrow();
        deliverySchedule.setActualEndTime(LocalTime.now());
        deliveryScheduleRepository.save(deliverySchedule);
        OfficeDelivery officeDelivery = officeDeliveryRepository.findById(deliverySchedule.getOfficeDeliveryId()).orElseThrow();
        List<DeliveryScheduleDetail> deliveryScheduleDetails = deliveryScheduleDetailRepository.findAllByDeliveryScheduleIdAndActualTimeIsNull(deliveryScheduleId);
        for (DeliveryScheduleDetail deliveryScheduleDetail : deliveryScheduleDetails) {
            deliveryScheduleDetail.setActualTime(LocalTime.now());
            DeliveryStatus deliveryStatus = new DeliveryStatus(0, deliveryScheduleDetail.getDeliveryId(), LocalDateTime.now(), 5, officeDelivery.getDestinationOfficeId());
            deliveryStatusRepository.save(deliveryStatus);
        }
        deliveryScheduleDetailRepository.saveAll(deliveryScheduleDetails);
        return "redirect:/deliveryman/menu?id=" + deliverySchedule.getEmployeeId();
    }

    @GetMapping("transport")
    public String transport(Model model, int deliveryScheduleId) {
        DeliverySchedule deliverySchedule = deliveryScheduleRepository.findById(deliveryScheduleId).orElseThrow();
        //start work
        if (deliverySchedule.getActualStartTime() == null && deliverySchedule.getActualDate() == null) {
            deliverySchedule.setActualStartTime(LocalTime.now());
            deliverySchedule.setActualDate(LocalDate.now());
            deliveryScheduleRepository.save(deliverySchedule);
            OfficeDelivery officeDelivery = officeDeliveryRepository.findById(deliverySchedule.getOfficeDeliveryId()).orElseThrow();
            List<DeliveryScheduleDetail> deliveryScheduleDetails = deliveryScheduleDetailRepository.findAllByDeliveryScheduleIdAndActualTimeIsNull(deliveryScheduleId);
            for (DeliveryScheduleDetail deliveryScheduleDetail : deliveryScheduleDetails) {
                DeliveryStatus deliveryStatus = new DeliveryStatus(0, deliveryScheduleDetail.getDeliveryId(), LocalDateTime.now(), 4, officeDelivery.getSenderOfficeId());
                deliveryStatusRepository.save(deliveryStatus);
            }
        }
        OfficeDelivery officeDelivery = officeDeliveryRepository.findById(deliverySchedule.getOfficeDeliveryId()).orElseThrow();
        Office office = officeRepository.findById(officeDelivery.getDestinationOfficeId()).orElseThrow();
        Truck truck = truckRepository.findById(deliverySchedule.getTruckId()).orElseThrow();
        DeliverymanOtherView deliverymanOtherView = new DeliverymanOtherView();
        deliverymanOtherView.setDeliveryScheduleId(deliveryScheduleId);
        deliverymanOtherView.setAddress("");
        deliverymanOtherView.setZipcode(office.getZipcode());
        deliverymanOtherView.setName(office.getName());
        deliverymanOtherView.setEstimatedTime(deliverySchedule.getEstimatedStartTime().plusMinutes(officeDelivery.getDeliveryTime()));
        deliverymanOtherView.setTruck(truck.getName());
        model.addAttribute("view", deliverymanOtherView);
        return "deliveryman_transport";
    }
}

