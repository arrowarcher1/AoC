import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class solution {
    public static void main(String[] args) {
        try{
            BufferedReader read = new BufferedReader(new FileReader("input.txt"));
            part1(read);
            read.close();
        }
        catch(IOException e){
            System.out.println("Error reading");
        }
    }

    private static void part1(BufferedReader reader)throws IOException{
        int safeCount1 = 0; 
        int safeCount2 = 0; 

        String strRead;

        while((strRead = reader.readLine()) != null){
            String vals[] = strRead.split(" ");

            int[] intVals = new int[vals.length];
            for (int i = 0; i < vals.length; i++) {
                intVals[i] = Integer.parseInt(vals[i]);
            }

            if(validOrder(intVals, false) || validOrder(intVals, true)){//check increasing and decreasing
                safeCount1++;
            }else{
                for(int i = 0; i < vals.length; i++){
                    if(checkSkip(intVals, i, true) || checkSkip(intVals, i, false)){
                        safeCount2++;
                        break;
                    }
                }
            }
                

        }
        System.out.println(safeCount1);
        System.out.println(safeCount1 + safeCount2);
    }

    private static boolean validOrder(int vals[], boolean isIncreasing){ //Try to use a single function to check for validity
        int curr = vals[0];
        for(int i = 1; i < vals.length; i++){
            int next = vals[i];
            int comp = next - curr;

            if(Math.abs(comp) > 3 || (isIncreasing ? comp <= 0 : comp >= 0))//Allow for either case based on current test
                return false;
            
            curr = next;
        }
        return true;
    }

    public static boolean checkSkip(int[] vals, int indexToSkip, boolean isIncreasing){
        int[] newVals = new int[vals.length - 1];
        for (int i = 0, j = 0; i < vals.length; i++) {
            if (i != indexToSkip) {
                newVals[j++] = vals[i];
            }
        }
        return validOrder(newVals, isIncreasing);
    }

    /*
    private static boolean checkDecreasingSkip(int[] vals, int indexToSkip){
        int[] newVals = new int[vals.length - 1];
        for (int i = 0, j = 0; i < vals.length; i++) {
            if (i != indexToSkip) {
                newVals[j++] = vals[i];
            }
        }
        return checkDecreasing(newVals);
    }

    private static boolean checkIncreasingSkip(int[] vals, int indexToSkip){
        int[] newVals = new int[vals.length - 1];
        for (int i = 0, j = 0; i < vals.length; i++) {
            if (i != indexToSkip) {
                newVals[j++] = vals[i];
            }
        }
        return checkIncreasing(newVals);
    }
    */
}