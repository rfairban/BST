package randomwalk;
import java.util.Scanner;
import java.util.Random;

/**
 * @author Ryan Fairbanks and Beryl Wei
 * 
 * @description A bug is placed in the center of a room of tiles represented by 
 * a 2D array. Program calculates how many tiles the bug will visit before every
 * tile in the array is visited at least once.
 */
public class RandomWalk {
    private static final int DEFAULT_SIZE = 10;
    private static final int DEFAULT_X = 10;
    private static final int DEFAULT_Y = 12;
    private int xCurrent;
    private int yCurrent;
    private int count;
    private int [][] bugWalk;
    private RandomWalk [] randomList;
    public RandomWalk(int [][] bugWalk){
        this.bugWalk = bugWalk;
    }
    public RandomWalk(int n){
        randomList = new RandomWalk[n];
        count = 0;
    }
    public RandomWalk(){
        this(DEFAULT_SIZE);
    }
    public void addWalk(RandomWalk newWalk){
        randomList[count] = newWalk;
        count++;
    }
    public void addWalk(int x, int y){
        int [][] tempWalk = new int [x][y];
        RandomWalk newWalk = new RandomWalk(tempWalk);
        addWalk(newWalk);
    }
    public void addWalk(){
        addWalk(DEFAULT_X, DEFAULT_Y);
    }
    public void runBug(){
        xCurrent = randomList[count-1].bugWalk.length/2; //Start bug in center or as close as possible to it
        yCurrent = randomList[count-1].bugWalk[xCurrent].length/2;
        randomList[count-1].bugWalk[xCurrent][yCurrent] += 1;
        while(allVisited() == false){
            nextMove();
        }
    }
    public boolean allVisited(){ //Detect if bug has visited all tiles.
        for(int x=0; x<randomList[count-1].bugWalk.length; x++){
            for(int y=0; y<randomList[count-1].bugWalk[x].length; y++){
                if(randomList[count-1].bugWalk[x][y] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    private void nextMove(){
        Random gen = new Random();
        int xMove = gen.nextInt(3)-1;
        int yMove = gen.nextInt(3)-1;
        if(xCurrent == 0){ //Bug against left wall
            if(xMove == -1){
                xMove = gen.nextInt(2);
            }
        }
        if(xCurrent == randomList[count-1].bugWalk.length-1){ //Bug against right wall
            if(xMove == 1){
                xMove = gen.nextInt(2)-1;
            }
        }
        if(yCurrent == 0){ //Bug against top wall
            if(yMove == -1){
                yMove = gen.nextInt(2);
            }
        }
        if(yCurrent == randomList[count-1].bugWalk[xCurrent].length-1){//Bug against bottom wall
            if(yMove == 1){
                yMove = gen.nextInt(2)-1;
            }
        }
        xCurrent += xMove;
        yCurrent += yMove;
        randomList[count-1].bugWalk[xCurrent][yCurrent] += 1; //Add step
    }
    public int getTotalSteps(){
        int total = 0;
        for(int x=0; x<randomList[count-1].bugWalk.length; x++){
            for(int y=0; y<randomList[count-1].bugWalk[x].length; y++){
                total += randomList[count-1].bugWalk[x][y];
            }
        }
        return total;
    }
    public void printRoom(){
        System.out.print("Current visits to each tile: ");
        for(int x=0; x<randomList[count-1].bugWalk.length; x++){
            for(int y=0; y<randomList[count-1].bugWalk[x].length; y++){
                if(y == 0){
                    System.out.printf("\n %5d", randomList[count-1].bugWalk[x][y]);
                }
                else{
                    System.out.printf(" %5d", randomList[count-1].bugWalk[x][y]);
                }
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        RandomWalk myWalk;
        System.out.print("Enter number of trials (Enter 0 for default): ");
        int n = in.nextInt();
        if(n != 0){
            myWalk = new RandomWalk(n);
        }
        else{
            myWalk = new RandomWalk();
        }
        System.out.print("Enter x-axis size for first random walk (Enter 0 to use default): ");
        int x = in.nextInt();
        System.out.print("Enter y-axis size for first random walk (Enter 0 to use default: ");
        int y = in.nextInt();
        if(x == 0 || y == 0){
            myWalk.addWalk();
        }
        else{
            myWalk.addWalk(x, y);
        }
        myWalk.runBug();
        myWalk.printRoom();
        System.out.println("Total number of steps: " + myWalk.getTotalSteps());
    }
}