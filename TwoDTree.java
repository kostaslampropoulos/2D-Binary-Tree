import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TwoDTree { 
    
    private Treenode head; //root of the tree
    int size;

    public TwoDTree(){
        head = null;
        size = 0;
    } // construct an empty tree
    
    public boolean isEmpty(){
        return head == null;
    } // is the tree empty?

    public int get_size(){
        return size;
    } // number of points in the tree

    public void insert(Point p){
        size++;
        if (isEmpty()){
            head = new Treenode(p);
        }
        else{
            Treenode runner = head;
            boolean check = true;
            
            while(true){
                if (check){
                    if(p.get_x() < runner.get_point().get_x()){
                        if(runner.get_l() == null){
                            runner.set_l(new Treenode(p));
                            break;
                        }else{
                            runner = runner.get_l();
                        }
                    }else{
                        if(runner.get_r() == null){
                            runner.set_r(new Treenode(p));
                            break;
                        }else{
                            runner = runner.get_r();
                        }
                    }
                    check = false;
                }
                else{
                    if(p.get_y() < runner.get_point().get_y()){
                        if(runner.get_l() == null){
                            runner.set_l(new Treenode(p));
                            break;
                        }else{
                            runner = runner.get_l();
                        }
                    }
                    else{
                        if(runner.get_r() == null){
                            runner.set_r(new Treenode(p));
                            break;
                        }else{
                            runner = runner.get_r();
                        }
                    }
                    check = true;
                } 
            }
        }
    } // inserts the point p to the tree 
    
    
    public boolean search(Point p){
        boolean found = false;
        boolean rl = false;  
        Treenode runner = head;
          
        while(!found){
            if (runner == null){
                return found;
            }
            if (rl == false){
                if(p.get_x() == runner.get_point().get_x() && p.get_y() == runner.get_point().get_y())
                    found = true;
                else if (p.get_x() < runner.get_point().get_x()){
                    runner = runner.get_l();
                }else{
                    runner = runner.get_r();
                }
                rl = true;
            }else{
                if(p.get_x() == runner.get_point().get_x() && p.get_y() == runner.get_point().get_y())
                    found = true;
                else if (p.get_y() < runner.get_point().get_y()){
                    runner = runner.get_l();
                }else{
                    runner = runner.get_r();
                }
                rl = false;
            }
        }

        return found;
    } // does the tree contain p?

    public Point nearestNeighbor(Point p){
        if(isEmpty()){
            return null;
        }
        
        Point nearest = head.get_point();
        Rectangle rect = new Rectangle(0, 100, 0, 100);
        boolean X0Y1 = false;
        
        nearest = nearesNeighborRecursive(nearest, p, head, rect, X0Y1);
        System.out.println("Result: " + nearest);
        return nearest;
    } // point in the tree that is closest to p (null if tree is empty)

    public Point nearesNeighborRecursive(Point nearest, Point p, Treenode current_head, Rectangle rect, boolean X0Y1){

        if(!(current_head.get_l() == null || current_head.get_r() == null)){
            // Rectangle rect = new Rectangle(Math.min(current_head.get_l().get_point().get_x(), current_head.get_r().get_point().get_x()),
            //                                Math.max(current_head.get_l().get_point().get_x(), current_head.get_r().get_point().get_x()),
            //                                Math.min(current_head.get_l().get_point().get_y(), current_head.get_r().get_point().get_y()),
            //                                Math.max(current_head.get_l().get_point().get_y(), current_head.get_r().get_point().get_y()));

            

            if(nearest.distanceTo(p) >= rect.distanceTo(p)){
                nearest = current_head.get_point();
                System.out.println(nearest);
                System.out.println(2);
                
                Rectangle rectl;
                if(X0Y1){
                    rectl = new Rectangle(rect);
                    rectl.set_ymax(current_head.get_point().get_y());
                }
                else{
                    rectl = new Rectangle(rect);
                    rectl.set_xmax(current_head.get_point().get_x());                 
                }
                X0Y1 = !(X0Y1);
                nearest = nearesNeighborRecursive(nearest, p, current_head.get_l(), rectl, X0Y1);
                
                Rectangle rectr;
                if(X0Y1){
                    rectr = new Rectangle(rect);
                    rectr.set_ymin(current_head.get_point().get_y());
                }
                else{
                    rectr = new Rectangle(rect);
                    rectr.set_xmin(current_head.get_point().get_x());
                    
                }
                X0Y1 = !(X0Y1);
                nearest = nearesNeighborRecursive(nearest, p, current_head.get_r(), rectr, X0Y1);
            }
            
        }else if(current_head.get_l()==null && current_head.get_r()!=null){
            
            //Rectangle rect = new Rectangle(0, current_head.get_r().get_point().get_x(), 0, current_head.get_r().get_point().get_y());
            if(nearest.distanceTo(p) >= rect.distanceTo(p)){
                nearest = current_head.get_point();
                System.out.println(nearest);
                System.out.println("1r");
                
                Rectangle rectr;
                if(X0Y1){
                    rectr = new Rectangle(rect);
                    rectr.set_ymin(current_head.get_point().get_y());
                }
                else{
                    rectr = new Rectangle(rect);
                    rectr.set_xmin(current_head.get_point().get_x());
                }
                X0Y1 = !(X0Y1);
                nearest = nearesNeighborRecursive(nearest, p, current_head.get_r(), rect, X0Y1);
            }
            
        }else if(current_head.get_l() != null && current_head.get_r() == null){
            
            //Rectangle rect = new Rectangle(0, current_head.get_l().get_point().get_x(), 0, current_head.get_l().get_point().get_y());
            if(nearest.distanceTo(p) >= rect.distanceTo(p)){
                nearest = current_head.get_point();
                System.out.println(nearest);
                System.out.println("1l");
                
                Rectangle rectl;
                if(X0Y1){
                    rectl = new Rectangle(rect);
                    rectl.set_ymax(current_head.get_point().get_y());
                }
                else{
                    rectl = new Rectangle(rect);
                    rectl.set_xmax(current_head.get_point().get_x());
                }
                X0Y1 = !(X0Y1);
                nearest = nearesNeighborRecursive(nearest, p, current_head.get_l(),rect, X0Y1);
            }
            
        }
        
        return nearest;
    }
    
    
    public static void main (String[] args){
        BufferedReader reader = null;
        String l, path = args[0];
        
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
            l = reader.readLine();
            int num_points = Integer.parseInt(l.trim());

            TwoDTree tree = new TwoDTree();
            
            for(int i=0; i<num_points; i++){
                l = reader.readLine();
                l = l.trim();
                String [] coo = l.split(" ");
                
                int x = Integer.parseInt(coo[0]);
                int y = Integer.parseInt(coo[1]);
                
                tree.insert(new Point(x,y));   
            }
            
            
            System.out.println(tree.nearestNeighbor(new Point(0,35)));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}