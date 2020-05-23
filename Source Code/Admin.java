import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.regex.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class Admin extends JFrame  implements ActionListener  
{	DataBase k=new DataBase();
	String system;
	String systempass;
	
	JPanel adminp1,adminp2,adminp3,adminp4;
	JTabbedPane admintp;
	JLabel h1,h3,aname,auserid,apassword,aemailid,aaddress,acity,acontactno,adeleteid,adeletepass,start;
	JTextField  anametf,auseridtf,aemailidtf,aaddresstf,acitytf,acontactnotf,adeleteidtf;
	JPasswordField apasswordtf,adeletepasstf;
	JButton alibrarian,acancel,delete,deleteclear;
	
	TableModel tmodel4;
	JTable jtable4;
	TableRowSorter<TableModel> rsorter4;
	String columns4[]={"Name","User ID","Password","Email ID","Address","CIty","Contact No"};
	JButton bt4,search4,refresh4,refresh5,logout,update;
	JTextField searchtf4;
	JPanel bookPanel4;
	RowCount1  r1=new RowCount1();
	ResultSet rs,rs2;
	PreparedStatement stm;
	Statement stm2;
	Connection con;
	int r2=101+r1.call4();
	String up;
	int p9=0;
	int q2=0;
	int pass5=0,pass9=0;
	public Admin()	throws Exception
	{	super("Admin v 1.1");
		WindowUtilities.setNativeLookAndFeel();
	//setPreferredSize(new Dimension(796,572));
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
		
		setSize(700,500);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("image/Red_book.png").getImage());
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e){dispose(); }});
		admintp=new JTabbedPane();
		admintp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		admintp.setTabPlacement(JTabbedPane.LEFT);
		adminp1= new JPanel();
		adminp2= new JPanel();
		adminp3= new JPanel();
		adminp4= new JPanel();

		adminp1.setLayout(null);
		//adminp1.setBackground(new Color(5,194,250));
		adminp2.setLayout(new BorderLayout());
		adminp3.setLayout(null);
		adminp4.setLayout(null);
		
		admintp.addTab("Add Librarian",new ImageIcon("image/admin.gif"),adminp1,"Add Librarian");
		//admintp.setBackground(new Color(0,128,255));
		admintp.addTab("View Librarian",new ImageIcon("image/view1.gif"),adminp2,"View Librarian");
		admintp.addTab("Delete Librarian",new ImageIcon("image/del.gif"),adminp3,"Delete Librarian");
		admintp.addTab("Log Out",new ImageIcon("image/Exit.png"),adminp4,"Log out");

		//Add Librarian
		h1=new JLabel("Add Librarian");
		h1.setBounds(200,20,200,50);
		Font font= new Font("Britannic",Font.PLAIN,25);
		h1.setFont(font);
		adminp1.add(h1);
		refresh5=new JButton("Refresh",new ImageIcon("image/Refresh.gif"));
		refresh5.setBounds(390,20,110,30);
		refresh5.addActionListener(this);
		adminp1.add(refresh5);
		aname= new JLabel("Name :");
		aname.setBounds(100,100,100,20);
		anametf= new JTextField();
		anametf.setBounds(250,100,150,25);
		adminp1.add(aname);
		adminp1.add(anametf);

		auserid= new JLabel("User Id :");
		auserid.setBounds(100,140,100,20);
		start= new JLabel("Uptade start");
		start.setBounds(430,140,100,20);
		start.setForeground(Color.red);
		auseridtf= new JTextField();
		String id1=String.valueOf(r2);
		auseridtf.setText(id1);
		auseridtf.setBounds(250,140,150,25);
		adminp1.add(auserid);
		adminp1.add(auseridtf);

		apassword= new JLabel("Password :");
		apassword.setBounds(100,180,100,20);
		apasswordtf= new JPasswordField();
		apasswordtf.setEchoChar('*');
		apasswordtf.setBounds(250,180,150,25);
		adminp1.add(apassword);
		adminp1.add(apasswordtf);
		
		aemailid= new JLabel("Email Id :");
		aemailid.setBounds(100,220,100,20);
		aemailidtf= new JTextField();
		aemailidtf.setBounds(250,220,150,25);
		adminp1.add(aemailid);
		adminp1.add(aemailidtf);
		
		aaddress= new JLabel("Address :");
		aaddress.setBounds(100,260,100,20);
		aaddresstf= new JTextField();
		aaddresstf.setBounds(250,260,150,25);
		adminp1.add(aaddress);
		adminp1.add(aaddresstf);
	
		acity= new JLabel("City :");
		acity.setBounds(100,300,100,20);
		acitytf= new JTextField();
		acitytf.setBounds(250,300,150,25);
		adminp1.add(acity);
		adminp1.add(acitytf);

		acontactno= new JLabel("Contact No :");
		acontactno.setBounds(100,340,100,20);
		acontactnotf= new JTextField();
		acontactnotf.setBounds(250,340,150,25);
		adminp1.add(acontactno);
		adminp1.add(acontactnotf);
		
		alibrarian= new JButton("Add Librarian",new ImageIcon("image/save.png"));
		alibrarian.setBounds(80,380,140,30);
		acancel= new JButton("Clear",new ImageIcon("image/clear.png"));
		acancel.setBounds(340,380,100,30);
		update= new JButton("Update",new ImageIcon("image/update.png"));
		update.setBounds(230,380,100,30);
		adminp1.add(update);
		update.addActionListener(this);
		adminp1.add(alibrarian);
		adminp1.add(acancel);
		alibrarian.addActionListener(this);
		acancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			 try  {        anametf.setText("");
				int r3=101+r1.call4();	
				String g3=String.valueOf(r3);
				auseridtf.setText(g3);
				apasswordtf.setText("");
				aemailidtf.setText("");
				aaddresstf.setText("");
				acitytf.setText("");
				acontactnotf.setText("");
				}
				catch(Exception r){}
			}
		});	
		


		//View Librarian
		JLabel h4=new JLabel("View Librarian");
		Font font4=new Font("Britannic",Font.PLAIN,25);
		h4.setFont(font4);
		bookPanel4=new JPanel(new BorderLayout());
		JPanel bu4=new JPanel();
		bu4.setBackground(new Color(5,194,250));
		 tmodel4= new DefaultTableModel(k.array4,columns4);
		 jtable4=new JTable(tmodel4);
		 rsorter4 = new TableRowSorter<TableModel>(tmodel4);
		JScrollPane jspane4=new JScrollPane(jtable4);
		jspane4.setPreferredSize(new Dimension(550, 350));
		jtable4.setRowSorter(rsorter4);
		searchtf4=new JTextField(20);
		bt4=new JButton("Search",new ImageIcon("image/search.png"));
		bt4.addActionListener(this);
		bu4.add(h4,BorderLayout.NORTH);
		bu4.add(searchtf4,BorderLayout.CENTER);
		bu4.add(bt4,BorderLayout.EAST);
		bookPanel4.add(jspane4,BorderLayout.CENTER);
		bookPanel4.add(bu4,BorderLayout.NORTH);
		adminp2.add(bookPanel4);
		refresh4=new JButton("Refresh",new ImageIcon("image/Refresh.gif"));
		refresh4.addActionListener(this);
		adminp2.add(refresh4,BorderLayout.SOUTH);
		


		


	

		//Delete Librarian
		h3=new JLabel("Delete Librarian");
		h3.setBounds(200,20,200,50);
		Font font3= new Font("Britannic",Font.PLAIN,25);
		h3.setFont(font3);
		adminp3.add(h3);
		adeleteid= new JLabel("Librarian Id :");
		adeleteid.setBounds(100,100,100,20);
		adeleteidtf= new JTextField();
		adeleteidtf.setBounds(250,100,150,25);
		adminp3.add(adeleteid);
		adminp3.add(adeleteidtf);

		adeletepass= new JLabel("Admin Password :");
		adeletepass.setBounds(100,140,150,20);
		adeletepasstf= new JPasswordField();
		adeletepasstf.setEchoChar('*');
		adeletepasstf.setBounds(250,140,150,25);
		adminp3.add(adeletepass);
		adminp3.add(adeletepasstf);
		
		delete= new JButton("Delete",new ImageIcon("image/delete.png"));
		delete.setBounds(100,220,100,25);
		deleteclear= new JButton("Clear",new ImageIcon("image/clear.png"));
		deleteclear.setBounds(250,220,100,25);
		adminp3.add(delete);
		adminp3.add(deleteclear);
		deleteclear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				adeleteidtf.setText("");
				adeletepasstf.setText("");
			}
		});
		delete.addActionListener(this);
	
		//Log out
		logout=new JButton("Log Out",new ImageIcon("image/Key.gif"));
		logout.setLayout(null);
		logout.setBounds(200,200,130,40);
		logout.addActionListener(this);
		adminp4.add(logout);
		
		JLabel im=new JLabel(new ImageIcon("image/a.jpg"));
		im.setBounds(0,0,700,500);
		JLabel im3=new JLabel(new ImageIcon("image/a.jpg"));
		im3.setBounds(0,0,700,500);
		JLabel im4=new JLabel(new ImageIcon("image/a.jpg"));
		im4.setBounds(0,0,700,500);
		adminp1.add(im);
		adminp3.add(im3);
		adminp4.add(im4);
		add(admintp);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ea)
	{	if(ea.getSource()==delete)
		{	String s1; int i=0;
			s1=adeleteidtf.getText();
			char [] s2=adeletepasstf.getPassword();
			if((s1.isEmpty())||(s2.length==0))
			{ System.out.println("Detail ");
				JOptionPane.showMessageDialog(this,"Please  fill details properly","Details",JOptionPane.ERROR_MESSAGE);
			}
		 	else{  
				
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
			                               	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
					stm2=con.createStatement();
					rs2=stm2.executeQuery("Select * from librarian");
						while (rs2.next())
						{
							if(adeleteidtf.getText().equals(rs2.getString(2)))
							{  pass5+=1;   break; }
						}	con.close();
					}
				catch(Exception e){  JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);}
				
				if(pass5==1)
				{

					pass5=0;
				try
    				{

				Class.forName("oracle.jdbc.driver.OracleDriver");
			                               		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
						stm2=con.createStatement();
						rs2=stm2.executeQuery("Select * from admin1");
						char []c4=adeletepasstf.getPassword();
						String p4=String.valueOf(c4);	
							while (rs2.next())
							{
								if(p4.equals(rs2.getString(2)))
								{
									i=1;
				
									break;
								}
							}

				if(i==1){
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
      				String query = "delete from Librarian where useridl = ?";
      				PreparedStatement preparedStmt = conn.prepareStatement(query);
      				preparedStmt.setString(1, s1);
      				preparedStmt.execute();
      				conn.close();
				JOptionPane.showMessageDialog(this,"Delete Librarian Successful","Success",JOptionPane.INFORMATION_MESSAGE);
				adeleteidtf.setText("");
				adeletepasstf.setText("");
					}
				else{   
			JOptionPane.showMessageDialog(this,"Incorrect Password ","Error",JOptionPane.ERROR_MESSAGE);
					}
			                }
    				catch (Exception e)
    				{   JOptionPane.showMessageDialog(this,e,"Details",JOptionPane.ERROR_MESSAGE);}
				} else{ JOptionPane.showMessageDialog(this,"Incorrect User ID ","Error",JOptionPane.ERROR_MESSAGE); pass5=0;}
					System.out.println("AVSK"); pass5=0;}
		}

		if(ea.getSource()==alibrarian)

		{	int q1=0;
			
			
			
				try{
						Class.forName("oracle.jdbc.driver.OracleDriver");
			                               		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
						stm2=con.createStatement();
						rs=stm2.executeQuery("Select * from librarian");
							while (rs.next())
							{
								if(!auseridtf.getText().equals(rs.getString(2)))
								{  
									
								
								}
								else{ q1=1;  System.out.println("else1"); }
							}	
					}
				catch(Exception e){}
			String s11,s22,s44,s55,s66,s77,s88;
			s11=anametf.getText();
			s22=auseridtf.getText();
			char [] s33=apasswordtf.getPassword();
			//s44=aemailidtf.getText();
			s55=aaddresstf.getText();
			s66=acitytf.getText();
			s77=acontactnotf.getText();
			if((s11.isEmpty())||(s22.isEmpty())||(s33.length==0)||(s55.isEmpty())||(s66.isEmpty())||(s77.isEmpty()))
			{ System.out.println("Detail ");
				JOptionPane.showMessageDialog(this,"Please  fill details properly","Error",JOptionPane.ERROR_MESSAGE);
			}
		 	else{ 	pass9=0;
				   String i = auseridtf.getText();
				   String i1=acontactnotf.getText();
				for(int y=0;y<i.length(); ++y)
				{
					if(!Character.isDigit(i.charAt(y)))
						{  pass9=7;  }
				}
				for(int y1=0;y1<i1.length(); ++y1)
				{
					if(!Character.isDigit(i1.charAt(y1)))
						{  pass9=7;  }
				}
				
				if(pass9==0)
				{

					pass9=5;



				try
				{
								
		
				up=auseridtf.getText();
				if(q1==1){} else{ q2=1; }
				if(q2==1){
					if(p9==1){
				//rs= stm2.executeQuery("SELECT *  FROM librarian WHERE USERIDL="+up+" ");	
				String query = "delete from Librarian where useridl = ?";
      				PreparedStatement preparedStmt = con.prepareStatement(query);
      				preparedStmt.setString(1, up);
      				preparedStmt.execute(); } 
				
				char []c3=apasswordtf.getPassword();
				String p3=String.valueOf(c3);	
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
				stm =  con.prepareStatement("insert into librarian values(?,?,?,?,?,?,?)");
			                stm.setString(1,anametf.getText());
				stm.setString(2,auseridtf.getText());
				stm.setString(3,p3);	
				stm.setString(4,aemailidtf.getText());
				stm.setString(5,aaddresstf.getText());
				stm.setString(6,acitytf.getText());
				stm.setString(7,acontactnotf.getText());
				stm.executeUpdate();
				JOptionPane.showMessageDialog(this,"Add Librarian Successful","Success",JOptionPane.INFORMATION_MESSAGE);
				auseridtf.setEditable(true);
				int g11=101+r1.call4();	
				String g12=String.valueOf(g11);
				auseridtf.setText(g12);
				anametf.setText("");
				apasswordtf.setText("");
				aemailidtf.setText("");
				aaddresstf.setText("");
				acitytf.setText("");
				acontactnotf.setText("");	
				p9=0;
				q2=0;
				start.setText("");  
				con.close();			
				}
				else{ JOptionPane.showMessageDialog(this,"User ID already use","Error",JOptionPane.ERROR_MESSAGE);}    
				
				}
				catch(Exception er)
				{   JOptionPane.showMessageDialog(this,er,"Success",JOptionPane.INFORMATION_MESSAGE);  }
				}
				else{ JOptionPane.showMessageDialog(this,"User id or Contact No Only Number Accepted","Error",JOptionPane.ERROR_MESSAGE); pass9=5;}
				System.out.println("AVSK");
			       }
		}
		
			if(ea.getSource()==bt4)
				{
					String text=searchtf4.getText();
					if(text.length()==0)
					{     rsorter4.setRowFilter(null);  }
					rsorter4.setRowFilter(RowFilter.regexFilter(text));
				}
		
			if(ea.getSource()==refresh4||ea.getSource()==refresh5)
			{  try{      new Admin();  dispose();     }
			catch(Exception e) { } }
		if(ea.getSource()==logout)
			{   new Login();  dispose(); }
		
		if(ea.getSource()==update)
		{   	
			  up=JOptionPane.showInputDialog(this,"Enter Librarian ID"); 
			 int i=0;
			
				
			if(!up.isEmpty()){
			try
    				{

						Class.forName("oracle.jdbc.driver.OracleDriver");
			                               		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
						stm2=con.createStatement();
						rs2=stm2.executeQuery("Select * from librarian");
							
							while (rs2.next())
							{
								if(up.equals(rs2.getString(2)))
								{
									i=1;
				
									break;
								}
							}

				if(i==1){

					p9=1;
				q2=1;
				auseridtf.setEditable(false);
				start.setText("Update start");
			adminp1.add(start); 
			//setVisible(false); setVisible(true);		
			
			
			rs= stm2.executeQuery("SELECT *  FROM librarian WHERE USERIDL="+up+" ");	
					while(rs.next())
					{				
					anametf.setText(rs.getString(1));
					auseridtf.setText(rs.getString(2));
					apasswordtf.setText(rs.getString(3));
					aemailidtf.setText(rs.getString(4));
					aaddresstf.setText(rs.getString(5));
					acitytf.setText(rs.getString(6));
					acontactnotf.setText(rs.getString(7));
						
					  }   
			
				//con.close();	
				System.out.println("AVSK");
				
			
					}
				else{   
			JOptionPane.showMessageDialog(this,"Incorrect USER ID ","Error",JOptionPane.ERROR_MESSAGE);
					}
			                }
    				catch (Exception e)
    				{   JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
															 }
			}
			else{   JOptionPane.showMessageDialog(this,"TextField is Empty","Error",JOptionPane.ERROR_MESSAGE);}
			System.out.println(up);   }
					 		 
	}
	
	/*public static void main(String []args) throws Exception

		{  new Admin();  }*/
}
		















		