package com.via.greeninstalators.model;

public record CompanyInfo(
    String name,
    String country,
    String postalCode,
    double pricePerDistanceUnit,
    String companyCode
) {}
