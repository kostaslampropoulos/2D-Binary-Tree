
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

    //TODO:  Fix thoe methods
    /* 
    public Point nearestNeighbor(Point p){
        if (isEmpty()){
            return null;
        }
        
    } // point in the tree that is closest to p (null if tree is empty)
    
    public List<Point> rangeSearch(Rectangle rect){

    } // Returns a list with the Points that are contained in the rectangle
    */
}