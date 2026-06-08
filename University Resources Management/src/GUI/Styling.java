package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Styling {
    public static void styleButton(JButton btn, String hoverHex, String pressHex) {
        // Capture the current color (Green, Red, or Blue) so we can return to it
        Color originalColor = btn.getBackground();

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(Color.decode(hoverHex));
            }

            public void mouseExited(MouseEvent evt) {
                btn.setBackground(originalColor); // Returns to the CORRECT original color
            }

            public void mousePressed(MouseEvent evt) {
                btn.setBackground(Color.decode(pressHex));
            }
        });
    }

    public static void styleButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBorderPainted(false); // Makes it look flatter and more modern

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(Color.decode("#2563EB")); // Darker blue on hover
            }

            public void mouseExited(MouseEvent evt) {
                btn.setBackground(Color.decode("#3B82F6")); // Back to normal
            }

            public void mousePressed(MouseEvent evt) {
                btn.setBackground(Color.decode("#1D4ED8")); // Deepest blue on click
            }
        });
    }
}
