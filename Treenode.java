public class Treenode {
    
    private Point point; // an object of the class Point
    private Treenode l; // pointer to left subtree
    private Treenode r; // pointer to right subtree

    public Treenode(Point point){
        this.point = point;
        this.l = null;
        this.r = null;
    }

    public Treenode(Point point, Treenode left, Treenode right){
        this.point = point;
        this.l = left;
        this.r = right;
    }
    
    public void set_l(Treenode node){
        this.l = node;
    }

    public void set_r(Treenode node){
        this.r = node;
    }

    public Treenode get_l(){
        return this.l;
    }
    
    public Treenode get_r(){
        return this.r;
    }

    public Point get_point(){
        return this.point;
    }
}
