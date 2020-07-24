package JCA;
import java.awt.*;
import java.lang.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
public class project22 implements ActionListener
{ 
	private enum Actions {
	    b1,
	    b2,
	    b3,
	    b4
	  }
	static JTextField jt1=new JTextField("Description",200);
	static JButton b1= new JButton("View");
	static JButton b2= new JButton("Add");
	static JButton b3= new JButton("Modify");
	static JButton b4= new JButton("Delete");
	JFrame frame1;
	
	static String[] columnNames = {"SR", "Name", "Date", "Time"};
	static JTable table;
	public void jd()
	{
		frame1 = new JFrame("All events in database");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setLayout(new BorderLayout()); 
		frame1.setLocationRelativeTo(null);
		DefaultTableModel model=new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(model); 
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","hanuman7");
			System.out.println("Connected!!");
			
			Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM event11");
	         
	         while (rs.next()) {
	            int id = rs.getInt("sr");
	            String name = rs.getString("desc1");
	            String dat = rs.getString("date1");
	            String tim = rs.getString("time1");
	            model.addRow(new Object[]{id, name, dat, tim});
	            
	         }
	         
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		frame1.add(scroll);
		frame1.setVisible(true);
		frame1.setSize(400,400);
	}
	public void viewbydate()
	{
		jd();
		JFrame frame11 = new JFrame("All events in database");
		frame11.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame11.setLayout(new BorderLayout()); 
		frame11.setLocationRelativeTo(null);
		DefaultTableModel model=new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(model); 
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll1 = new JScrollPane(table);
		scroll1.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","hanuman7");
			String eve22 = JOptionPane.showInputDialog(frame11, "Starting date in YYYY-MM-DD format");
			String eve1 = JOptionPane.showInputDialog(frame11, "Ending date in YYYY-MM-DD format");
			int k=Integer.parseInt(eve22.substring(0,4)+eve22.substring(5,7)+eve22.substring(8));
			int k1=Integer.parseInt(eve1.substring(0,4)+eve1.substring(5,7)+eve1.substring(8));
			Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM event11");
	         
	         while (rs.next()) {
	        	String t=rs.getString("time1");
	        	if(k<=Integer.parseInt(t.substring(0,4)+t.substring(5,7)+t.substring(8)) && Integer.parseInt(t.substring(0,4)+t.substring(5,7)+t.substring(8))<k1)
	            
	        	{int id = rs.getInt("sr");
	            String name = rs.getString("desc1");
	            String dat = rs.getString("date1");
	            String tim = rs.getString("time1");
	            model.addRow(new Object[]{id, name, dat, tim});}
	            
	         }
	        JOptionPane.showMessageDialog(frame11, "Your Preferencial view.");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		frame11.add(scroll1);
		frame11.setVisible(true);
		frame11.setSize(400,400);
	} 
	
