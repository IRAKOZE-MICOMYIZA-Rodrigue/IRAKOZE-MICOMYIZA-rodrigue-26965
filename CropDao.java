package dao;

import model.Crop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CropDao {
    public static boolean addCrop(Crop crop) {
        String sql = "INSERT INTO crops (user_id, crop_type_id, crop_name, planting_date, harvest_date, growth_status, quantity_harvested) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, crop.getUserId());
            ps.setInt(2, crop.getCropTypeId());
            ps.setString(3, crop.getCropName());
            
            // Handle dates properly for SQLite
            if (crop.getPlantingDate() != null) {
                ps.setString(4, crop.getPlantingDate().toString());
            } else {
                ps.setNull(4, Types.DATE);
            }
            
            if (crop.getHarvestDate() != null) {
                ps.setString(5, crop.getHarvestDate().toString());
            } else {
                ps.setNull(5, Types.DATE);
            }
            
            ps.setString(6, crop.getGrowthStatus());
            ps.setDouble(7, crop.getQuantityHarvested());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static List<Crop> getByUser(int userId) {
        List<Crop> crops = new ArrayList<>();
        String sql = "SELECT * FROM crops WHERE user_id = ? ORDER BY planting_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Crop crop = extractCropFromResultSet(rs);
                crops.add(crop);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return crops;
    }
    
    public static List<Crop> getAll() {
        List<Crop> crops = new ArrayList<>();
        String sql = "SELECT * FROM crops ORDER BY planting_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Crop crop = extractCropFromResultSet(rs);
                crops.add(crop);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return crops;
    }
    
    public static boolean updateHarvest(int cropId, double harvestedQuantity, Date harvestDate, String status) {
        String sql = "UPDATE crops SET quantity_harvested = quantity_harvested + ?, harvest_date = ?, growth_status = ? WHERE crop_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, harvestedQuantity);
            
            // Handle date properly for SQLite
            if (harvestDate != null) {
                ps.setString(2, harvestDate.toString());
            } else {
                ps.setNull(2, Types.DATE);
            }
            
            ps.setString(3, status);
            ps.setInt(4, cropId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static Crop findById(int cropId) {
        String sql = "SELECT * FROM crops WHERE crop_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cropId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractCropFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    // Helper method to extract Crop from ResultSet with proper date handling
    private static Crop extractCropFromResultSet(ResultSet rs) throws SQLException {
        Crop crop = new Crop();
        crop.setCropId(rs.getInt("crop_id"));
        crop.setUserId(rs.getInt("user_id"));
        crop.setCropTypeId(rs.getInt("crop_type_id"));
        crop.setCropName(rs.getString("crop_name"));
        
        // Handle dates safely for SQLite
        try {
            String plantingDateStr = rs.getString("planting_date");
            if (plantingDateStr != null && !plantingDateStr.trim().isEmpty()) {
                // If it's a timestamp (number), convert it
                if (plantingDateStr.matches("\\d+")) {
                    long timestamp = Long.parseLong(plantingDateStr);
                    crop.setPlantingDate(new Date(timestamp));
                } else {
                    // If it's already in proper format
                    crop.setPlantingDate(Date.valueOf(plantingDateStr));
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing planting date: " + e.getMessage());
            crop.setPlantingDate(null);
        }
        
        try {
            String harvestDateStr = rs.getString("harvest_date");
            if (harvestDateStr != null && !harvestDateStr.trim().isEmpty()) {
                // If it's a timestamp (number), convert it
                if (harvestDateStr.matches("\\d+")) {
                    long timestamp = Long.parseLong(harvestDateStr);
                    crop.setHarvestDate(new Date(timestamp));
                } else {
                    // If it's already in proper format
                    crop.setHarvestDate(Date.valueOf(harvestDateStr));
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing harvest date: " + e.getMessage());
            crop.setHarvestDate(null);
        }
        
        crop.setGrowthStatus(rs.getString("growth_status"));
        crop.setQuantityHarvested(rs.getDouble("quantity_harvested"));
        return crop;
    }
    
    // Additional utility methods for statistics
    public static int getTotalCropsCount() {
        String sql = "SELECT COUNT(*) FROM crops";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public static int getActiveCropsCount() {
        String sql = "SELECT COUNT(*) FROM crops WHERE growth_status IN ('growing', 'harvested')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public static double getTotalHarvestQuantity() {
        String sql = "SELECT SUM(quantity_harvested) FROM crops WHERE quantity_harvested > 0";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static boolean deleteCrop(int cropId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}