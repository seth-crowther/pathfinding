import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Grid {
    private static Cell[][] grid;
    private JPanel panel;
    private static int sideLength;
    private static int numSquares;
    private static Dimension cellSize;

    public static Cell[][] getGrid() {
        return grid;
    }

    public Grid(JPanel panel) {
        this.panel = panel;
        sideLength = 400;
        numSquares = 20;
        cellSize = new Dimension(sideLength / numSquares, sideLength / numSquares);
        grid = new Cell[numSquares][numSquares];
        panel.setLayout(new GridLayout(numSquares, numSquares));
        createGrid();
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

    public static boolean isInGrid(int x, int y) {
        return (x >= 0 && x < numSquares && y >= 0 && y < numSquares);
    }

    public static Set<Cell> getAdjacentCells(Cell input) {
        Set<Cell> toReturn = new HashSet<>();
        int x = input.getXCoord();
        int y = input.getYCoord();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isInGrid(i, j) && !grid[i][j].getObstacle()) {
                    toReturn.add(grid[i][j]);
                }
            }
        }
        return toReturn;
    }



    public static void resetCells() {
        for (Cell[] row: Grid.getGrid()) {
            for (Cell c: row) {
                c.setFCost(Float.MAX_VALUE);
                c.setGCost(0);
                c.setPrev(null);
                if (c.getBackground() == Color.BLUE) {
                    c.setColor(Cell.backgroundColor.gray);
                }
            }
        }
    }
}
