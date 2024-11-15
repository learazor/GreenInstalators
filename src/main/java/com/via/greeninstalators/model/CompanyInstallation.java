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
    private String companyCode;

    private double output;
    private int settingUpTimePerUnit;
    private double pricePerUnit;

    // Default constructor for JPA
    protected CompanyInstallation() {}

    // Constructor
    public CompanyInstallation(String type, String companyCode, double output, int settingUpTimePerUnit, double pricePerUnit) {
        this.type = type;
        this.companyCode = companyCode;
        this.output = output;
        this.settingUpTimePerUnit = settingUpTimePerUnit;
        this.pricePerUnit = pricePerUnit;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public double getOutput() {
        return output;
    }

    public int getSettingUpTimePerUnit() {
        return settingUpTimePerUnit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyInstallation that = (CompanyInstallation) o;
        return Objects.equals(type, that.type) && Objects.equals(companyCode, that.companyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, companyCode);
    }
}
