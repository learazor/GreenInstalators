package com.via.greeninstalators.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(CompanyInstallation.class)
@Table(name = "companyinstallation")
public class CompanyInstallation implements Serializable {

    @Id
    private String type;

    @Id
    private String company_code;

    private double output;
    private int setting_up_time_per_unit;
    private double price_per_unit;

    //for JPA
    protected CompanyInstallation() {
    }


    public CompanyInstallation(String type, String company_code, double output, int setting_up_time_per_unit, double price_per_unit) {
        this.type = type;
        this.company_code = company_code;
        this.output = output;
        this.setting_up_time_per_unit = setting_up_time_per_unit;
        this.price_per_unit = price_per_unit;
    }

    public String getType() {
        return type;
    }

    public String getCompanyCode() {
        return company_code;
    }

    public double getOutput() {
        return output;
    }

    public int getSettingUpTimePerUnit() {
        return setting_up_time_per_unit;
    }

    public double getPricePerUnit() {
        return price_per_unit;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public void setSetting_up_time_per_unit(int setting_up_time_per_unit) {
        this.setting_up_time_per_unit = setting_up_time_per_unit;
    }

    public void setPrice_per_unit(double price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyInstallation that = (CompanyInstallation) o;
        return Objects.equals(type, that.type) && Objects.equals(company_code, that.company_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, company_code);
    }
}
