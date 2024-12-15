import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class day03 {
    public static void main(String[] args){
        try{
            String input = Files.readString(Paths.get("input.txt"));
            part1(input);
            part2(input);
        }
        catch(IOException e){
            System.out.println("Error reading file");
        }

    }

    public static void part1(String input){
        String reg = "mul\\(\\d{1,3},\\d{1,3}\\)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(input);
        
        long total = 0;
        
        while(matcher.find()){
            String currMatch = matcher.group();
            currMatch = currMatch.replaceAll("mul\\(", "");
            currMatch = currMatch.replaceAll("\\)", "");
            String[] vals = currMatch.split(",");
            
            total += (Integer.parseInt(vals[0]) * Integer.parseInt(vals[1]));
        }
        
        System.out.println(total);
    }

    public static void part2(String input){
        String reg = "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(input);
        boolean enableMUL = true;
        long total = 0;

        while(matcher.find()){
            String currMatch = matcher.group();
            if(currMatch.compareTo("do()") == 0){
                enableMUL = true;
            }else if(currMatch.compareTo("don't()") == 0){
                enableMUL = false;
            }else if(enableMUL){
                currMatch = currMatch.replaceAll("mul\\(", "");
                currMatch = currMatch.replaceAll("\\)", "");
                String[] vals = currMatch.split(",");
                total += (Integer.parseInt(vals[0]) * Integer.parseInt(vals[1]));
            }
        }

        System.out.println(total);
    }
}
