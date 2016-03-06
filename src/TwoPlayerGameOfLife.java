import java.io.*;

/**
 * The Class TwoPlayerGameOfLife.
 * 
 * @author sharan
 *
 */
public class TwoPlayerGameOfLife {

	public static int rows, columns;
	public static int generation = 0, Player1 = 0, Player2 = 0;
	static String[][] grid;
	static String[][] temp;
	static String[][] toCheck;

	/**
	 * The main method. Read the Initial file and kick start the game
	 * 
	 * @param args
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		try {
			readInitialGrid();
			gameStart();

		} catch (Exception e) {
			System.out.println("Exception in starting the game");
			System.out.println(e.getMessage());
			return;
		}
	}

	/**
	 * Game start. Method the check the initial grid positions and run the game,
	 * prints out the outcome of the game.
	 */
	public static void gameStart() {
		initTocheck();
		runGame();
		while (gridCompare()) {
			copyGrid();
			runGame();
		}
		if (Player1 < Player2)
			System.out.println("Player 2 Wins! with " + Player2 + " live cells");
		else if (Player1 == Player2)
			System.out.println("The Game is Tied! Both players have equal number of live cells :" + Player1);
		else if (Player1 > Player2)
			System.out.println("Player 1 Wins! with " + Player1 + " live cells");
		System.out.println(" Game Over!!");
	}

	/**
	 * Method to copy each iteration of the game to compare with the previous
	 * run.
	 */
	public static void initTocheck() {
		toCheck = new String[grid.length][grid.length];
		for (int a = 0; a < grid.length; a++) {
			for (int b = 0; b < grid.length; b++) {
				toCheck[a][b] = grid[a][b];
			}
		}
	}

	/**
	 * Method to copy the grid before each run of the game
	 */
	public static void copyGrid() {
		for (int a = 0; a < grid.length; a++) {
			for (int b = 0; b < grid.length; b++) {
				grid[a][b] = toCheck[a][b];
			}
		}
	}

	/**
	 * Compare the grids after each run, to check if there is any change
	 *
	 * @return true, if there is any change so that the game can proceed false,
	 *         if there is no change, indicating the end of the game
	 */
	public static boolean gridCompare() {
		// TODO Auto-generated method stub

		if (Player1 == 0 || Player2 == 0)
			return false;
		else {
			for (int a = 0; a < grid.length; a++) {
				for (int b = 0; b < grid.length; b++) {
					if (!grid[a][b].equals(toCheck[a][b])) {
						return true;
					}

				}
			}
		}
		return false;
	}

