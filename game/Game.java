package game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener {

	int[][] board = new int[5][5];
	Point start;
	Point currentLocation = new Point();
	HashMap<Integer, ImageIcon> tiles;
	int level = 1;
	int score = 0;
	int boardLength = 5;
	boolean fastFoward = false;

	int numLevels = 7;

	ImageIcon restart;
	ImageIcon skip;
	ImageIcon background = new ImageIcon("background.jpg");
	ImageIcon ff = new ImageIcon("fastfoward.png");

	boolean levelSelect = false;

	public static void main(String[] args) {
		Game g = new Game();
	}

	public Game() {

		this.setFocusable(true);
		this.requestFocus();
		JFrame frame = new JFrame();

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

		System.out.println(tiles.get(0));

		frame.add(this);
		frame.setSize(310, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
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
						System.out.println("wertyhooooo");
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

		if(!levelSelect) {
			g.drawImage(background.getImage(), 0, 0, this);
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					g.drawImage(tiles.get(board[i][j]).getImage(), i*50, j*50, this);
				}
			}
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(score), 280, 0);

			g.drawImage(restart.getImage(), 250, 0, this);
			g.drawImage(skip.getImage(), 250, 52, this);
			g.drawImage(ff.getImage(), 250, 104, this);
		} else {
			for(int i = 0; i < numLevels - 1; i++) {

			}
		}
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
		//score += level;
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
				{0,0,4,4,4},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{4,4,0,0,0}
					};
		} else if(level > numLevels){
			board = new int[0][0];
		} else {
			board = new int[][]
					{
				{0,0,0,0,0},
				{0,0,0 ,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0}
					};
		}

		start = null;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point click = new Point(e.getX()/50, e.getY()/50);
		int x = click.x;
		int y = click.y;

		if(click.x > 4) {
			if(click.y < 1) {
				System.out.println("restart!");
				//Restart the level
				level--;
				score--;
				score -= level;
				nextLevel();
				if(score < 0) {
					score = 0;
				}
			} else if(click.y >= 1 && click.y < 2) {
				//Skip the level
				System.out.println("skip!");
				score--;
				score -= level;
				nextLevel();
			} else if(click.y  == 2) {
				if(fastFoward == false) {
					ff = new ImageIcon("highlighted_fastFoward.png");
					fastFoward = true;
				} else {
					ff = new ImageIcon("fastfoward.png");
					fastFoward = false;
					
				}
				ff.setImage(ff.getImage().getScaledInstance((int) 50, 50, Image.SCALE_DEFAULT));
			}
			repaint();
			return;
		}

		int boardClick = board[click.x][click.y];
		if(start == null) {
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
				nextLevel();
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
}

