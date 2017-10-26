# conways-game-of-life
Please use the ConwaysGameOfLife.jar executable jar to run the program.

1) Improved Result Display:
		Developed a GUI to make the program interactive and visualize the output efficiently. 
		The state of the individual cell can be altered by clicking on the respective cell.
		
2) Loop through states: updated state becomes initial state and recalculates.
		An update button is available on the GUI to easily calculate updated state and make it as the initial state.
		
3) Emphasis on performance:
		The entire grid is initially read cell by cell to prepare the initial state.
		Optimized loops and conditions to improve performance.

Rules followed:

1. Any live cell with fewer than two live neighbors dies, as if caused by under-
population.
2. Any live cell with more than three live neighbors dies, as if by overcrowding.
3. Any live cell with two or three live neighbors lives on to the next generation.
4. Any dead cell with exactly three live neighbors becomes a live cell.
5. A cell’s neighbors are those cells which are horizontally, vertically or
diagonally adjacent. Most cells will have eight neighbors. Cells placed on the
edge of the grid will have fewer.
