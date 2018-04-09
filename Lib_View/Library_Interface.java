package Lib_View;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Library_Interface extends JFrame
{
	private Add_Book_Panel abp;
	private Browse_Library_Panel blp;
	private JTabbedPane jtb;
	private String filler;
	
	public Library_Interface(String title) 
	{
		super(title);
		jtb= new JTabbedPane();
		abp = new Add_Book_Panel();
		blp=new Browse_Library_Panel();
		
		filler="      ";
		jtb.addTab(filler +filler+filler+ filler + "Add Book" + filler + filler + filler + filler , abp);
		jtb.addTab(filler +filler +filler + "Browse Library" + filler + filler +filler+"   ",blp);
		add(jtb);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(460,480);
		setResizable(false);
		
	}
	
	public Add_Book_Panel   getAdd_Book_Panel() 
	{
		return abp;
	}
	
	public Browse_Library_Panel   getBrowse_Library_Panel() 
	{
		return blp;
	}
	
	public  JTabbedPane  getTabbedPane() 
	{
		return jtb;
	}
	
	public String getFiller() {return filler;}
}
	
	
