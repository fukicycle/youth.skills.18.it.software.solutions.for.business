package jp.co.toyota.sato.youth.skills18.controllers;

import jp.co.toyota.sato.youth.skills18.TwoOpt;
import jp.co.toyota.sato.youth.skills18.entities.*;
import jp.co.toyota.sato.youth.skills18.models.*;
import jp.co.toyota.sato.youth.skills18.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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
    @Autowired
    private DeliveryScheduleDetailRepository deliveryScheduleDetailRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private ZipcodeRepository zipcodeRepository;

    @GetMapping("menu")
    public String getMenu(Model model, int id) {
        model.addAttribute("id", id);
        return "manager_menu";
    }

    @PostMapping("deliveryman/schedule/view")
    public String deliverymanScheduleView(Model model, int id, LocalDate date) {
        return deliverymanSchedule(model, id, date);
    }

    @GetMapping("deliveryman/schedule")
    public String deliverymanSchedule(Model model, int id, LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
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

    @PostMapping("delivery/schedule/view")
    public String deliveryScheduleView(Model model, int id, LocalDate date) {
        return deliverySchedule(model, id, date);
    }

    @GetMapping("delivery/schedule")
    public String deliverySchedule(Model model, int id, LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        Employee employee = employeeRepository.findById(id).orElseThrow();
        Office office = officeRepository.findById(employee.getOfficeId()).orElseThrow();
        List<Employee> employees = employeeRepository.findAllByOfficeIdAndIsAdminIsFalse(employee.getOfficeId());
        List<ManagerDeliveryScheduleTableView> managerDeliveryScheduleTableViews = new ArrayList<>();
        for (Employee employee1 : employees) {
            for (DeliverySchedule deliverySchedule : deliveryScheduleRepository.findAllByEmployeeIdAndEstimatedDate(employee1.getId(), date)) {
                String type = deliveryTypeRepository.findById(deliverySchedule.getDeliveryTypeId()).orElseThrow().getName();
                long count = deliveryScheduleDetailRepository.findAllByDeliveryScheduleId(deliverySchedule.getId()).stream().filter(a -> a.getDeliveryOrder() == 0).count();
                managerDeliveryScheduleTableViews.add(new ManagerDeliveryScheduleTableView(deliverySchedule.getEstimatedStartTime(), type, count == 0 ? "#8f8" : "#f88", deliverySchedule.getId()));
            }
        }
        //set view
        ManagerDeliveryScheduleView view = new ManagerDeliveryScheduleView();
        view.setOffice(office.getName());
        view.setDate(date);
        view.setEmployeeId(id);
        view.setTableViews(managerDeliveryScheduleTableViews);
        model.addAttribute("view", view);
        return "manager_delivery_schedule";
    }

    @PostMapping("delivery/plan/submit")
    public String submit(Model model, boolean isCost, LocalDate date, int employeeId, int id) throws Exception {
        deliverySchedulePlan(model, employeeId, id, isCost, date, true);
        return "redirect:/manager/delivery/schedule?id=" + employeeId + "&date=" + date;
    }

    @GetMapping("delivery/schedule/plan")
    public String deliverySchedulePlan(Model model, int employeeId, int id, @RequestParam(defaultValue = "True") boolean isCost, LocalDate date, @RequestParam(defaultValue = "false") boolean isPost) throws Exception {
        DeliverySchedule deliverySchedule = deliveryScheduleRepository.findById(id).orElseThrow();
        Employee employee = employeeRepository.findById(deliverySchedule.getEmployeeId()).orElseThrow();
        Office office = officeRepository.findById(employee.getOfficeId()).orElseThrow();
        List<DeliveryScheduleDetail> deliveryScheduleDetails = deliveryScheduleDetailRepository.findAllByDeliveryScheduleId(id);
        if (deliveryScheduleDetails.size() == 0) throw new Exception("配送スケジュール詳細が存在しません。");
        List<PointWrapper> points = new ArrayList<>();
        PointWrapper startAndEndPoint = new PointWrapper(new Point(office.getxLocation(), office.getyLocation()), null);
        for (DeliveryScheduleDetail deliveryScheduleDetail : deliveryScheduleDetails) {
            Delivery delivery = deliveryRepository.findById(deliveryScheduleDetail.getDeliveryId()).orElseThrow();
            Zipcode zipcode;
            if (deliverySchedule.getDeliveryTypeId() == 1) {
                zipcode = zipcodeRepository.findById(delivery.getSenderZipcode()).orElseThrow();
            } else {
                zipcode = zipcodeRepository.findById(delivery.getDestinationZipcode()).orElseThrow();
            }
            int x = zipcode.getxLocation();
            int y = zipcode.getyLocation();
            points.add(new PointWrapper(new Point(x, y), delivery));
        }
        if (!isCost) {
            if (deliverySchedule.getDeliveryTypeId() == 1) {
                points.sort(Comparator.comparing(a -> a.getDelivery().getCollectionDatetime()));
            } else {
                points.sort(Comparator.comparing(a -> a.getDelivery().getDeliveryDatetime()));
            }
        }
        points.add(0, startAndEndPoint);
        points.add(startAndEndPoint);
        List<PointWrapper> bestRoute = isCost ? TwoOpt.getCalculatedRoute(points) : points;
        if (isPost) {
            int seq = 1;
            for (PointWrapper point : bestRoute) {
                if (point.getDelivery() != null) {
                    DeliveryScheduleDetail deliveryScheduleDetail = deliveryScheduleDetails.stream().filter(a -> a.getDeliveryId().equals(point.getDelivery().getId()) && a.getDeliveryScheduleId() == id).findFirst().orElseThrow();
                    deliveryScheduleDetail.setDeliveryOrder(seq++);
                    deliveryScheduleDetailRepository.save(deliveryScheduleDetail);
                }
            }
        } else {
            int seq = 0;
            final int MAP_RATIO = 35;
            final int offset = 10;
            double minX = bestRoute.stream().mapToDouble(a -> a.getPoint().getX()).min().getAsDouble();
            double minY = bestRoute.stream().mapToDouble(a -> a.getPoint().getY()).min().getAsDouble();
            double maxX = bestRoute.stream().mapToDouble(a -> a.getPoint().getX()).max().getAsDouble();
            double maxY = bestRoute.stream().mapToDouble(a -> a.getPoint().getY()).max().getAsDouble();
            List<ManagerPlanTableAndMapView> mapItems = new ArrayList<>();
            List<ManagerPlanTableAndMapView> tableItems = new ArrayList<>();
            PointWrapper prevPoint = bestRoute.get(bestRoute.size() - 1);
            LocalTime estimatedTime = deliverySchedule.getEstimatedStartTime();
            for (PointWrapper point : bestRoute) {
                mapItems.add(new ManagerPlanTableAndMapView(seq,
                        "",
                        null,
                        null,
                        (point.getPoint().getX() - minX) * MAP_RATIO + offset,
                        (point.getPoint().getY() - minY) * MAP_RATIO + offset,
                        (prevPoint.getPoint().getX() - minX) * MAP_RATIO + offset,
                        (prevPoint.getPoint().getY() - minY) * MAP_RATIO + offset,
                        office));
                if (point.getDelivery() != null) {
                    estimatedTime = estimatedTime.plusMinutes(Math.round(prevPoint.getPoint().distance(point.getPoint())));
                    tableItems.add(new ManagerPlanTableAndMapView(
                            seq,
                            deliverySchedule.getDeliveryTypeId() == 1 ? point.getDelivery().getSenderAddress() : point.getDelivery().getDestinationAddress(),
                            estimatedTime,
                            deliverySchedule.getDeliveryTypeId() == 1 ? point.getDelivery().getCollectionDatetime() : point.getDelivery().getDeliveryDatetime(),
                            0, 0, 0, 0,
                            office));
                }
                prevPoint = point;
                seq++;
            }

            //set view
            ManagerDeliverySchedulePlanView view = new ManagerDeliverySchedulePlanView();
            view.setMaxWidth((maxX - minX) * MAP_RATIO + offset);
            view.setMaxHeight((maxY - minY) * MAP_RATIO + offset);
            view.setOfficeX((office.getxLocation() - minX) * MAP_RATIO + offset);
            view.setOfficeY((office.getyLocation() - minY) * MAP_RATIO + offset);
            view.setId(id);
            view.setOffice(office);
            view.setStartTime(deliverySchedule.getActualStartTime());
            view.setCost(getCost(bestRoute));
            view.setTableItems(tableItems);
            view.setMapItems(mapItems);
            view.setCost(isCost);
            view.setEmployeeId(employeeId);
            view.setDate(date);
            model.addAttribute("view", view);
        }
        return "manager_delivery_schedule_plan";
    }

    private long getCost(List<PointWrapper> points) {
        double dist = TwoOpt.getRouteLength(points);
        return Math.round(dist / 60 * 360);
    }
}
