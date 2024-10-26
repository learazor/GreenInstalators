package com.via.greeninstalators.repository;

import com.via.greeninstalators.model.CompanyInfo;
import com.via.greeninstalators.model.CompanyInstallation;
import com.via.greeninstalators.util.DatabaseUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {

    // Save a CompanyInfo object to the database
    public boolean saveCompanyInfo(CompanyInfo companyInfo) {
        String query = "INSERT INTO CompanyInfo (name, country, postal_code, price_per_distance_unit, company_code) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyInfo.name());
            stmt.setString(2, companyInfo.country());
            stmt.setString(3, companyInfo.postalCode());
            stmt.setDouble(4, companyInfo.pricePerDistanceUnit());
            stmt.setString(5, companyInfo.companyCode());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Save a CompanyInstallation object to the database
    public boolean saveCompanyInstallation(CompanyInstallation installation) {
        String query = "INSERT INTO CompanyInstallation (type, output, setting_up_time_per_unit, price_per_unit, company_code) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, installation.type());
            stmt.setDouble(2, installation.output());
            stmt.setInt(3, installation.settingUpTimePerUnit());
            stmt.setDouble(4, installation.pricePerUnit());
            stmt.setString(5, installation.companyCode());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve a CompanyInfo object by company code
    public CompanyInfo getCompanyInfoByCode(String companyCode) {
        String query = "SELECT * FROM CompanyInfo WHERE company_code = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CompanyInfo(
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getString("postal_code"),
                        rs.getDouble("price_per_distance_unit"),
                        rs.getString("company_code")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all installations for a specific company code
    public List<CompanyInstallation> getInstallationsByCompanyCode(String companyCode) {
        String query = "SELECT * FROM CompanyInstallation WHERE company_code = ?";
        List<CompanyInstallation> installations = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                installations.add(new CompanyInstallation(
                        rs.getString("type"),
                        rs.getDouble("output"),
                        rs.getInt("setting_up_time_per_unit"),
                        rs.getDouble("price_per_unit"),
                        rs.getString("company_code")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return installations;
    }

    public List<CompanyInstallation> getInstallationsByType(String type) {
        String query = "SELECT * FROM CompanyInstallation WHERE type = ?";
        List<CompanyInstallation> installations = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                installations.add(new CompanyInstallation(
                        rs.getString("type"),
                        rs.getDouble("output"),
                        rs.getInt("setting_up_time_per_unit"),
                        rs.getDouble("price_per_unit"),
                        rs.getString("company_code")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return installations;
    }

}
