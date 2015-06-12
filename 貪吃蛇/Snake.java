import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class Snake {
	
	private Node head = null;
	private Node tail = null;
	private int size = 0;
	
	private Node n = new Node(20, 30, Dir.L);
	private Park p;
	
	public Snake(Park p){
		head = n;     //剛開始只有一節,tail = head
		tail = n;
		size = 1;
		this.p = p;
	}
	
	public void addLength(){
		Node node = null;
		switch(head.dir){
		case L:
			node = new Node(head.row, head.col - 1, head.dir);
			break;
		case U:
			node = new Node(head.row - 1, head.col, head.dir);
			break;
		case R:
			node = new Node(head.row, head.col + 1, head.dir);
			break;
		case D:
			node = new Node(head.row + 1, head.col, head.dir);
			break;
		}
		node.next = head;
		head.prev = node;
		head = node;
		size ++;
	}
	
	public void draw(Graphics g){
		if(size <= 0) return;
		move();
		for(Node n = head; n!=null; n = n.next){
			n.draw(g);
		}
		
		
	}
	
	
	private void move() {
		addLength();
		deleteFromtail();
		checkDead();
	}

	//檢查有沒有死
	private void checkDead() {
		if(head.row < 1 || head.col < 0 || head.row > Park.ROWGRIDS - 1 || head.col > Park.COLGRIDS - 1){
			p.stop();
		}
		
		for(Node n = head.next; n != null; n = n.next){  //檢查每個節點,看頭有沒有跟它相撞
			if(head.row == n.row && head.col == n.col){ 
				p.stop();
			}
		}
		
	}

	private void deleteFromtail() {
		if(size == 0) return;
		tail = tail.prev;
		tail.next = null;
	}


	private class Node{
		int w = Park.GRID_SIZE;
		int h = Park.GRID_SIZE;
		int row , col;
		Dir dir = Dir.L;
		Node next = null; //下一個節點
		Node prev = null; //前一個節點
		
		Node(int row,int col, Dir dir){
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		
		void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(Park.GRID_SIZE * col, Park.GRID_SIZE * row , w, h);
			g.setColor(c);
		}
	}
	
	public void eat(Food f){
		if(this.getRect().intersects(f.getRect())){ //判斷長方形有沒有交叉,true就是把食物吃掉
			f.reAppear();//重新出現食物
			this.addLength();
			p.setScore(p.getScore() + 5);
		}
		
	}
	
	private Rectangle getRect(){
		return new Rectangle(Park.GRID_SIZE * head.col, Park.GRID_SIZE * head.row , head.w, head.h);
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			if(head.dir != Dir.R)  //按左鍵
				head.dir = Dir.L;	
			break;
		case KeyEvent.VK_UP:	   //上
			if(head.dir != Dir.D)
				head.dir = Dir.U;	
			break;
		case KeyEvent.VK_RIGHT:		//按右鍵
			if(head.dir != Dir.L)
				head.dir = Dir.R;	
			break;
		case KeyEvent.VK_DOWN:		//下
			if(head.dir != Dir.U)
				head.dir = Dir.D;	
			break;
		}
	}
}


