import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

public class day3 {
    public static void main(String[] args) {
        // reading wire inputs from file
        List<String> inputs = FileUtil.loadFile("C:/Users/Owner/.vscode/Projects/AOC 2019/Day 3/day3/src/input3.txt");
        String wire1 = inputs.get(0); //gets both wire inputs
        String wire2 = inputs.get(1); //as a string

        String[] wire1Input = wire1.split(","); //splits the string into
        String[] wire2Input = wire2.split(","); //the specific directions

        int wire1InputSize = wire1Input.length;
        int wire2InputSize = wire2Input.length;

        ArrayList<String> wire1Coords = new ArrayList<String>(); //this will track where the wire is on the plane

        int[] closestCrossCoords = new int[2]; //this will store the coords

        // setting these to max int so anything is closer
        closestCrossCoords[0] = Integer.MAX_VALUE;
        closestCrossCoords[1] = Integer.MAX_VALUE;

        // current coordinates
        int xCoord = 0;
        int yCoord = 0;

        // starting point (starts at x=0, y=0)
        Point startPt2 = new Point(xCoord, yCoord);

        int wire2steps = 0;
        // setting these to max int so anything is lower
        int fewestSteps = Integer.MAX_VALUE;
        int lowestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < wire2InputSize; i++) {
            String inst2 = wire2Input[i]; // instructions for wire 2
            String dir2 = String.valueOf(inst2.charAt(0)); // direction of wire
            int dist2 = Integer.valueOf(inst2.replaceAll(dir2, "")); // length of wire

            // initialize ending point
            // shouldn't end up at (0, 0), java just complained that it might not have been initialized
            // this is because the compiler doesn't know that U,D,R,L are the only directions
            Point endPt2 = new Point(0, 0);

            switch (dir2) {
                case "U":
                    // adding dist to y because it's going up, and positive y is up in this case
                    endPt2 = new Point(startPt2.x, startPt2.y + dist2);
                    break;
                case "D":
                    // subtracting dist from y because it's going down
                    endPt2 = new Point(startPt2.x, startPt2.y - dist2);
                    break;
                case "R":
                    // adding dist to x because it's going right, and positive x is right in this case
                    endPt2 = new Point(startPt2.x + dist2, startPt2.y);
                    break;
                case "L":
                    // subtracting dist from x because it's going left
                    endPt2 = new Point(startPt2.x - dist2, startPt2.y);
                    break;
            }

            // creating a line segment between the starting and ending points, with length of dist
            Segment segment2 = new Segment(startPt2, endPt2, dist2);

            // create a starting point
            Point startPt1 = new Point(xCoord, yCoord);

            int wire1steps = 0;

            /*
                Nested for loop.
                This loops over every line, and then checks every line again to see if it intersects with it.
             */
            for (int j = 0; j < wire1InputSize; j++) {
                String inst1 = wire1Input[j]; // wire 1 instructions
                String dir1 = String.valueOf(inst1.charAt(0)); // wire 1 direction
                int dist1 = Integer.valueOf(inst1.replaceAll(dir1, "")); // wire 1 length

                // initialize ending point
                // shouldn't end up at (0, 0), java just complained that it might not have been initialized
                // this is because the compiler doesn't know that U,D,R,L are the only directions
                Point endPt1 = new Point(0, 0);

                switch (dir1) {
                    case "U":
                        // adding dist to y because it's going up, and positive y is up in this case
                        endPt1 = new Point(startPt1.x, startPt1.y + dist1);
                        break;
                    case "D":
                        // subtracting dist from y because it's going down
                        endPt1 = new Point(startPt1.x, startPt1.y - dist1);
                        break;
                    case "R":
                        // adding dist to x because it's going right, and positive x is right in this case
                        endPt1 = new Point(startPt1.x + dist1, startPt1.y);
                        break;
                    case "L":
                        // subtracting dist from x because it's going left
                        endPt1 = new Point(startPt1.x - dist1, startPt1.y);
                        break;
                }

                // creating another line segment between the starting point and ending point, with a length of dist
                Segment segment1 = new Segment(startPt1, endPt1, dist1);

                // getting the intersection of the two wires as a point
                Point intersect = segment2.intersect(segment1);

                if (intersect != null) { // if they actually intersect
                    // gets the distance between the point of intersection and the origin (0, 0)
                    int totalDistance = intersect.getDistance(new Point(0, 0));
                    if (totalDistance < lowestDistance)
                        // sets the lowest distance to total distance if it's lower
                        lowestDistance = totalDistance;

                    int wire1dist = startPt1.getDistance(intersect); // gets the distance between wire 1 start and the intersection
                    int wire2dist = startPt2.getDistance(intersect); // gets the distance between wire 2 start and the intersection

                    // total steps/score of the wire
                    int steps = wire2steps + wire1steps + wire1dist + wire2dist;

                    if (steps < fewestSteps) {
                        // sets fewest steps to steps if it's lower
                        fewestSteps = steps;
                    }
                } else {
                    // don't intersect, so we print out that there's no intersection
                    System.out.println(String.format("(%d, %d) -> (%d, %d) && (%d, %d) -> (%d, %d) NO INTERSECT",
                            segment1.start.x, segment1.start.y, segment1.end.x, segment1.end.y, segment2.start.x,
                            segment2.start.y, segment2.end.x, segment2.end.y));
                }

                // set start point to end point, therefore continuing the wire from the end of the previous one
                startPt1 = endPt1;
                // adding length of the wire to wire1steps because the distance would be greater
                wire1steps += segment1.dist;
            }

            // set start point to end point, therefore continuing the wire from the end of the previous one
            startPt2 = endPt2;
            // adding length of the wire to wire2steps because the distance would be greater
            wire2steps += segment2.dist;
        }

        // printing out results
        System.out.println("LOWEST DISTANCE: " + lowestDistance);
        System.out.println("FEWEST STEPS: " + fewestSteps);
    }
}

