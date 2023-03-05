# TwoDTree.java #

### nearestNeighbor: ###

The nearest neighbor method finds the closest point to a given query point in the 2D tree. The algorithm works by traversing the tree recursively and comparing the query point to the current node's point.
Starting at the root of the tree, the algorithm checks the distance between the query point and the current node's point. If the distance is less than the distance to the current nearest neighbor, the nearest neighbor is updated. Then, the algorithm recursively searches the branch of the tree that is closer to the query point.
As the recursion continues, the algorithm backtracks up the tree to explore other branches as necessary. At each node, the algorithm checks the distance between the query point and the current node's point. If the distance is less than the distance to the current nearest neighbor, the nearest neighbor is updated.
The algorithm terminates when it has traversed the entire tree and the nearest neighbor is the final updated value.

### rangeSearch: ###

The range search method finds all points in the 2D tree that lie within a given range of a query point. The algorithm works by traversing the tree recursively and checking if each node's point lies within the range of the query point.
Starting at the root of the tree, the algorithm checks the distance between the query point and the current node's point. If the distance is less than or equal to the range, the current node's point is added to the result set. Then, the algorithm recursively searches both branches of the tree.
As the recursion continues, the algorithm backtracks up the tree to explore other branches as necessary. At each node, the algorithm checks if the current node's point lies within the range of the query point. If it does, it is added to the result set.
The algorithm terminates when it has traversed the entire tree and the result set contains all points that lie within the given range of the query point.
In both cases, recursion is used to traverse the tree efficiently and explore the branches that are closest to the query point.


#  Rectangle.java #

### Contains: ###

Using the given point and its “get_x()” ,“get_y()” methods, we compare the point’s coordinates with the coordinates of the rectangle. In order for the point to be inside of the rectangle, the x coordinate of the point must be smaller or equal to the maximum coordinate x of the rectangle and also bigger or equal to the minimum coordinate x of the rectangle. The same applies for the y coordinate.

### Intersects: ###

There are many ways to check if two rectangles intersect. We decided to use the following method. We first think linearly. In order to check if two lines intersect on the x axis we can do the following: (the two lines are represented by 2 points [xmin,xmax]) if the smaller xmax from the two lines is greater than the largest xmin from the two lines, then the lines intersect.
We can check the same for the y coordinates and combine them together to check if two rectangles intersect.

### DistanceTo: ###

In order to find the distance between a point and a rectangle, we will use Pythagorean Theorem. The point and the rectangle create a right triangle, with the distance between them being the hypotenuse. We calculate the value of the two “legs” of the triangle, in order to find the value of the hypotenuse. One of the legs corresponds to the x axis and the other to the y axis. The distance of the point from the rectangle on the x axis (xDiff) can be calculated by subtracting the minimum of the point’s x coordinate and the xmax of the rectangle from the maximum of the point’s x coordinate and the xmin of the rectangle. The same applies to the y axis. This way we find the value of the two value of the legs (xDiff, yDiff). Then we square them, add them and calculate the square root to find the hypotenuse.