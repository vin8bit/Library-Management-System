import java.awt.*;
import javax.swing.*;

public class ReturnBookReciept extends JFrame
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
	JLabel lr1,lr2,lr3,lr4,lr5,lr6,lr7,lr8,lr9,l1r0,lr11,lr12;
	public ReturnBookReciept(String s1, String s2, String s3,String s4)
	{
		super("Return Book Reciept");
		char c2= s4.charAt(0);
		String g=String.valueOf(c2);
		if(g.equals("-"))
			{        s4="0";  }
		setSize(300,400);
		setIconImage(new ImageIcon("image/Red_book.png").getImage());
		setLayout(null);
		setLocation(400,100);
		JPanel p= new JPanel();
		p.setLayout(null);
		p.setBounds(0,0,300,300);
		JLabel r= new JLabel("Return Book Reciept");
		r.setBounds(75,0,200,20);
		p.add(r);
		l1= new JLabel("Book Id :");	
		l1.setBounds(20,20,100,20);
		p.add(l1);
		l2= new JLabel("User Id :");	
		l2.setBounds(20,60,100,20);
		p.add(l2);
		l3= new JLabel("Return Date :");	
		l3.setBounds(20,100,100,20);
		p.add(l3);
		l4= new JLabel("Fine :");	
		l4.setBounds(20,140,100,20);
		p.add(l4);
		l5= new JLabel();	
		l5.setBounds(150,20,300,20);
		l5.setText(s1);
		p.add(l5);
		l6= new JLabel();	
		l6.setBounds(150,60,300,20);
		l6.setText(s2);
		p.add(l6);
		l7= new JLabel();	
		l7.setBounds(150,100,300,20);
		l7.setText(s3);
		p.add(l7);
		l8= new JLabel();	
		l8.setBounds(150,140,300,20);
		l8.setText(s4);
		p.add(l8);










		add(p);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
public static void main(String []args)
	{   //new ReturnBookReciept();
	  }
}