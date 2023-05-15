import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton {
    private Cell cell = this;
    private int x;
    private int y;
    private static Color green = new Color(0, 255, 0);
    private static Color red = new Color(255, 0, 0);
    private static Color gray = new Color(108, 108, 108);
    private static Color black = new Color(0, 0, 0);
    private ActionListener cellHandler;

    public enum backgroundColor {
        green,
        red,
        gray,
        black
    }

    public void setColor(backgroundColor c) {
        switch (c) {
            case green -> setBackground(green);
            case red -> setBackground(red);
            case gray -> setBackground(gray);
            default -> setBackground(black);
        }
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        setBackground(gray);

        cellHandler = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (Menu.current) {
                    case start:
                        setBackground(green);
                        Algorithms.start = cell;
                        break;
                    case end:
                        setBackground(red);
                        Algorithms.end = cell;
                    case obstacle:
                        setBackground(black);
                    default:
                        setBackground(gray);
                }
            }
        };
    }
}
