package Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;

import Data.Data;

public class Uppanel1 extends JPanel implements ActionListener {
	public boolean gamestart;
	public JButton startbutton, databutton;
	public JLabel problemlabel;
	public TimeLabel timelabel;
	public JPanel p1, p2, p3, p4;
	private mainPanel mp;
	public Data data;
	public ResultSet result;
	public ArrayList<String> wordList,wl;
	public ArrayList<String> tipList,tl;
    public String word,tip;
    public int time;
    public 	getWord gw;
	public Uppanel1(mainPanel mp) {
		this.mp = mp;
		gamestart = false;
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		gw = new getWord();
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		wl = gw.getwordList();
		tl = gw.gettipList();
		// ���ڿ�ʼ��Ϸ�İ�ť��������˰�ť�����޷���������client����
		startbutton = new JButton("��ʼ");
		startbutton.setFont(new Font("����",Font.ITALIC|Font.BOLD,20));
		startbutton.setBorder(null);
		startbutton.addActionListener(this);
		startbutton.setPreferredSize(new Dimension(120, 30));
		startbutton.setContentAreaFilled(false);
		startbutton.setBorder(BorderFactory.createRaisedBevelBorder());
		p1.add(startbutton);
		// �����޸ôʿ�İ�ť
		databutton = new JButton("�ʿ�");
		databutton.setFont(new Font("����",Font.ITALIC|Font.BOLD,20));
		databutton.setBorder(null);
		databutton.addActionListener(this);
		databutton.setPreferredSize(new Dimension(120, 30));
		databutton.setContentAreaFilled(false);
		databutton.setBorder(BorderFactory.createRaisedBevelBorder());
		p4.add(databutton);
		problemlabel = new JLabel("���");
		problemlabel.setFont(new Font("����",Font.ITALIC|Font.BOLD,28));
		problemlabel.setOpaque(false);
		p2.add(problemlabel);
		timelabel = new TimeLabel();
		timelabel.setPreferredSize(new Dimension(120, 30));
		timelabel.setOpaque(false);
		p3.add(timelabel);
		//timelabel.setFont(new Font("����",Font.ITALIC|Font.BOLD,28));
		this.setLayout(new GridLayout(1, 4, 100, 0));
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		this.setPreferredSize(new Dimension(920, 40));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startbutton) {
			int again;
			if (mp.s.headxlist.size() <= 1)
				again = JOptionPane.showConfirmDialog(null,
						"��û���˼��룬������С���һ����ˣ�ɣ�");
			else {
				gamestart = true;
				int x =(int)(Math.random()*wl.size());
				int a;
				if(wl.size()==0)
				{	
					a = JOptionPane.showConfirmDialog(null,
							"�ʿ���Ĵ��ù��ˣ����һЩ������ɣ�");
				}
				word = wl.get(x);
				wl.remove(x);
				tip = tl.get(x);
				tl.remove(x);
				problemlabel.setText(word);
				mp.dataserver.send("2");
				mp.dataserver.send(word);
				mp.dataserver.send(tip);
				timelabel.timer.start();
				startbutton.setEnabled(false);
			}
		}
		if (e.getSource() == databutton) {
			wordFrame wf = new wordFrame();
			Thread th = new Thread(wf);
			th.start();
		}
	}
//���Ƽ�ʱ��label
	public class TimeLabel extends JLabel implements ActionListener {
        public Timer timer;
		public TimeLabel() {
			this.setFont(new Font("����",Font.ITALIC|Font.BOLD,28));
			this.setText("ʱ��");
			timer = new Timer(1000, this);
			time = 30;
		}

		public void actionPerformed(ActionEvent e) {
			this.setText(Integer.toString(time));
			time--;
			updateUI();
			if(time==0)
			{   
				this.setFont(null);
				this.setText("ʱ�䵽,���˴��");
				startbutton.setEnabled(true);
				mp.paintpanel.List = new ArrayList<Polygon>();
				mp.paintpanel.colorList = new ArrayList<Color>();
				mp.paintpanel.thickList = new ArrayList<Float>();
			    mp.paintpanel.repaint();
				timer.stop();
				time = 20;
			}
		}
	}
//���ڷ������ݿⲢ��������ʻ����ʾ���ڲ���
	public class getWord {
		public getWord() {
			data = new Data();
			result = data.getData();
			wordList = new ArrayList<String>();
			tipList = new ArrayList<String>();
			try {
				while (result.next()) {
					wordList.add(result.getString(2));
					tipList.add(result.getString(3));
				}
			} catch (Exception e) {
			}
		}
		public ArrayList<String> getwordList(){
			return wordList;
		}
		public ArrayList<String> gettipList(){
			return tipList;
		}
	}
}
