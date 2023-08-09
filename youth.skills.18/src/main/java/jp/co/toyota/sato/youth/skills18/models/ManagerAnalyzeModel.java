package jp.co.toyota.sato.youth.skills18.models;

import jp.co.toyota.sato.youth.skills18.entities.Office;

import java.time.LocalDate;
import java.util.List;

public class ManagerAnalyzeModel {
    private List<OfficeEarnView> offices;
    private String yearMonth;

    public ManagerAnalyzeModel() {
    }

    public ManagerAnalyzeModel(List<OfficeEarnView> offices, String yearMonth) {
        this.offices = offices;
        this.yearMonth = yearMonth;
    }

    public List<OfficeEarnView> getOffices() {
        return offices;
    }

    public void setOffices(List<OfficeEarnView> offices) {
        this.offices = offices;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

}
