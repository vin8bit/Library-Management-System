import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;

public class BookStatus extends JFrame
{
	String system;
	String systempass;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
	JLabel lr1,lr2,lr3,lr4,lr5,lr6,lr7,lr8,lr9,l1r0,lr11,lr12;
	ResultSet rs,rs2;
	PreparedStatement stm;
	Statement stm2;
	Connection con;
	String up="1";
	public BookStatus(String s1)
	{
		super("Book Status");
		up=s1;
		setSize(300,400);
		setIconImage(new ImageIcon("image/Red_book.png").getImage());
		setLayout(null);
		setLocation(400,100);
		lr1= new JLabel();	
		lr2= new JLabel();	
		lr3= new JLabel();
		lr4= new JLabel();
		lr6= new JLabel();
		lr7= new JLabel();
		lr8= new JLabel();	
		lr1.setText("None");
		lr2.setText("None");
		lr3.setText("None");
		lr4.setText("None");
		lr6.setText("None");
		lr7.setText("None");
		lr8.setText("None");
		
		
		try{
				FileInputStream file3=new  FileInputStream("username.txt");
				byte []by12= new byte[50];
				file3.read(by12);
				system= new String(by12,0,50);
				//System.out.println("2 byte "+user1);
				FileInputStream file4=new  FileInputStream("password.txt");
				byte []by22= new byte[50];
				file4.read(by22);
				systempass= new String(by22,0,50);
				//System.out.println("3 byte "+pass1);
			}
		catch(IOException io){  }


		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm2=con.createStatement();
			rs= stm2.executeQuery("SELECT *  FROM book WHERE BOOK_ID="+up+" ");	
					while(rs.next())
					{				
					lr1.setText(rs.getString(1));
					lr2.setText(rs.getString(2));
					lr3.setText(rs.getString(3));
					lr4.setText(rs.getString(4));

					 }	
			con.close();
			}
		catch(Exception e)
			{      }



		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm2=con.createStatement();
			
			rs= stm2.executeQuery("SELECT *  FROM issue_book WHERE BOOK_ID1="+up+" ");	
					while(rs.next())
					{				
					lr6.setText(rs.getString(2));
					lr7.setText(rs.getString(3));
					lr8.setText(rs.getString(4));

					 }	
			}
		catch(Exception e)
			{      }





		JPanel p= new JPanel();
		p.setLayout(null);
		p.setBounds(0,0,300,400);
		JLabel r= new JLabel("Book Status");
		Font ft= new Font("Arial Rounded MT",Font.BOLD,15);
		r.setFont(ft);
		r.setBounds(75,0,200,20);
		p.add(r);
		l1= new JLabel("Book Id :");	
		l1.setBounds(20,20,100,20);
		p.add(l1);
		l2= new JLabel("Book Name :");	
		l2.setBounds(20,60,100,20);
		p.add(l2);
		l3= new JLabel("Author :");	
		l3.setBounds(20,100,100,20);
		p.add(l3);
		l4= new JLabel("Publisher :");	
		l4.setBounds(20,140,100,20);
		p.add(l4);
		l5= new JLabel("Book Issue Details ");	
		l5.setBounds(20,180,200,20);
		Font ft1= new Font("Arial Rounded MT",Font.BOLD,15);
		l5.setFont(ft1);
		p.add(l5);
		l6= new JLabel("User ID :");	
		l6.setBounds(20,220,100,20);
		p.add(l6);
		l7= new JLabel("Issue Date :");	
		l7.setBounds(20,260,100,20);
		p.add(l7);
		l8= new JLabel("Return Date :");	
		l8.setBounds(20,300,100,20);
		p.add(l8);




		
		lr1.setBounds(150,20,100,20);
		p.add(lr1);
		
		lr2.setBounds(150,60,100,20);
		p.add(lr2);
			
		lr3.setBounds(150,100,100,20);
		p.add(lr3);
			
		lr4.setBounds(150,140,100,20);
		p.add(lr4);
			
		lr6.setBounds(150,220,100,20);
		p.add(lr6);
			
		lr7.setBounds(150,260,100,20);
		p.add(lr7);
		lr8.setBounds(150,300,100,20);
		p.add(lr8);









		add(p);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
public static void main(String []args)
	{   //new BookStatus(); 
	 }
}