# Sudoku README

The goal of this project was to create a sudoku solver and generator. I accomplished this by applying some knowledge learned from the artificial intelligence class. My approach to this project was to create a solved state sudoku board generator, then create a solver for sudoku puzzles. Using this solved state sudoku board and solver, I would then be able to create the sudoku puzzle generator. 


## Creating the solved state sudoku board generator

When thinking about how to create a solved state sudoku board generator, I reflected upon A.I. projects such as N-Queens solver. Although there are many different ways to solve the N-Queens problem, the most efficient way to solve the problem that I knew was by using the min-conflicts algorithm. 

The min-conflicts algorithm works by choosing a randomly conflicted variable and randomly choosing from the set of possible values that are minimally conflicted.The variable's value is then changed to that randomly selected minimally conflicted values. The process is repeated until either a solution is found or an arbitrary max number of attempts are reached. Applying min-conflicts to the solved state sudoku board problem, I had the tiles represented as the variables. This means that any tile that was conflicted (conflicting with the sudoku rules) would then be changed to a randomly selected minimally conflicted tile number. The process would be repeated until a solution is found or a max number of steps is reached which I derived through testing and concluded to be most efficient. That efficient max steps number for a 9x9 sudoku board I concluded to be around 350 steps, since it minimizes the number of iterations for a board state if the algorithm was stuck on a local maxima preventing it from finding a solution, but the number was still big enough for most boards to be solved within those max steps. The average number of iterations before success using this number was around 6.5 iterations. This resulted in an average number of around 2200-2350 steps for a solved state sudoku board.

The min-conflicts algorithm was fairly efficient at generating the solved state sudoku board but I still wonder if using backtracking would be faster. Given more time, I would probably attempt the backtracking method to compare the efficiency.


## Creating the sudoku solver

When attempting to create the sudoku solver, I initially attempted to also apply min-conflicts to solve the problem. However, I quickly realized that it was foolish since min-conflicts is only efficient when solutions are densely distributed within the state space and since sudoku only had one unique solution. The only way I could think of creating the solver was to apply backtracking to the problem. To do this, I created a class called the PotentialMovesBoard. This is a 2d array similar to a board, but instead of the 2d array holding values, it holds potential moves which are implemented using linked lists. A tile whose value is known has a potential moves list of size 1. Creating a potential moves board for the solver involves copying a board state, then removing potential moves based on the initial state of the board. The removal of potential moves can potentially create a chain of potential move removals which could even potentially solve the sudoku problem.

Once a potential moves board is created, if the sudoku board is not solved, the solver then uses the minimum-remaining values heuristic (MRV) to choose the first tile from the set of tiles that have minimum-remaining values (potential moves list greater than size 1). This tile is then chosen to begin the backtracking process. The MRV heuristic helps to significantly reduce the branching factor when traversing through the search space thus increasing efficiency of the algorithm. Once the first tile is chosen, a value is chosen from the list of potential moves. The backtracking algorithm then creates a new deep copy of the PotentialMovesBoard but with all of the potential moves except the chosen value removed. This essentially assumes that the tile value is the chosen value. The algorithm then eliminates potential moves due to this new tile value. If the eliminiation of potential moves creates a solution, the solution is returned. If the elimination creates a state where a potential moves list has no remaining values, the board is not solveable and null is returned. Otherwise, the board state is potentially solveable, and the algorithm creates a new potential moves board and repeats the process until a solution is found or no solution is found.

It's possible arraylists may be more efficient than linked lists for the purposes of the solver. Other ways that could potentially improve the speed of the solver would be using multiple threads, or object pools to decrease the time needed to create new objects.


## Creating the sudoku generator

With the solved state generator created and the sudoku solver created, creating the sudoku generator becomes very simple. The sudoku generator attempts to remove all of the tiles of the solved board state in a random order. If removing a tile creates multiple solutions, the tile is restored. This is done since a sudoku puzzle requires that there is one unique solution. The function to check for multiple solutions is a modification of the solver algorithm that instead of returning when a solution is found, will return true when 2 solutions are found or false if only 1 solution is found. Upon completion of the attempt to remove all tiles, a minimal sudoku 
puzzle is generated. A minimal sudoku puzzle is a puzzle that can not have a tile removed without creating multiple solutions. The generator could also potentially be faster from multiple threads or object pools.


