import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;


public class solution {
    public static void main(String[] args) {
        try{
            File file = new File("input.txt");
            Scanner input = new Scanner(file);
            
            part1(input);
            input.close();
            
            input = new Scanner(file);
            part2(input);   
            input.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Error reading from file");
        }        
    }

    public static void part1(Scanner input){
        int sum = 0;
        PriorityQueue<Integer> list1 = new PriorityQueue<>();
        PriorityQueue<Integer> list2 = new PriorityQueue<>();

        //Read in data
        while(input.hasNextLine()){
            String vals[] = input.nextLine().split("   ");
            list1.offer(Integer.parseInt(vals[0]));
            list2.offer(Integer.parseInt(vals[1]));
        }
        
        while(!list1.isEmpty()){
            sum += Math.abs(list1.poll() - list2.poll());
        }
        
        System.out.println(sum);
    }

    public static void part2(Scanner input){
        int simScore = 0;
        LinkedList<Integer> list1 = new LinkedList<>(); // To store all the the read in values of list1
        HashMap<Integer, Integer> list2 = new HashMap<>(); //To give quick lookups for list2 counts; 
        
        while(input.hasNextLine()){
            String[] vals = input.nextLine().split("   ");
            list1.addLast(Integer.parseInt(vals[0]));

            int l2 = Integer.parseInt(vals[1]);
            list2.put(l2, list2.getOrDefault(l2, 0) + 1);//Increment the value in the list
        }


        Iterator<Integer> iter = list1.iterator();
        while(iter.hasNext()){
            int val = iter.next();
            simScore += (val * list2.getOrDefault(val, 0));
        }
        System.out.println(simScore);
    }
}
