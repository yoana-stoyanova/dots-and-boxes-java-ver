import java.util.Scanner;

public class Main {

    public static void getPoints (Player[] p, int count){
        System.out.println("\nPOINTS:");

        for(int i = 0; i < count; i++){
            System.out.print("(" + (i + 1) + "): " + p[i].points + " ");
        }
    }

    public static int sumPoints(Player[] p, int count){
        int sum = 0;

        for(int i = 0; i < count; i++){
            sum += p[i].points;
        }

        return sum;
    }

    public static void sortPoints(Player[] p, int count){
        for(int i = 0; i < count - 1; i++){
            for(int j = 0; j < count - j - 1; j++){
                if(p[j].points > p[j + 1].points){

                    int b = p[j].points;
                    char c = p[j].letter;

                    p[j].points = p[j + 1].points;
                    p[j].letter = p[j + 1].letter;

                    p[j + 1].points = b;
                    p[j + 1].letter = c;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("\n************************************\nWELCOME TO DOTS & BOXES: UNLIMITED!\n************************************\n");

        System.out.print("Enter the number of players [< 51]: ");
        int playerCount = input.nextInt();

        while(playerCount > 50){

            System.out.println("!INVALID INPUT!");

            System.out.print("Enter the number of players [< 51]: ");
            playerCount = input.nextInt();
        }

        Player[] players = new Player[playerCount];

        for(int i = 0; i < playerCount; i++){

            System.out.print("PLAYER " + (i + 1)+", enter your name: ");
            players[i] = new Player(input.next().charAt(0));
        }

        System.out.print("\nEnter the size of your board /ROWS & COLUMNS/ [> 2]: ");
        int rows = input.nextInt();
        int columns = input.nextInt();

        while(rows < 2 || columns < 2 || (rows < 2 && columns < 2)){

            System.out.println("!INVALID INPUT!");
            System.out.print("\nEnter the size of your board /ROWS & COLUMNS/ [> 2]: ");

            rows = input.nextInt();
            columns = input.nextInt();
        }

        Board b = new Board(rows, columns);

        b.setDots();
        b.setBoardRows();
        b.setBoardColumns();
        b.setLetters();
        b.print();

        getPoints(players, playerCount);

        int totalPoints = (rows - 1) * (columns - 1);

        do {
            for(int i = 0; i < playerCount; i++){

                System.out.println("\n[Player " + players[i].letter + "'s turn]");

                b.setCoordinates(input);

                b.swap();

                while(!b.changeBoard()){

                    System.out.println("\n!INVALID COORDINATES!\n");

                    b.setCoordinates(input);

                    b.swap();
                }

                if(b.pointChecker(players[i].letter)) players[i].points++;

                System.out.println("\n==============================================================");

                b.print();
                getPoints(players, playerCount);

                if(players[0].points + players[1].points == (rows - 1) * totalPoints) break;
            }
        } while(sumPoints(players, playerCount) != totalPoints);

        sortPoints(players, playerCount);
        getPoints(players, playerCount);
    }
}