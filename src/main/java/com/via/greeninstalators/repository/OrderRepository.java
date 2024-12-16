package com.via.greeninstalators.repository;

import com.via.greeninstalators.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(value = "SELECT * FROM \"InstallationOrders\" WHERE \"CompanyCode\" = :companyCode", nativeQuery = true)
    List<Order> findByCompanyCode(@Param("companyCode") String companyCode);
}
