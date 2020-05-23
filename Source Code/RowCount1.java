import java.sql.*;
import java.io.*;

class RowCount1
{ 
	String system;
	String systempass;
	 Connection con;
	 Statement stm;
	 ResultSet rs;
	 String names,roll_no1;
	 int column,h;
	 String n[]=new String[4];
	int rowCount,rowCount2,rowCount3,rowCount4;
	int r;
	public RowCount1() throws Exception
	{          try{
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
	}
	public  int call()throws Exception
	{
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
		            
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm= con.createStatement(); 
			rs= stm.executeQuery("SELECT COUNT(*)  FROM book ");
			rs.next();
			int rowCount=rs.getInt(1);	
			rs.close();
			stm.close();
			con.close();
			
			System.out.println(rowCount);
			return rowCount;
	}
	
	public  int call1()throws Exception
	{
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
		            
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm= con.createStatement(); 
			rs= stm.executeQuery("SELECT COUNT(*)  FROM student1 ");
			rs.next();
			int rowCount1=rs.getInt(1);	
			rs.close();
			stm.close();
			con.close();
			return rowCount1;
	}


	public  int call3()throws Exception
	{
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
		            
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm= con.createStatement(); 
			rs= stm.executeQuery("SELECT COUNT(*)  FROM issue_book ");
			rs.next();
			int rowCount3=rs.getInt(1);	
			rs.close();
			stm.close();
			con.close();
			return rowCount3;
	}
	
	public  int call4()throws Exception
	{
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
		            
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",system,systempass);
			stm= con.createStatement(); 
			rs= stm.executeQuery("SELECT COUNT(*)  FROM librarian ");
			rs.next();
			int rowCount4=rs.getInt(1);	
			rs.close();
			stm.close();
			con.close();
			return rowCount4;
	}
public static void main(String []args) throws Exception
	{
		RowCount1 d=new RowCount1();	
	}
}	





















