import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Grid {
    Cell[][] grid;
    JPanel panel;
    private static int sideLength;
    private static int numSquares;
    private static Dimension cellSize;

    public Cell[][] getGrid() {
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

    public Set<Cell> getAdjacentCells(int x, int y) {
        Set<Cell> toReturn = new HashSet<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isInGrid(i, j) && !grid[i][j].getObstacle()) {
                    toReturn.add(grid[i][j]);
                }
            }
        }
        return toReturn;
    }

    public Cell getClosestCell(Cell input) {
        Cell toReturn = null;
        float minDist = Float.MAX_VALUE;
        for (Cell c: getAdjacentCells(input.getXCoord(), input.getYCoord())) {
            if (c.getDist() + input.distanceTo(c) < minDist) {
                minDist = c.getDist() + input.distanceTo(c);
                toReturn = c;
            }
        }
        return toReturn;
    }
}
