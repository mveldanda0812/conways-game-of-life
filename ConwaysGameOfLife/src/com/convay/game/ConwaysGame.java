/**
 * This class contains the logic for Conway's Game of Life 
 * to identify no of neighbors and update the state.
 * 
 * @author Madhu Veldanda
 * Date: 10/08/2017
 */
package com.convay.game;

public class ConwaysGame
{
	private int neighbors = 0;
	/**
	 * this draw the initial state of input 
	 * @param state
	 * @param rows
	 * @param cols
	 */
	void drawInitialState(int[][] state, int rows, int cols){
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (state[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("o");
            }
            System.out.println();
        }
        System.out.println();
        //updatedStatus(grid, rows, cols);
	}
	
	/**
	 * 
	 * @param state
	 * @param rows
	 * @param cols
	 * @return
	 */
	int[][] updatedStatus(int state[][], int rows, int cols)
    {
    	int[][] updatedState = new int[rows][cols];
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols ; col++)
            {
                // find alive neighbours
                int aliveNeighbours = countNeighbors(state, row, col);
                
                if(state[row][col] == 1){
	                // Any live cell with fewer than two live neighbors dies, as if caused by under- population.
                	// Any live cell with more than three live neighbors dies, as if by overcrowding.
	                if (aliveNeighbours < 2 || aliveNeighbours > 3)
	                    updatedState[row][col] = 0;
	                else
	                    updatedState[row][col] = state[row][col];
                }
                //Any dead cell with exactly three live neighbors becomes a live cell.
                else if ((state[row][col] == 0) && (aliveNeighbours == 3))
                    updatedState[row][col] = 1;
                else
                    updatedState[row][col] = state[row][col];
            }
        }
        drawUpdatedState(updatedState);
        return updatedState;
    }
    
    private int countNeighbors(final int state[][] , final int row, final int col) {
        neighbors = 0;

        //grouped common conditions to improve the performance.
        
      //if it is not first row
        if (row - 1 >= 0){
	        // check top right corner
	        if (col + 1 < state[0].length) {
	        	if(state[row - 1][col + 1] == 1){
	        		neighbors++;
	        	}
	        }
	        // check top
	        if (col < state[0].length) {
	        	if(state[row - 1][col] == 1){
	        		neighbors++;
	        	}
	        }
        }
        
        //if the left column exist
        if(col - 1 >= 0){
	        // check top left corner
	        if (row - 1 >= 0) {
	        	if(state[row - 1][col - 1] == 1){
	        		neighbors++;
	        	}
	        }
	        // check left 
	        if (row >= 0) {
	        	if(state[row][col - 1] == 1){
	        		neighbors++;
	        	}
	        }
	
	        // check bottom left corner
	        if (row + 1 < state.length) {
	        	if(state[row + 1][col - 1] == 1){
	        		neighbors++;
	        	}
	        }
        }
        
      //if next row exist
        if(row + 1 < state.length){
	        // check bottom
	        if (col < state[0].length) {
	        	if(state[row + 1][col] == 1){
	        		neighbors++;
	        	}
	        }
	        // check bottom right corner
	        if (col + 1 < state[0].length) {
	        	if(state[row + 1][col + 1] == 1){
	        		neighbors++;
	        	}
	        }
        }
        // check right
        if ((row < state.length) && (col + 1 < state[0].length)) {
        	if(state[row][col + 1] == 1){
        		neighbors++;
        	}
        }
        
        return neighbors;
    }
    
    void drawUpdatedState(int[][] updatedState){
        System.out.println("First Updated Status");
        for (int i = 0; i < StaticValues.rows; i++)
        {
            for (int j = 0; j < StaticValues.cols; j++)
            {
                if (updatedState[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("o");
            }
            System.out.println();
        }    	
    }
}