package Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Colorselectpanel   extends JPanel implements ActionListener{
	public Color paintcolor;
	public JButton selectbutton;
	public JButton colorlabel = new JButton();
	public mainPanel mp;
    public Colorselectpanel(mainPanel mp)
    {   
    	try{
    	paintcolor = Color.black;
    	this.setPreferredSize(new Dimension(100,70));
    	selectbutton = new JButton("ѡ����ɫ"); 
    	selectbutton.addActionListener(this);
    	selectbutton.setContentAreaFilled(false);
    	selectbutton.setBorder(BorderFactory.createRaisedBevelBorder());
    	selectbutton.setFont(new Font("����",Font.ITALIC|Font.BOLD,20));
    	colorlabel.setPreferredSize(new Dimension(90,30));
    	colorlabel.setBackground(paintcolor);
    	this.add(selectbutton);
    	this.add(colorlabel);
    	this.mp = mp;
    	}catch(Exception e){}
    }
	
	public void actionPerformed(ActionEvent event) {		
		try{
			paintcolor = JColorChooser.showDialog(null, "ѡ��һ����ɫ", paintcolor);
			colorlabel.setBackground(paintcolor);
			mp.paintpanel.paintColor = paintcolor;
		}catch(Exception evt){}
	
	}

}
