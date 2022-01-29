/**
 * This file contains the implementation of various Sorting methods
 */

import java.util.*;

/**
 * Sorting class contains a list of elements 
 * and various sorting methods that can be 
 * performed on this list to sort the elements
 * in ascending order
 */
public class Sorting<E extends Comparable<E>>{

    ArrayList<E> myList; //list of elements
    ArrayList<E> tempList; //helper list

    /**
     * Constructor that initializes the myList
     * with an array of integers
     * @param arr
     */
    public Sorting(E[] arr){
        myList = new ArrayList<>();
        tempList = new ArrayList<>();
        for (E i : arr) {
            myList.add(i);
            tempList.add(i);
        }     

    }

    /**
     * Given two indices in the array, swaps the 
     * elements in these indices
     * @param firstPosition - first element's index
     * @param secondPosition - second element's index
     */
    private void swap(int firstPosition, int secondPosition){
        E temp = myList.get(firstPosition);
        myList.set(firstPosition, myList.get(secondPosition));
        myList.set(secondPosition, temp);

    }

    /**
     * Compares current element and next element in the array
     * and swaps them if not in correct order
     * Does this in every iteration for each element
     */
    public void bubbleSort(){
        for(int i=0; i<myList.size(); i++){
            for(int j=0; j<myList.size()-1-i; j++){
                //If current element is larger than next element
                if(myList.get(j).compareTo(myList.get(j+1)) > 0){
                    swap(j,j+1);
                }
                //this.printMyList();
            }
        }
    }

    /**
     * Finds minimum element position and swaps the element at
     * this position with the element at the current position 
     * in every iteration
     */
    public void selectionSort(){
        for(int i=0; i<myList.size();i++){
            int minPosition = i;
            for(int j=i; j< myList.size(); j++){
                //If element at jth position is smaller than
                //element at minPosition
                if(myList.get(j).compareTo(myList.get(minPosition))<0){
                    minPosition = j;
                }
            }
            swap(minPosition, i);
        }
        
    }

    /**
     * Split into sorted and unsorted portions
     * Each element in unsorted is picked 
     * and inserted in the right position
     */
    public void insertionSort(){
        for(int i=1; i<myList.size(); i++){
            E temp = myList.get(i);
            int j = i-1;
            //Till we find an element that is smaller than temp
            while(j>=0 && (myList.get(j).compareTo(temp)>0 )){
                //shift elements one position to the right
                myList.set(j+1, myList.get(j));
                j--;
            }
            myList.set(j+1, temp);
        }
    }

    /**
     * Use divide and conquer
     * Split array into halves, sort each half while merging
     */
    public void mergeSort(){
        mergeSortHelper(0,myList.size()-1);
    }

    /**
     * Helper method for mergeSort()
     * Divides the array into halves and 
     * calls merge()
     * @param low - start index
     * @param high - end index
     */
    private void mergeSortHelper(int low, int high){
        if(low<high){
            int mid = low + (high-low)/2;
            //Divide the left half
            mergeSortHelper(0, mid);
            //Divide the right half
            mergeSortHelper(mid+1, high);
            //Merge the halves while sorting
            merge(low, mid, high);
        }
    }

    /**
     * Helper method for mergeSortHelper()
     * Merges the split halves while sorting
     * @param low - start index
     * @param mid - middle index
     * @param high - end index
     */
    private void merge(int low, int mid, int high){

        //copy into a temp array
        for(int i=low; i<=high;i++){
            this.tempList.set(i, myList.get(i));
        }

        //set indices for these left and right halves
        int left = low;
        int curr = low;
        int right = mid+1;

        //Perform sorting by comparing before adding element to array
        while((left<=mid) && (right<=high)){
            
            //If element in left half is smaller, add that to array
            if(this.tempList.get(left).compareTo(this.tempList.get(right)) <= 0){
                
                this.myList.set(curr, this.tempList.get(left));                
                left++;
            } 
            //Else if element in right half is smaller, add that to array
            else{
                this.myList.set(curr, this.tempList.get(right));                
                right++;
            }
            //Increment current index in array 
            curr++;
        }

        

        
        //Copy left half alone as right will already be there
        while(left <= mid){
            this.myList.set(curr, tempList.get(left));
            left++;
            curr++;            
        }

    }

    /**
     * Picks a pivot element and repeatedly
     * puts elements to left and right of the 
     * pivot element
     */
    public void quickSort(){
        quickSortHelper(0, myList.size()-1);
    }

    /**
     * Divides the array into two parts 
     * using partitionPosition returned from
     * partition method
     * @param low - start index
     * @param high - end index
     */
    private void quickSortHelper(int low, int high){
        if(low <= high){
            int partitionPosition = partition(low, high);
            if(low< partitionPosition-1)
                quickSortHelper(low, partitionPosition-1);
            if(partitionPosition < high)
                quickSortHelper(partitionPosition, high); 
        }
    }

    /**
     * Performs sorting based on pivot element
     * @param left - start index
     * @param right - end index
     * @return - new partition element's index
     */
    private int partition(int left, int right){
        int midPosition = left + (right-left)/2;
        E pivot = myList.get(midPosition);

        while(myList.get(left).compareTo(pivot) < 0) left++;
        while(myList.get(right).compareTo(pivot) > 0) right--;

        if(left<=right){
            swap(left, right);
            left++;
            right--;
        }

        return left;
    }
    
    

    @Override
    /**
     * Format and Print elements in myList
     */
    public String toString(){
        StringBuilder output = new StringBuilder("{ ");
        for (Object element : myList) {
            output.append(element + ", ");
        }
        output.append("}");
        return output.toString();
    }

    


    public static void main(String[] args) {
        Integer[] intArray = {4,3,2,1};
        String[] strArray = {"sanjana", "mitul", "balamurugan", "jayapriya"};
        Sorting<Integer> s1 = new Sorting<>(intArray);
        Sorting<String> s2 = new Sorting<>(strArray);
        s1.quickSort();
        s2.insertionSort();
        System.out.println(s1);
        System.out.println(s2);
    }



}