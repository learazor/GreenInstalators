package com.via.greeninstalators.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "InstallationOrders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String companyCode;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String clientEmail;

    @Column(nullable = false)
    private Double totalCost;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCompanyCode() { return companyCode; }
    public void setCompanyCode(String companyCode) { this.companyCode = companyCode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }
}
