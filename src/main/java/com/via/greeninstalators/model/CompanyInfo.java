package com.via.greeninstalators.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(CompanyInfo.class)
@Table(name = "companyinfo")
public class CompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "postal_code")
    private String postal_code;
    
    @Column(name = "price_per_distance_unit")
    private double price_per_distance_unit;

    //default constructor for JPA
    protected CompanyInfo() {}

    public CompanyInfo(String companyCode, String name, String country, String postal_code, double price_per_distance_unit) {
        if (companyCode == null || companyCode.isEmpty()) {
            throw new IllegalArgumentException("Company code cannot be null or empty");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.companyCode = companyCode;
        this.name = name;
        this.country = country;
        this.postal_code = postal_code;
        this.price_per_distance_unit = price_per_distance_unit;
    }

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
        return postal_code;
    }

    public double getPricePerDistanceUnit() {
        return price_per_distance_unit;
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
    
    @Override
        public String toString() {
            return "CompanyInfo{" +
                    "companyCode='" + companyCode + '\'' +
                    ", name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    ", postal code='" + postal_code + '\'' +
                    ", price per distanceUnit=" + price_per_distance_unit +
                    '}';
        }
}
