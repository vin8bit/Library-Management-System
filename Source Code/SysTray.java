import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SysTray
{
	public static void   Gui()
	{
		if(!SystemTray.isSupported())
		{  System.out.println("SystemTray is not Supported ");  return; } 
		
		SystemTray systray= SystemTray.getSystemTray();
		Toolkit  kit =  Toolkit.getDefaultToolkit();
		Image img=  kit.getImage("image/Red_book.png");
		PopupMenu pop= new PopupMenu();
		MenuItem item= new MenuItem("Library Login");
		item.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{  new Login(); }});
		pop.add(item);
		MenuItem item2= new MenuItem("Exit");
		item2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{   System.exit(0);  }});
		pop.add(item2);
		TrayIcon icon = new TrayIcon(img,"Library vk ",pop);
		icon.setImageAutoSize(true);
		try{ systray.add(icon); }
		catch(AWTException e)
		{    System.out.println("Cannot be added");  }
	}
	
/*public static void main(String []args)
	{
		SwingUtilities.invokeLater(new Runnable(){
		public void run(){  Gui(); } });
	}*/
}
		
		