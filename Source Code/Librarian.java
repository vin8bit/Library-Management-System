import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.regex.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Librarian extends JFrame implements ActionListener , ItemListener
{
	DataBase k=new DataBase();
	String system;
	String systempass;
	JTabbedPane pn;
	JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9;
	JButton addbook1,cancel1,addstudent1,cancel2;
	JLabel h1,h2,bookid1,name1,author1,publisher1,quantity1,username1,userid1,password1,phone1,emailid1;
	JLabel h3,bookid3,userid3,issuedate3,returndate3;
	JTextField bookidtf3,useridtf3,issuedatetf3,returndatetf3;
	JButton issue3,cancel3;
	JTextField bookidtf1,nametf1,authortf1,publishertf1,quantitytf1,usernametf1,useridtf1,phonetf1,emailidtf1;
	JPasswordField passwordtf1,passwordtf5;
	JLabel h4,bookid4,userid4,returndate4;
	JTextField bookidtf4,useridtf4,returndatetf4;
	JButton submit4,cancel4;
	JLabel h5,bookid5,password5,start;	
	JTextField bookidtf5, searchtf,searchtf1,searchtf3;
	JButton delete5,delete2,cancel5,bt,bt1,bt3;
	JPanel bookPanel,bookPanel1,bookPanel3;
	TableModel tmodel,tmodel1,tmodel3;
	JTable jtable,jtable1,jtable3;
	TableRowSorter<TableModel> rsorter;
	TableRowSorter<TableModel> rsorter1,rsorter3;
	String columns[]={"Book ID","Book Name","Author","Publisher","Quantity"};
	String columns1[]={"User ID","User Name","User Password","Phone No","Email ID"};
	String columns3[]={"Book ID","User ID","Issue Date","Return Date"};
	RowCount1  r1=new RowCount1();
	int r2=1+r1.call();
	int r3=101+r1.call1();
	PreparedStatement stm;
	Statement stm2,stm29;
	ResultSet rs2,rs29;
	Connection con, con9;
	String up,up2;
	int p9=0;
	int q2=0;
	int q1=0;
	int pb9=0;
	int qb2=0;
	int qb1=0;
	int pb39=0;
	int qb32=0;
	int qb31=0;
	JButton refresh,refresh2,refresh3,refresh4,logout,update;
	JButton bookstatus,studentrecord;
	Choice choice1,choice2,choice3,choice1r,choice2r,choice3r;

	java.time.LocalDateTime now = java.time.LocalDateTime.now();  
	java.time.format.DateTimeFormatter format = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");  
        	String is = now.format(format);  
	String issueDate=is.replace('-',' ');
	String d="11",m="11",y="11",rt1="01 01 2016";
	int pass=0,pass2=0,pass3=0,pass5=0,pass9=0,pass91=0;
	String returndate;
	
	public   Librarian() throws Exception
	{	
		super("Librarian v 1.1");
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
		setSize(700,500);
		setIconImage(new ImageIcon("image/Red_book.png").getImage());
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e){dispose(); }});
		setLocationRelativeTo(null);
		pn = new JTabbedPane();
		pn.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		pn.setTabPlacement(JTabbedPane.LEFT);
		panel1 =new JPanel();
		panel1.setLayout(null);
		panel2 =new JPanel();
		panel2.setLayout(null);
		panel3 =new JPanel();
		panel3.setLayout(null);
		panel4 =new JPanel();
		panel4.setLayout(null);
		panel5 =new JPanel();
		panel5.setLayout(null);
		panel6 =new JPanel();
		panel6.setLayout(new BorderLayout());
		panel7 =new JPanel();
		panel7.setLayout(new BorderLayout());
		panel8 =new JPanel();
		panel8.setLayout(new BorderLayout());
		panel9 =new JPanel();
		panel9.setLayout(null);
		pn.addTab("Add Book",new ImageIcon("image/add.png"),panel1,"Add Book");
		pn.addTab("Add Student",new ImageIcon("image/student.png"),panel2,"Add Student");
		pn.addTab("Issue Book",new ImageIcon("image/issue.png"),panel3,"Issue Book");
		pn.addTab("Return Book",new ImageIcon("image/Red_book.png"),panel4,"Return Book");
		pn.addTab("Delete Book",new ImageIcon("image/add.gif"),panel5,"Delete Book");
		pn.addTab("View Student",new ImageIcon("image/student.gif"),panel6,"View Student");
		pn.addTab("View Book",new ImageIcon("image/view.gif"),panel7,"View Book");
		pn.addTab("View Iss.. Books",new ImageIcon("image/issue.png"),panel8,"View Issued Books");
		pn.addTab("Log Out",new ImageIcon("image/Exit.png"),panel9,"Log Out");
		
		//Add Book
		h1=new JLabel("Add Book");
		h1.setBounds(200,20,200,50);
		Font font=new Font("Britannic",Font.PLAIN,25);
		h1.setFont(font);
		panel1.add(h1);
		bookid1=new JLabel("Book Id :");
		bookid1.setBounds(100,100,100,20);
		bookidtf1=new JTextField();
	               String id1=String.valueOf(r2);
		bookidtf1.setText(id1);
		bookidtf1.setBounds(250,100,150,25);
		panel1.add(bookid1);
		panel1.add(bookidtf1);
		name1=new JLabel("Book Name :");
		name1.setBounds(100,140,100,20);
		nametf1=new JTextField();
		nametf1.setBounds(250,140,150,25); 
		panel1.add(name1);
		panel1.add(nametf1);

		author1=new JLabel("Author :");
		author1.setBounds(100,180,100,20);
		authortf1=new JTextField();
		authortf1.setBounds(250,180,150,25); 
		panel1.add(author1);
		panel1.add(authortf1);
		
		publisher1=new JLabel("Publisher :");
		publisher1.setBounds(100,220,100,20);
		publishertf1=new JTextField();
		publishertf1.setBounds(250,220,150,25); 
		panel1.add(publisher1);
		panel1.add(publishertf1);

		quantity1=new JLabel("Quantity :");
		quantity1.setBounds(100,260,100,20);
		quantitytf1=new JTextField();
		quantitytf1.setBounds(250,260,150,25); 
		panel1.add(quantity1);
		panel1.add(quantitytf1);

		addbook1= new JButton("Add Book",new ImageIcon("image/save.png"));
		addbook1.setBounds(100,340,120,30);
		cancel1= new JButton("Clear",new ImageIcon("image/clear.png"));
		cancel1.setBounds(250,340,100,30);
		panel1.add(addbook1);
		panel1.add(cancel1);
		bookstatus= new JButton("Book Status",new ImageIcon("image/clear.png"));
		bookstatus.setBounds(380,340,130,30);
		bookstatus.addActionListener(this);
		panel1.add(bookstatus);
		addbook1.addActionListener(this);
		cancel1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			     try{	int r14=1+r1.call();
				String rr1=String.valueOf(r14);
				bookidtf1.setText(rr1);
				//bookidtf1.setText("");
				nametf1.setText("");
				authortf1.setText("");
				publishertf1.setText("");
				quantitytf1.setText("");
				}
			   catch(Exception rt){}
			
			}
		});	


		//Add Studens
		h2=new JLabel("Add Student");
		h2.setBounds(200,20,200,50);
		Font font2=new Font("Britannic",Font.PLAIN,25);
		h2.setFont(font2);
		refresh4=new JButton("Refresh",new ImageIcon("image/Refresh.gif"));
		refresh4.setBounds(390,20,110,30);
		refresh4.addActionListener(this);
		panel2.add(refresh4);
		start= new JLabel("Uptade start");
		start.setBounds(430,140,100,20);
		start.setForeground(Color.red);

		panel2.add(h2);
		username1=new JLabel("User Name :");
		username1.setBounds(100,100,100,20);
		usernametf1=new JTextField();
		usernametf1.setBounds(250,100,150,25);
		panel2.add(username1);
		panel2.add(usernametf1);
		userid1=new JLabel("User Id :");
		userid1.setBounds(100,140,100,20);
		useridtf1=new JTextField();
		String id3=String.valueOf(r3);
		useridtf1.setText(id3);
		useridtf1.setBounds(250,140,150,25); 
		panel2.add(userid1);
		panel2.add(useridtf1);

		password1=new JLabel("User password :");
		password1.setBounds(100,180,100,20);
		passwordtf1=new JPasswordField();
		passwordtf1.setBounds(250,180,150,25); 
		passwordtf1.setEchoChar('*');
		panel2.add(password1);
		panel2.add(passwordtf1);
		
		phone1=new JLabel("phone No :");
		phone1.setBounds(100,220,100,20);
		phonetf1=new JTextField();
		phonetf1.setBounds(250,220,150,25); 
		panel2.add(phone1);
		panel2.add(phonetf1);

		emailid1=new JLabel("Eamil Id :");
		emailid1.setBounds(100,260,100,20);
		emailidtf1=new JTextField();
		emailidtf1.setBounds(250,260,150,25); 
		panel2.add(emailid1);
		panel2.add(emailidtf1);

		addstudent1= new JButton("Add Student",new ImageIcon("image/save.png"));
		addstudent1.setBounds(20,340,130,30);
		cancel2= new JButton("Clear",new ImageIcon("image/clear.png"));
		cancel2.setBounds(290,340,90,30);
		update= new JButton("Update",new ImageIcon("image/update.png"));
		update.setBounds(170,340,100,30);
		panel2.add(update);
		update.addActionListener(this);
		panel2.add(addstudent1);
		panel2.add(cancel2);
		addstudent1.addActionListener(this);
		delete2= new JButton("Delete",new ImageIcon("image/delete.png"));
		delete2.setBounds(420,340,90,30);
		delete2.addActionListener(this);
		panel2.add(delete2);
		
		studentrecord= new JButton("Student Record",new ImageIcon("image/clear.png"));
		studentrecord.setBounds(220,400,150,30);
		studentrecord.addActionListener(this);
		panel2.add(studentrecord);

		cancel2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try{ 
				usernametf1.setText("");
				int r4=101+r1.call1();
				String rr=String.valueOf(r4);
				useridtf1.setText(rr);
				passwordtf1.setText("");
				phonetf1.setText("");
				emailidtf1.setText("");}
				catch(Exception r){}
			
			}
		});	

		//Issue Book
		h3=new JLabel("Issue Book");
		h3.setBounds(200,20,200,50);
		Font font3=new Font("Britannic",Font.PLAIN,25);
		h3.setFont(font3);
		panel3.add(h3);
		bookid3=new JLabel("Book Id :");
		bookid3.setBounds(100,100,100,20);
		bookidtf3=new JTextField();
		bookidtf3.setBounds(250,100,150,25);
		panel3.add(bookid3);
		panel3.add(bookidtf3);
		userid3=new JLabel("User Id :");
		userid3.setBounds(100,140,100,20);
		useridtf3=new JTextField();
		useridtf3.setBounds(250,140,150,25); 
		panel3.add(userid3);
		panel3.add(useridtf3);

		issuedate3=new JLabel("Issue Date :");
		issuedate3.setBounds(100,180,100,20);
		issuedatetf3=new JTextField();
		issuedatetf3.setBounds(250,180,150,25);
		issuedatetf3.setEditable(false);
		issuedatetf3.setText(issueDate);
		panel3.add(issuedate3);
		panel3.add(issuedatetf3);


		

		
		returndate3=new JLabel("Return Date :");
		returndate3.setBounds(100,220,100,20);
		//returndatetf3=new JTextField();
		//returndatetf3.setBounds(250,220,150,25); 
		panel3.add(returndate3);


		
		choice1= new Choice();
		choice1.addItem("01");
		choice1.addItem("02");
		choice1.addItem("03");
		choice1.addItem("04");
		choice1.addItem("05");
		choice1.addItem("06");
		choice1.addItem("07");
		choice1.addItem("08");
		choice1.addItem("09");
		choice1.addItem("10");
		choice1.addItem("11");
		choice1.addItem("12");
		choice1.addItem("13");
		choice1.addItem("14");
		choice1.addItem("15");
		choice1.addItem("16");
		choice1.addItem("17");
		choice1.addItem("18");
		choice1.addItem("19");
		choice1.addItem("20");
		choice1.addItem("21");
		choice1.addItem("22");
		choice1.addItem("23");
		choice1.addItem("24");
		choice1.addItem("25");
		choice1.addItem("26");
		choice1.addItem("27");
		choice1.addItem("28");
		choice1.addItem("29");
		choice1.addItem("30");
		choice1.addItem("31");
		choice1.setBounds(250,220,40,20);
		choice1.addItemListener(this);
		panel3.add(choice1);

		choice2= new Choice();
		choice2.addItem("01");
		choice2.addItem("02");
		choice2.addItem("03");
		choice2.addItem("04");
		choice2.addItem("05");
		choice2.addItem("06");
		choice2.addItem("07");
		choice2.addItem("08");
		choice2.addItem("09");
		choice2.addItem("10");
		choice2.addItem("11");
		choice2.addItem("12");
		choice2.setBounds(310,220,40,20);
		JLabel month= new JLabel("D-M-YEAR");
		month.setBounds(450,220,150,20);
		panel3.add(month);
		choice2.addItemListener(this);
		panel3.add(choice2);
		
		choice3= new Choice();
		choice3.addItem("2016");
		choice3.addItem("2017");
		choice3.addItem("2018");
		choice3.addItem("2019");
		choice3.addItem("2020");
		choice3.addItem("2021");
		choice3.addItem("2022");
		choice3.addItem("2023");
		choice3.addItem("2024");
		choice3.addItem("2025");
		choice3.addItem("2026");
		choice3.addItem("2027");
		choice3.addItem("2028");
		choice3.addItem("2029");
		choice3.addItem("2030");
		choice3.setBounds(370,220,60,20);
		choice3.addItemListener(this);
		panel3.add(choice3);










		//panel3.add(returndatetf3);

		issue3= new JButton("Issue Book",new ImageIcon("image/save.png"));
		issue3.setBounds(100,340,120,30);
		cancel3= new JButton("Clear",new ImageIcon("image/clear.png"));
		cancel3.setBounds(300,340,100,30);
		panel3.add(issue3);
		panel3.add(cancel3);
		issue3.addActionListener(this);
		cancel3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				 
				bookidtf3.setText("");
				useridtf3.setText("");
				//issuedatetf3.setText("");
				returndatetf3.setText("");
			
			}
		});	
		
		//Return Book
		h4=new JLabel("Return Book");
		h4.setBounds(200,20,200,50);
		Font font4=new Font("Britannic",Font.PLAIN,25);
		h4.setFont(font4);
		panel4.add(h4);
		bookid4=new JLabel("Book Id :");
		bookid4.setBounds(100,100,100,20);
		bookidtf4=new JTextField();
		bookidtf4.setBounds(250,100,150,25);
		panel4.add(bookid4);
		panel4.add(bookidtf4);
		userid4=new JLabel("User Id :");
		userid4.setBounds(100,140,100,20);
		useridtf4=new JTextField();
		useridtf4.setBounds(250,140,150,25); 
		panel4.add(userid4);
		panel4.add(useridtf4);

		submit4= new JButton("Submit",new ImageIcon("image/save.png"));
		submit4.setBounds(100,300,120,30);
		cancel4= new JButton("Clear",new ImageIcon("image/clear.png"));
		cancel4.setBounds(300,300,100,30);
		panel4.add(submit4);
		panel4.add(cancel4);
		submit4.addActionListener(this);
		cancel4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				 
				bookidtf4.setText("");
				useridtf4.setText("");
			
			}
		});	

		//Delete Book
		h5=new JLabel("Delete Book");
		h5.setBounds(200,20,200,50);
		Font font5=new Font("Britannic",Font.PLAIN,25);
		h5.setFont(font5);
		panel5.add(h5);
		bookid5=new JLabel("Book Id :");
		bookid5.setBounds(100,100,100,20);
		bookidtf5=new JTextField();
		bookidtf5.setBounds(250,100,150,25);
		panel5.add(bookid5);
		panel5.add(bookidtf5);

		password5=new JLabel("Password :");
		password5.setBounds(100,140,100,20);
		passwordtf5=new JPasswordField();
		passwordtf5.setEchoChar('*');
		passwordtf5.setBounds(250,140,150,25); 
		panel5.add(password5);
		panel5.add(passwordtf5);

		delete5= new JButton("Delete",new ImageIcon("image/delete.png"));
		delete5.setBounds(100,260,120,30);
		cancel5= new JButton("Clear",new ImageIcon("image/clear.png"));
		cancel5.setBounds(300,260,100,30);
		panel5.add(delete5);
		panel5.add(cancel5);
		delete5.addActionListener(this);
		cancel5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				 
				bookidtf5.setText("");
				passwordtf5.setText("");
			
			}
		});
		
		//View Book
		JLabel h9=new JLabel("View Books");
		Font font9=new Font("Britannic",Font.PLAIN,25);
		h9.setFont(font9);
		bookPanel=new JPanel(new BorderLayout());
		JPanel bu=new JPanel();
		bu.setBackground(new Color(5,194,250));
		 tmodel= new DefaultTableModel(k.array,columns);
		 jtable=new JTable(tmodel);
		 rsorter = new TableRowSorter<TableModel>(tmodel);
		JScrollPane jspane=new JScrollPane(jtable);
		jspane.setPreferredSize(new Dimension(550, 350));
		jtable.setRowSorter(rsorter);
		searchtf=new JTextField(25);
		bt=new JButton("Search",new ImageIcon("image/search.png"));
		bt.addActionListener(this);
		bu.add(h9,BorderLayout.NORTH);
		bu.add(searchtf,BorderLayout.CENTER);
		bu.add(bt,BorderLayout.EAST);
		bookPanel.add(jspane,BorderLayout.CENTER);
		bookPanel.add(bu,BorderLayout.NORTH);
		panel7.add(bookPanel);
		refresh=new JButton("Refresh",new ImageIcon("image/Refresh.gif"));
		refresh.addActionListener(this);
		panel7.add(refresh,BorderLayout.SOUTH);
		
	
		//View Student
		
		JLabel h8=new JLabel("View Student");
		Font font8=new Font("Britannic",Font.PLAIN,25);
		h8.setFont(font8);
		bookPanel1=new JPanel(new BorderLayout());
		JPanel bu1=new JPanel();
		bu1.setBackground(new Color(5,194,250));
		 tmodel1= new DefaultTableModel(k.array2,columns1);
		 jtable1=new JTable(tmodel1);
		 rsorter1 = new TableRowSorter<TableModel>(tmodel1);
		JScrollPane jspane1=new JScrollPane(jtable1);
		jspane1.setPreferredSize(new Dimension(550, 350));
		jtable1.setRowSorter(rsorter1);
		searchtf1=new JTextField(20);
		bt1=new JButton("Search",new ImageIcon("image/search.png"));
		bt1.addActionListener(this);
		bu1.add(h8,BorderLayout.NORTH);
		bu1.add(searchtf1,BorderLayout.CENTER);
		bu1.add(bt1,BorderLayout.EAST);
		bookPanel1.add(jspane1,BorderLayout.CENTER);
		bookPanel1.add(bu1,BorderLayout.NORTH);
		panel6.add(bookPanel1);
		refresh2=new JButton("Refresh",new ImageIcon("image/Refresh.gif"));
		refresh2.addActionListener(this);
		panel6.add(refresh2,BorderLayout.SOUTH);
		
		
		//View issue book
		JLabel h10=new JLabel("View Issue Book");
		Font font10=new Font("Britannic",Font.PLAIN,25);
		h10.setFont(font10);
		bookPanel3=new JPanel(new BorderLayout());
		JPanel bu3=new JPanel();
		bu3.setBackground(new Color(5,194,250));
		 tmodel3= new DefaultTableModel(k.array3,columns3);
		 jtable3=new JTable(tmodel3);
		 rsorter3 = new TableRowSorter<TableModel>(tmodel3);
		JScrollPane jspane3=new JScrollPane(jtable3);
		jspane3.setPreferredSize(new Dimension(550, 350));
		jtable3.setRowSorter(rsorter3);
		searchtf3=new JTextField(15);
		bt3=new JButton("Search",new ImageIcon("image/search.png"));
		bt3.addActionListener(this);
		bu3.add(h10,BorderLayout.NORTH);
		bu3.add(searchtf3,BorderLayout.CENTER);
		bu3.add(bt3,BorderLayout.EAST);
		bookPanel3.add(jspane3,BorderLayout.CENTER);
		bookPanel3.add(bu3,BorderLayout.NORTH);
		panel8.add(bookPanel3);
		refresh3=new JButton("Refresh",new ImageIcon("image/Refresh.gif"));
		refresh3.addActionListener(this);
		panel8.add(refresh3,BorderLayout.SOUTH);
		
		//Log Out
		logout=new JButton("Log Out",new ImageIcon("image/Key.gif"));
		logout.addActionListener(this);
		logout.setLayout(null);
		logout.setBounds(200,200,140,40);
		panel9.add(logout);
		JLabel im1=new JLabel(new ImageIcon("image/a.jpg"));
		im1.setBounds(0,0,700,500);
		panel1.add(im1);	
		JLabel im2=new JLabel(new ImageIcon("image/a.jpg"));
		im2.setBounds(0,0,700,500);
		panel2.add(im2);
		JLabel im3=new JLabel(new ImageIcon("image/a.jpg"));
		im3.setBounds(0,0,700,500);
		panel3.add(im3);
		JLabel im4=new JLabel(new ImageIcon("image/a.jpg"));
		im4.setBounds(0,0,700,500);
		panel4.add(im4);
		JLabel im5=new JLabel(new ImageIcon("image/a.jpg"));
		im5.setBounds(0,0,700,500);
		panel5.add(im5);
		JLabel im9=new JLabel(new ImageIcon("image/a.jpg"));
		im9.setBounds(0,0,700,500);
		panel9.add(im9);
		add(pn);
		setVisible(true);
	}

	public void itemStateChanged(ItemEvent e)
		{
			if(e.getStateChange()== ItemEvent.SELECTED)
			{ 	d=(String)choice1.getSelectedItem();
				m=(String)choice2.getSelectedItem();
				y=(String)choice3.getSelectedItem();
				rt1=d+" "+m+" "+y;
				System.out.println(rt1);
				
			}
		}









	public void actionPerformed(ActionEvent ea) 
	{	
		if(ea.getSource()==addbook1)
		{	 
			try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
			                               	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
					stm2=con.createStatement();
					rs2=stm2.executeQuery("Select book_id from book");
					
						while (rs2.next())
						{
							if(!bookidtf1.getText().equals(rs2.getString(1)))
							{  }
							else{  qb1=1;   System.out.println("else1"); }
						}	con.close();
					}
				catch(Exception e){}
		
			String s11,s22,s33,s44,s55,s66,s77,s88;
			s11=bookidtf1.getText();
			s22=nametf1.getText();
			s33=authortf1.getText();
			s44=publishertf1.getText();
			s55=quantitytf1.getText();
			if((s11.isEmpty())||(s22.isEmpty())||(s33.isEmpty())||(s44.isEmpty())||(s55.isEmpty()))
			{ System.out.println("AVSK Detail ");
				JOptionPane.showMessageDialog(this,"Please  fill details properly","Error",JOptionPane.ERROR_MESSAGE);
			}
		 	else{    pass9=0; 

				 String i = bookidtf1.getText();
				   String i1=quantitytf1.getText();
				for(int y=0;y<i.length(); ++y)
				{
					if(!i1.equals("1"))
						{  pass9=7;  }
					if(!Character.isDigit(i.charAt(y)))
						{  pass9=7;  }
				}
					
				
			

			if(pass9==0)
			{

				pass9=5;

 
				try
			{
			if(qb1==1){qb1=0;} else{ qb2=1; }
				if(qb2==1){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm =  con.prepareStatement("insert into book values(?,?,?,?,?)");
			                stm.setString(1,bookidtf1.getText());
				stm.setString(2,nametf1.getText());
				stm.setString(3,authortf1.getText());	
				stm.setString(4,publishertf1.getText());
				stm.setString(5,quantitytf1.getText());
				stm.executeUpdate();
				JOptionPane.showMessageDialog(this,"Add Book Successful","Success",JOptionPane.INFORMATION_MESSAGE);
				int g11=1+r1.call();	
				String g12=String.valueOf(g11);
				bookidtf1.setText(g12);
				nametf1.setText("");
				authortf1.setText("");
				publishertf1.setText("");
				quantitytf1.setText("");
				con.close();
				pb9=0;
				//q1=0;
				qb2=0;
				
				}
				else{ JOptionPane.showMessageDialog(this,"Book ID already use","Error",JOptionPane.ERROR_MESSAGE);}

			}
		catch(Exception er)
		{	JOptionPane.showMessageDialog(this,er,"Success",JOptionPane.INFORMATION_MESSAGE); System.out.println(er);}
			}
		else{ JOptionPane.showMessageDialog(this,"Book id Number or Quentity Only 1","Error",JOptionPane.ERROR_MESSAGE); pass9=5;   }

				System.out.println("AVSK");}
		}		 
		
		if(ea.getSource()==addstudent1)
		{
			
			
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
			                               	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
					stm2=con.createStatement();
					rs2=stm2.executeQuery("Select * from student1");
					
						while (rs2.next())
						{
							if(!useridtf1.getText().equals(rs2.getString(1)))
							{  }
							else{  q1=1;   System.out.println("else1"); }
						}	//con.close();
					}
				catch(Exception e){}
			String s1,s2,s4,s5;
			s1=usernametf1.getText();
			s2=useridtf1.getText();
			char [] s3= passwordtf1.getPassword();
			s4=phonetf1.getText();
			s5=emailidtf1.getText();
			if((s1.isEmpty())||(s2.isEmpty())||(s3.length==0)||(s4.isEmpty())||(s5.isEmpty()))
			{ System.out.println("AVSK Detail ");
				JOptionPane.showMessageDialog(this,"Please  fill details properly","Error",JOptionPane.ERROR_MESSAGE);
			}
		 	else{ 
				
				pass91=0;
				   String i = useridtf1.getText();
				   String i1=phonetf1.getText();
				for(int y=0;y<i.length(); ++y)
				{
					if(!Character.isDigit(i.charAt(y)))
						{  pass91=7;  }
				}
				for(int y1=0;y1<i1.length(); ++y1)
				{
					if(!Character.isDigit(i1.charAt(y1)))
						{  pass91=7;  }
				}


			if(pass91==0)
			{			
				pass91=5;
			
				up=useridtf1.getText();
				try
			{   System.out.println(q1);
			if(q1==1){q1=0;} else{ q2=1; }
				if(q2==1){
					if(p9==1){
				String query = "delete from student1 where user_id = ?";
      				PreparedStatement preparedStmt = con.prepareStatement(query);
      				preparedStmt.setString(1, up);
      				preparedStmt.execute(); }
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm =  con.prepareStatement("insert into student1 values(?,?,?,?,?)");
			         char []c=passwordtf1.getPassword();
				String p= String.valueOf(c);
				stm.setString(1,useridtf1.getText());
				 stm.setString(2,usernametf1.getText());
				stm.setString(3,p);	
				stm.setString(4,phonetf1.getText());
				stm.setString(5,emailidtf1.getText());
				stm.executeUpdate();
				JOptionPane.showMessageDialog(this,"Add Student Successful","Success",JOptionPane.INFORMATION_MESSAGE);
				useridtf1.setEditable(true);
				usernametf1.setText("");
				int g8=101+r1.call1();	
				String g9=String.valueOf(g8);	
				useridtf1.setText(g9);
				 passwordtf1.setText("");
				phonetf1.setText("");
				emailidtf1.setText("");
				con.close();
				p9=0;
				//q1=0;
				q2=0;
				start.setText("");  
				}
				else{ JOptionPane.showMessageDialog(this,"User ID already use","Error",JOptionPane.ERROR_MESSAGE);}
			}
		catch(Exception er)
		{	JOptionPane.showMessageDialog(this,er,"Success",JOptionPane.INFORMATION_MESSAGE);
			System.out.println(er);
		}  } else{   JOptionPane.showMessageDialog(this,"User ID or Phone No Only Number ","Error",JOptionPane.ERROR_MESSAGE); pass91=5; }
				 System.out.println("AVSK");}
		}
		
		if(ea.getSource()==issue3)
		{
				

					
					DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd MM yyyy");
					//String is="04 04 2018";     //Issue Date
					//String rt="04 04 2019";    //Current Date

					LocalDate firstDate = LocalDate.parse(issueDate, formatter1);
        					LocalDate secondDate = LocalDate.parse(rt1, formatter1);
		
					long days = ChronoUnit.DAYS.between(firstDate, secondDate);
					System.out.println(days);
					String days1=String.valueOf(days);
					char c2= days1.charAt(0);
					String g=String.valueOf(c2);
					pass=0;
					if(g.equals("-"))
					{  JOptionPane.showMessageDialog(this,"Inccorect Return Date","Error",JOptionPane.ERROR_MESSAGE);      }
					else{   pass+=1; }
					System.out.println("Pass "+pass);
				
					



	
		

				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
			                               	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
					stm2=con.createStatement();
					rs2=stm2.executeQuery("Select * from book");
					System.out.println(bookidtf3.getText());
						while (rs2.next())
						{
							if(bookidtf3.getText().equals(rs2.getString(1)))
							{  pass+=1;   break;}
						}	con.close();
					}
				catch(Exception e){}


				
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
			                               	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
					stm2=con.createStatement();
					rs2=stm2.executeQuery("Select * from student1");
					System.out.println(useridtf1.getText());
						while (rs2.next())
						{
							if(useridtf3.getText().equals(rs2.getString(1)))
							{  pass+=1;   break; }
						}	con.close();
					}
				catch(Exception e){}
				






			
			
			


				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
			                               	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
					stm2=con.createStatement();
					rs2=stm2.executeQuery("Select book_id1 from issue_book");
					System.out.println(useridtf1.getText());
						while (rs2.next())
						{
							if(!bookidtf3.getText().equals(rs2.getString(1)))
							{  }
							else{  qb31=1;   System.out.println("else1"); }
						}	con.close();
					}
				catch(Exception e){}
				
	
			String s11,s,s12,s13,s14;
			s11=bookidtf3.getText();
			s12=useridtf3.getText();
			s13= issuedatetf3.getText();
			//s14=returndatetf3.getText();
			if((s11.isEmpty())||(s12.isEmpty())||(s13.isEmpty()))
			{ System.out.println("AVSK Detail "); pass=0;
				JOptionPane.showMessageDialog(this,"Please  fill details properly","Error",JOptionPane.ERROR_MESSAGE);
			}
		 	else{ 

	if(pass==3){

			pass=0;

 
				try
			{
			if(qb31==1){qb31=0;} else{ qb32=1; }
				if(qb32==1){		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm =  con.prepareStatement("insert into issue_book values(?,?,?,?)");
			                stm.setString(1,bookidtf3.getText());
				stm.setString(2,useridtf3.getText());
				stm.setString(3,issuedatetf3.getText());	
				stm.setString(4,rt1);
				stm.executeUpdate();
				JOptionPane.showMessageDialog(this,"Issue Successful","Success",JOptionPane.INFORMATION_MESSAGE);
				String ir1,ir2,ir3,ir4;
				ir1=bookidtf3.getText();
				ir2=useridtf3.getText();
				ir3= issuedatetf3.getText();
				IssueBookReciept   re= new IssueBookReciept(ir1,ir2,ir3,rt1);				

				bookidtf3.setText("");
				useridtf3.setText("");
				con.close();
				pb39=0;
				//q1=0;
				qb32=0;
				
				}
				else{ JOptionPane.showMessageDialog(this,"Book already Issue","Error",JOptionPane.ERROR_MESSAGE);}

			}
		catch(Exception er)
		{	JOptionPane.showMessageDialog(this,er,"Error",JOptionPane.INFORMATION_MESSAGE);
			System.out.println(er);
		}
	}
	else{  pass=0;   JOptionPane.showMessageDialog(this,"Incorrect User ID or Book Id  ","Error",JOptionPane.ERROR_MESSAGE); }
			System.out.println("AVSK"+pass);}
		
		}
	

		if(ea.getSource()==submit4)
		{		String is4 = now.format(format);  
				String issueDate4=is.replace('-',' ');

				String s15,s16,s17;
				s15=bookidtf4.getText();
				s16=useridtf4.getText();
				
			
			try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
			                               	con9 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
					stm29=con9.createStatement();
					rs29=stm29.executeQuery("Select * from book");
					System.out.println(bookidtf3.getText());
						while (rs29.next())
						{
							if(bookidtf4.getText().equals(rs29.getString(1)))
							{  pass2+=1;   break; }
						}	con9.close();
					}
				catch(Exception e){  JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);}


				
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
			                               	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
					stm2=con.createStatement();
					rs2=stm2.executeQuery("Select * from student1");
					System.out.println(useridtf1.getText());
						while (rs2.next())
						{
							if(useridtf4.getText().equals(rs2.getString(1)))
							{  pass2+=1;    break;}
						}	con.close();
					}
				catch(Exception e){   JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);}

		
				








			
			if((s15.isEmpty())||(s16.isEmpty()))
			{ System.out.println(" AVSK Detail ");  pass2=0;  pass3=0; 
				JOptionPane.showMessageDialog(this,"Please  fill details properly","Error",JOptionPane.ERROR_MESSAGE);
			}
		 	else{  
				System.out.println("Pass2 "+pass2);
			if(pass2==2)
			{	pass2=0;
	
				try{	
							
				Class.forName("oracle.jdbc.driver.OracleDriver");
			                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
				stm2=con.createStatement();
				rs2= stm2.executeQuery("SELECT *  FROM issue_book WHERE BOOK_ID1="+s15+" ");
					System.out.println("vineet");	
					while(rs2.next())
					{	
							if(bookidtf4.getText().equals(rs2.getString(1))&&useridtf4.getText().equals(rs2.getString(2)))
							{  pass3=1;  }
						
					  }   
			
				con.close();
				    }	
				catch(Exception e){   JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);}

				if(pass3==1)
				{
					pass3=0;

				try{	

						Class.forName("oracle.jdbc.driver.OracleDriver");
			             			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
						stm2=con.createStatement();
						rs2= stm2.executeQuery("SELECT *  FROM issue_book WHERE BOOK_ID1="+s15+" ");
						System.out.println(returndate);


						while(rs2.next())
						{	
							returndate=rs2.getString(4);
						  }
						con.close();
					 }	
					catch(Exception e){}

				try{  
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
      				String query = "delete from issue_book where book_id1 = ?";
      				PreparedStatement preparedStmt = conn.prepareStatement(query);
      				preparedStmt.setString(1, s15);
      				preparedStmt.execute();
      				conn.close();

				DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd MM yyyy");
					//String is="04 04 2020";     //Issue Date
					//String rt="04 04 2019";    //Current Date

					LocalDate firstDate = LocalDate.parse(issueDate, formatter1);
        					LocalDate secondDate = LocalDate.parse(returndate, formatter1);
		
					long days = ChronoUnit.DAYS.between( secondDate,firstDate);
					System.out.println(days);
					long days3=days*2;
					System.out.println(days3);
					String days1=String.valueOf(days3);
					char c2= days1.charAt(0);
					String g=String.valueOf(c2);



				System.out.println(returndate);
				JOptionPane.showMessageDialog(this,"Return Book Successful","Success",JOptionPane.INFORMATION_MESSAGE);
				String f1,f2;
				f1=bookidtf4.getText();
				f2=useridtf4.getText();
				ReturnBookReciept  cc= new ReturnBookReciept(f1,f2,issueDate,days1);		
		
				bookidtf4.setText("");
				useridtf4.setText("");
				conn.close();
				}
			catch (Exception e)
    				{   JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE); }
				}  else{ JOptionPane.showMessageDialog(this,"User id not issue this Book","Error",JOptionPane.ERROR_MESSAGE); }
			}
			else{  pass2=0; pass3=0;  JOptionPane.showMessageDialog(this,"Incorrect Book ID or User ID","Error",JOptionPane.ERROR_MESSAGE);} 
			System.out.println("AVSK");  pass2=0; pass3=0;}
		}
		
		if(ea.getSource()==delete5)
		{	int i=0;
			String s18;
			s18=bookidtf5.getText();
			char [] s19=passwordtf5.getPassword();
			if((s18.isEmpty())||(s19.length==0))
			{ System.out.println("AVSK Detail ");
				JOptionPane.showMessageDialog(this,"Please  fill details properly","Error",JOptionPane.ERROR_MESSAGE);
			}
		 	else{  

				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
			                               	con9 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
					stm29=con9.createStatement();
					rs29=stm29.executeQuery("Select * from book");
					System.out.println(bookidtf3.getText());
						while (rs29.next())
						{
							if(bookidtf5.getText().equals(rs29.getString(1)))
							{  pass5+=1;   break; }
						}	con9.close();
					}
				catch(Exception e){  JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);}



				if(pass5==1)
				{   pass5=0;

				try
    				{

				Class.forName("oracle.jdbc.driver.OracleDriver");
			                               		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
						stm2=con.createStatement();
						//rs=stm2.executeQuery("Select * from librarian");
						rs2=stm2.executeQuery("Select * from librarian");
							char []c1=passwordtf5.getPassword();
							String p1=String.valueOf(c1);	
							while (rs2.next())
							{
								if(p1.equals(rs2.getString(3)))
								{
									i=1;
				
									break;
								}
							}

				if(i==1){
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
      				String query = "delete from book where book_id = ?";
      				PreparedStatement preparedStmt = conn.prepareStatement(query);
      				preparedStmt.setString(1, s18);
      				preparedStmt.execute();
      				conn.close();
				JOptionPane.showMessageDialog(this,"Delete Book Successful","Success",JOptionPane.INFORMATION_MESSAGE);
				bookidtf5.setText("");
				passwordtf5.setText("");
				conn.close();
					}
				else{   
			JOptionPane.showMessageDialog(this,"Incorrect Password ","Error",JOptionPane.ERROR_MESSAGE);
					}
			                }
    				catch (Exception e)
    				{   JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE); }
				} else{JOptionPane.showMessageDialog(this,"Incorrect Book ID ","Error",JOptionPane.ERROR_MESSAGE); pass5=0;}
				System.out.println("AVSK"); pass5=0;}
		
		}
		
		if(ea.getSource()==bt)
		{
					String text=searchtf.getText();
					if(text.length()==0)
					{     rsorter.setRowFilter(null);  }
					rsorter.setRowFilter(RowFilter.regexFilter(text));
		}
		
		if(ea.getSource()==bt1)
		{
					String text=searchtf1.getText();
					if(text.length()==0)
					{     rsorter1.setRowFilter(null);  }
					rsorter1.setRowFilter(RowFilter.regexFilter(text));
		}
	
		if(ea.getSource()==bt3)
		{
					 
					String text=searchtf3.getText();
					if(text.length()==0)
					{     rsorter3.setRowFilter(null);  }
					rsorter3.setRowFilter(RowFilter.regexFilter(text));
		}

		if(ea.getSource()==bookstatus)
		{   	
			  String p=JOptionPane.showInputDialog(this,"Enter Book ID"); 
			 if(!p.isEmpty()){   BookStatus bs= new BookStatus(p); }
			else {	JOptionPane.showMessageDialog(this,"Text Field is Empty","Error",JOptionPane.ERROR_MESSAGE);}

		}
		
		if(ea.getSource()==studentrecord)
		{   	
			  String ps=JOptionPane.showInputDialog(this,"Enter User ID"); 
			 if(!ps.isEmpty()){   try{StudentRecord bss= new StudentRecord(ps);} catch(Exception e){  } }
			else {	JOptionPane.showMessageDialog(this,"Text Field is Empty","Error",JOptionPane.ERROR_MESSAGE);}

		}















		
		if(ea.getSource()==refresh||ea.getSource()==refresh2||ea.getSource()==refresh3||ea.getSource()==refresh4)
		{  try{          new Librarian(); dispose();      }
			catch(Exception e) { } }	
		if(ea.getSource()==logout)
		{  	new Login();  dispose();	}	
		if(ea.getSource()==update)
		{   	
			  up=JOptionPane.showInputDialog(this,"Enter Student ID"); 
			 int i=0;
			
				
			if(!up.isEmpty()){
			try
    				{

						Class.forName("oracle.jdbc.driver.OracleDriver");
			                               		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
						stm2=con.createStatement();
						rs2=stm2.executeQuery("Select * from student1");
							
							while (rs2.next())
							{
								if(up.equals(rs2.getString(1)))
								{
									i=1;
				
									break;
								}
							}

				if(i==1){

		
				p9=1;
				q2=1;
				useridtf1.setEditable(false);
				start.setText("Update start");
			panel2.add(start); 
			//setVisible(false); setVisible(true);
			rs2= stm2.executeQuery("SELECT *  FROM student1 WHERE USER_ID="+up+" ");	
					while(rs2.next())
					{	
					useridtf1.setText(rs2.getString(1));
					usernametf1.setText(rs2.getString(2));
					passwordtf1.setText(rs2.getString(3));
					phonetf1.setText(rs2.getString(4));
					emailidtf1.setText(rs2.getString(5));
						
					  }   
			
				con.close();	
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
			else{   JOptionPane.showMessageDialog(this,"TextField is Empty","Details",JOptionPane.ERROR_MESSAGE);}
			System.out.println(up);   }

			


		if(ea.getSource()==delete2)
		{   	
			  up2=JOptionPane.showInputDialog(this,"Enter Student ID"); 
			 int i22=0;
			
				
			if(!up2.isEmpty()){ System.out.println("0");
			try
    				{

						Class.forName("oracle.jdbc.driver.OracleDriver");
			                               		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
						stm2=con.createStatement();
						rs2=stm2.executeQuery("Select * from student1");
							
							while (rs2.next())
							{
								if(up2.equals(rs2.getString(1)))
								{
									i22=1;
				
									break;
								}
							}

				if(i22==1){
				//rs2= stm2.executeQuery("SELECT *  FROM student1 WHERE USER_ID="+up2+" ");
				String query = "delete from student1 where user_id = ?";
      				PreparedStatement preparedStmt = con.prepareStatement(query);
      				preparedStmt.setString(1, up2);
      				preparedStmt.execute();  
				 JOptionPane.showMessageDialog(this,"Delete Student Successful","Details",JOptionPane.INFORMATION_MESSAGE);			
				con.close();	
			
				}
				else{   
			JOptionPane.showMessageDialog(this,"Incorrect USER ID ","Error",JOptionPane.ERROR_MESSAGE);
					}
			                }
    				catch (Exception e)
    				{   JOptionPane.showMessageDialog(this,"11"+e,"Error",JOptionPane.ERROR_MESSAGE);
															 }
			}
			else{   JOptionPane.showMessageDialog(this,"TextField is Empty","Details",JOptionPane.ERROR_MESSAGE);}
			   }

 
	}

	/*public static void main(String []args)throws Exception
		{ 

		new Librarian();
		 }*/
}