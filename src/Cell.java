import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton {
    private Cell cell = this;
    private int x;
    private int y;
    private boolean obstacle = false;
    private float fCost = Float.MAX_VALUE;
    private float gCost;
    private float hCost;
    private Cell prev = null;
    private final static Color green = new Color(0, 255, 0);
    private final static Color red = new Color(255, 0, 0);
    private final static Color gray = new Color(108, 108, 108);
    private final static Color black = new Color(0, 0, 0);
    private ActionListener cellHandler;
    public int getXCoord() {
        return x;
    }

    public int getYCoord() {
        return y;
    }
    public boolean getObstacle() {
        return obstacle;
    }

    public float getFCost() {
        return fCost;
    }

    public void setFCost(float value) {
        fCost = value;
    }
    public float getGCost() {
        return gCost;
    }
    public void setGCost(float value) {
        gCost = value;
    }
    public float getHCost() {
        return hCost;
    }
    public void setHCost(float value) {
        hCost = value;
    }

    public void updateFCost() {
        fCost = gCost + hCost;
    }

    public Cell getPrev() {
        return prev;
    }

    public void setPrev(Cell value) {
        prev = value;
    }

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
                if (cell == Algorithms.start) {
                    Algorithms.start = null;
                }
                else if (cell == Algorithms.end) {
                    Algorithms.end = null;
                }

                switch (Menu.current) {
                    case start -> {
                        if (Algorithms.start != null) {
                            Algorithms.start.setBackground(gray);
                        }
                        Algorithms.start = cell;
                        setBackground(green);
                        obstacle = false;
                    }

                    case end -> {
                        if (Algorithms.end != null) {
                            Algorithms.end.setBackground(gray);
                        }
                        Algorithms.end = cell;
                        setBackground(red);
                        obstacle = false;
                    }

                    case obstacle -> {
                        setBackground(black);
                        obstacle = true;
                    }
                    default -> {
                        setBackground(gray);
                        obstacle = false;
                    }
                }
            }
        };
        addActionListener(cellHandler);
    }

    public float distanceTo(Cell other) {
        float xDiff = other.x - x;
        float yDiff = other.y - y;
        return (float)(Math.sqrt(xDiff * xDiff + yDiff * yDiff));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cell)) {
            return false;
        }
        return (this.x == ((Cell) o).getXCoord() && this.y == ((Cell) o).getYCoord());
    }
}
