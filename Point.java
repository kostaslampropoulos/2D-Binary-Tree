import java.lang.Math;

public class Point {
    private int x;
    private int y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int get_x(){
        return x;
    } // return the x-coordinate
    
    public int get_y(){
        return y;
    } // return the y-coordinate
    
    public double distanceTo(Point z){
        return Math.sqrt((x - z.get_x())^2 + (y - z.get_y())^2);
    } // Euclidean distance between two points public int squareDistanceTo(Point z) // square of the Euclidean distance // between two points
    
    public String toString(){
       return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
    } // string representation: (x, y)
    }