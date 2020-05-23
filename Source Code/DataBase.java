import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
public class DataBase
{
	
	String system;
	String systempass;
	Connection con;
	Statement stm;
	ResultSet rs;
	int i=0,k=0,l=0,p=0,y=0;
	int i1=0,k1=0,l1=0,p1=0,y1=0;
	int i3=0,k3=0,l3=0,p3=0;
	int i4=0,k4=0,l4=0,p4=0,y4=0,q4=0,w4=0;

	RowCount1 f=new RowCount1();
	int g=5+f.call();
	int g1=5+f.call1();
	int g3=5+f.call3();
	int g4=5+f.call4();
	Object array[][] = new Object[g][10];
	Object array2[][] = new Object[g1][10];
	Object array3[][] = new Object[g3][10];
	Object array4[][] = new Object[g4][10];
	public DataBase() throws Exception
	{
		try{
				FileInputStream file3=new  FileInputStream("username.txt");
				byte []by12= new byte[50];
				file3.read(by12);
				system= new String(by12,0,50);
				FileInputStream file4=new  FileInputStream("password.txt");
				byte []by22= new byte[50];
				file4.read(by22);
				systempass= new String(by22,0,50);
			}
		catch(IOException io){  }
		//System.out.println(g);
		//array[0] = new Object[g];
		//array[1] = new Object[g];
		data();
		data2();
		data3();
		data4();	
	}
	public void  data() throws Exception
		{	
			try
			{Class.forName("oracle.jdbc.driver.OracleDriver");
		            
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm= con.createStatement(); 
			rs= stm.executeQuery("SELECT BOOK_ID,BOOK_NAME,AUTHOR,PUBLISHER,QUANTITY  FROM book ");	
			
			while(rs.next())
			{   
				array[i++][0]=rs.getObject("BOOK_ID");
				array[k++][1]=rs.getObject("BOOK_NAME");
				array[l++][2]=rs.getObject("AUTHOR");
				array[p++][3]=rs.getObject("PUBLISHER");
				array[y++][4]=rs.getObject("QUANTITY");


				
				
			}      
		con.close();	System.out.println("AVSK");
				
			}
		catch(SQLException e)
		{   	JOptionPane.showMessageDialog((Component) null,e,"Error",JOptionPane.ERROR_MESSAGE);
			 System.out.println("Unable to load Driver"); } 
		}


	public void  data2() throws Exception
		{	
			try
			{Class.forName("oracle.jdbc.driver.OracleDriver");
		            
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm= con.createStatement(); 
			rs= stm.executeQuery("SELECT USER_ID,USER_NAME,USER_PASSWORD,USER_PHONENO,USER_EMAILID  FROM student1 ");	
			
			while(rs.next())
			{   
				array2[i1++][0]=rs.getObject("USER_ID");
				array2[k1++][1]=rs.getObject("USER_NAME");
				array2[l1++][2]=rs.getObject("USER_PASSWORD");
				array2[p1++][3]=rs.getObject("USER_PHONENO");
				array2[y1++][4]=rs.getObject("USER_EMAILID");


				
				
			}      
		con.close();	System.out.println("AVSK");
				
			}
		catch(SQLException e)
		{	JOptionPane.showMessageDialog((Component) null,e,"Error",JOptionPane.ERROR_MESSAGE);   
		 System.out.println("Unable to load Driver"); } 
		}

	public void  data3() throws Exception
		{	
			try
			{Class.forName("oracle.jdbc.driver.OracleDriver");
		            
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm= con.createStatement(); 
			rs= stm.executeQuery("SELECT BOOK_ID1,USER_ID1,ISSUE_DATE1,RETURN_DATE1  FROM issue_book ");	
			
			while(rs.next())
			{   
				array3[i3++][0]=rs.getObject("BOOK_ID1");
				array3[k3++][1]=rs.getObject("USER_ID1");
				array3[l3++][2]=rs.getObject("ISSUE_DATE1");
				array3[p3++][3]=rs.getObject("RETURN_DATE1");
			}      
		con.close();	System.out.println("AVSK");
				
			}
		catch(SQLException e)
		{   JOptionPane.showMessageDialog((Component) null,e,"Error",JOptionPane.ERROR_MESSAGE);
			 System.out.println("Unable to load Driver"); } 
		}

	public void  data4() throws Exception
		{	
			try
			{Class.forName("oracle.jdbc.driver.OracleDriver");
		            
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm= con.createStatement(); 
			rs= stm.executeQuery("SELECT NAMEL,USERIDL,PASSWORDL,EMAILIDL,ADDRESSL,CITYL,CONTACTNOL  FROM librarian ");	
			
			while(rs.next())
			{   
				array4[i4++][0]=rs.getObject("NAMEL");
				array4[k4++][1]=rs.getObject("USERIDL");
				array4[l4++][2]=rs.getObject("PASSWORDL");
				array4[p4++][3]=rs.getObject("EMAILIDL");
				array4[y4++][4]=rs.getObject("ADDRESSL");
				array4[q4++][5]=rs.getObject("CITYL");
				array4[w4++][6]=rs.getObject("CONTACTNOL");
			}      
		con.close();	System.out.println("AVSK");
				
			}
		catch(SQLException e)
		{    	JOptionPane.showMessageDialog((Component) null,e,"Error",JOptionPane.ERROR_MESSAGE);
			System.out.println("Unable to load Driver"); } 
		}

public static void main(String []args) throws Exception
	{ new DataBase(); }
}