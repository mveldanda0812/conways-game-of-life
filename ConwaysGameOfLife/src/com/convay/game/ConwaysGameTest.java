/**
 * JUnit object to unit test the Conway's Game of life
 * 
 * @author Madhu Veldanda
 * Date: 10/08/2017
 */
package com.convay.game;

import org.junit.Test;
public class ConwaysGameTest {

	private ConwaysGame game = new ConwaysGame();
	private int[][] currentState = 
    {   { 0, 0, 0, 0, 0, 0, 1, 0},
        { 1, 1, 1, 0, 0, 0, 1, 0},
        { 0, 0, 0, 0, 0, 0, 1, 0},
        { 0, 0, 0, 0, 0, 0, 0, 0},
        { 0, 0, 0, 1, 1, 0, 0, 0},
        { 0, 0, 0, 1, 1, 0, 0, 0},
    };
    
	@Test(timeout = 1000)
	public void test() {
		System.out.println("Initial State");
        game.drawInitialState(currentState, StaticValues.rows, StaticValues.cols);
        
        long startTime = System.nanoTime();
        int[][] updatedState = game.updatedStatus(currentState, StaticValues.rows, StaticValues.cols);
        long endTime = System.nanoTime();
        System.out.println("Time took to run first time-->"+(endTime-startTime) +" nano seconds");
        //assertArrayEquals(currentState, game.updatedStatus(updatedState, rows, cols));
        calculateRecursive(updatedState, 10);
	}

	private void calculateRecursive(int[][] newState, int counter){
        long startTime = System.nanoTime();
        //calculate recursively specified no of times by making updated state as initial
		newState = game.updatedStatus(newState, StaticValues.rows, StaticValues.cols);
        long endTime = System.nanoTime();
        System.out.println("Time took to run "+ counter+" time-->"+(endTime-startTime) +" nano seconds");		
		if(counter > 0){
			counter--;
			calculateRecursive(newState, counter);
		}
	}
}
