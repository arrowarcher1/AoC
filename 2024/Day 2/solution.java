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

            if(checkDecreasing(vals) || checkIncreasing(vals)){
                safeCount1++;
            }else{
                for(int i = 0; i < vals.length; i++){
                    if(checkDecreasingSkip(vals, i) || checkIncreasingSkip(vals, i)){
                        safeCount2++;
                        break;
                    }
                }
            }
                

        }
        System.out.println(safeCount1);
        System.out.println(safeCount1 + safeCount2);
    }


    private static boolean checkDecreasing(String vals[]){
        int curr = Integer.parseInt(vals[0]);
        int next;
        for(int i = 1; i < vals.length; i++){
            next = Integer.parseInt(vals[i]);
            int comp = Integer.parseInt(vals[i]) - curr;
            if(Math.abs(comp) > 3 || comp >= 0)
                return false;
            curr = next;
        }
        return true; 
    }

    private static boolean checkIncreasing(String vals[]){
        int curr = Integer.parseInt(vals[0]);
        int next;
        for(int i = 1; i < vals.length; i++){
            next = Integer.parseInt(vals[i]);
            int comp = next - curr;
            if(Math.abs(comp) > 3 || comp <= 0)
                return false;
            curr = next;
        }
        return true;
    }

    private static boolean checkDecreasingSkip(String[] vals, int indexToSkip){
        String[] newVals = new String[vals.length - 1];
        for (int i = 0, j = 0; i < vals.length; i++) {
            if (i != indexToSkip) {
                newVals[j++] = vals[i];
            }
        }
        return checkDecreasing(newVals);
    }

    private static boolean checkIncreasingSkip(String[] vals, int indexToSkip){
        String[] newVals = new String[vals.length - 1];
        for (int i = 0, j = 0; i < vals.length; i++) {
            if (i != indexToSkip) {
                newVals[j++] = vals[i];
            }
        }
        return checkIncreasing(newVals);
    }
}