	/**
	 * Method to read initial grid.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void readInitialGrid() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Generation0.txt"));
		try {
			String line = br.readLine();
			String[] rc = line.split(" ");
			rows = Integer.parseInt(rc[0]);
			columns = Integer.parseInt(rc[1]);
			grid = new String[rows][columns];
			temp = new String[rows][columns];
			int r = 0;
			line = br.readLine();
			while (line != null) {
				String[] str_array = new String[line.length()];
				for (int i = 0; i < line.length(); i++) {
					String ch = line.substring(i, i + 1);
					str_array[i] = ch;
				}
				for (int i = 0; i < str_array.length; i++) {
					grid[r][i] = str_array[i];

					if (grid[r][i].equals("1")) {
						Player1++;
					} else if (grid[r][i].equals("2")) {
						Player2++;
					}
				}
				line = br.readLine();
				r += 1;
			}
		} catch (Exception e) {
			System.out.println("Exception in File reading");
			throw e;
		} finally {
			br.close();
		}
	}

	/**
	 * Display generated grid.
	 *
	 * @param toprint
	 *            the toprint
	 */
	public static void displayGeneratedGrid(String[][] toprint) {
		int p1 = 0, p2 = 0;
		for (int c = 0; c < grid.length; c++) {
			for (int d = 0; d < grid.length; d++) {
				if (grid[c][d].equals("1")) {
					p1++;
				} else if (grid[c][d].equals("2")) {
					p2++;
				}
			}
		}
		Player1 = p1;
		Player2 = p2;
		System.out.println("Generation #" + generation);
		System.out.println("Player 1 Cells - " + p1);
		System.out.println("Player 2 Cells - " + p2);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				String character = toprint[i][j];
				System.out.print(character);
			}
			System.out.println();
		}
	}

	// This method checks for number of live and dead cells for each player

	/**
	 * Lets play.
	 *
	 * @param to_check
	 *            the to_check
	 * @param key
	 *            the key
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
	public static void letsPlay(String[] to_check, String key, int i, int j) {
		int zero = 0, one = 0, two = 0;
		for (int k = 0; k < to_check.length; k++) {
			if (to_check[k].equals("."))
				zero = zero + 1;
			else if (to_check[k].equals("1"))
				one = one + 1;
			else
				two = two + 1;
		}
		if (!grid[i][j].equals(".") && (one + two) < 2)
			toCheck[i][j] = ".";
		else if (!grid[i][j].equals(".") && (one + two) > 3)
			toCheck[i][j] = ".";
		else if (!grid[i][j].equals(".") && ((one + two) == 3 || (one + two) == 2))
			toCheck[i][j] = grid[i][j];
		else if (grid[i][j].equals(".") && (one + two) == 3) {
			if (one > two)
				toCheck[i][j] = "1";
			else if (one < two)
				toCheck[i][j] = "2";
			else
				toCheck[i][j] = "1";
		}
	}

	/**
	 * Run game.
	 */
	public static void runGame() {
		displayGeneratedGrid(grid);
		int i, j;
		for (i = 0; i < grid.length; i++) {
			for (j = 0; j < grid.length; j++) {
				if (i == 0 && j == 0) {
					check_gridA(i, j, grid[i][j]);
				} else if (i == 0 && j != grid.length - 1) {
					check_gridT(i, j, grid[i][j]);
				} else if (j == 0 && i != grid.length - 1) {
					check_gridL(i, j, grid[i][j]);
				} else if (i == grid.length - 1 && j == grid.length - 1) {
					check_gridZ(i, j, grid[i][j]);
				} else if (i == 0 && j == grid.length - 1) {
					check_gridTR(i, j, grid[i][j]);
				} else if (j == 0 && i == grid.length - 1) {
					check_gridBL(i, j, grid[i][j]);
				} else if ((i != 0 && j == grid.length - 1) || (j == grid.length - 1 && i != grid.length - 1)) {
					check_gridR(i, j, grid[i][j]);
				} else if ((j != 0 && i == grid.length - 1) || (i == grid.length - 1 && j != grid.length - 1)) {
					check_gridB(i, j, grid[i][j]);
				} else {
					check_grid(i, j, grid[i][j]);
				}
			}
		}
		generation++;
	}

	/**
	 * Check_grid.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @param val
	 *            the val
	 */
	public static void check_grid(int i, int j, String val) {
		String[] array = new String[] { grid[i - 1][j - 1], grid[i - 1][j], grid[i - 1][j + 1], grid[i][j - 1],
				grid[i][j + 1], grid[i + 1][j - 1], grid[i + 1][j], grid[i + 1][j + 1] };
		letsPlay(array, val, i, j);
	}

	/**
	 * Check_grid t.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @param val
	 *            the val
	 */
	public static void check_gridT(int i, int j, String val) {

		String[] array = new String[] { grid[i][j - 1], grid[i][j + 1], grid[i + 1][j - 1], grid[i + 1][j],
				grid[i + 1][j + 1] };
		letsPlay(array, val, i, j);
	}

	/**
	 * Check_grid l.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @param val
	 *            the val
	 */
	public static void check_gridL(int i, int j, String val) {
		String[] array = new String[] { grid[i - 1][j], grid[i - 1][j + 1], grid[i][j + 1], grid[i + 1][j],
				grid[i + 1][j + 1] };
		letsPlay(array, val, i, j);
	}

	/**
	 * Check_grid a.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @param val
	 *            the val
	 */
	public static void check_gridA(int i, int j, String val) {
		String[] array = new String[] { grid[i][j + 1], grid[i + 1][j], grid[i + 1][j + 1] };
		letsPlay(array, val, i, j);
	}

	/**
	 * Check_grid z.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @param val
	 *            the val
	 */
	public static void check_gridZ(int i, int j, String val) {
		String[] array = new String[] { grid[i][j - 1], grid[i - 1][j], grid[i - 1][j - 1] };
		letsPlay(array, val, i, j);
	}

	/**
	 * Check_grid tr.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @param val
	 *            the val
	 */
	public static void check_gridTR(int i, int j, String val) {
		String[] array = new String[] { grid[i][j - 1], grid[i + 1][j - 1], grid[i + 1][j] };
		letsPlay(array, val, i, j);
	}

	/**
	 * Check_grid bl.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @param val
	 *            the val
	 */
	public static void check_gridBL(int i, int j, String val) {
		String[] array = new String[] { grid[i][j + 1], grid[i - 1][j], grid[i - 1][j + 1] };
		letsPlay(array, val, i, j);
	}

	/**
	 * Check_grid r.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @param val
	 *            the val
	 */
	public static void check_gridR(int i, int j, String val) {
		String[] array = new String[] { grid[i - 1][j - 1], grid[i - 1][j], grid[i][j - 1], grid[i + 1][j - 1],
				grid[i + 1][j] };
		letsPlay(array, val, i, j);
	}

	/**
	 * Check_grid b.
	 *
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @param val
	 *            the val
	 */
	public static void check_gridB(int i, int j, String val) {
		String[] array = new String[] { grid[i][j - 1], grid[i - 1][j - 1], grid[i - 1][j], grid[i - 1][j + 1],
				grid[i][j + 1] };
		letsPlay(array, val, i, j);
	}
}
