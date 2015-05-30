import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ReadyPlay extends JPanel {
	private JButton start = new JButton("�}�l�C��");

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
		g.drawString("�W�h����", 345, 60);
		g.drawRect(130, 90, 550, 450);
		g.setFont(new Font("Microsoft YaHei",Font.BOLD,19));
		g.drawString("*��", 150, 110);
		g.drawString("*���k", 150, 180);
		g.setColor(Color.blue);
		g.drawString(" ���j�p-�®�>����>���>����", 150, 130);
		g.drawString(" �Ʀr�j�p-A�̤j 2�̤p", 150, 150);
		
		g.drawString(" 4�H�U�o13�i ��H�@�� ����� �]�N�O���ﭱ���O��A�@", 150, 200);
		g.drawString(" �a�ݧ��P�n��'�s�P' 4�ӤH�̧ǥs�P  ", 150, 220);
		g.setColor(Color.red);
		g.drawString(" �s�P:", 150, 250);
		g.drawString(" �s�k:", 150, 340);
		g.drawString(" �X�P:", 150, 450);
		g.setColor(Color.blue);
		g.drawString(" ����P��'���' �]�N�O��®�.����.���.����εL��", 200, 250);
		g.drawString(" (�N�O�S���P �º���I�Ƥj�p) �]�b�s����(�]�N�OĹ��)��", 150, 270);
		g.drawString(" '�[��'Ĺ�@��(4�ӤH�U�X�@���P�N�O�@��)�N�O�@�[ �]�N�O", 150,290);
		g.drawString(" ���@���P��13�[", 150, 310);

		g.drawString(" �H7�[����¦'1' ���W�s ��:1�®�3.����...... �s'1'�N ", 200, 340);
		g.drawString(" �n�Y��8�[......�̦����� �n�V�s�V�j ��:�Y�w�s��{1�®�} �U", 150, 360);
		g.drawString(" �@�ӥs����s{1����}7�[ '2'�n�Y��i�s{1�L��} �αq2����", 150, 380);
		g.drawString(" �W�s�i���s ��'PASS' 4�H�̧ǥs �����L�T�H��PASS ��", 150, 400);
		g.drawString(" �w�X������", 150, 420);
		
		g.drawString(" �s���P��}�l�� �ѨS�s��X�����@�趶�����X�P �H ", 200, 450);
		g.drawString(" �Ĥ@�ӥX�P����⬰�D ��L�H�]�n�X�P�Ӫ�� �Y�S���~��", 150, 470);
		g.drawString(" �X�O����� ��P���ݬO�_�����P �Y���H�X���P�h���P�̤j", 150, 490);
		g.drawString(" (�����I�� ���D�]���H�X���P ���A���I�� ) �S�����P�A���", 150, 510);
		g.drawString(" ����⪺�I��", 150, 530);
	}
	
}
