# Game Of Life
### Introduction
This is a implementation of 2 players [Conway's Game of life](https://en.wikipedia.org/wiki/Conway%27_s_Game_of_Life) with variations. The Game of Life is played on a grid of cells, where each cell is either dead or alive. For each “turn” or “generation” of the game, the game iterates over all of the cells and determines whether a cell is alive or dead based on a set of rules. 
The rules for the standard single player version are as follows:
  - Any live cell with fewer than two live neighbors dies (under population) 
  - Any live cell with two or three live neighbors lives.
  - Any live cell with more than three neighbors dies (over population)
  - Any dead cell with exactly three neighbors becomes a live cell.

Neighboring cells are cells adjacent to the cell (up to 8 cells). The alive or dead state is determined simultaneously for all cells.
In this project for two player Game of Life, each cell is either dead, contains an alive cell owned by Player 1 or an alive cell owned by Player 2. 
The rules for the two player Game of Life are then as follows:
  - Any live cell with fewer than two live neighbors of either player dies. 
  - Any live cell with two or three live neighbors of either player lives.
  - Any live cell with more than three neighbors of either player dies.
  - Any dead cell with exactly three neighbors becomes a live cell owned by the player who has the majority of live neighboring cells.

The game continues creating new generations until one player no longer has any more live cells. The player with remaining live cells is the winner. If both players’ remaining cells die simultaneously, a tie is declared.

### Functional Specfications
This is a Java program that will play the two player Game of Life. 

The format of the initial generation file is as follows:
1. The first line contains the number of rows and columns in the grid, delimited by a space (i.e. 5 6)
2. The remaining lines specify the state of the initial generation. Each line is a row in the grid where the state of each cell is a character in that line. There is a character for each column in the grid. The state of a cell is specified as follows:

  * A . denotes a dead cell.
  * A 1 denotes a live cell owned by player 1. 
  * A 2 denotes a live cell owned by player 2.

Here is an example initial generation file: 10 10 
```sh
    ..........  
    ..1....... 
    ...1...... 
    ..111.....
    ..........
    .......22. 
    ......2..2 
    .......22.
    ..........
    ..........
```

When the program starts, it reads the initial generation file and validates that it is correct. An invalid ini- tial generation file is one that does not follow the specification. If the program determines that the initial generation file is invalid, it displays an error message and terminates.
After the initial generation file is read, the program prints the state of the generation. The state of the generation consists of:
1. The generation number. The initial generation is considered generation 0. 
2. The number of live cells owned by player 1.
3. The number of live cells owned by player 2.
4. The grid of live and dead cells following same convention for the state of the cells used for the initial generation file.

For the example initial generation file above, the program prints out the following state:

>Generation #0 

>Player 1 Cells: 5 

>Player 2 Cells: 6 

```sh
    .......... 
    ..1....... 
    ...1...... 
    ..111.....
    .......... 
    .......22. 
    ......2..2 
    .......22. 
    .......... 
    ..........
```

The program then generates the next state given the initial state and prints out the state in the same format
as the initial state:

>Generation #1 

>Player 1 Cells: 5 

>Player 2 Cells: 6 

```sh
    ..........
    ..........
    ....1.....
    ..111.....
    ...1......
    .......22. 
    ......2..2
    .......22.
    ..........
    ..........
```

The program checks to see if one of the players no longer has any live cells. If so, the program prints out the winner and how many cells that player has remaining:

>Player 1 Wins! with 11 live cells

>Game Over!!
 
If both players no longer have any live cells, the program prints out there is a tie and then terminates. Otherwise, the program continues generating new states, printing out the state and determine whether or not there is a winner until a winner is declared or the program is terminated.


### Contributing 
I encourage you to test and play the Game of Life, its real fun!!. Send your pull requests with improvements and suggestions to make the game even more fun.

