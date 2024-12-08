package com.via.greeninstalators.repository;

import com.via.greeninstalators.model.user.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query(value = "SELECT * FROM \"AspNetUsers\" WHERE \"Email\" = :email", nativeQuery = true)
    Optional<Company> findByEmail(@Param("email") String email);
}
