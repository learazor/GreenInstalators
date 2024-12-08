package com.via.greeninstalators.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"AspNetUsers\"")
public class Company {

    @Id
    @Column(name = "\"Id\"")
    private String Id;

    @Column(name = "\"Email\"")
    private String Email;
    @Column(name = "\"PasswordHash\"")
    private String PasswordHash;
    @Column(name = "\"CompanyCode\"")
    private String CompanyCode;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.PasswordHash = passwordHash;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.CompanyCode = companyCode;
    }
}
