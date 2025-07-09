import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JuliaLSys extends JFrame implements ActionListener {
    private JButton juliaButton, lsysButton;
    
    public JuliaLSys() {
        setTitle("Fractal Generator - Julia Set and L-System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        
        setLayout(new FlowLayout());
        
        juliaButton = new JButton("Launch Julia Set Generator");
        lsysButton = new JButton("Launch L-System Generator");
        
        juliaButton.addActionListener(this);
        lsysButton.addActionListener(this);
        
        add(juliaButton);
        add(lsysButton);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == juliaButton) {
            new Julia();
        } else if (e.getSource() == lsysButton) {
            try {
                new Turtle();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuliaLSys());
    }
}
