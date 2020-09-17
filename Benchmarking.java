import java.util.*;
import java.io.*;

public class Benchmarking{

  //Take a file scanner and return a sorted linked list created from the elements of the Scanner
  private static LinkedList<Integer> createSortedList(Scanner fileScanner){

  //START TIMER

    LinkedList<Integer> list = new LinkedList<Integer>();
    int currentValue = fileScanner.nextInt();
    list.addFirst(currentValue);
    while(fileScanner.hasNextInt()){

      currentValue = fileScanner.nextInt();
      ListIterator iterator = list.listIterator(0);

      //see if the current value of the array can be set as the head
      if (currentValue <= list.getFirst()){
        list.addFirst(currentValue);
      }
      //if the current value can be added to the last
      else if (currentValue > list.getLast()){
        list.addLast(currentValue);
      }
      //if the current value is in between fist and last links
      else {
        int linkIndex = 1;
        iterator = list.listIterator(1);
        while (iterator.hasNext() && currentValue > list.get(linkIndex)){
          linkIndex++;
          iterator.next();
        }
        list.add(linkIndex, currentValue);
      }

    }
    return list;
  //END TIMER
  }


  //Find the median of the list.
  //If the size of the list is even number then we take the average of the middle two number in the list
  //If the size of the list is odd number then we take the middle number of the list.
  private static int findMedian(LinkedList<Integer> list){
    int median = 0;

    if ((list.size() % 2) == 0){
      median = (list.get((list.size() / 2)) + list.get((list.size() / 2) - 1)) / 2 ;
    } else {
      median = list.get(list.size()/2);
      System.out.println(list.size()/2);
      System.out.println(median);
    }


    return median;
  }

  public static void main(String[] args){

    int min = 0;
    int max = 0;
    int med = 0;
    long time_insert = 0;
    long time_min = 0;
    long time_max = 0;
    long time_med = 0;

    //input the file and
    //check if the file is valid


    Scanner fileScanner = null;
    try {
      String fileName = "";
      Scanner terminal = new Scanner(System.in);
      if(args.length == 1){
        fileName = args[0];
      } else {
        System.out.println("Please enter a file name: ");
        fileName = terminal.nextLine();
      }
      fileScanner = new Scanner(new File(fileName));
    } catch(FileNotFoundException e) {
      System.out.println("File not found. Please re-run the program and enter valid file name.");
      System.exit(0);
    }

    //turn the given file into linkedList
    if (fileScanner.hasNextInt()){

      long insert_start = System.nanoTime();
      LinkedList<Integer> list = createSortedList(fileScanner);
      long insert_end = System.nanoTime();
      time_insert = (insert_end - insert_start)/1000;

      long med_start = System.nanoTime();
      med = findMedian(list);
      long med_end = System.nanoTime();
      time_med = (med_end - med_start)/1000;

      long min_start = System.nanoTime();
      min = list.getFirst();
      long min_end = System.nanoTime();
      time_min = (min_end - min_start)/1000;

      long max_start = System.nanoTime();
      max = list.getLast();
      long max_end = System.nanoTime();
      time_max = (max_end - max_start)/1000;

    } else {
      System.out.println("The file is empty or don't have integer data values.");
      System.exit(0);
    }
    fileScanner.close();
    //Hello world yo

    System.out.println("The maximum integer from the given file is: " + max);
    System.out.println("The minimum integer from the given file is: " + min);
    System.out.println("The median value from the given file is: " + med);
    System.out.println("\nThe time it took to insert the list was : " + time_insert + " microseconds");
    System.out.println("The time it took to find the maximum integer was : " + time_max + " microseconds");
    System.out.println("The time it took to find the minimum integer was : " + time_min + " microseconds");
    System.out.println("The time it took to find the median value was : " + time_med + " microseconds");
  }
}
