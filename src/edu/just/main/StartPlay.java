package edu.just.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.just.jpanel.MyJpanel;

@SuppressWarnings("serial")
public class StartPlay extends JFrame{
	static StartPlay s;
	MyJpanel jpanel;
	public StartPlay(){
		this.setSize(800, 680);
		this.setTitle("五子棋");	
		this.setResizable(false);
		jpanel=new MyJpanel();
		JButton restart=new JButton("重新开始");
		restart.addMouseListener(new Mylisten1());
		jpanel.setLayout(null);
		restart.setBounds(660,250, 100, 30);
		jpanel.add(restart);
		this.add(jpanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	public class Mylisten1 extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			s.setVisible(false);
			init();
		}	
	}	
	public static void init(){
		s=new StartPlay();
	}
	public static void main(String[] args) {
		init();
	}

}
