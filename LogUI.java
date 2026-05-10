import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class LogUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Log System");
        frame.setSize(500, 400);
        frame.setLayout(null);

        JLabel levelLabel = new JLabel("Filter Level:");
        levelLabel.setBounds(30, 30, 120, 30);
        frame.add(levelLabel);

        String[] levels = {"INFO", "ERROR", "WARNING"};
        JComboBox<String> levelBox = new JComboBox<>(levels);
        levelBox.setBounds(150, 30, 150, 30);
        frame.add(levelBox);

        JButton viewBtn = new JButton("View Logs");
        viewBtn.setBounds(150, 80, 120, 30);
        frame.add(viewBtn);

        JTextArea area = new JTextArea();
        area.setBounds(30, 130, 420, 200);
        frame.add(area);

        viewBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String level = (String) levelBox.getSelectedItem();

                    Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/monitoring_system",
                        "root",
                        "Yashvi2108"
                    );

                    Statement stmt = conn.createStatement();

                    String query = "SELECT * FROM logs WHERE level='" + level + "'";
                    ResultSet rs = stmt.executeQuery(query);

                    area.setText("");

                    while (rs.next()) {
                        area.append(
                            rs.getInt("id") + " | " +
                            rs.getString("level") + " | " +
                            rs.getString("message") + "\n"
                        );
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error!");
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }
}