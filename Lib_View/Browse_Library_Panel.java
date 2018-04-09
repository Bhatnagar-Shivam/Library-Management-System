package Lib_View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Browse_Library_Panel extends JPanel {
	private Box mainBox, hBox1, hBox2, hBox3, hBox4, hBox5;

	private JLabel jlBookTable, jlFileTable;
	private JButton bOpenBook, bDeleteBook, bOpenFile, bDeleteFile, bSave , bSaveAndQuit;
	private JScrollPane spBookTable, spFileTable;
	private JTable tBooks , tFiles;
	private My_Table_Model model;
	
	String[] bookColumns = { "Title", "Author", "Price", "Serial Number" };
	String[] fileColumns = { "Sounds", "Images", "Videos" };
	String[][] bookData = { { " ", " ", " ", " " } };
	String[][] fileData = { { " ", " ", " " } };

	public Browse_Library_Panel() {
		super(new FlowLayout());
		intWidgets();
		addWidgets();
		setBackground(new Color(194, 230, 248));

	}

	private void intWidgets() 
	{
		mainBox = Box.createVerticalBox();

		hBox1 = Box.createHorizontalBox();
		hBox2 = Box.createHorizontalBox();
		hBox3 = Box.createHorizontalBox();
		hBox4 = Box.createHorizontalBox();
		hBox5 = Box.createHorizontalBox();
		
		jlBookTable= new JLabel("Showing all books in library");
		jlFileTable= new JLabel("Showing all Files in library");
		
		bOpenBook=new JButton("Open"); 
		bDeleteBook=new JButton("Delete"); 
		bOpenFile=new JButton("Open"); 
		bDeleteFile=new JButton("Delete"); 
		bSave=new JButton("Save"); 
		bSaveAndQuit=new JButton("Save And Quit"); 
		
		model=new My_Table_Model(bookData, bookColumns);
		tBooks = new JTable(model);
		tBooks.setPreferredScrollableViewportSize(new Dimension(428,120));
		tBooks.setFillsViewportHeight(true);
		tBooks.setAutoCreateRowSorter(true);
		tBooks.getTableHeader().setReorderingAllowed(false);
		tBooks.getColumnModel().getColumn(0).setPreferredWidth(200);
		tBooks.getColumnModel().getColumn(1).setPreferredWidth(250);
		
		model = new My_Table_Model(fileData,fileColumns);
		tFiles = new JTable(model);
		tFiles.setPreferredScrollableViewportSize(new Dimension(428,80));
		tFiles.setFillsViewportHeight(true);
		tFiles.setAutoCreateRowSorter(true);
		tFiles.getTableHeader().setReorderingAllowed(false);
		
		spBookTable = new JScrollPane(tBooks);
		spFileTable = new JScrollPane(tFiles);
		
		spBookTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spBookTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
		spFileTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spFileTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
	}

	private void addWidgets() 
	{
		hBox1.add(jlBookTable);
		hBox1.add(Box.createHorizontalStrut(40));
		hBox1.add(bDeleteBook);
		hBox1.add(bOpenBook);
		hBox2.add(spBookTable);
		hBox3.add(jlFileTable);
		hBox3.add(Box.createHorizontalStrut(60));
		hBox3.add(bDeleteFile);
		hBox3.add(bOpenFile);
		hBox4.add(spFileTable);
		hBox5.add(Box.createHorizontalStrut(185));
		hBox5.add(bSave);
		hBox5.add(bSaveAndQuit);
		
		mainBox.add(hBox1);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox2);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox3);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox4);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox5);
		
		add(mainBox);
		
	}
	
	public void ActionListener(ActionListener a) {
		bOpenBook.addActionListener(a);
		bDeleteBook.addActionListener(a);
		bOpenFile.addActionListener(a);
		bDeleteFile.addActionListener(a);
		bSave.addActionListener(a);
		bSaveAndQuit.addActionListener(a);
	}
	
public JButton getButtonOpenBook() {
	return bOpenBook;
	
}public JButton getButtonDeleteBook() {
	return bDeleteBook;
	
}
public JButton getButtonOpenFile() {
	return bOpenFile;
	
}
public JButton getButtonDeleteFile() {
	return bDeleteFile;
	
}public JButton getButtonSave() {
	return bSave;
	
}public JButton getButtonSaveAndQuit() {
	return bSaveAndQuit;
	
}

}
