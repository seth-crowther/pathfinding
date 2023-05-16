import java.awt.*;
import java.util.*;
import java.util.List;

public class Algorithms {
    public static Cell start;
    public static Cell end;

    public static void main(String[] args) {
        Canvas c = new Canvas();
    }

    public static void dijkstra() {

        // Initialising cells and unvisited set
        Grid.resetCells();
        start.setDist(0);
        Set<Cell> unvisited = new HashSet<>();

        // Adding all cells to unvisited set
        for (Cell[] row: Grid.getGrid()) {
            unvisited.addAll(Arrays.asList(row));
        }

        while (unvisited.size() > 0) {

            Cell current = Grid.minimumFrom(unvisited);
            unvisited.remove(current);

            for (Cell v: Grid.getAdjacentCells(current.getXCoord(), current.getYCoord())) {

                // Skip checking cell if it's already been visited
                if (!unvisited.contains(v)) {
                    continue;
                }

                float alt = current.getDist() + current.distanceTo(v);
                if (alt < v.getDist()) {
                    v.setDist(alt);
                    v.setPrev(current);
                }
            }

            // Exit once end cell has been visited
            if (current.equals(end)) {
                break;
            }
        }

        Set<Cell> finalPath = tracePath();
        for (Cell path: finalPath) {
            // Only colour cells between start and end cells
            if (!path.equals(start) && !path.equals(end)) {
                path.setBackground(Color.BLUE);
            }
        }
    }

    public static Set<Cell> tracePath() {
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