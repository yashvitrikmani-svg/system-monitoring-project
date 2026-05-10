import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class Dashboard {

    static JLabel total, info, error, warning;
    static JTextArea area;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Live Dashboard");
        frame.setSize(600, 450);
        frame.setLayout(null);

        JLabel title = new JLabel("LIVE SYSTEM DASHBOARD");
        title.setBounds(200, 20, 250, 30);
        frame.add(title);

        total = new JLabel();
        total.setBounds(50, 70, 200, 30);
        frame.add(total);

        info = new JLabel();
        info.setBounds(50, 110, 200, 30);
        frame.add(info);

        error = new JLabel();
        error.setBounds(50, 150, 200, 30);
        frame.add(error);

        warning = new JLabel();
        warning.setBounds(50, 190, 200, 30);
        frame.add(warning);

        area = new JTextArea();
        area.setBounds(250, 70, 300, 250);
        frame.add(area);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(50, 250, 150, 30);
        frame.add(refreshBtn);

        refreshBtn.addActionListener(e -> loadData());

        loadData();

        frame.setVisible(true);
    }

    static void loadData() {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/monitoring_system",
                "root",
                "Yashvi2108"
            );

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM logs");
            rs.next();
            total.setText("Total Logs: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT COUNT(*) FROM logs WHERE level='INFO'");
            rs.next();
            info.setText("INFO: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT COUNT(*) FROM logs WHERE level='ERROR'");
            rs.next();
            error.setText("ERROR: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT COUNT(*) FROM logs WHERE level='WARNING'");
            rs.next();
            warning.setText("WARNING: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT * FROM logs ORDER BY id DESC");

            area.setText("");
            while(rs.next()) {
                area.append(rs.getString("level") + " - " + rs.getString("message") + "\n");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}