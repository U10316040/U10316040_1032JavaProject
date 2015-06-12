import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class Park extends Frame {
	
	PaintThread paintThread = new PaintThread();
	private boolean gameOver = false;

	public static final int ROWGRIDS = 30;
	public static final int COLGRIDS = 35;
	public static final int GRID_SIZE = 15;
	
	private Font fontGameOver = new Font("標楷體", Font.BOLD, 50);
	
	private int score = 0;
	public static int Difficulty;
	Snake s = new Snake(this);
	Food f = new Food();
	
	Image offScreenImage = null;
	
	
	public void launch(){
		this.setLocation(100, 50);
		this.setSize(COLGRIDS * GRID_SIZE, ROWGRIDS * GRID_SIZE);
		//讓frame能夠關閉
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
			
		});
		this.setVisible(true);
		this.addKeyListener(new keyMonitor());
		new Thread(paintThread).start();
		
	}
	
	public static void main(String[] args) {
		
		new Park().launch();
	}
	
	public void stop(){
		gameOver = true;
	}
	
	@Override
	public void paint(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.green);
		g.fillRect(0, 0, COLGRIDS*GRID_SIZE, ROWGRIDS*GRID_SIZE);
		g.setColor(Color.lightGray);
		
		//畫出格線
		for(int i=1; i<ROWGRIDS; i++){
			g.drawLine(0, GRID_SIZE * i, COLGRIDS * GRID_SIZE, GRID_SIZE * i);
		}
		for(int i=1; i<COLGRIDS; i++){
			g.drawLine(GRID_SIZE * i, 0, GRID_SIZE * i, ROWGRIDS * GRID_SIZE);
		}
		
		g.setColor(Color.blue);
		g.drawString("score:" + score, 10, 60);
		
		if(gameOver){ //遊戲結束
			g.setFont(fontGameOver);
			g.drawString("Game Over", 130, 180);
			paintThread.gameOver();
		}
		
		g.setColor(c);
		
		s.eat(f);
		f.draw(g);
		s.draw(g);
	}
	
	@Override
	//雙緩衝解決閃爍
	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(COLGRIDS * GRID_SIZE, ROWGRIDS * GRID_SIZE);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	//實作Interface Runnable 的 run
	private class PaintThread implements Runnable{
		private boolean running = true;
		public void run(){
			while(running){
				repaint();
			
				try{
					Thread.sleep(80);//可調難度
				} catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		
		public void gameOver(){
			running = false;
		}
	}
	//繼承KeyAdapter並實作keyPressed方法
	private class keyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}
		
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore(){
		return score;
	}

}
