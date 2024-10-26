package com.via.greeninstalators.model;

public record CompanyInstallation(
    String type,
    double output,
    int settingUpTimePerUnit,
    double pricePerUnit,
    String companyCode
) {}
