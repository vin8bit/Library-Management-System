import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.regex.*;
import java.sql.*;
import java.util.*;
import java.io.*;
public class Student extends JFrame implements ActionListener
{
	DataBase k=new DataBase();
	String system;
	String systempass;
	JTabbedPane pn;
	JPanel panel7,panel8,panel9;
	JPanel bookPanel,bookPanel3;
	TableModel tmodel,tmodel3;
	JTable jtable,jtable3;
	TableRowSorter<TableModel> rsorter;
	TableRowSorter<TableModel> rsorter3;
	String columns[]={"Book ID","Book Name","Author","Publisher","Quantity"};
	String columns3[]={"Book ID","User ID","Issue Date","Return Date"};
	RowCount1  r1=new RowCount1();
	PreparedStatement stm;
	Statement stm2;
	ResultSet rs2;
	Connection con;
	JButton logout;
	JTextField searchtf,searchtf3;
	JButton bt,bt3;
	
	
	
	public Student() throws Exception
	{
		super("Student v 1.1");	
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
		
		setIconImage(new ImageIcon("image/Red_book.png").getImage());
		setSize(700,500);
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e){dispose(); }});
		pn = new JTabbedPane();
		pn.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		pn.setTabPlacement(JTabbedPane.LEFT);
		
		panel7 =new JPanel();
		panel7.setLayout(new BorderLayout());
		panel8 =new JPanel();
		panel8.setLayout(new BorderLayout());
		panel9 =new JPanel();
		panel9.setLayout(null);
		
		pn.addTab("View Book",new ImageIcon("image/Red_book.png"),panel7,"View Book");
		pn.addTab("View Iss.. Books",new ImageIcon("image/add.gif"),panel8,"View Issued Books");
		pn.addTab("Log Out",new ImageIcon("image/Exit.png"),panel9,"Log Out");
		
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
		searchtf=new JTextField(20);
		bt=new JButton("Search",new ImageIcon("image/search.png"));
		bt.addActionListener(this);
		bu.add(h9,BorderLayout.NORTH);
		bu.add(searchtf,BorderLayout.CENTER);
		bu.add(bt,BorderLayout.EAST);
		bookPanel.add(jspane,BorderLayout.CENTER);
		bookPanel.add(bu,BorderLayout.NORTH);
		panel7.add(bookPanel);
		
	
		
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
	
		
		//Log Out
		logout=new JButton("Log Out",new ImageIcon("image/Key.gif"));
		logout.addActionListener(this);
		logout.setLayout(null);
		logout.setBounds(200,200,130,40);
		panel9.add(logout);
		JLabel im9=new JLabel(new ImageIcon("image/a.jpg"));
		im9.setBounds(0,0,700,500);
		panel9.add(im9);
		add(pn);
		setLocationRelativeTo(null);
		setVisible(true);

		
	}
public void actionPerformed(ActionEvent ea)
		{
			if(ea.getSource()==bt)
			{
					String text=searchtf.getText();
					if(text.length()==0)
					{     rsorter.setRowFilter(null);  }
					rsorter.setRowFilter(RowFilter.regexFilter(text));
			}
			if(ea.getSource()==bt3)
			{
					 
					String text=searchtf3.getText();
					if(text.length()==0)
					{     rsorter3.setRowFilter(null);  }
					rsorter3.setRowFilter(RowFilter.regexFilter(text));
			}	
			if(ea.getSource()==logout)
			{  	new Login();  dispose();	}		 
		
		   }
	/*public static void main(String []args)  throws Exception
		{ new Student(); }*/
}