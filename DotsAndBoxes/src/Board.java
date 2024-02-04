import java.util.Scanner;
public class Board {
    private int rows = 10;
    private int columns = 10;

    public int x1, y1, x2, y2;

    Board(){};
    Board(int newRows, int newColumns){
        setRows(newRows);
        setColumns(newColumns);
    }

    protected char[][] dots = new char[rows][columns];
    protected String[][] boardRows = new String[rows][columns - 1];
    protected String[][] boardColumns = new String[rows - 1][columns];
    protected char[][] letters = new char[rows - 1][columns - 1];



    public void setRows(int rows) {
        this.rows = rows;
    }
    public int getRows() {
        return rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
    public int getColumns() {
        return columns;
    }

    public void setDots(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                dots[i][j] = '.';
            }
        }
    }

    public void setBoardRows(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns - 1; j++){
                boardRows[i][j] = "    ";
            }
        }
    }

    public void setBoardColumns(){
        for(int i = 0; i < rows - 1; i++){
            for(int j = 0; j < columns; j++){
                boardColumns[i][j] = " ";
            }
        }
    }

    public void setLetters(){
        for(int i = 0; i < rows - 1; i++){
            for(int j = 0; j < columns - 1; j++){
                letters[i][j] = ' ';
            }
        }
    }

    public void setCoordinates(Scanner inp){
        System.out.print("Enter 1st point /X & Y/: ");
        x1 = inp.nextInt();
        y1 = inp.nextInt();

        System.out.print("Enter 2nd point /X & Y/: ");
        x2 = inp.nextInt();
        y2 = inp.nextInt();
    }

    public void print(){

        System.out.println();

        System.out.print("   ");
        for(int j = 0; j < columns; j++) System.out.print("(" + j + ")  ");
        System.out.println();

        for(int i = 0; i < rows - 1; i++){
            System.out.print("(" + i + ") ");
            for(int j = 0; j < columns - 1; j++) System.out.print(dots[i][j] + boardRows[i][j]);
            System.out.println(dots[i][columns-1]);

            System.out.print("    ");
            for(int j = 0; j < columns - 1; j++) System.out.print(boardColumns[i][j] + " " + letters[i][j] + "  ");
            System.out.println(boardColumns[i][columns-1]);
        }
        System.out.print("("+ (rows - 1) + ") ");
        for(int j = 0; j < columns - 1; j++) System.out.print(dots[rows - 1][j] + boardRows[rows - 1][j]);
        System.out.println(dots[rows - 1][columns-1]);
    }

    public void swap(){
        if(x1 == x2){
            if(y1 > y2){
                int s = y1;
                y1 = y2;
                y2 = s;
            }
        } else if(y1 == y2){
            if(x1 > x2){
                int s = x1;
                x1 = x2;
                x2 = s;
            }
        }
    }

    public boolean changeBoard(){
        if(x1 == x2){
            if(boardRows[x1][y1].equals("    ") && y2 - y1 == 1){
               boardRows[x1][y1] = "----";
               return true;
            }
            else return false;
        }
        else if(y1 == y2){
            if (boardColumns[x1][y1].equals(" ") && x2 - x1 == 1) {
                boardColumns[x1][y1] = "|";
                return true;
            }
            else return false;
        }
        else return false;
    }

    public boolean pointChecker(char letter){
        if(x1 == x2){
            if(x1 < rows - 1 && boardColumns[x1][y1].equals("|") && boardColumns[x2][y2].equals("|") && boardRows[x1 + 1][y1].equals("----")){
                letters[x1][y1] = letter;
                return true;
            } else if(x1 > 0 && boardColumns[x1 - 1][y1].equals("|") && boardColumns[x2 - 1][y2].equals("|") && boardRows[x1 - 1][y1].equals("----")){
                letters[x1 - 1][y1] = letter;
                return true;
            }
        } else if(y1 == y2){
            if(y1 < columns - 1 && boardRows[x1][y1].equals("----") && boardRows[x2][y2].equals("----") && boardColumns[x1][y1 + 1].equals("|")){
                letters[x1][y1] = letter;
                return true;
            } else if(y1 > 0 && boardRows[x1][y1 - 1].equals("----") && boardRows[x2][y1 - 1].equals("----") && boardColumns[x1][y1 - 1].equals("|")){
                letters[x1][y1 - 1] = letter;
                return true;
            }
        }
        return false;
    }

}
