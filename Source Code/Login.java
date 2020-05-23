import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
public class Login extends JFrame implements ItemListener , ActionListener 
{

	String system;
	String systempass;
	Choice choice1;	
	JLabel userid,password;
	JTextField useridtf;
	JPasswordField passwordtf;
	JButton login,login2,exit,table;
	Statement stm;
	Connection con;
	ResultSet rs;
	String lib="Librarian Login";
	JPanel panel;
	public Login()
	{
		super("Library Management v 1.1");
		WindowUtilities.setNativeLookAndFeel();
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
		SysTray t=new SysTray(); t.Gui(); 
		setSize(600,400);
		setLayout(null);
		setIconImage(new ImageIcon("image/Red_book.png").getImage());
		setLocationRelativeTo(null);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e){dispose(); }});
		 panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0,0,600,400);
		panel.setBackground(new Color(5,194,250));
		JLabel label1=new JLabel("Library Management");
		label1.setLayout(null);
		label1.setBounds(100,20,500,60);
		label1.setForeground(Color.blue);
		Font font=new Font("Rosewood Std",Font.BOLD,40);
		label1.setFont(font);
		choice1=new Choice();
		choice1.add("Librarian Login");
		choice1.add("Student Login");
		choice1.add("Admin Login");
		choice1.setBounds(200,100,200,30);
		choice1.addItemListener(this);
		panel.add(choice1);
		userid= new JLabel("User ID :");
		userid.setLayout(null);
		userid.setBounds(150,150,100,20);
		panel.add(userid);
		password= new JLabel("User Password :");
		password.setLayout(null);
		password.setBounds(150,190,100,20);
		panel.add(password);
		useridtf= new JTextField(30);
		useridtf.setLayout(null);
		useridtf.setBounds(300,150,100,20);
		panel.add(useridtf);
		passwordtf= new JPasswordField(30);
		//passwordtf.setLayout(null);
		passwordtf.setBounds(300,190,100,20);
		passwordtf.setEchoChar('*');
		panel.add(passwordtf);
		login=new JButton("Login",new ImageIcon("image/log.png"));
		login.setLayout(null);
		login.setBounds(180,270,100,20);
		login.addActionListener(this);
		panel.add(login);
		exit=new JButton("Exit",new ImageIcon("image/delete.png"));
		exit.setLayout(null);
		exit.setBounds(310,270,100,20);
		exit.addActionListener(this);
		panel.add(exit);

		table=new JButton("Create DataBase",new ImageIcon("image/clear.png"));
		table.setLayout(null);
		table.setBackground(new Color(5,194,250));
		table.setForeground(Color.red);
		table.setBounds(410,320,150,20);
		table.addActionListener(this);
		panel.add(table);
		
		panel.add(label1);
		JLabel im1=new JLabel(new ImageIcon("image/a.jpg"));
		im1.setBounds(0,0,600,400);
		panel.add(im1);
		add(panel);
		setVisible(true);
	}
	public void itemStateChanged(ItemEvent ie)
		{    if(ie.getItemSelectable()==choice1)
			{	lib=((Choice)ie.getItemSelectable()).getSelectedItem();
				System.out.println(lib);
			    
			}
			
		  }
	
	public void actionPerformed(ActionEvent ea)
		{  
			char []c5=passwordtf.getPassword();
			String p5=String.valueOf(c5);
			/*char []c6=passwordtf.getPassword();
			String p6=String.valueOf(c6);*/
			
			int i=0,i1=0;
			if(ea.getSource()==exit)
			{  dispose(); }
			if(ea.getSource()==table)
			{ new Oracle(); dispose(); }
			
			if(ea.getSource()==login)
			{	String s1,s2;
			s1=useridtf.getText();
			s2=p5;
			if((s1.isEmpty())||(s2.isEmpty()))
			{ 
			      JOptionPane.showMessageDialog(this,"ID or Password is Empty","Details",JOptionPane.ERROR_MESSAGE);
			}
		 	else{ 
				if(ea.getSource()==login)
			{
				try
				{	if("Librarian Login".equals(lib))
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
			                               		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
						stm=con.createStatement();
						rs=stm.executeQuery("Select * from librarian");
							while (rs.next())
							{
								if(useridtf.getText().equals(rs.getString(2))&&p5.equals(rs.getString(3))&&("Librarian Login".equals(lib)))
								{
									i=1;
									break;
								}
							}
						if(i==1)
						{
						
							new Librarian();
							dispose();
						}
						else
						{             	
							
						JOptionPane.showMessageDialog(this,"User ID or Password Incorrect ","Details",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
						{
						if("Admin Login".equals(lib))
							{
						Class.forName("oracle.jdbc.driver.OracleDriver");
			                               		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
						stm=con.createStatement();
						rs=stm.executeQuery("Select * from admin1");
							while (rs.next())
							{
								if(useridtf.getText().equals(rs.getString(1))&&p5.equals(rs.getString(2))&&("Admin Login".equals(lib)))
								{
									i1=1;
									break;
								}
							}
						if(i1==1)
						{
							new Admin();
							dispose();
						}
						else
						{
						JOptionPane.showMessageDialog(this,"User ID or Password Incorrect ","Details",JOptionPane.ERROR_MESSAGE);
						}
							}
					else
						{
							int i11=0;
							if("Student Login".equals(lib))
							{    
						Class.forName("oracle.jdbc.driver.OracleDriver");
			                               		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
						stm=con.createStatement();
						rs=stm.executeQuery("Select * from student1");
						
							while (rs.next())
							{
								if(useridtf.getText().equals(rs.getString(1))&&p5.equals(rs.getString(3))&&("Student Login".equals(lib)))
								{
									i11=1;
									break;
								}
							}
						if(i11==1)
						{
							new Student();
							dispose();
						}
						else
						{
						JOptionPane.showMessageDialog(this,"User ID or Password Incorrect ","Details",JOptionPane.ERROR_MESSAGE);
						}
							}
					else
						{
						JOptionPane.showMessageDialog(this,"User ID or Password Incorrect ","Details",JOptionPane.ERROR_MESSAGE);
						}
						}	

				}
						
		
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(this,"Data Base Conaction Error  "+e,"Details",JOptionPane.ERROR_MESSAGE);
					new Oracle(); dispose();
					System.out.println(e);
				}
			}

				


				 System.out.println("AVSK");
			         }
		}
		}
public static void main(String []args)
	{     new Login();   }
}