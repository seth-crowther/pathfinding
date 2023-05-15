import javax.swing.*;
import java.awt.*;

public class Grid {
    Cell[][] grid;
    JPanel panel;
    private static int sideLength;
    private static int numSquares;
    private static Dimension cellSize;

    public Grid(JPanel panel) {
        this.panel = panel;
        sideLength = 400;
        numSquares = 20;
        cellSize = new Dimension(sideLength / numSquares, sideLength / numSquares);
        grid = new Cell[numSquares][numSquares];
        createPanel();
        createGrid();
    }

    public void createPanel() {
        panel.setLayout(new GridLayout(numSquares, numSquares));
    }

    public void createGrid() {
        for (int x = 0; x < numSquares; x++) {
            for (int y = 0; y < numSquares; y++) {
                Cell newCell = new Cell(x, y);
                newCell.setPreferredSize(cellSize);
                grid[x][y] = newCell;
                panel.add(newCell);
            }
        }
    }
}
