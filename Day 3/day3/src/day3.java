import java.util.ArrayList;
import java.lang.Math;

public class day3 {
    public static void main(String[] args) {
        String wire1 = FileUtil.loadFile("C:/Users/Owner/.vscode/Projects/AOC 2019/Day 3/day3/src/input3.txt").get(0); //gets both wire inputs
        String wire2 = FileUtil.loadFile("C:/Users/Owner/.vscode/Projects/AOC 2019/Day 3/day3/src/input3.txt").get(1); //as a string

        String[] wire1Input = wire1.split(","); //splits the string into
        String[] wire2Input = wire2.split(","); //the specific directions

        int wire1InputSize = wire1Input.length;
        int wire2InputSize = wire2Input.length;

        ArrayList<String> wire1Coords = new ArrayList<String>(); //this will track where the wire is on the plane

        int[] closestCrossCoords = new int[2]; //this will store the coords

        closestCrossCoords[0] = Integer.MAX_VALUE;
        closestCrossCoords[1] = Integer.MAX_VALUE;

        int xCoord = 0;
        int yCoord = 0;

        Point startPt2 = new Point(xCoord, yCoord);

        int wire2steps = 0;
        int fewestSteps = Integer.MAX_VALUE;
        int lowestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < wire2InputSize; i++) {
            String inst2 = wire2Input[i];
            String dir2 = String.valueOf(inst2.charAt(0));
            int dist2 = Integer.valueOf(inst2.replaceAll(dir2, ""));

            Point endPt2 = new Point(0, 0);

            switch (dir2) {
                case "U":
                    endPt2 = new Point(startPt2.x, startPt2.y + dist2);
                    break;
                case "D":
                    endPt2 = new Point(startPt2.x, startPt2.y - dist2);
                    break;
                case "R":
                    endPt2 = new Point(startPt2.x + dist2, startPt2.y);
                    break;
                case "L":
                    endPt2 = new Point(startPt2.x - dist2, startPt2.y);
                    break;
            }

            Segment segment2 = new Segment(startPt2, endPt2, dist2);

            Point startPt1 = new Point(xCoord, yCoord);

            int wire1steps = 0;

            for (int j = 0; j < wire1InputSize; j++) {
                String inst1 = wire1Input[j];
                String dir1 = String.valueOf(inst1.charAt(0));
                int dist1 = Integer.valueOf(inst1.replaceAll(dir1, ""));

                Point endPt1 = new Point(0, 0);

                switch (dir1) {
                    case "U":
                        endPt1 = new Point(startPt1.x, startPt1.y + dist1);
                        break;
                    case "D":
                        endPt1 = new Point(startPt1.x, startPt1.y - dist1);
                        break;
                    case "R":
                        endPt1 = new Point(startPt1.x + dist1, startPt1.y);
                        break;
                    case "L":
                        endPt1 = new Point(startPt1.x - dist1, startPt1.y);
                        break;
                }

                Segment segment1 = new Segment(startPt1, endPt1, dist1);

                Point intersect = segment2.intersect(segment1);

                if (intersect != null) {
                    int totalDistance = intersect.getDistance(new Point(0, 0));
                    if (totalDistance < lowestDistance)
                        lowestDistance = totalDistance;

                    int wire1dist = startPt1.getDistance(intersect);
                    int wire2dist = startPt2.getDistance(intersect);

                    int steps = wire2steps + wire1steps + wire1dist + wire2dist;

                    if (steps < fewestSteps) {
                        fewestSteps = steps;
                    }
                } else {
                    System.out.println(String.format("(%d, %d) -> (%d, %d) && (%d, %d) -> (%d, %d) NO INTERSECT",
                            segment1.start.x, segment1.start.y, segment1.end.x, segment1.end.y, segment2.start.x,
                            segment2.start.y, segment2.end.x, segment2.end.y));
                }

                startPt1 = endPt1;
                wire1steps += segment1.dist;
            }

            startPt2 = endPt2;
            wire2steps += segment2.dist;
        }

        System.out.println("LOWEST DISTANCE: " + lowestDistance);
        System.out.println("FEWEST STEPS: " + fewestSteps);
    }
}

