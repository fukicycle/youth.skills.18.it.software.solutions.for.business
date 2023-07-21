package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalDateTime;

public class CustomerDeliveryStatusView {
    private String status;
    private LocalDateTime datetime;
    private String office;

    public CustomerDeliveryStatusView(String status, LocalDateTime datetime, String office) {
        this.status = status;
        this.datetime = datetime;
        this.office = office;
    }

    public CustomerDeliveryStatusView() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
