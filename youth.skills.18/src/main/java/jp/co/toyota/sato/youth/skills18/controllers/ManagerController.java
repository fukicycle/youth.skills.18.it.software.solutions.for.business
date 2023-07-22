package jp.co.toyota.sato.youth.skills18.controllers;

import jp.co.toyota.sato.youth.skills18.entities.*;
import jp.co.toyota.sato.youth.skills18.models.DeliveryTypeWrapper;
import jp.co.toyota.sato.youth.skills18.models.ManagerDeliverymanScheduleTableView;
import jp.co.toyota.sato.youth.skills18.models.ManagerDeliverymanScheduleView;
import jp.co.toyota.sato.youth.skills18.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DeliveryScheduleRepository deliveryScheduleRepository;
    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;
    @Autowired
    private OfficeRepository officeRepository;

    @GetMapping("menu")
    public String getMenu(Model model, int id) {
        model.addAttribute("id", id);
        return "manager_menu";
    }

    @PostMapping("deliveryman/schedule/view")
    public String changeDate(Model model, int id, LocalDate date) {
        return deliverymanSchedule(model, id, date);
    }

    @GetMapping("deliveryman/schedule")
    public String deliverymanSchedule(Model model, int id, @RequestParam(required = false) LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        System.out.println(date);
        Employee employee = employeeRepository.findById(id).orElseThrow();
        Office office = officeRepository.findById(employee.getOfficeId()).orElseThrow();
        List<Employee> employees = employeeRepository.findAllByOfficeIdAndIsAdminIsFalse(employee.getOfficeId());
        List<DeliveryType> deliveryTypes = deliveryTypeRepository.findAll();
        List<DeliveryTypeWrapper> deliveryTypeWrappers = new ArrayList<>();
        List<LocalTime> times = new ArrayList<>();
        List<ManagerDeliverymanScheduleTableView> tableViews = new ArrayList<>();
        Random r = new Random();
        for (DeliveryType deliveryType : deliveryTypes) {
            String color = String.format("rgb(%d, %d, %d)", 255 - (r.nextInt(1, 5) * 40), 255 - (r.nextInt(1, 5) * 40), 255 - (r.nextInt(1, 5) * 40));
            deliveryTypeWrappers.add(new DeliveryTypeWrapper(deliveryType.getId(), deliveryType.getName(), color));
        }
        int count = 0;
        for (Employee employee1 : employees) {
            List<DeliverySchedule> deliverySchedules = deliveryScheduleRepository.findAllByEmployeeIdAndEstimatedDate(employee1.getId(), date);
            List<String> items = new ArrayList<>();
            for (LocalTime time = LocalTime.of(8, 0); time.isBefore(LocalTime.of(18, 31)); time = time.plusMinutes(30)) {
                if (count == 0) {
                    times.add(time);
                }
                LocalTime finalTime = time;
                var tmp = deliverySchedules.stream().filter(a -> a.getEstimatedStartTime().isBefore(finalTime.plusMinutes(1)) && a.getEstimatedEndTime().isAfter(finalTime)).findFirst();
                if (tmp.isPresent()) {
                    items.add(deliveryTypeWrappers.stream().filter(b -> b.getId() == tmp.get().getDeliveryTypeId()).findFirst().orElseThrow().getColor());
                } else {
                    items.add("#bbb");
                }
            }
            tableViews.add(new ManagerDeliverymanScheduleTableView(employee1.getName(), items));
            count++;
        }
        //set view
        ManagerDeliverymanScheduleView view = new ManagerDeliverymanScheduleView();
        view.setId(employee.getId());
        view.setDate(date);
        view.setDeliveryTypes(deliveryTypeWrappers);
        view.setOffice(office.getName());
        view.setTimes(times);
        view.setTableViews(tableViews);
        model.addAttribute("view", view);
        return "manager_deliveryman_schedule";
    }

    @GetMapping("delivery/schedule")
    public String deliverySchedule(Model model) {
        return "manager_delivery_schedule";
    }
}
