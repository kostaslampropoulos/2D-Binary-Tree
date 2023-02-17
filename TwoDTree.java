import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.event.PrintEvent;

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

    public int size(){
        return size;
    } // number of points in the tree

    public void insert(Point p){
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
        if (isEmpty()){
            return null;
        }
        Point nearest = new Point(head.get_point().get_x(), head.get_point().get_y());
        double nearest_distance = p.distanceTo(nearest);

        recursive_nearestNeighbor(nearest, nearest_distance, p, head);

        return nearest;
    } // point in the tree that is closest to p (null if tree is empty)

    public void recursive_nearestNeighbor(Point nearest, double nearest_distance, Point p, Treenode head){

        if(head == null ){
            return;
        }

        if(head.get_l() != null && head.get_r() != null){
            int x_max = Math.max(head.get_l().get_point().get_x(), head.get_r().get_point().get_x());
            int x_min = Math.min(head.get_l().get_point().get_x(), head.get_r().get_point().get_x());
    
            int y_max = Math.max(head.get_l().get_point().get_y(), head.get_r().get_point().get_y());
            int y_min = Math.max(head.get_l().get_point().get_y(), head.get_r().get_point().get_y());
            
            Rectangle head_rect = new Rectangle(x_min, x_max, y_min, y_max);
        
            if (head_rect.distanceTo(p) < nearest_distance){
                
                nearest = head.get_point();
                nearest_distance = p.distanceTo(head.get_point());

                recursive_nearestNeighbor(nearest, nearest_distance, p, head.get_l());
                recursive_nearestNeighbor(nearest, nearest_distance, p, head.get_r());
            }
        }
        return;
    }
    
    public List<Point> rangeSearch(Rectangle rect){
        List<Point> return_list = new ArrayList<Point>();
        recursive_rangeSearch(rect, head, return_list);
        return return_list;
    } // Returns a list with the Points that are contained in the rectangle
    
    public void recursive_rangeSearch(Rectangle rect, Treenode head, List<Point> list){
        if(head == null){
            return;
        }
        Rectangle head_rect = new Rectangle(head.get_l().get_point().get_x(), head.get_r().get_point().get_x(), head.get_l().get_point().get_y(),  head.get_r().get_point().get_y());
        if(!head_rect.intersects(rect)){
            return;
        }
        list.add(head.get_point());
        recursive_rangeSearch(rect, head.get_l(), list);
        recursive_rangeSearch(rect, head.get_r(), list);
    }

    public void print(Treenode head){
        if (head == null){
            return;
        }
        System.out.println(head.get_point());
        if(head.get_l() != null){
            System.out.println("Left child of " + head.get_point());
            print(head.get_l());
        }
        if(head.get_r() != null){
            System.out.println("Right child of " + head.get_point());
            print(head.get_r());
        }
        return;
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
            
            //tree.print(tree.head);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}