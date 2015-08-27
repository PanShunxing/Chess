package edu.just.jpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.swing.JPanel;
public class MyJpanel extends JPanel {
	static int[][] board;// 0无子，1为黑，2为白
	static int turn;// 1,黑落子.2,白落子
	int win;// 标记输赢
	public MyJpanel() {
		setBackground(Color.WHITE);
		int win = 0;
		board = new int[16][16];
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				board[i][j] = 0;
			}
		}
		turn = 1;
		addMouseListener(new MouseHandler());
	}
	/**
	 * 播放音效
	 * 
	 * @param music
	 *            音效名
	 */
	public static void playMusic(String music) {
		try {
			InputStream is = new FileInputStream(music);
			AudioStream as = new AudioStream(is);
			AudioPlayer.player.start(as);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 画一个棋盘
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int startX = 20;
		int startY = 20;
		for (int i = 0; i < 16; i++) {
			g2.draw(new Line2D.Double(startX, startY + 40 * i, startX + 600, startY + 40 * i));
			g2.draw(new Line2D.Double(startX + 40 * i, startY, startX + 40 * i, startY + 600));
		}
		// 画棋子
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				// 画黑棋
				if (board[i][j] == 1) {
					g2.setColor(Color.BLACK);
					g2.fillOval(i * 40, j * 40, 40, 40);
				}
				// 画白棋
				if (board[i][j] == 2) {
					g2.setColor(Color.RED);
					g2.fillOval(i * 40, j * 40, 40, 40);
				}
			}
		}
		// 画提示
		if (turn == 1) {
			g2.setColor(Color.RED);
			g2.setFont(new Font("宋体", Font.ITALIC, 20));
			g2.drawString("黑棋走", 660, 100);
		} else if (turn == 2) {
			g2.setColor(Color.RED);
			g2.setFont(new Font("宋体", Font.ITALIC, 20));
			g2.drawString("白棋走", 660, 100);
		}
		if (win == 1) {
			g2.setColor(Color.RED);
			g2.setFont(new Font("宋体", Font.ITALIC, 20));
			g2.drawString("黑棋赢了！", 660, 100);
		}
		if (win == 2) {
			g2.setColor(Color.RED);
			g2.setFont(new Font("宋体", Font.ITALIC, 20));
			g2.drawString("黑棋赢了！", 660, 100);
		}
	}

	// 放置一个棋子
	public static void putChess(int x, int y) {
		if (x > 20 && x < 640 && y > 20 && y < 640) {
			int i = x / 40;
			int j = y / 40;
			// 黑落子
			if (turn == 1 && board[i][j] == 0) {
				board[i][j] = 1;
				turn = 2;
				playMusic("sound\\1.au");
			}
			if (turn == 2 && board[i][j] == 0) {
				board[i][j] = 2;
				turn = 1;
				playMusic("sound\\1.au");
			}
		}
	}

	// 判断胜负,0未分胜负，1黑胜，2白胜
	public static int judgeWiner() {
		// 横五个赢
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (j < 12) {
					if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i][j + 2] == 1 && board[i][j + 3] == 1
							&& board[i][j + 4] == 1) {
						return 1;
					}
					if (board[i][j] == 2 && board[i][j + 1] == 2 && board[i][j + 2] == 2 && board[i][j + 3] == 2
							&& board[i][j + 4] == 2) {
						return 2;
					}
				}
			}
		}
		// 竖五个赢
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (i < 12) {
					if (board[i][j] == 1 && board[i + 1][j] == 1 && board[i + 2][j] == 1 && board[i + 3][j] == 1
							&& board[i + 4][j] == 1) {
						return 1;
					}
					if (board[i][j] == 2 && board[i + 1][j] == 2 && board[i + 2][j] == 2 && board[i + 3][j] == 2
							&& board[i + 4][j] == 2) {
						return 2;
					}
				}
			}
		}
		// 下右斜五个赢
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (i < 12 && j < 12) {
					if (board[i][j] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j + 2] == 1
							&& board[i + 3][j + 3] == 1 && board[i + 4][j + 4] == 1) {
						return 1;
					}
					if (board[i][j] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j + 2] == 2
							&& board[i + 3][j + 3] == 2 && board[i + 4][j + 4] == 2) {
						return 2;
					}
				}
			}
		}
		// 上右斜五个赢
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (i > 4 && j < 12) {
					if (board[i][j] == 1 && board[i - 1][j + 1] == 1 && board[i - 2][j + 2] == 1
							&& board[i - 3][j + 3] == 1 && board[i - 4][j + 4] == 1) {
						return 1;
					}
					if (board[i][j] == 2 && board[i - 1][j + 1] == 2 && board[i - 2][j + 2] == 2
							&& board[i - 3][j + 3] == 2 && board[i - 4][j + 4] == 2) {
						return 2;
					}
				}
			}
		}
		return 0;
	}

	// 鼠标点击事件
	public class MouseHandler extends MouseAdapter {
		// 当未点击，而只是按下鼠标左键的时候就应该有显示棋子
		@Override
		public void mousePressed(MouseEvent e) {
			int chessX = e.getX();
			int chessY = e.getY();
			MyJpanel.putChess(chessX, chessY);
			// 重绘
			repaint();
			int winner = judgeWiner();
			if (winner == 1) {
				win = 1;
				turn = 0;
			}
			if (winner == 2) {
				win = 2;
				turn = 0;
			}
		}
	}
}
