import java.awt.*;
import java.util.*;

public class Algorithms {
    public static Cell start;
    public static Cell end;

    public static void main(String[] args) {
        Canvas c = new Canvas();
    }

    public static void dijkstra() {

        // Initialising cells and unvisited set
        Grid.resetCells();
        start.setFCost(0);
        Set<Cell> unvisited = new HashSet<>();

        // Adding all cells to unvisited set
        for (Cell[] row: Grid.getGrid()) {
            unvisited.addAll(Arrays.asList(row));
        }

        Cell current = start;

        while (unvisited.size() > 0) {

            if (current != null) {
                for (Cell v : Grid.getAdjacentCells(current.getXCoord(), current.getYCoord())) {
                    // Skip checking cell if it's already been visited
                    if (!unvisited.contains(v)) {
                        continue;
                    }

                    v.setGCost(start.distanceTo(v));
                    v.setHCost(end.distanceTo(v));
                    v.setFCost(v.getGCost() + v.getHCost());
                }


            }

            Cell prev = current;
            unvisited.remove(current);

            // Exit once end cell has been visited
            if (current.equals(end)) {
                break;
            }

            current = Grid.getNextCell(unvisited);
            current.setPrev(prev);
        }

        Set<Cell> finalPath = getPath();
        for (Cell path: finalPath) {
            // Only colour cells between start and end cells
            if (!path.equals(start) && !path.equals(end)) {
                path.setBackground(Color.BLUE);
            }
        }
    }

    public static Set<Cell> getPath() {
        Set<Cell> toReturn = new HashSet<>();
        Cell c = end;
        while(c.getPrev() != null) {
            toReturn.add(c);
            c = c.getPrev();
        }
        toReturn.add(c);
        return toReturn;
    }
}