## Analyzing the results

The solved states are generated with an average time of 0.00939868448599997 seconds on my machine. This seems to be the slower component of the two generators.

The puzzle generator creates puzzles with an average time 0.008429166396199977 seconds.

The solver solves puzzles with an average time of 5.2170906900000244E-5 seconds on my machine. It is significantly faster than either the solve state generator or the puzzle generator.

The result of the solved state generator, solver, and generator is a fairly efficient program that can generate and solve minimal sudoku puzzles with times of around 0.017880021789099948 seconds per puzzle. 

Analyzing the minimal sudoku puzzles generated interesting results. Through millions of puzzles generated, the most common number of clues in a minimal sudoku puzzle appears to be 26 clues at around 32%. The highest clued puzzle I have randomly generated are 32 clue puzzles with only 6 out of 3195099 puzzles generated. The lowest clued puzzle that I have generated are 21 clue puzzles with 78 out of 3195099 puzzles generated. Upon doing research on sudoku puzzles, it appears that there may be around 49000 17 clue sudoku puzzles possible, which is a large number but significantly smaller than the total number of minimal sudoku puzzles which is estimated to be at around 3.10 × 10^37. The fact that I have never generated even a 20 clue sudoku puzzle, much less a 17 clue sudoku puzzle shows how small of a chance it is to randomly generate such a puzzle.


## Limitations of the Program

The sudoku puzzle generator is not currently able to dynamically generate different size n boards. This is due my implementation of the solved state generator using the min-conflicts algorithm. Since the min-conflicts algorithm requires a maximum step size and since an optimal step size for various nxn boards is not known, it is not possible to dynamically generate higher n boards. Using a different method such as backtracking might make it possible, although sudoku puzzles with higher levels of n will surely take an unreasonable amount of time to generate.
Although the sudoku generator is efficient for 9x9 sudoku boards, higher dimension boards such as 16x16 or higher are able to be generated but require significantly higher amounts of time. I believe this is due to the nature of sudoku where the solution space is exponentially higher which significantly increases the amount of time needed to generate puzzles. Another reason why the generator might be significantly slower for higher sizes of n is because I chose to use the min-conflicts algorithm to generate the solved state solution. Min-conflicts is significantly worse if the solution space is sparse which might be the case for higher levels of n. Perhaps backtracking instead of min-conflicts would be significantly faster and allow for more reasonable generation times for higher nxn puzzles.


## Sample data generated

21 clue puzzle tally: 1
22 clue puzzle tally: 10
23 clue puzzle tally: 183
24 clue puzzle tally: 1109
25 clue puzzle tally: 2743
26 clue puzzle tally: 3280
27 clue puzzle tally: 1894
28 clue puzzle tally: 654
29 clue puzzle tally: 108
30 clue puzzle tally: 17
31 clue puzzle tally: 1
Total puzzles generated : 10000
Puzzles generated: 10000
Average solved state generation time (s): 0.00939868448599997
Average puzzle generation time (s): 0.008429166396199977
Average puzzle solving time (s): 5.2170906900000244E-5
Average time elapsed (s): 0.017880021789099948
Puzzles generated and solved per second: 55.92834347716633389
Total time elapsed (s): 178.80021789099948

Total puzzles generated: 3195099
Total seconds: 60810.113969185
Average puzzles generated per second: 52.542230090525546790449113848852

Number of clues		Puzzles generated	Percentage of total clues
21			78			2.4412389099680479384206874340983e-3
22 			4084			0.127820765491147535647565224114
23			60004			1.878001276329778826884550369175
24			349471			10.937720552633893347279693054894
25			871196			27.266635556519531945645502690214
26			1033790			32.355491958152157413588749519185
27			627490			19.639141071998082062558937923363
28			205814			6.441553141232869466642504661045
29			38648			1.209602581954424573385675999398
30			4210			0.131764305268788228471167873046
31			308			9.6397639008994713465842529449009e-3
32			6			1.8778760845908061064774518723833e-4



