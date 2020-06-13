/*
 *Besirat Melaku
 *CSC 364
 * HW3
*/
public class InPlaceIntHeapSort {
	/*Define main method, iterate through the list then print out elements in the list */
	public static void main(String[] args) {								
		int[] list = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53}; 		
	    heapSort(list);														
	    for (int i = 0; i < list.length; i++)								
	      System.out.print(list[i] + " ");									
	}
	
	//definig heapSort method that only takes an array of ints
	public static void heapSort(int [] list){								
		//PART I - create heap out of random list of integers
		int n = list.length;												//list size
		for(int i = 1; i < n; i ++){								
			int current = i;												//current Index
			boolean trace = true;											//while loop condition
			while(trace){									
				int parent = (current-1)/2;									//use formula to find parent Node of current element
				if(list[current] > list[parent]){							//compare current and parent 		
					int temp = list[parent];								//swap elements if current is greater than the parent
					list[parent] = list[current];
					list[current] = temp;
				}
				else 
					trace = false;											//end loop
				current = parent;											//update current
			}
		}
		//PART II-create sorted list out of heap list
		for(int i = n-1; i>0; i--){
			//Store and swap first and last element
			int temp = list[0];												
			list[0] = list[i];
			list[i] = temp;
			int heapSize = i-1;												//update heap size
			int currentIndex = 0;											//current Index for while loop
			while(currentIndex < heapSize){									//compare current index and heap size, break if currentIndex is greater than heap size
				int leftChildIndex = 2 * currentIndex +1;					//formula to find index of left child
				int rightChildIndex = leftChildIndex +1;					//formula to find index of right child
				
				//Find the max between two children
				if(leftChildIndex > heapSize) break;						//break if index of left child is greater than heap size
				int maxIndex = leftChildIndex;								//max Index stores max index of the children
				if(rightChildIndex <= heapSize){							//compare right child index with heap size
					if(list[maxIndex] < list[rightChildIndex]){				
						maxIndex = rightChildIndex;							//update max Index if right child index is greater than max index
					}
				}
				//swap if the current node is less than max
				if(list[currentIndex] < list[maxIndex]){
					int temp1 = list[maxIndex];
					list[maxIndex] = list[currentIndex];
					list[currentIndex] = temp1;
					currentIndex = maxIndex;								//update current index
				}
				else break;													//break if element in the current Index is greater than element in the max Index
			}
		}
			
		}
		
}
