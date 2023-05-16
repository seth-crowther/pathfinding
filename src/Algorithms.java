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
        Grid.resetCells();
        start.setDist(0);
        Set<Cell> unvisited = new HashSet<>();
        Set<Cell> visited = new HashSet<>();

        for (Cell[] row: Grid.getGrid()) {
            unvisited.addAll(Arrays.asList(row));
        }

        while (unvisited.size() > 0) {
            Cell current = Grid.minimumFrom(unvisited);
            unvisited.remove(current);
            for (Cell v: Grid.getAdjacentCells(current.getXCoord(), current.getYCoord())) {
                if (!unvisited.contains(v)) {
                    continue;
                }
                float alt = current.getDist() + current.distanceTo(v);
                if (alt < v.getDist()) {
                    v.setDist(alt);
                    v.setPrev(current);
                }
            }
            visited.add(current);
        }

        List<Cell> finalPath = tracePath();
        if (finalPath.size() > 0) {
            for(int i = 0; i < finalPath.size(); i++) {
                if (i != 0 && i != finalPath.size() - 1) {
                    finalPath.get(i).setBackground(Color.BLUE);
                }
            }
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