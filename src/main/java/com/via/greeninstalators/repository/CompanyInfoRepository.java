package com.via.greeninstalators.repository;

import com.via.greeninstalators.model.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String> {

    //@Query("SELECT c FROM CompanyInfo c WHERE c.companyCode = :companyCode")
    @Query(value = "SELECT * FROM \"companyinfo\" WHERE \"company_code\" = :companyCode", nativeQuery = true)
    Optional<CompanyInfo> findByCompanyCode(@Param("companyCode") String companyCode);


}
