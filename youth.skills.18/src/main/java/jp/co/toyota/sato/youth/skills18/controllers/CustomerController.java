package jp.co.toyota.sato.youth.skills18.controllers;

import jp.co.toyota.sato.youth.skills18.entities.Delivery;
import jp.co.toyota.sato.youth.skills18.entities.DeliveryStatus;
import jp.co.toyota.sato.youth.skills18.entities.Office;
import jp.co.toyota.sato.youth.skills18.models.CustomerDeliveryStatusModel;
import jp.co.toyota.sato.youth.skills18.models.CustomerDeliveryStatusView;
import jp.co.toyota.sato.youth.skills18.repositories.DeliveryRepository;
import jp.co.toyota.sato.youth.skills18.repositories.DeliveryStatusRepository;
import jp.co.toyota.sato.youth.skills18.repositories.DeliveryStatusTypeRepository;
import jp.co.toyota.sato.youth.skills18.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private DeliveryStatusRepository deliveryStatusRepository;
    @Autowired
    private DeliveryStatusTypeRepository deliveryStatusTypeRepository;
    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @GetMapping("menu")
    public String getMenu(Model model) {
        return "customer_menu";
    }

    @GetMapping("delivery/status")
    public String getDeliveryStatus(Model model, CustomerDeliveryStatusModel viewModel) {
        model.addAttribute("viewModel", viewModel);
        return "customer_delivery_status";
    }

    @PostMapping("post/search")
    public String postSearch(Model model, String id) {
        List<DeliveryStatus> deliveryStatuses = deliveryStatusRepository.findAllByDeliveryId(id);
        //deliveryStatuses.sort(Comparator.comparing(DeliveryStatus::getDatetime).reversed());//降順
        deliveryStatuses.sort(Comparator.comparing(DeliveryStatus::getDatetime));//昇順
        List<CustomerDeliveryStatusView> items = new ArrayList<>();
        for (DeliveryStatus deliveryStatus : deliveryStatuses) {
            String status = deliveryStatusTypeRepository.findById(deliveryStatus.getDeliveryStatusTypeId()).orElseThrow().getName();
            String office = officeRepository.findById(deliveryStatus.getOfficeId() == null ? 0 : deliveryStatus.getOfficeId()).orElse(new Office()).getName();
            items.add(new CustomerDeliveryStatusView(
                    status, deliveryStatus.getDatetime(), office
            ));
        }
        CustomerDeliveryStatusModel viewModel;
        if (items.size() == 0) {
            viewModel = new CustomerDeliveryStatusModel();
            model.addAttribute("error", "該当する配送情報がありません。");
        } else {
            String status = switch (deliveryStatuses.stream().mapToInt(DeliveryStatus::getDeliveryStatusTypeId).max().getAsInt()) {
                case 1 -> "集荷前";
                case 7 -> "配達済み";
                default -> "配送中";
            };
            Delivery delivery = deliveryRepository.findById(id).orElseThrow();
            viewModel = new CustomerDeliveryStatusModel(items, id, status, delivery.getDeliveryDatetime());
        }
        return getDeliveryStatus(model, viewModel);
    }
}
