import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class Oracle   extends JFrame implements ActionListener ,ItemListener
{
		JTextField tf1,tf2;
		JLabel l1,l2;
		JButton b;
		Choice choice1;
		String  user1;
		String  pass1;
		Connection con;
		PreparedStatement stm;
		PreparedStatement preparedStmt;
		String lib;
		public Oracle() 
		{
		super("Oracle Connection");
		setSize(400,240);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(new ImageIcon("image/Red_book.png").getImage());
		JPanel p= new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(5,194,250));
		p.setBounds(0,0,400,400);
		l1= new JLabel("Oracle Username");
		l1.setBounds(50,70,200,20);
		p.add(l1);
		l2= new JLabel("Oracle Password");
		l2.setBounds(50,110,200,20);
		p.add(l2);
		choice1=new Choice();
		choice1.add("");
		choice1.add("Create Table");
		choice1.setBounds(100,20,200,30);
		choice1.addItemListener(this);
		p.add(choice1);
		add(p);
		tf1 = new JTextField();
		tf1.setBounds(200,70,100,20);
		p.add(tf1);
		tf2 = new JTextField();
		tf2.setBounds(200,110,100,20);
		p.add(tf2);
		b= new JButton("Save",new ImageIcon("image/save.png"));
		b.setBounds(150,150,100,30);
		b.addActionListener(this);
		p.add(b);
		setVisible(true);
		}
	public void itemStateChanged(ItemEvent ie)
		{    if(ie.getItemSelectable()==choice1)
			{	lib=((Choice)ie.getItemSelectable()).getSelectedItem();
				System.out.println(lib);
			    				}
			
		  }
	public void actionPerformed(ActionEvent e)
		{  if(e.getSource()==b)
			{    
			String tx1=tf1.getText();
			String tx2=tf2.getText();
			if(tx1.isEmpty()||tx2.isEmpty())
			{
			JOptionPane.showMessageDialog(this,"TextFields is Empty","Error",JOptionPane.ERROR_MESSAGE);
			}
			else{
				try{
			               String user=tf1.getText();
				byte []by=user.getBytes();
				FileOutputStream file=new  FileOutputStream("username.txt");
				file.write(by);
				String pass=tf2.getText();
				byte []by2=pass.getBytes();
				FileOutputStream file2=new  FileOutputStream("password.txt");
				file2.write(by2);
				FileInputStream file3=new  FileInputStream("username.txt");
				byte []by12= new byte[50];
				file3.read(by12);
				user1= new String(by12,0,50);
				System.out.println("2 byte "+user1);
				FileInputStream file4=new  FileInputStream("password.txt");
				byte []by22= new byte[50];
				file4.read(by22);
				pass1= new String(by22,0,50);
				System.out.println("3 byte "+pass1);
				try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",user1,pass1);
				JOptionPane.showMessageDialog(this,"User name or Password Successful","Success",JOptionPane.INFORMATION_MESSAGE);
				con.close();
				if(lib=="Create Table")
				{	  
					try{
						Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",user1,pass1);
				String query = "CREATE TABLE  admin1 (ADMINID VARCHAR2(100), PASSWORD VARCHAR2(100))";
      				preparedStmt = conn.prepareStatement(query);
      				preparedStmt.execute();
				stm =  conn.prepareStatement("insert into admin1 values(?,?)");
			                stm.setString(1,"101");
				stm.setString(2,"vineet");
				stm.executeUpdate();
				String query2 = "CREATE TABLE  STUDENT1 (USER_ID VARCHAR2(100), USER_NAME VARCHAR2(200), USER_PASSWORD VARCHAR2(200), USER_PHONENO VARCHAR2(200), USER_EMAILID VARCHAR2(200))";
      				preparedStmt = conn.prepareStatement(query2);
      				preparedStmt.execute();				
				String query3 = "CREATE TABLE  LIBRARIAN (NAMEL VARCHAR2(200), USERIDL VARCHAR2(100), PASSWORDL VARCHAR2(200), EMAILIDL VARCHAR2(200), ADDRESSL VARCHAR2(200), CITYL VARCHAR2(100), CONTACTNOL VARCHAR2(50))";
      				preparedStmt = conn.prepareStatement(query3);
      				preparedStmt.execute();
				String query4 = "CREATE TABLE  BOOK (BOOK_ID VARCHAR2(100), BOOK_NAME VARCHAR2(200), AUTHOR VARCHAR2(200), PUBLISHER VARCHAR2(200), QUANTITY VARCHAR2(100))";
      				preparedStmt = conn.prepareStatement(query4);
      				preparedStmt.execute();
				String query5 = "CREATE TABLE  ISSUE_BOOK (BOOK_ID1 VARCHAR2(100), USER_ID1 VARCHAR2(100), ISSUE_DATE1 VARCHAR2(100), RETURN_DATE1 VARCHAR2(100))";
      				preparedStmt = conn.prepareStatement(query5);
      				preparedStmt.execute();
				JOptionPane.showMessageDialog(this,"Create Table  Successful","Success",JOptionPane.INFORMATION_MESSAGE);
				System.out.println("table");

				
				    }
				catch(Exception v) {    }
				}
				else{ JOptionPane.showMessageDialog(this,"Create Table  UnSuccessful","ERROR",JOptionPane.ERROR_MESSAGE);
 


				
					 }


				new Login(); dispose();
				}
				catch(Exception p) {JOptionPane.showMessageDialog(this,"Incorrect Username or Password","Error",JOptionPane.ERROR_MESSAGE);  }
				}

			catch(IOException io){}
			       }
			}		
		

	
			  }
	public static void main(String []args) 
		{  new Oracle();  }
}