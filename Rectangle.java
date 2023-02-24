public class Rectangle {
    
    private int xmin;
    private int xmax;
    private int ymin;
    private int ymax;
    
    public Rectangle(int xmin, int xmax,int ymin,int  ymax){
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public Rectangle(Rectangle rect){
        this.xmin = rect.get_xmin();
        this.xmax = rect.get_xmax();
        this.ymin = rect.get_ymin();
        this.ymax = rect.get_ymax();
    }

    public int get_xmin(){
        return xmin;
    } // minimum x-coordinate of rectangle
    
    public int get_ymin(){
        return ymin;
    } // minimum y-coordinate of rectangle
    
    public int get_xmax(){
        return xmax;
    } // maximum x-coordinate of rectangle
    
    public int get_ymax(){
        return ymax;
    } // maximum y-coordinate of rectangle


    public void set_xmin(int xmin){
        this.xmin = xmin;
    }

    public void set_xmax(int xmax){
        this.xmax = xmax;
    }

   public void set_ymin(int ymin){
        this.ymin = ymin;
    }

    public void set_ymax(int ymax){
        this.ymax = ymax;
    }
    
    public boolean contains(Point p){
        return (p.get_x() <= xmax && p.get_x() >= xmin) && (p.get_y() <= ymax && p.get_y() >= ymin);
    } //does p belong to the rectangle? 
    
    public boolean intersects(Rectangle that){
        int x1 = Math.max(that.get_xmin(), xmin);
        int x2 = Math.min(that.get_xmax(), xmax);

        int y1 = Math.max(that.get_ymin(), ymin);
        int y2 = Math.min(that.get_ymax(), ymax);
 
        return (x1 <= x2) && (y1 <= y2);
    } // do the two rectangles intersect?
    
    public double distanceTo(Point p) {
        int xDiff = Math.max(Math.min(p.get_x(), xmax) - Math.max(p.get_x(), xmin), 0);
        int yDiff = Math.max(Math.min(p.get_y(), ymax) - Math.max(p.get_y(), ymin), 0);
        
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }// Euclidean distance from p to closest point in rectangle
    
    public int squareDistanceTo(Point p) {
        int xDiff = Math.max(Math.min(p.get_x(), xmax) - Math.max(p.get_x(), xmin), 0);
        int yDiff = Math.max(Math.min(p.get_y(), ymax) - Math.max(p.get_y(), ymin), 0);
        
        return xDiff * xDiff + yDiff * yDiff;
    }// square of Euclidean distance from p to closest point in rectangle
    
    public String toString(){
        return "[" + Integer.toString(xmin) + "," + Integer.toString(xmax) + "] X [" + Integer.toString(ymin) + "," + Integer.toString(ymax) + "]";
    } // string representation
}
