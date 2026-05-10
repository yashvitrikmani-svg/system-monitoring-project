import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

public class DBConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/monitoring_system",
                "root",
                "Yashvi2108"
            );

            Statement stmt = conn.createStatement();

            String[] levels = {"INFO", "ERROR", "WARNING"};
            String[] messages = {
                "System started",
                "Connection failed",
                "Low memory",
                "User login success",
                "File not found"
            };

            Random rand = new Random();

            for (int i = 0; i < 5; i++) {
                String level = levels[rand.nextInt(levels.length)];
                String message = messages[rand.nextInt(messages.length)];

                String query = "INSERT INTO logs (timestamp, level, message) VALUES (NOW(), '" 
                                + level + "', '" + message + "')";

                stmt.executeUpdate(query);

                System.out.println("Inserted: " + level + " - " + message);
            }

            System.out.println("Auto Logging Completed!");

        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
}