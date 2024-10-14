package proiect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/oop2024", 
                connectionProps
        );

        String alienSpecies = "Zorgon";
        String invasionReason = "Conquer Earth";
        int numberOfUFOs = 5;

        String sqlInsertAlienInvasion = "INSERT INTO alienInvasion (alienSpecies, invasionReason, numberOfUFOs) VALUES (?,?,?)";
        PreparedStatement psAlienInvasion = conn.prepareStatement(sqlInsertAlienInvasion);
        psAlienInvasion.setString(1, alienSpecies);
        psAlienInvasion.setString(2, invasionReason);
        psAlienInvasion.setInt(3, numberOfUFOs);

        int rowsAffectedAlienInvasion = psAlienInvasion.executeUpdate();
        System.out.println("Rows affected (alienInvasion): " + rowsAffectedAlienInvasion);

        String callReason = "Alien Attack";
        String requesterName = "John Doe";
        int urgencyLevel = 10;

        String sqlInsertEmergencyCall = "INSERT INTO emergencyCall (callReason, requesterName, urgencyLevel) VALUES (?,?,?)";
        PreparedStatement psEmergencyCall = conn.prepareStatement(sqlInsertEmergencyCall);
        psEmergencyCall.setString(1, callReason);
        psEmergencyCall.setString(2, requesterName);
        psEmergencyCall.setInt(3, urgencyLevel);

        int rowsAffectedEmergencyCall = psEmergencyCall.executeUpdate();
        System.out.println("Rows affected (emergencyCall): " + rowsAffectedEmergencyCall);

        System.out.println("Table alienInvasion:");
        Statement stmtAlienInvasion = conn.createStatement();
        ResultSet rsAlienInvasion = stmtAlienInvasion.executeQuery("SELECT * FROM alienInvasion");
        
        while (rsAlienInvasion.next()) {
            System.out.println(rsAlienInvasion.getString("alienSpecies") + " | "
                               + rsAlienInvasion.getString("invasionReason") + " | "
                               + rsAlienInvasion.getInt("numberOfUFOs"));
        }

        System.out.println("Table emergencyCall:");
        Statement stmtEmergencyCall = conn.createStatement();
        ResultSet rsEmergencyCall = stmtEmergencyCall.executeQuery("SELECT * FROM emergencyCall");
        
        while (rsEmergencyCall.next()) {
            System.out.println(rsEmergencyCall.getString("callReason") + " | "
                               + rsEmergencyCall.getString("requesterName") + " | "
                               + rsEmergencyCall.getInt("urgencyLevel"));
        }

        String newInvasionReason = "Peace Treaty";
        String alienSpeciesToUpdate = "Zorgon";

        String sqlUpdateAlienInvasion = "UPDATE alienInvasion SET invasionReason = ? WHERE alienSpecies = ?";
        PreparedStatement psUpdateAlienInvasion = conn.prepareStatement(sqlUpdateAlienInvasion);
        psUpdateAlienInvasion.setString(1, newInvasionReason);
        psUpdateAlienInvasion.setString(2, alienSpeciesToUpdate);
        psUpdateAlienInvasion.executeUpdate();
        System.out.println("Updated invasionReason for alienSpecies: " + alienSpeciesToUpdate);

        String requesterNameToDelete = "John Doe";

        String sqlDeleteEmergencyCall = "DELETE FROM emergencyCall WHERE requesterName = ?";
        PreparedStatement psDeleteEmergencyCall = conn.prepareStatement(sqlDeleteEmergencyCall);
        psDeleteEmergencyCall.setString(1, requesterNameToDelete);
        psDeleteEmergencyCall.executeUpdate();
        System.out.println("Deleted emergencyCall for requesterName: " + requesterNameToDelete);

        conn.close();
    }
}

