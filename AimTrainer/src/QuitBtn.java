import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuitBtn extends JButton {
    QuitBtn(JFrame win) {
        this.setText("Quit");
        this.setBorderPainted(false);
        this.setBackground(Color.decode("#0CF574"));
        this.setForeground(Color.decode("#231F20"));
        this.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        this.setFocusPainted(false);
        this.addActionListener(openWin);
        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                this.setBackground(Color.decode("#E9D758"));
                this.setForeground(Color.decode("#231F20"));
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent evt) {
                //startButton.setBackground(UIManager.getColor("control"));
                this.setBackground(Color.decode("#0CF574"));
                this.setForeground(Color.decode("#231F20"));
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    ActionListener openWin = new ActionListener() {
        public void actionPerformed(ActionEvent action) {
            dispose();
            GameWindow w = new GameWindow();
            w.setVisible(true);
        }
    };
}
