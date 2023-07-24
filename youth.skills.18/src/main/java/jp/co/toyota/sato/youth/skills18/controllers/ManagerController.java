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
    //依存関係の注入
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

    /*管理者メニューの表示
     * 引数には管理者のIDが入る*/
    @GetMapping("menu")
    public String getMenu(Model model, int id) {
        model.addAttribute("id", id);
        return "manager_menu";
    }

    /*配達員スケジュールの日付変更時に呼ばれる。
     * Postメソッドなので引数の値はBodyに含まれる*/
    @PostMapping("deliveryman/schedule/view")
    public String deliverymanScheduleView(Model model, int id, LocalDate date) {

        //更新した情報で配達員スケジュールを再描画
        return deliverymanSchedule(model, id, date);
    }

    /*配達員スケジュールを表示する
     * 引数のDateが空だった場合今日の日付をいれる。*/
    @GetMapping("deliveryman/schedule")
    public String deliverymanSchedule(Model model, int id, LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }

        //必要な情報を取得
        Employee employee = employeeRepository.findById(id).orElseThrow();
        Office office = officeRepository.findById(employee.getOfficeId()).orElseThrow();
        List<Employee> employees = employeeRepository.findAllByOfficeIdAndIsAdminIsFalse(employee.getOfficeId());
        List<DeliveryType> deliveryTypes = deliveryTypeRepository.findAll();

        //画面で使うListの生成
        List<DeliveryTypeWrapper> deliveryTypeWrappers = new ArrayList<>();
        List<LocalTime> times = new ArrayList<>();
        List<ManagerDeliverymanScheduleTableView> tableViews = new ArrayList<>();

        //色を動的に生成するためにRandomインスタンスを生成
        Random r = new Random();
        for (DeliveryType deliveryType : deliveryTypes) {

            //動的に色を生成
            //今回はマニュアルで設定してください。
            String color = String.format("rgb(%d, %d, %d)", 255 - (r.nextInt(1, 5) * 40), 255 - (r.nextInt(1, 5) * 40), 255 - (r.nextInt(1, 5) * 40));

            //色とタイプをセットにし、画面で扱いやすくするためにWrapperクラスを生成
            deliveryTypeWrappers.add(new DeliveryTypeWrapper(deliveryType.getId(), deliveryType.getName(), color));
        }

        //テーブルのカラムを生成するために時間リストを生成する。
        int count = 0;

        //管理者が所属する営業所の全配達員のスケジュールを突き合わせる
        for (Employee employee1 : employees) {
            List<DeliverySchedule> deliverySchedules = deliveryScheduleRepository.findAllByEmployeeIdAndEstimatedDate(employee1.getId(), date);
            List<String> items = new ArrayList<>();

            //8:00 ~ 18:30でfor文を回したいため、終了は18:31を指定する。
            for (LocalTime time = LocalTime.of(8, 0); time.isBefore(LocalTime.of(18, 31)); time = time.plusMinutes(30)) {

                //countが0の時（初回）のみ時間リストに時間を追加する。
                if (count == 0) {
                    times.add(time);
                }
                LocalTime finalTime = time;

                //該当の時間に作業が入っていればtrue入っていなければfalse
                var tmp = deliverySchedules.stream().filter(a -> a.getEstimatedStartTime().isBefore(finalTime.plusMinutes(1)) && a.getEstimatedEndTime().isAfter(finalTime)).findFirst();
                if (tmp.isPresent()) {

                    //作業が入っている場合はその作業のタイプに合わせて色を入れる
                    items.add(deliveryTypeWrappers.stream().filter(b -> b.getId() == tmp.get().getDeliveryTypeId()).findFirst().orElseThrow().getColor());
                } else {

                    //作業が入ってない場合はそのセルを#bbbで塗りつぶす。
                    items.add("#bbb");
                }
            }

            //テーブル（表）表示の時に利用するリストに値を追加。配達員名をKey、配達員のスケジュール情報をValueとして保存している。
            tableViews.add(new ManagerDeliverymanScheduleTableView(employee1.getName(), items));
            count++;
        }

        //画面を構成するViewに値を表示※MVCを意識した作り。Model:manager_deliveryman_schedule.html, View:viewModel, Controller: ManagerController.
        ManagerDeliverymanScheduleModel viewModel = new ManagerDeliverymanScheduleModel(employee.getId(), office.getName(), date, deliveryTypeWrappers, times, tableViews);
        model.addAttribute("viewModel", viewModel);
        return "manager_deliveryman_schedule";
    }

    /* 配送スケジュールの日付変更時に呼ばれる。
     * Postメソッドなので引数の値はBodyに含まれる*/
    @PostMapping("delivery/schedule/view")
    public String deliveryScheduleView(Model model, int id, LocalDate date) {
        return deliverySchedule(model, id, date);
    }

    /* 配送スケジュールを表示する
     * 引数のDateが空だった場合今日の日付をいれる。*/
    @GetMapping("delivery/schedule")
    public String deliverySchedule(Model model, int id, LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }

        //必要な情報を取得
        Employee employee = employeeRepository.findById(id).orElseThrow();
        Office office = officeRepository.findById(employee.getOfficeId()).orElseThrow();
        List<Employee> employees = employeeRepository.findAllByOfficeIdAndIsAdminIsFalse(employee.getOfficeId());

        //画面で使うListの生成
        List<ManagerDeliveryScheduleTableView> managerDeliveryScheduleTableViews = new ArrayList<>();

        //全配達員のスケジュールを1つのリストで表示する必要があるため全配達員のスケジュールを取得する。
        for (Employee employee1 : employees) {

            //配送タイプが集荷または配達のみ表示する。
            for (DeliverySchedule deliverySchedule : deliveryScheduleRepository.findAllByEmployeeIdAndEstimatedDate(employee1.getId(), date).stream().filter(a -> a.getDeliveryTypeId() == 1 || a.getDeliveryTypeId() == 2).toList()) {
                String type = deliveryTypeRepository.findById(deliverySchedule.getDeliveryTypeId()).orElseThrow().getName();
                long count = deliveryScheduleDetailRepository.findAllByDeliveryScheduleId(deliverySchedule.getId()).stream().filter(a -> a.getDeliveryOrder() == 0).count();
                managerDeliveryScheduleTableViews.add(new ManagerDeliveryScheduleTableView(deliverySchedule.getEstimatedStartTime(), type, count == 0 ? "#8f8" : "#f88", deliverySchedule.getId()));
            }
        }
        //画面を構成するViewに値を表示※MVCを意識した作り。Model:manager_delivery_schedule.html, View:viewModel, Controller: ManagerController.
        ManagerDeliveryScheduleModel viewModel = new ManagerDeliveryScheduleModel(office.getName(), date, managerDeliveryScheduleTableViews, id);
        model.addAttribute("viewModel", viewModel);
        return "manager_delivery_schedule";
    }

    @PostMapping("delivery/plan/submit")
    public String submit(Model model, boolean isCost, LocalDate date, int employeeId, int id) throws Exception {
        deliverySchedulePlan(model, employeeId, id, isCost, date, true);
        return "redirect:/manager/delivery/schedule?id=" + employeeId + "&date=" + date;
    }

    /*
     * 配送スケジュール計画画面
     * リストをBodyに含めてPostする方法が分からないためPostの際にもIsPostをTrueで呼び出す。
     * 共通メソッド
     * また、1つ前の画面戻る際にDateが必要なためクエリパラメータとして取得する必要がある。
     */
    @GetMapping("delivery/schedule/plan")
    public String deliverySchedulePlan(Model model, int employeeId, int id, @RequestParam(defaultValue = "True") boolean isCost, LocalDate date, @RequestParam(defaultValue = "false") boolean isPost) throws Exception {

        //必要な情報を取得
        //id指定で必ずとれる必要がある値が取れない場合はExceptionをスローする。スローされたExceptionはerror.htmlにてキャッチされる。
        DeliverySchedule deliverySchedule = deliveryScheduleRepository.findById(id).orElseThrow();
        Employee employee = employeeRepository.findById(deliverySchedule.getEmployeeId()).orElseThrow();
        Office office = officeRepository.findById(employee.getOfficeId()).orElseThrow();
        List<DeliveryScheduleDetail> deliveryScheduleDetails = deliveryScheduleDetailRepository.findAllByDeliveryScheduleId(id);
        if (deliveryScheduleDetails.size() == 0) throw new Exception("配送スケジュール詳細が存在しません。");

        //画面で使うListを生成
        List<PointWrapper> points = new ArrayList<>();

        //アルゴリズムにより経路決定する為の準備（Point、Deliveryの情報を保持するWrapperクラスを生成。）
        PointWrapper startAndEndPoint = new PointWrapper(new Point(office.getxLocation(), office.getyLocation()), null);
        for (DeliveryScheduleDetail deliveryScheduleDetail : deliveryScheduleDetails) {
            Delivery delivery = deliveryRepository.findById(deliveryScheduleDetail.getDeliveryId()).orElseThrow();
            Zipcode zipcode;

            //集荷の場合はSender、それ以外の場合はDestinationの値を追加
            if (deliverySchedule.getDeliveryTypeId() == 1) {
                zipcode = zipcodeRepository.findById(delivery.getSenderZipcode()).orElseThrow();
            } else {
                zipcode = zipcodeRepository.findById(delivery.getDestinationZipcode()).orElseThrow();
            }
            int x = zipcode.getxLocation();
            int y = zipcode.getyLocation();
            points.add(new PointWrapper(new Point(x, y), delivery));
        }

        //希望時間優先の場合始点と終点として営業所を追加してしまうと順番が狂うため先に希望時間を基準に並べ替える。
        if (!isCost) {

            //集荷の場合は集荷時間、配達の場合は配達時間を基準にソート
            if (deliverySchedule.getDeliveryTypeId() == 1) {
                points.sort(Comparator.comparing(a -> a.getDelivery().getCollectionDatetime()));
            } else {
                points.sort(Comparator.comparing(a -> a.getDelivery().getDeliveryDatetime()));
            }
        }

        //始点と終点を追加
        points.add(0, startAndEndPoint);
        points.add(startAndEndPoint);

        //コスト優先の場合はTwoOptアルゴリズムをもとに並べ替えた結果を取得。希望時間優先の場合はすでに並び変えてあるのでその結果を使用
        List<PointWrapper> bestRoute = isCost ? TwoOpt.getCalculatedRoute(points) : points;

        //登録モードの場合はbestRouteの情報を基にDeliveryOrderをUpdateする。
        if (isPost) {
            int seq = 1;
            for (PointWrapper point : bestRoute) {

                //配送元の営業所はgetDelivery()を実行するとNullが返ってくるのでそれ以外の時にUpdateを実行
                if (point.getDelivery() != null) {
                    DeliveryScheduleDetail deliveryScheduleDetail = deliveryScheduleDetails.stream().filter(a -> a.getDeliveryId().equals(point.getDelivery().getId()) && a.getDeliveryScheduleId() == id).findFirst().orElseThrow();
                    deliveryScheduleDetail.setDeliveryOrder(seq++);
                    deliveryScheduleDetailRepository.save(deliveryScheduleDetail);
                }
            }
        } else {

            //bestRouteをもとにMap表示用とTable表示用のリストを生成する
            int seq = 0;

            //Mapの拡大倍率
            final int MAP_RATIO = 35;

            //左上基準で始まるがオフセットがないと描画が見切れてしまうため設定
            final int offset = 10;

            //位置調整のために最大値と最小値を取得
            double minX = bestRoute.stream().mapToDouble(a -> a.getPoint().getX()).min().getAsDouble();
            double minY = bestRoute.stream().mapToDouble(a -> a.getPoint().getY()).min().getAsDouble();
            double maxX = bestRoute.stream().mapToDouble(a -> a.getPoint().getX()).max().getAsDouble();
            double maxY = bestRoute.stream().mapToDouble(a -> a.getPoint().getY()).max().getAsDouble();

            //リストを初期化
            List<ManagerPlanTableAndMapView> mapItems = new ArrayList<>();
            List<ManagerPlanTableAndMapView> tableItems = new ArrayList<>();

            //始点と終点に営業所のポイント、同一ポイントが入っているが、描画上問題ないのでひとつ前のポイントとして0番目を取得
            PointWrapper prevPoint = bestRoute.get(0);
            LocalTime estimatedTime = deliverySchedule.getEstimatedStartTime();
            for (PointWrapper point : bestRoute) {

                //Mapに描画する際は営業所の情報も必要なのですべてをListに追加
                mapItems.add(new ManagerPlanTableAndMapView(seq,
                        "",
                        null,
                        null,
                        (point.getPoint().getX() - minX) * MAP_RATIO + offset,
                        (point.getPoint().getY() - minY) * MAP_RATIO + offset,
                        (prevPoint.getPoint().getX() - minX) * MAP_RATIO + offset,
                        (prevPoint.getPoint().getY() - minY) * MAP_RATIO + offset,
                        office));

                //Table表示には営業所の情報が必要ないためDeliveryがNull以外のみListに追加
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

            //画面を構成するViewに値を表示※MVCを意識した作り。Model:manager_delivery_schedule_plan.html, View:viewModel, Controller: ManagerController.
            ManagerDeliverySchedulePlanModel viewModel = new ManagerDeliverySchedulePlanModel(
                    office,
                    deliverySchedule.getEstimatedStartTime(),
                    getCost(bestRoute),
                    deliverySchedule.getId(),
                    mapItems,
                    tableItems,
                    isCost,
                    (maxX - minX) * MAP_RATIO + offset,
                    (maxY - minY) * MAP_RATIO + offset,
                    (office.getxLocation() - minX) * MAP_RATIO + offset,
                    (office.getyLocation() - minY) * MAP_RATIO + offset,
                    employeeId,
                    date);
            model.addAttribute("viewModel", viewModel);
        }
        return "manager_delivery_schedule_plan";
    }

    //指定された経路で回った際のコストを取得する
    private long getCost(List<PointWrapper> points) {
        double dist = TwoOpt.getRouteLength(points);

        //前提として時速60Km/hなので1km=1minが成立するため単純にdistanceを60で割ると時間に直すことができる。
        return Math.round(dist / 60 * 360);
    }
}
