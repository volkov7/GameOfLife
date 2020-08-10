import java.util.*;
import javax.swing.*;
import java.awt.*;

class GridsCanvass extends JPanel {
	int width, height;
	int rows;
	int cols;

	GridsCanvass(int w, int h, int r, int c) {
		setSize(width = w, height = h);
		rows = r;
		cols = c;
	}

	public void paint(Graphics g) {
		int i;
		width = getSize().width;
		height = getSize().height;

		// draw the rows
		int rowHt = height / (rows);
		// System.out.println(rowHt);
		for (i = 0; i <= rows + 1; i++)
			g.drawLine(0, i * rowHt, width, i * rowHt);

		// draw the columns
		int rowWid = width / (cols);
		// System.out.println(rowWid);
		for (i = 0; i <= cols + 1; i++)
			g.drawLine(i * rowWid, 0, i * rowWid, height);
	}
}

public class GameOfLife extends JFrame {

	public GameOfLife() {
		super("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 480 473 good when 400, 400, 30, 30
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		int size = 32;
		final int countOfGenerations = 100;
		JPanel[][] currentGen = null;
		Random random = new Random();
		currentGen = fillGeneration(random, size);
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				add(currentGen[row][col]);
			}
		}

		GridsCanvass xyz = new GridsCanvass(500, 500, 30, 30);
		add(xyz);
		setVisible(true);
		for (int k = 0; k < countOfGenerations; k++) {
			clearScreen();
			JPanel[][] newGen = createNewGen(currentGen, size);
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					currentGen[row][col].setBackground(newGen[row][col].getBackground());
				}
			}
		}
	}

	public static JPanel[][] fillGeneration(Random random, int size) {
		JPanel[][] generation = new JPanel[size][size];

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				generation[row][col] = new JPanel();
				generation[row][col].setBounds(row * 16, col * 15, 15, 14);
				generation[row][col].setBackground((random.nextBoolean()) ? Color.BLACK : Color.WHITE);
			}
		}
		return generation;
	}

	public static int countNeighbours(JPanel[][] currentGen, int row, int col, int size) {
		int neighbours = 0;

		for (int i = -1; i < 2; i++) {
			int x = (row + i + size) % size;
			for (int j = -1; j < 2; j++) {
				int y = (col + j + size) % size;
				if (x == row && y == col) {
					continue;
				}
				neighbours += (currentGen[x][y].getBackground() == Color.BLACK) ? 1 : 0;
			}
		}
		return neighbours;
	}

	public static JPanel[][] createNewGen(JPanel[][] currentGen, int size) {
		JPanel[][] newGen = new JPanel[size][size];

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				int neighbours = countNeighbours(currentGen, row, col, size);
				if (currentGen[row][col].getBackground() == Color.WHITE && neighbours == 3) {
					newGen[row][col] = new JPanel();
					newGen[row][col].setBounds(row * 15, col * 14, 15, 14);
					newGen[row][col].setBackground(Color.BLACK);
				} else if (currentGen[row][col].getBackground() == Color.BLACK && (neighbours < 2 || neighbours > 3)) {
					newGen[row][col] = new JPanel();
					newGen[row][col].setBounds(row * 15, col * 14, 15, 14);
					newGen[row][col].setBackground(Color.WHITE);
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
			java.lang.Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GameOfLife();
		// Scanner sc = new Scanner(System.in);

		// int size = sc.nextInt();
		// int countGenerations = 12;

		// char[][] currentGen = new char[size][size];
		// Random random = new Random();
		// fillGeneration(currentGen, random, size);
		// for (int count = 0; count < countGenerations; count++) {
		// 	System.out.println("Generation #" + (count + 1));
		// 	System.out.println("Alive: " + countAlive(currentGen, size));
		// 	clearScreen();
		// 	printGeneration(currentGen, size);
		// 	currentGen = createNewGen(currentGen, size);
		// }
		// sc.close();
	}
}