/*Besirat Melaku
 * CSC 364
 * HW4
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BSTAnimation extends Application {
  @Override 
  public void start(Stage primaryStage) {
    BST<Integer> tree = new BST<>(); 

    BorderPane pane = new BorderPane();
    BTView view = new BTView(tree); 
    pane.setCenter(view);

    TextField tfKey = new TextField();
    tfKey.setPrefColumnCount(3);
    tfKey.setAlignment(Pos.BASELINE_RIGHT);
    Button btInsert = new Button("Insert");
    Button btDelete = new Button("Delete");
    Button btSearch = new Button("Search");
    Button btInorder = new Button("Inorder");
    Button btPreorder = new Button("Preorder");
    Button btPostorder = new Button("Postorder");
    Button btBreadthfirst = new Button("Breadth-first");
    Button btHeight = new Button("Height");
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Please enter a key: "),
            tfKey, btInsert, btDelete, btSearch, btInorder, btPreorder, btPostorder, btBreadthfirst, btHeight);
    hBox.setAlignment(Pos.CENTER);
    pane.setBottom(hBox);
    
    
    btInsert.setOnAction(e -> {
      int key = Integer.parseInt(tfKey.getText());
      if (tree.search(key)) { 
        view.displayTree();
        view.setStatus(key + " is already in the tree");
      } 
      else {
        tree.insert(key); 
        view.displayTree();
        view.setStatus(key + " is inserted in the tree");
      }
    });

    btDelete.setOnAction(e -> {
      int key = Integer.parseInt(tfKey.getText());
      if (!tree.search(key)) { 
        view.displayTree();
        view.setStatus(key + " is not in the tree");
      } 
      else {
    	  if((view.getPath()).containsAll(tree.path(key)))
      		view.getPath().clear();
      	
        tree.delete(key); 
        view.displayTree();
        view.setStatus(key + " is deleted from the tree");
       
        
      }
    });
    
    
    btSearch.setOnAction(e -> {
    	int key = Integer.parseInt(tfKey.getText());
    	if(!tree.search(key)){ 			
    		view.displayTree();
    		view.setStatus(key + " is not in the tree");
    	}
    	else {							
    		view.setPath(tree.path(key));
    		view.displayTree();
            view.setStatus("Found " + key + " in the tree");
          }
    	
    });
    
    btInorder.setOnAction(e -> {
    	view.displayTree();
    	view.setStatus("Inorder traversal: " + tree.inorderList().toString());
    });
    
    
    btPreorder.setOnAction(e -> {
    	view.displayTree();
    	view.setStatus("Preorder traversal: " + tree.preorderList().toString());
    });
    
    btPostorder.setOnAction(e -> {
    	view.displayTree();
    	view.setStatus("Postorder traversal: " + tree.postorderList().toString());
    });
    
    btBreadthfirst.setOnAction(e->{
    	view.displayTree();
    	view.setStatus("Breadth-first traversal: " + tree.breadthFirstOrderList().toString());

    });
    
      btHeight.setOnAction(e->{
    	view.displayTree();
    	view.setStatus("Tree height is " + tree.height());
    });
    
    Scene scene = new Scene(pane, 700, 400);
    primaryStage.setTitle("BSTAnimation"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 
  }

  public static void main(String[] args) {
    launch(args);
  }
}
