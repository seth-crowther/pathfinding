import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Algorithms {
    public static Cell start;
    public static Cell end;

    public static void main(String[] args) {
        Canvas c = new Canvas();
    }

    public static void aStar() {

        Grid.resetCells();
        Set<Cell> open = new HashSet<>();
        Set<Cell> closed = new HashSet<>();
        open.add(start);
        Cell current = null;

        while (!open.isEmpty()) {
            current = getNextCell(open);

            // Mark current cell as closed
            open.remove(current);
            closed.add(current);

            if (current == end) {
                break;
            }

            for (Cell adj: Grid.getAdjacentCells(current)) {
                if (closed.contains(adj)) {
                    continue;
                }

                // Set H Cost, doesn't need to be done on every visit
                adj.setHCost(adj.distanceTo(end));

                float alt = current.getGCost() + current.distanceTo(adj);

                if (alt < adj.getGCost() || !open.contains(adj)) {
                    adj.setGCost(alt);
                    adj.setPrev(current);
                    adj.updateFCost();
                }

                // Since open is a set, adj won't be added if it's already open
                open.add(adj);
            }
        }

        Set<Cell> finalPath = getPath();
        for (Cell c: finalPath) {
            if (!(c == start || c == end)) {
                c.setBackground(Color.BLUE);
            }
        }
    }

    public static Cell getNextCell(Set<Cell> cells) {
        List<Cell> minFCost = new ArrayList<>();
        Cell toReturn = null;
        float min = Float.MAX_VALUE;

        // Build a list of all cells with the smallest F Cost
        for (Cell c: cells) {
            if (c.getFCost() < min) {
                min = c.getFCost();
                minFCost.clear();
                minFCost.add(c);
            }
            else if (c.getFCost() == min) {
                minFCost.add(c);
            }
        }

        // Return the cell with the lowest H Cost if there's more than 1 cell with the lowest F Cost
        if (minFCost.size() > 1) {
            for (Cell c: minFCost) {
                if (c.getHCost() <= min) {
                    min = c.getHCost();
                    toReturn = c;
                }
            }
            return toReturn;
        }

        // Otherwise, just return the one cell
        else if (minFCost.size() == 1) {
            return minFCost.get(0);
        }

        // I think this only executes if there aren't any cells input
        else {
            return null;
        }
    }

    public static Set<Cell> getPath() {
        Set<Cell> toReturn = new HashSet<>();
        Cell c = end;

        //System.out.println(end.getPrev() == null);

        while(c.getPrev() != null) {
            toReturn.add(c);
            c = c.getPrev();
        }
        toReturn.add(c);
        return toReturn;
    }
}