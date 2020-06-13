/*Besirat Melaku
 * CSC 364
 * HW4
 */
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView<E> extends Pane {
  private BST<Integer> tree = new BST<>();
  private double radius = 15; // Tree node radius
  private double vGap = 50; // Gap between two levels in a tree
  
  private java.util.ArrayList<BST.TreeNode<E>> path = new ArrayList<BST.TreeNode<E>>();
  
  
  BTView(BST<Integer> tree) {
    this.tree = tree;
    setStatus("Tree is empty");
  }

  public void setStatus(String msg) {
    this.getChildren().add(new Text(20, 20, msg));
  }
  
  public void setPath(ArrayList<BST.TreeNode<E>> path){
	  this.path = path;
  }
  
  public java.util.ArrayList<BST.TreeNode<E>> getPath(){
	  return path;
  }
  
  public void displayTree() {
    this.getChildren().clear(); // Clear the pane
    if (tree.getRoot() != null) {
      // Display tree recursively    
      displayTree(tree.getRoot(), getWidth() / 2, vGap,
        getWidth() / 4);
    }
  }

  /** Display a subtree rooted at position (x, y) */
  private void displayTree(BST.TreeNode<Integer> root,
      double x, double y, double hGap) {
    if (root.left != null) {
      // Draw a line to the left node
      getChildren().add(new Line(x - hGap, y + vGap, x, y));
      // Draw the left subtree recursively
      displayTree(root.left, x - hGap, y + vGap, hGap / 2);
    }

    if (root.right != null) {
      // Draw a line to the right node
      getChildren().add(new Line(x + hGap, y + vGap, x, y));
      // Draw the right subtree recursively
      displayTree(root.right, x + hGap, y + vGap, hGap / 2);
    }
    
    // Display a node
    Circle circle = new Circle(x, y, radius);
    
    if(path.contains(root))
    	circle.setFill(Color.ORANGE);
    else
    	circle.setFill(Color.WHITE);
    	circle.setStroke(Color.BLACK);
    this.getChildren().addAll(circle,
      new Text(x - 4, y + 4, root.element.toString()));
  }
}

