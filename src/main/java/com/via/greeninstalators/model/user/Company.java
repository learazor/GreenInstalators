package com.via.greeninstalators.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
@Table(name = "\"AspNetUsers\"")
public class Company implements Serializable {

    @Id
    @Column(name = "\"Id\"", nullable = false, unique = true)
    private String Id;

    @Column(name = "\"Email\"", nullable = false, unique = true)
    private String Email;

    @Column(name = "\"PasswordHash\"", nullable = false)
    private String PasswordHash;

    @Column(name = "\"CompanyCode\"", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + Id + '\'' +
                ", email='" + Email + '\'' +
                ", company code='" + CompanyCode + '\'' +
                '}';
    }
}