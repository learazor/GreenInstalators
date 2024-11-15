package com.via.greeninstalators.repository;

import com.via.greeninstalators.model.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyInfo, String> {

    Optional<CompanyInfo> findByCompanyCode(String companyCode);
    //if needed
    @Query("SELECT c FROM CompanyInfo c WHERE c.companyCode = :companyCode")
    Optional<CompanyInfo> getCompanyInfoByCode(@Param("companyCode") String companyCode);
}
