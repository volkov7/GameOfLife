import java.util.*;

public class GameOfLife {
	public static void fillGeneration(char[][] generation, Random random, int size) {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				generation[row][col] = random.nextBoolean() ? 'O' : ' ';
			}
		}
	}

	public static void printGeneration(char[][] generation, int size) {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				System.out.print(generation[row][col]);
			}
			System.out.println();
		}
	}

	public static int countNeighbours(char[][] currentGen, int row, int col, int size) {
		int neighbours = 0;

		for (int i = -1; i < 2; i++) {
			int x = (row + i + size) % size;
			for (int j = -1; j < 2; j++) {
				int y = (col + j + size) % size;
				if (x == row && y == col) {
					continue;
				}
				neighbours += (currentGen[x][y] == 'O') ? 1 : 0;
			}
		}
		return neighbours;
	}

	public static char[][] createNewGen(char[][] currentGen, int size) {
		char[][] newGen = new char[size][size];

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				int neighbours = countNeighbours(currentGen, row, col, size);

				if (currentGen[row][col] == ' ' && neighbours == 3) {
					newGen[row][col] = 'O';
				} else if (currentGen[row][col] == 'O' && (neighbours < 2 || neighbours > 3)) {
					newGen[row][col] = ' ';
				} else {
					newGen[row][col] = currentGen[row][col];
				}
			}
		}
		return newGen;
	}

	public static int countAlive(char[][] gen, int size) {
		int alive = 0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (gen[i][j] == 'O') {
					alive++;
				}
			}
		}
		return alive;
	}

	public  static void clearScreen() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int size = sc.nextInt();
		int countGenerations = 12;

		char[][] currentGen = new char[size][size];
		Random random = new Random();
		fillGeneration(currentGen, random, size);
		for (int count = 0; count < countGenerations; count++) {
			System.out.println("Generation #" + (count + 1));
			System.out.println("Alive: " + countAlive(currentGen, size));
			clearScreen();
			printGeneration(currentGen, size);
			currentGen = createNewGen(currentGen, size);
		}
		sc.close();
	}
}