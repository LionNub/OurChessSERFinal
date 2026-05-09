// Griffin, Hernest, Jared
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class CapturedSideBar extends JPanel {
    String capturedText = "Captured Pieces:\n";
    String pieceColor = "White";

    public CapturedSideBar() {
        setPreferredSize(new Dimension(196, 128));
        setBackground(new Color(29, 53, 87));
    }

    public CapturedSideBar getCapSideBar(CapturedSideBar bar) {
        return bar;
    }

    public void setCapturedMessage(String piece, String color, Graphics g) {
        capturedText += piece + " (" + color + ")\n";
        java.awt.FontMetrics fm = g.getFontMetrics();
        g.drawString(capturedText, getWidth() - fm.stringWidth(capturedText) - 12, 21);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();
        g.setFont(new Font("Monospaced", Font.BOLD, 16));

        g.setColor(new Color(0xE8C08C));
        // g.drawString(capturedText, 12, 21);

        g.setColor(new Color(0xFF3860));
        java.awt.FontMetrics fm = g.getFontMetrics();
        g.drawString(capturedText, getWidth() - fm.stringWidth(capturedText) - 12, 21);
        g.dispose();
    }
}
