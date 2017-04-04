package magicsquares;
import java.util.Scanner;
/**
 * @author Ryan Fairbanks
 * @version 11/1/2016
 * @description User specifies size of a square (2D array) and its values.
 * Following user input, the program determines if the user's square is a magic
 * square: all rows and columns add up to the same value.
 */
public class MagicSquares {
    private static final int DEFAULT_SIZE = 10;
    private int count;
    private int i = 0;
    private MagicSquares [] magicList;
    private int [][] magicBox;
    public MagicSquares(int magicBox[][]){
        this.magicBox = magicBox;
    }
    public MagicSquares(){
        magicList = new MagicSquares[DEFAULT_SIZE];
        count = 0;
    }
    public void addSquare(MagicSquares newSquare){
        magicList[count] = newSquare;
        count++;
    }
    public void addSquare(int [][] mBox){
        MagicSquares newSquare = new MagicSquares(mBox);
        addSquare(newSquare);
    }
    private void setPosition(int n){
        i=n;
        return;
    }
    private int sizeMagic(){
        return magicList[i].magicBox.length;
    }
    private void buildMagic(){
        Scanner in = new Scanner(System.in);
        for(int x=0; x<magicList[i].magicBox.length; x++){
            for(int y=0; y<magicList[i].magicBox[x].length; y++){
                System.out.print("Row: " + (x+1) + " Column: " + (y+1) + " Enter a value: ");
                magicList[i].magicBox[x][y] = in.nextInt();
            }
        }
    }
    private void printMagic(){
        System.out.print("Current values stored in magic box: ");
        for(int x=0; x<magicList[i].magicBox.length; x++){
            for(int y=0; y<magicList[i].magicBox[x].length; y++){
                if(y == 0){
                    System.out.print("\n" + magicList[i].magicBox[x][y]);
                }
                else{
                    System.out.print(" " + magicList[i].magicBox[x][y]);
                }
            }
        }
    }
    public String toString(){
        StringBuilder display = new StringBuilder("");
        for(int x=0; x<magicList[i].magicBox.length; x++){
            for(int y=0; y<magicList[i].magicBox.length; y++){
                if(y == 0){
                    display.append(("\n" + magicList[i].magicBox[x][y]).toString());
                }
                else{
                    display.append((" " + magicList[i].magicBox[x][y]).toString());
                }
            }
        }
        return display.toString();
    }
    private boolean isMagic(){
        int value = 0;
        int tempX = 0;
        int tempY = 0;
        for(int z=0; z<magicList[i].magicBox.length; z++){
            value += magicList[i].magicBox[z][0];
        }
        for(int x=0; x<magicList[i].magicBox.length; x++){
            for(int y=0; y<magicList[i].magicBox[x].length; y++){
                tempX += magicList[i].magicBox[x][y];
                tempY += magicList[i].magicBox[y][x];
            }
            if(tempX != value || tempY !=+ value){
                return false;
            }
            tempX = 0;
            tempY = 0;
        }
        return true;
    }
    public static void main(String[] args) {
        MagicSquares myBox = new MagicSquares();
        Scanner in = new Scanner(System.in);
        System.out.print("Set size of square: ");
        int n = in.nextInt();
        int [][] mBox = new int [n][n];
        myBox.addSquare(mBox);
        System.out.println("Size of the magic square is: " + myBox.sizeMagic() + " by " + myBox.sizeMagic());
        myBox.buildMagic();
        System.out.println();
        myBox.printMagic();
        System.out.println();
        System.out.print("Test toString method: ");
        System.out.println(myBox.toString());
        System.out.print("Test if magic square is valid: ");
        System.out.println(myBox.isMagic());
    }
}