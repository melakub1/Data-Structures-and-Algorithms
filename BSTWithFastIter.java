/* Besirat Melaku
 * CSC 364
 * */
	import java.util.NoSuchElementException;

	public class BSTWithFastIter<E extends Comparable<E>> extends BST<E> {
		public BSTWithFastIter(){
			
		}
		@Override
		public java.util.Iterator<E> iterator(){
			return new BSTFastIter();
			
		}
		private class BSTFastIter implements java.util.Iterator<E> {
			private java.util.Stack<TreeNode<E>> stack =new java.util.Stack<>();
			private TreeNode<E> current = root;
			private E lastReturned = null;
			private BSTFastIter(){
				
			}
			@Override
			public boolean hasNext(){
				return (!stack.empty() || (current != null));
			}
			@Override
			public E next(){
				 if(!hasNext())
				 	throw new NoSuchElementException();
				 while (current != null){
					 stack.push(current);
					 current = current.left;
					
				 }
				 current = stack.pop();
				 lastReturned = current.element;
				 current = current.right;
				 return lastReturned;

			}
			@Override
			public void remove(){
				
				if(lastReturned == null)
					throw new IllegalStateException();
				delete(lastReturned);
				lastReturned = null;
			}
		}
		
		
	}




