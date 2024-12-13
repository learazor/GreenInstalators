package com.via.greeninstalators.repository;

import com.via.greeninstalators.model.CompanyInstallation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyInstallationRepository extends JpaRepository<CompanyInstallation, Long> {
      
        //Finds installations by company code.
        List<CompanyInstallation> findByCompanyCode(String companyCode);
    
        //Finds installations by type.
        List<CompanyInstallation> findByType(String type);
    
        //Finds installations by company code with pagination support.
        Page<CompanyInstallation> findByCompanyCode(String companyCode, Pageable pageable);
    
        //Finds installations by type with pagination support.
        Page<CompanyInstallation> findByType(String type, Pageable pageable);
    
        //Finds distinct types of installations for a given company code.
        List<String> findDistinctTypeByCompanyCode(String companyCode);
    
        //Counts the number of installations for a given company code.
        long countByCompanyCode(String companyCode);
}
