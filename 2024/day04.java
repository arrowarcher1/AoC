import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class day04 {
    public static void main(String[] args) {
        try{
            ArrayList<char[]> readGrid = new ArrayList<>();
            
            BufferedReader read = new BufferedReader(new FileReader("inputs/day04.txt"));

            String data;
            while((data = read.readLine()) != null){
                readGrid.add(data.toCharArray());
            }

            char[][] grid = new char[readGrid.size()][];
            for(int i = 0; i < readGrid.size(); i++){
                grid[i] = readGrid.get(i);
            }

            System.out.println(countXMAS(grid));
            System.out.println(countXMAS2(grid));

            read.close();
        }
        catch(IOException e){
            System.out.println("Error reading file");
        }        
    }

    private static int countXMAS(char[][] grid){
        int count = 0;
        int tRows = grid.length;
        int tCols = grid[0].length;

        int[][] possibleDirections = {
            {0, 1},//right
            {0, -1}, //left
            {1, 0}, //Down
            {-1, 0},//up
            {1, 1},//down right
            {1, -1},//down left
            {-1, 1},//Up right
            {-1, -1}//Up left
        };

        for(int r = 0; r < tRows; r++){
            for(int c = 0; c < tCols; c++){
                if(grid[r][c] != 'X') continue;

                for(int[] dir : possibleDirections){//we can use this to loop through each direction
                    int rowDir = dir[0];
                    int colDir = dir[1];

                    int rowEnd = (r + (3 * rowDir));
                    int colEnd = (c + (3 * colDir));

                    if(rowEnd >= tRows || rowEnd < 0 || colEnd >= tCols || colEnd < 0)
                        continue;
                    
                    StringBuilder sb = new StringBuilder();
                    sb.append('X');
                    for(int i = 1; i <= 3; i++){
                        sb.append(grid[r + (rowDir * i)][c + (colDir * i)]);
                    }

                    if(sb.toString().compareTo("XMAS") == 0)
                        count++;
                }
                
            }
        }

        return count; 
    }

    private static int countXMAS2(char[][] grid){
        int count = 0;
        int tRows = grid.length;
        int tCols = grid[0].length;

        for(int r = 0; r < tRows; r++){
            for(int c = 0; c < tCols; c++){
                if(grid[r][c] != 'A') continue;
                boolean x1Valid = false;
                boolean x2Valid = false;
                
                int[][] x1 = {
                    {-1, -1}, //Up Left
                    {1, 1}//down right
                };
                int[][] x2 = {
                    {1, -1},//down left
                    {-1, 1}//Up right
                };

                StringBuilder sb = new StringBuilder();
                for(int dir[] : x1){
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];

                    if(newRow >= tRows || newRow < 0 || newCol >= tCols || newCol < 0){
                        x1Valid = false;
                        break;
                    }
                    sb.append(grid[newRow][newCol]);
                }

                String str = sb.toString();

                if((str.compareTo("SM") == 0) || (str.compareTo("MS") == 0))
                    x1Valid = true;
                    


                sb.setLength(0);
                for(int dir[] : x2){
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];

                    if(newRow >= tRows || newRow < 0 || newCol >= tCols || newCol < 0){
                        x2Valid = false;
                        break;
                    }
                    sb.append(grid[newRow][newCol]);
                }

                str = sb.toString();
                if((str.compareTo("SM") == 0) || (str.compareTo("MS") == 0))
                    x2Valid = true;


                if(x1Valid && x2Valid){
                    count++;
                    
                }
            }
        }
        return count;
    }
}
