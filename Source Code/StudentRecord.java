import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.regex.*;
import java.sql.*;
import java.io.*;

public class StudentRecord extends JFrame
{
		String system;
		String systempass;
		DataBase k=new DataBase();
		JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
		JLabel lr1,lr2,lr3,lr4,lr5,lr6,lr7,lr8,lr9,l1r0,lr11,lr12;
		TableModel tmodel,tmodel1,tmodel3;
		JTable jtable3;
		TableRowSorter<TableModel> rsorter3;
		String columns3[]={"Book ID","User ID","Issue Date","Return Date"};
		ResultSet rs,rs2;
		PreparedStatement stm;
		Statement stm2;
		Connection con;
		String up="";

		public StudentRecord( String s1) throws Exception
		{
			super("Student Reccord");
			up=s1;
			setSize(400,550);
			setIconImage(new ImageIcon("image/Red_book.png").getImage());
			setLocation(400,100);
			//setLayout(new BorderLayout());
			lr1= new JLabel();	
			lr2= new JLabel();	
			lr3= new JLabel();
			lr4= new JLabel();
			lr1.setText("None");
			lr2.setText("None");
			lr3.setText("None");
			lr4.setText("None");

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
			rs= stm2.executeQuery("SELECT *  FROM student1 WHERE USER_ID="+up+" ");	
					while(rs.next())
					{				
					lr1.setText(rs.getString(1));
					lr2.setText(rs.getString(2));
					lr3.setText(rs.getString(4));
					lr4.setText(rs.getString(5));

					 }	
			con.close();
			}
		catch(Exception e)
			{      }








			
			JPanel p1= new JPanel();
			p1.setLayout(null);
			p1.setBounds(0,0,400,250);
			//p1.setBackground(Color.green);
			add(p1);
			JPanel p2= new JPanel();
			p2.setLayout(new BorderLayout());
			p2.setBounds(0,200,400,200);
			//p2.setBackground(Color.red);
			add(p2);
			
			JLabel r= new JLabel("Student Record ");
			Font ft= new Font("Arial Rounded MT",Font.BOLD,15);
			r.setFont(ft);
			r.setBounds(150,0,200,20);
			p1.add(r);

			l1= new JLabel("User Id :");	
			l1.setBounds(100,30,100,20);
			p1.add(l1);
			l2= new JLabel("User Name :");	
			l2.setBounds(100,70,100,20);
			p1.add(l2);
			l3= new JLabel("Phone No :");	
			l3.setBounds(100,110,100,20);
			p1.add(l3);
			l4= new JLabel("Email ID :");	
			l4.setBounds(100,150,100,20);
			p1.add(l4);
			l5= new JLabel("Issue Book Details");
			Font ft1= new Font("Arial Rounded MT",Font.BOLD,15);
			l5.setFont(ft1);	
			l5.setBounds(150,220,250,20);
			p1.add(l5);


			
			lr1.setBounds(200,30,200,20);
			p1.add(lr1);
				
			lr2.setBounds(200,70,200,20);
			p1.add(lr2);
				
			lr3.setBounds(200,110,200,20);
			p1.add(lr3);
				
			lr4.setBounds(200,150,200,20);
			p1.add(lr4);



			 tmodel3= new DefaultTableModel(k.array3,columns3);
			 jtable3=new JTable(tmodel3);
			 rsorter3 = new TableRowSorter<TableModel>(tmodel3);
			JScrollPane jspane3=new JScrollPane(jtable3);
			rsorter3.setRowFilter(RowFilter.regexFilter(up));
			jspane3.setPreferredSize(new Dimension(400, 250));
			jtable3.setRowSorter(rsorter3);
			p2.add(jspane3,BorderLayout.SOUTH);
	



			//setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
		}
public static void main(String []args) throws Exception
	{  //new StudentRecord("101");
	 }
}