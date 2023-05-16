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
        start.setDist(0);
        Set<Cell> unvisited = new HashSet<>();
        Set<Cell> visited = new HashSet<>();
        for (Cell[] row: Grid.getGrid()) {
            unvisited.addAll(Arrays.asList(row));
        }
        unvisited.remove(start);

        Cell current = start;

        for (Cell c: Grid.getAdjacentCells(current.getXCoord(), current.getYCoord())) {
            if (visited.contains(c)) {
                continue;
            }

            float alt = current.getDist() + current.distanceTo(c);
            if (alt < c.getDist()) {
                c.setDist(alt);
                c.setPrev(current);
            }

            if (current == end) {
                System.out.println("Reached end");
                for (Cell path: tracePath()) {
                    path.setBackground(Color.BLUE);
                }
                return;
            }

            visited.add(current);
            current = Grid.getClosestCell(current);
        }
    }

    public static List<Cell> tracePath() {
        List<Cell> toReturn = new ArrayList<>();
        Cell c = end;
        while(c.getPrev() != null) {
            toReturn.add(c);
            c = c.getPrev();
        }
        toReturn.add(c);
        Collections.reverse(toReturn);
        return toReturn;
    }
}