	public void add()
	{
		try {
			jd();
			Class.forName("com.mysql.jdbc.Driver");
			Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","hanuman7");
			String eve22 = JOptionPane.showInputDialog(frame1, "Assign an id to event that does not exists already");
			int c11=Integer.parseInt(eve22);
			String eve1 = JOptionPane.showInputDialog(frame1, "Enter a description to event");
			String eve2 = JOptionPane.showInputDialog(frame1, "Enter date of event in YYYY-MM-DD format");
			String eve3 = JOptionPane.showInputDialog(frame1, "Enter time of event in 24 hour format");
			PreparedStatement pstmt=con.prepareStatement("insert into event11 values("+c11+","+eve1+","+eve2+","+eve3+");");
			pstmt.executeUpdate();
	        jd(); 
	        JOptionPane.showMessageDialog(frame1, "Row Added.");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	public void modify()
	{
		try {
			jd();
			Class.forName("com.mysql.jdbc.Driver");
			Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","hanuman7");
			String eve22 = JOptionPane.showInputDialog(frame1, "Select id to event that you want to modify");
			int c11=Integer.parseInt(eve22);
			String eve1 = JOptionPane.showInputDialog(frame1, "Enter a description to event");
			String eve2 = JOptionPane.showInputDialog(frame1, "Enter date of event in YYYY-MM-DD format");
			String eve3 = JOptionPane.showInputDialog(frame1, "Enter time of event in 24 hour format");
			PreparedStatement pstmt=con.prepareStatement("update event11 set desc1="+eve1+",date1="+eve2+",time1="+eve3+" where sr="+c11+";");
			pstmt.executeUpdate();
	        jd(); 
	        JOptionPane.showMessageDialog(frame1, "Row Modified.");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	public void del()
	{
		try {
			jd();
			String eve22 = JOptionPane.showInputDialog(frame1, "Enter id of event you want to delete");
			int c11=Integer.parseInt(eve22);
			Class.forName("com.mysql.jdbc.Driver");
			Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","hanuman7");
			PreparedStatement pstmt=con.prepareStatement("DELETE FROM event11 WHERE sr="+c11+";");
			pstmt.executeUpdate();
	        jd(); 
	        JOptionPane.showMessageDialog(frame1, "Row Deleted.");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public static void main(String []aks)
	{
		project22 instance=new project22();
		
		JFrame jf=new JFrame("Event Manager");
		jf.setSize(500,600);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setBackground(Color.orange);
		jf.setLayout(null);
		jf.setResizable(false);
		JLabel l=new JLabel("Event Guide");
		l.setFont(new Font("Serif",1,24));
		l.setForeground(Color.blue);
		jf.add(l);
		l.setBounds(200,100, 400, 50);
		JFrame frame12 = new JFrame("Upcoming events");
		frame12.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame12.setLayout(new BorderLayout()); 
		frame12.setLocationRelativeTo(null);
		DefaultTableModel model=new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(model); 
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll1 = new JScrollPane(table);
		scroll1.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","hanuman7");
			
			Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM event11");
	         String p=LocalDate.now().toString();
	         String p1=LocalDate.now().plusDays(2).toString();
	         while (rs.next()) {
	        	String t=rs.getString("time1");
	        	
	        	if(Integer.parseInt(p.substring(0,4)+p.substring(5,7)+p.substring(8))<=Integer.parseInt(t.substring(0,4)+t.substring(5,7)+t.substring(8)) && Integer.parseInt(t.substring(0,4)+t.substring(5,7)+t.substring(8))<Integer.parseInt(p1.substring(0,4)+p1.substring(5,7)+p1.substring(8)))
	            
	        	{int id = rs.getInt("sr");
	            String name = rs.getString("desc1");
	            String dat = rs.getString("date1");
	            String tim = rs.getString("time1");
	            model.addRow(new Object[]{id, name, dat, tim});
	            
	         }

			con.close();
		}} catch (Exception e) {
			e.printStackTrace();
			
		}
frame12.add(scroll1);
frame12.setVisible(true);
frame12.setSize(400,400);
		b1.setActionCommand(Actions.b1.name());
	    b1.addActionListener(instance);
	    jf.add(b1);
	    b1.setBounds(100, 200, 280, 50); b1.setBackground(Color.yellow);b1.setFont(new Font("Serif",1,16));
	    b2.setActionCommand(Actions.b2.name());
	    b2.addActionListener(instance);
	    jf.add(b2);b2.setBounds(100, 300, 280, 50); b2.setBackground(Color.magenta);b2.setFont(new Font("Serif",1,16));
	    b3.setActionCommand(Actions.b3.name());
	    b3.addActionListener(instance);
		jf.add(b3);b3.setBounds(100, 400, 280, 50); b3.setBackground(Color.green);b3.setFont(new Font("Serif",1,16));
		b4.setActionCommand(Actions.b4.name());
	    b4.addActionListener(instance);
		jf.add(b4);b4.setBounds(100, 500, 280, 50); b4.setBackground(Color.red);b4.setFont(new Font("Serif",1,16));
		
		jf.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == Actions.b1.name()) 
		viewbydate();
		else if (e.getActionCommand() == Actions.b2.name()) 
		add();
		else if (e.getActionCommand() == Actions.b3.name()) 
		modify();
		else if (e.getActionCommand() == Actions.b4.name()) 
		del();
	}
	

}
