package com.via.greeninstalators.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(CompanyInfo.class)
@Table(name = "companyinfo")
public class CompanyInfo implements Serializable {

    @Id
    private String companyCode;

    private String name;
    private String country;
    private String postalCode;
    private double pricePerDistanceUnit;

    // Default constructor for JPA
    protected CompanyInfo() {}

    // Constructor
    public CompanyInfo(String companyCode, String name, String country, String postalCode, double pricePerDistanceUnit) {
        this.companyCode = companyCode;
        this.name = name;
        this.country = country;
        this.postalCode = postalCode;
        this.pricePerDistanceUnit = pricePerDistanceUnit;
    }

    // Getters
    public String getCompanyCode() {
        return companyCode;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public double getPricePerDistanceUnit() {
        return pricePerDistanceUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyInfo that = (CompanyInfo) o;
        return Objects.equals(companyCode, that.companyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCode);
    }
}
