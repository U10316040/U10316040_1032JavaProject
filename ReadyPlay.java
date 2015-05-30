import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ReadyPlay extends JPanel {
	private JButton start = new JButton("開始遊戲");

	ReadyPlay(){
		setLayout(null);
		setBounds(0, 0, 800, 700);
		setVisible(true);
		
		start.setBounds(550,560,150,50);
		start.setFont(new Font("Microsoft YaHei",Font.BOLD,17));
		start.setBackground(Color.gray);
		add(start);
		startGame();
	}
	
	private void startGame(){
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				remove(start);
				setVisible(false);
				GameFrame.getFrame().add(GameFrame.playGame);
				
				for(int i=0; i<13; i++){
					PlayGame.timer[0][i].start();
					PlayGame.timer[1][i].start();
					PlayGame.timer[2][i].start();
					PlayGame.timer[3][i].start();
				}
				
			}
		});
		
	}
	
	
	
	@Override 
	protected void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 800, 700);
		DrawRule(g);
	}
	
	private void DrawRule(Graphics g){
		g.setColor(Color.black);
		g.setFont(new Font("Microsoft YaHei",Font.BOLD,30));
		g.drawString("規則說明", 345, 60);
		g.drawRect(130, 90, 550, 450);
		g.setFont(new Font("Microsoft YaHei",Font.BOLD,19));
		g.drawString("*基本", 150, 110);
		g.drawString("*玩法", 150, 180);
		g.setColor(Color.blue);
		g.drawString(" 花色大小-黑桃>紅心>方塊>梅花", 150, 130);
		g.drawString(" 數字大小-A最大 2最小", 150, 150);
		
		g.drawString(" 4人各發13張 兩人一組 交錯坐 也就是坐對面的是跟你一", 150, 200);
		g.drawString(" 家看完牌要先'叫牌' 4個人依序叫牌  ", 150, 220);
		g.setColor(Color.red);
		g.drawString(" 叫牌:", 150, 250);
		g.drawString(" 叫法:", 150, 340);
		g.drawString(" 出牌:", 150, 450);
		g.setColor(Color.blue);
		g.drawString(" 選王牌的'花色' 也就是選黑桃.紅心.方塊.梅花或無王", 200, 250);
		g.drawString(" (就是沒王牌 純粹比點數大小) 也在叫成局(也就是贏時)的", 150, 270);
		g.drawString(" '墩數'贏一輪(4個人各出一次牌就是一輪)就是一墩 也就是", 150,290);
		g.drawString(" 說一場牌有13墩", 150, 310);

		g.drawString(" 以7墩為基礎'1' 往上叫 例:1黑桃3.梅花...... 叫'1'就 ", 200, 340);
		g.drawString(" 要吃到8墩......依此類推 要越叫越大 例:若已叫到{1黑桃} 下", 150, 360);
		g.drawString(" 一個叫不能叫{1紅心}7墩 '2'要吃到可叫{1無王} 或從2的往", 150, 380);
		g.drawString(" 上叫可不叫 喊'PASS' 4人依序叫 直到其他三人都PASS 選", 150, 400);
		g.drawString(" 定合約為止", 150, 420);
		
		g.drawString(" 叫完牌後開始玩 由沒叫到合約的一方順時鐘出牌 以 ", 200, 450);
		g.drawString(" 第一個出牌的花色為主 其他人也要出同個花色 若沒有才能", 150, 470);
		g.drawString(" 出別的花色 比牌先看是否有王牌 若有人出王牌則王牌最大", 150, 490);
		g.drawString(" (不論點數 除非也有人出王牌 那再比點數 ) 沒有王牌再比原", 150, 510);
		g.drawString(" 本花色的點數", 150, 530);
	}
	
}
