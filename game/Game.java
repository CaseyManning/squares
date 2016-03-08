package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Game extends JPanel implements MouseListener, ActionListener {

	int[][] board = new int[5][5];
	Point start;
	Point currentLocation = new Point();
	HashMap<Integer, ImageIcon> tiles;
	
	ArrayList<String> achievements = new ArrayList<String>();
	JPanel HSP = new JPanel();
	JPanel boardPanel = new JPanel();
	
	ImageIcon restart;
	ImageIcon skip;
	ImageIcon background = new ImageIcon("background.jpg");
	ImageIcon ff = new ImageIcon("highlighted_fastFoward.png");
	ImageIcon settings;
	ImageIcon back = new ImageIcon("back.png");
	ImageIcon fade = new ImageIcon("white_square.png");
	ImageIcon help = new ImageIcon("help.png");
	
	JSlider boardSizeSlider = new JSlider(5, 10, 5);

	int boardOffset = 0;
	int numLevels = 7;
	int level = 1;
	int score = 0;
	int boardLength = 5;
	float opacity = 0.02f;
	float Ropacity = 0.0999999f;
	
	boolean up = true;
	boolean rup = true;
	boolean go = true;
	boolean lvl1start = false;
	boolean levelSelect = false;
	boolean settingsMenu = false;
	boolean helpMenu = false;
	boolean restarting = false;
	boolean fastFoward = true;

	public static void main(String[] args) {
		Game g = new Game();
	}

	public Game() {

		this.setFocusable(true);
		this.requestFocus();
		JFrame frame = new JFrame();
		add(HSP);
		tiles = new HashMap<Integer, ImageIcon>();
		ImageIcon g = new ImageIcon("gray_box.png");
		g.setImage(g.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));
		tiles.put(0, g);

		ImageIcon gr = new ImageIcon("green_box.png");
		gr.setImage(gr.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));
		tiles.put(1, gr);

		ImageIcon l = new ImageIcon("light-green_box.png");
		l.setImage(l.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));
		tiles.put(2, l);

		ImageIcon d = new ImageIcon("darkergreen_box.png");
		d.setImage(d.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));
		tiles.put(3, d);

		ImageIcon r = new ImageIcon("red_box.png");
		r.setImage(r.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));
		tiles.put(4, r);

		ImageIcon o = new ImageIcon("orange_box.png");
		o.setImage(o.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));
		tiles.put(5, o);

		ImageIcon b = new ImageIcon("blue_box.png");
		b.setImage(b.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));
		tiles.put(6, b);

		ImageIcon y = new ImageIcon("blue_box.png");
		y.setImage(y.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));
		tiles.put(7, y);

		restart = new ImageIcon("restart.png");
		restart.setImage(restart.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

		skip = new ImageIcon("skip.png");
		skip.setImage(skip.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

		background.setImage(background.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));

		ff.setImage(ff.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));

		help.setImage(help.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));

		settings = new ImageIcon("settings.png");
		settings.setImage(settings.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

		back.setImage(back.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

		System.out.println(tiles.get(0));
		HSP.setSize(500, 500);
		add(boardPanel);
		frame.add(this);
		frame.setSize(310, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//TODO e
		frame.setResizable(true);
		this.setFocusable(true);
		this.requestFocus();
		System.out.println(board[0][0]);
		repaint();
		 


		addMouseListener(this);
		KeyListener listener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("wertyh");
				if(start != null) {

					if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						for(int i = 1; i < board.length - currentLocation.getX() - 1; i++) {
							if(board[currentLocation.x + i][(int) currentLocation.getY()] == 0) {

								board[currentLocation.x + i][(int) currentLocation.getY()] = 1;
							} else {

								currentLocation.setLocation(currentLocation.x + i, currentLocation.y);
								break;
							}
						}
					} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {

						for(int i = 1; i < currentLocation.getX() - 1; i++) {
							if(board[currentLocation.x - i][(int) currentLocation.getY()] == 0) {

								board[currentLocation.x - i][(int) currentLocation.getY()] = 1;
							} else {

								currentLocation.setLocation(currentLocation.x - i, currentLocation.y);
								break;
							}
						}
					} else if(e.getKeyCode() == KeyEvent.VK_UP) {

						for(int i = 1; i < board.length - currentLocation.getY() - 1; i++) {
							if(board[currentLocation.x][(int) currentLocation.getY() + i] == 0) {

								board[currentLocation.x][(int) currentLocation.getY() + i] = 1;
							} else {

								currentLocation.setLocation(currentLocation.x, currentLocation.y + i);
								break;
							}
						}
					}  else if(e.getKeyCode() == KeyEvent.VK_DOWN) {

						for(int i = 1; i < currentLocation.getY() - 1; i++) {
							if(board[currentLocation.x][(int) currentLocation.getY() - i] == 0) {

								board[currentLocation.x][(int) currentLocation.getY() - i] = 1;
							} else {

								currentLocation.setLocation(currentLocation.x, currentLocation.y - i);
								break;
							}
						}
					}
				}
				repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyReleased(KeyEvent e) {


			}
		};
		addKeyListener(listener);
		frame.addKeyListener(listener);

	}

	@Override
	public void paintComponent(Graphics g) {

		if(!levelSelect && !settingsMenu && !helpMenu) {
			g.drawImage(background.getImage(), 0, 0, this);
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					//if(board[i][j] == 1 || board[i][j] == 2 || board[i][j] == 3)
					//((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Ropacity));
					g.drawImage(tiles.get(board[i][j]).getImage(), i*50 - boardOffset, j*50, boardPanel);
					//((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.999999f));
				}
			}

			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
			g.drawImage(background.getImage(), 0, 0, this);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.999999f));

			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(score), 255, 170);

			g.drawImage(restart.getImage(), 250, 0, this);
			g.drawImage(skip.getImage(), 250, 52, this);
			g.drawImage(ff.getImage(), 250, 104, this);
			g.drawImage(settings.getImage(), 250, 206, this);
			g.drawImage(help.getImage(), 250, 156, this);
		} else if(settingsMenu){

			g.drawImage(background.getImage(), 0, 0, this);
			g.drawImage(back.getImage(), 0, 0, this);

		} else if(levelSelect){
			for(int i = 0; i < numLevels - 1; i++) {

			}
		} else if(helpMenu) {

			g.drawImage(background.getImage(), 0, 0, this);
			g.drawImage(back.getImage(), 0, 0, this);
			g.drawString("The goal is to fill up the whole board", 50, 50);
			g.drawString("To start, click on an empy square to fill it", 40, 70);
			g.drawString("Then click on one of the surrounding light", 40, 90);
			g.drawString("green squares to extend green squares", 40, 100);
			g.drawString("outwards in that direction", 40, 110);
			g.drawString("Continue to do this until the board is full", 40, 130);
			g.drawString("You may restart and skip the level using", 40, 150);
			g.drawString("the buttons on the sidebar", 40, 160);
		} else {
			g.drawImage(background.getImage(), 0, 0, this);
			g.drawImage(back.getImage(), 0, 0, this);
		}

	}

	public void restartLevel() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] != 4) {
					board[i][j] = 0;
				}
			}
		}
		start = null;
	}



	@Override
	public void mouseClicked(MouseEvent e) {

	}

	public void drawLine(int x, int y) {
		System.out.println("Going down");
		//int forNum = board.length - y;
		//board[x][y] = 1;
		//int i;
		int curX = currentLocation.x;
		int curY = currentLocation.y;
		Point v = new Point(x - curX, y - curY);
		System.out.println("curX: " + curX + ", curY: " + curY + ", x: " + x + ", y: " + y + ", v: " + v.toString());
		while(true){
			curX += v.x;
			curY += v.y;
			if(isInside(curX, curY) && (board[curX][curY] == 0 || board[curX][curY] == 2)) {
				board[curX][curY] = 1;
			} else {
				setCurrentLocation(curX - v.x, curY - v.y);
				break;
			}
		}
		/*for(i = 1; i < board.length - currentLocation.y - 1; i++) {
			System.out.println(board.length - currentLocation.getY());
			if(board[currentLocation.x][(int) currentLocation.getY() + i + 1] == 0) {
				System.out.println("Filling in a tile");
				board[currentLocation.x][(int) currentLocation.getY() + i + 1] = 1;
			} else {
				System.out.println("got to the end");

				break;
			}
		}
		setCurrentLocation(currentLocation.x, currentLocation.y + i);*/
		repaint();
	}

	public void nextLevel() {
		score += level;
		level++;

		if(level == 2) {
			board = new int[][]
					{
				{0,0,0,0,4},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0}
					};
		} else if(level == 3) {
			board = new int[][]
					{
				{0,0,0,0,0},
				{0,0,0,4,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,4}
					};
		} else if(level == 4) {
			board = new int[][]
					{
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,4,0,4},
				{0,0,0,0,0},
				{0,0,0,0,0}
					};
		} else if(level == 5) {
			board = new int[][]
					{
				{0,0,0,0,0},
				{0,4,0,4,0},
				{0,0,0,0,0},
				{0,4,4,0,4},
				{0,0,0,0,4}
					};
		} else if(level == 6) {
			board = new int[][]
					{
				{0,0,0,0,4},
				{0,0,0,0,0},
				{0,0,0,4,0},
				{4,0,0,0,0},
				{0,0,4,0,0}
					};
		} else if(level == 7) {
			board = new int[][]
					{
				{0,0,4,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{4,4,4,0,0}
					};
		} else if(level == 8) {
			board = new int[][]
					{
				{0,0,4,4,4},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{4,4,0,0,0}
					};
		} else if(level == 9) {
			board = new int[][]
					{
				{0,0,0,0,0},
				{0,4,4,0,0},
				{0,0,0,0,0},
				{0,0,0,4,4},
				{0,0,0,4,4}
					};
		} else if(level == 10) {
			board = new int[][]
					{
				{0,0,0,0,0},
				{0,0,0,4,0},
				{0,4,0,4,0},
				{0,4,0,0,0},
				{0,0,0,0,0}
					};
		} else if(level == 11) {
			board = new int[][]
					{
				{0,0,0,0,0},
				{0,0,4,4,0},
				{0,0,0,0,0},
				{0,4,0,0,0},
				{0,0,0,0,0}
					};
		} else if(level > numLevels){
			leve();
			while(hasToomanyReds()) {
				leve();
			}

		} else {
			board = new int[][]
					{
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0}
					};
		}

		start = null;
		repaint();
	}
 
	private boolean hasToomanyReds() {
		int redCount = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == 4) {
					redCount++;
				}
			}
		}
		return redCount > boardLength*boardLength/4;
	}

	public void leve() {
		boolean[][] level = randomLevel();
		board = new int[boardLength][boardLength];
		for(int i = 0; i < level.length; i++) {
			for(int j = 0; j < level[i].length; j++) {
				if(!level[i][j]) {
					board[i][j] = 4;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!settingsMenu && !levelSelect && !helpMenu) {
			Point click = new Point(e.getX()/50, e.getY()/50);
			int x = click.x;
			int y = click.y;

			if(click.x > boardLength - 1) {
				if(click.y < 1) {
					System.out.println("restart!");
					//Restart the level
					score--;
					restarting = true;
					fade(0);

					if(score < 0) {
						score = 0;
					}
				} else if(click.y == 1) {
					//Skip the level
					System.out.println("skip!");
					score-= 2;

					score -= level;
					//nextLevel();
					skip(1);
					if(score < 0) {
						score = 0;
					}
				} else if(click.y  == 2) {
					if(fastFoward == false) {
						ff = new ImageIcon("highlighted_fastFoward.png");
						fastFoward = true;
					} else {
						ff = new ImageIcon("fastfoward.png");
						fastFoward = false;

					}
					ff.setImage(ff.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));
				} else if(click.y == 4) {
					settingsMenu = true;
					HSP.add(boardSizeSlider);
				} else if(click.y == 3) {
					//Help!
					helpMenu = true;
				}
				repaint();
				return;
			}

			int boardClick = board[click.x][click.y];
			if(start == null && boardClick == 0) {
				System.out.println("Setting start location");
				setCurrentLocation(click.x, click.y);
				start = click;

				repaint();
			} else if(boardClick == 2) {
				drawLine(click.x, click.y);



				boolean full = true;
				for(int i = 0; i < board.length; i++) {
					for(int j = 0; j < board[i].length; j++) {
						if(board[i][j] == 0 || board[i][j] == 2) {
							full = false;
						}
					}
				}
				if(full) {
					System.out.println("Going to the next level");
					fade(10);
					//nextLevel();

				}
				Point p = new Point();
				if(fastFoward) {
					int num = 0;


					for(int i = 0; i < board.length; i++) {
						for(int j = 0; j < board[i].length; j++) {
							if(board[i][j] == 2) {
								num++;
								p = new Point(i, j);
							}
						}
					}

					if(num == 1) {
						System.out.println("Only one option");
						try {
							Thread.sleep(400);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//repaint();
						EventQueue.invokeLater(new MERunnable(this, p));
					}
				}
			}
			//årepaint();
		} else if(settingsMenu) {
			System.out.println("Mouse pressed in settings menu");
			Point click = new Point(e.getX()/50, e.getY()/50);
			if(click.x == 0 && click.y == 0) {
				System.out.println("Leaving settings");
				HSP.remove(boardSizeSlider);
				settingsMenu = false;
				helpMenu = false;
				repaint();
			}
		} else if(helpMenu) {
			Point click = new Point(e.getX()/50, e.getY()/50);
			if(click.x == 0 && click.y == 0) {
				helpMenu = false;
				repaint();
			}
		}
	}

	public void fade(int wait) {
		//recursively lowers and then increases the opacity of the board
		EventQueue.invokeLater(new FadeRunnable(this, wait));
		repaint();
	}

	public void skip(int wait) {
		//recursively lowers and then increases the opacity of the board
		EventQueue.invokeLater(new SkipRunnable(this, wait));
		repaint();
	}

	public void restart(int wait) {
		//recursively lowers and then increases the opacity of the board
		EventQueue.invokeLater(new RestartRunnable(this, wait));
		repaint();
	}

	class MERunnable implements Runnable {
		private Game g;
		private Point p;
		public MERunnable(Game g, Point p) {
			this.g = g;
			this.p = p;
		}
		public void run() {
			g.mousePressed(new MouseEvent(g, 2, 2l, 0, p.x*50, p.y*50, 0, 0, 0, false, 0));
		}
	}

	class FadeRunnable implements Runnable {
		private Game g;
		private Point p;
		int wait;
		public FadeRunnable(Game g, int wait) {
			this.g = g;
			this.wait = wait;
		}
		public void run() {
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(up) {
				opacity+= 0.01;

			} else {
				opacity-= 0.01;
			}
			if(opacity >= 0.98) {
				up = false;
				if(restarting) {
					restartLevel();
					restarting = false;
				} else {
					nextLevel();
				}
			}
			if(opacity > 0.02) {
				fade(wait);
			} else {
				up = true;

			}

		}
	}

	class RestartRunnable implements Runnable {
		private Game g;
		private Point p;
		int wait;
		public RestartRunnable(Game g, int wait) {
			this.g = g;
			this.wait = wait;
		}
		public void run() {
			System.out.println("Ropacity is " + Ropacity + " and the Ropacity Restart Runnable is running");
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(rup) {
				Ropacity-= 0.01;

			} else {
				Ropacity+= 0.01;
			}
			if(Ropacity >= 0.98) {
				rup = false;
				nextLevel();
			}
			if(opacity > 0.02) {
				fade(wait);
			} else {
				rup = true;

			}

		}
	}

	class SkipRunnable implements Runnable {
		private Game g;
		private Point p;
		int wait;

		public SkipRunnable(Game g, int wait) {
			this.g = g;
			this.wait = wait;
		}
		public void run() {
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(go) {
				boardOffset++;
			} else {
				boardOffset++;
			}
			if(boardOffset >= 400) {
				System.out.println("Setting to to false");
				go = false;
				nextLevel();
				boardOffset = -400;
			}
			if(go == true) {
				skip(wait);
			} else if(go == false && boardOffset < 0) {
				skip(wait);
			} else {
				go = true;
			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {


	}

	@Override
	public void mouseEntered(MouseEvent e) {


	}

	@Override
	public void mouseExited(MouseEvent e) {


	}

	public void setCurrentLocation(int x, int y) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == 3) 
					board[i][j] = 1;

				if(board[i][j] == 2) 
					board[i][j] = 0;
			}
		}

		currentLocation.setLocation(x, y);
		board[currentLocation.x][currentLocation.y] = 3;
		if(isInside(x - 1, y)) {
			if(board[x - 1][y] == 0) {
				board[x - 1][y] = 2;
			}
		}
		if(isInside(x + 1, y)) {
			if(board[x + 1][y] == 0) {
				board[x + 1][y] = 2;
			}
		}
		if(isInside(x, y - 1)) {
			if(board[x][y - 1] == 0) {
				board[x][y - 1] = 2;
			}
		}
		if(isInside(x, y + 1)) {
			if(board[x][y + 1] == 0) {
				board[x][y + 1] = 2;
			}
		}
	}
	public boolean isInside(int x, int y) {
		if(x < 0) return false;
		if(y < 0) return false;
		if(y > board.length - 1) return false;
		if(x > board.length - 1) return false;

		return true;
	}

	public boolean[][] randomLevel() {
		boolean[][] filled = new boolean[boardLength][boardLength];
		Point current = new Point((int)(Math.random() * boardLength), (int)(Math.random() * boardLength));
		Point past = getRandomAdjacent(current);
		while(true){
			if(isValid(current)){
				filled[current.x][current.y] = true;
				List<Point> possible = new ArrayList<Point>();
				Point forward = getForward(current, past);
				if(isValid(forward)){
					if(!(filled[forward.x][forward.y])){
						possible.add(forward);
						possible.add(forward);
					}
				}
				Point[] sides = getSides(current, past);
				boolean canTurn = true;
				for(Point p: sides){
					if(isValid(p)){
						if(filled[p.x][p.y]){
							canTurn = false;
						}
					}
				}
				if(canTurn){
					for(Point p: sides){
						if(isValid(p)){
							if(!(filled[p.x][p.y])){
								possible.add(p);
							}
						}
					}
				}
				if(possible.isEmpty()){
					break;
				}
				past = current;
				current = random(possible);

			}
		}
		return filled;
	}

	private Point random(List<Point> possible) {
		int index = new Random().nextInt(possible.size());
		return possible.get(index);
	}

	private Point[] getSides(Point current2, Point past) {

		Point[] retur = new Point[2];
		retur[0] = new Point(current2.x + (current2.y - past.y), current2.y + (current2.x - past.x));
		retur[1] = new Point(current2.x - (current2.y - past.y), current2.y - (current2.x - past.x));

		return retur;
	}

	private Point getForward(Point current2, Point past) {

		return new Point(current2.x + current2.x - past.x, current2.y + current2.y - past.y);
	}

	private boolean isValid(Point p) {
		return p.x >= 0 && p.x < boardLength && p.y >= 0 && p.y < boardLength;
	}

	private Point getRandomAdjacent(Point current) {
		int i = random(1, -1);
		int dir = new Random().nextInt(3) - 1;
		int dir2 = 0;
		if(dir == 0) {
			dir2 = i;
		}
		return new Point(current.x + dir, current.y + dir2);
	}

	private int random(int... options){
		return options[(int)(Math.random() * options.length)];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(boardSizeSlider)) {
			boardLength = boardSizeSlider.getValue();
		}
		
	}

}
