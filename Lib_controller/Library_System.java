package Lib_Controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Lib_Model.Book;
import Lib_Model.Library;
import Lib_Model.VIM;
import Lib_View.Add_Book_Panel;
import Lib_View.Browse_Library_Panel;
import Lib_View.Library_Interface;
import Lib_View.Load_Screen;

public class Library_System implements ChangeListener, ActionListener {
	private Library_Interface screen;
	private Add_Book_Panel abp;
	private Browse_Library_Panel blp;

	private JFileChooser chooser;
	private FileFilter filter;
	private int resultCode;
	private File vimFile, saveFile;
	private Library lib;
	private Book book;
	private List<VIM> vimCache;

	private FileInputStream fis;
	private FileOutputStream fos;
	private ObjectOutputStream out;
	private String fileName;
	private boolean exit;
	private String[] validFileTypes = { ".wav", ".mp3", ".avi", ".mp4", ".png", ".jpeg" };
	private String validFileTypeReminder;

	public Library_System() {
		initEventAttributes();
		screen = new Library_Interface("Library Management System");
		abp = screen.getAdd_Book_Panel();
		blp = screen.getBrowse_Library_Panel();
		screen.getTabbedPane().addChangeListener(this);
		abp.addActionListener(this);
		blp.ActionListener(this);

		Load_Screen ls = new Load_Screen("Welcome !");
		ls.addActionListener(this);
		ls.setVisible(true);
		
		
	}

	private void initEventAttributes() {
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Video/Image/Music Files", "wav", "mp3", "avi", "mp4", "png", "jpeg");
		chooser.addChoosableFileFilter(filter);
		chooser.setFileFilter(filter);
		lib = new Library();
		exit=false;

		vimCache = new ArrayList<VIM>();
		vimFile = null;
		saveFile = null;
		validFileTypeReminder = "Valid file types end with .wav.mp3.avi.mp4.png.jpeg";

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == abp.getButtonBrowse()) {
			openChooserAndSetTheVIMFile();
		} else if (event.getSource() == abp.getButtonAddFile()) {
			addVimFileTovimCache();

		} else if (event.getSource() == abp.getButtonAddBook()) {
			addVimFilesInvimCacheToBookAndAddBookToLibrary();
            reloadDataBook();
            reloadDataFile();
		} else if (event.getSource() == abp.getButtonListAllBooks()) {
			listAllBooksInLibrary();

		} else if (event.getSource() == abp.getButtonSave()) {
			save();

		} else if (event.getSource() == abp.getButtonSaveAndQuit()) {
			openBook();
			saveAndQuit();

		} else if (event.getSource() == blp.getButtonOpenBook()) {

		}

		else if (event.getSource() == blp.getButtonDeleteBook()) {

		} else if (event.getSource() == blp.getButtonOpenFile()) {
			openFile();

		} else if (event.getSource() == blp.getButtonDeleteFile()) {

		} else if (event.getSource() == blp.getButtonSave()) {
			save();

		} else if (event.getSource() == blp.getButtonSaveAndQuit()) { // SaveAndQuit In Browse Library tab
			saveAndQuit();
		} else if (event.getSource() == ls.getButtonLoad()){
			// clear the data inside the tables if any
			reloadDataBook(); 
	        reloadDataFile();
			loadLibrary();
	     chooser.setFileFilter(filter);
	     
		} else if (event.getSource() == ls.getButtonNew()){
		
		  screen.setVisible(true);
		} else if (event.getSource() == ls.getButtonExit()){
		   System.exit(0);	
		}
	}	
			private void openFile() {
				int row, column;
				
				VIM v;
				String fileName;
				File file;
				row = ((JTable) blp.getFileTable()).getSelectedRow();
				column = ((JTable) blp.getFileTable()).getSelectedColumn();
				fileName= = ((JTable) blp.getFileTable()).getValueAt(row, column).toString();
				
				
				v = book.getVIMByName(fileName);
	
				try {
				    file =  new File(v.getName());
				    fos = new FileOutputStream(file);
				    fos.write(v.getData());
				    fos.close();
					
					Desktop.getDesktop().open(file);
				} catch (IOException e) {
					e.printStackTrace();
				}				
	}

			private void openBook() {
				
		int row, column;
		String isbn;
		
		
		row = ((JTable) blp.getBookTable()).getSelectedRow();
		column = 3;
		isbn = ((JTable) blp.getBookTable()).getValueAt(row, column).toString();
		book = lib.getBookByISBN(isbn);
		
		dataFile = book.toStringVectorFiles();
		reloadDataFile();
	}

			private void loadLibrary() {
			chooser.setFileFilter(filter2);
			resultCode = chooser.showOpenDialog(screen);
			if(resultCode == JFileChooser.APPROVE_OPTION) {
				libFile = chooser.getSelectedFile();
				try {
					fis = new FileInputStream(libFile);
					in = new ObjectInputStream(fis);
					lib = (Library) in.readObject();
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				  ls.dispose();
				  
				  // load the book table
				  reloadDataBook();
				 
				screen.setVisible(true);
			}
			
		}
	private void reloadDataBook() {
		
		  
		while ((MyTableModel) blp.getBookTable().getModel()).getRowCount() > 0){
			((MyTableModel) blp.getBookTable().getModel()).removeRow(0);
		}
			 dataBook = lib.toStringVector();
			 for(int i=0; i<dataBook.length; i++) {
				 
				 ((MyTableModel) blp.getBookTable().getModel()).addRow(dataBook[i]);
		  }
			
		}
	
	private void reloadDataFile() {
		while ((MyTableModel) blp.getBookTable().getModel()).getRowcount() > 0) {
			
			((MyTableModel) blp.getBookTable().getModel()).removeRow(0);
	}
		if(datafile !=null)
			for(int i = 0; i< dataFile.length; i++) {
				((MyTableModel) blp.getBookTable().getModel()).insertRow(i,dataFile[i]);
}
	}
	private void saveAndQuit() {
		save();
		if(exit)
			System.exit(0);
			}

	private void save() {
		fileName = JOptionPane.showInputDialog(screen, "enter file name to save", "Save", JOptionPane.INFORMATION_MESSAGE);
		if (fileName != null) {
			if (!fileName.trim().contentEquals(" ")) {
				FileOutputStream fos = null;
				ObjectOutputStream out = null;
				try {
					saveFile = new File(fileName.trim() + ".ser");
					if (!saveFile.exists()) {
						fos = new FileOutputStream(saveFile);
						out = new ObjectOutputStream(fos);
						out.writeObject(lib);
						fos.close();
						out.close();
						exit=true;
					} else {
						int resultCode=JOptionPane.showConfirmDialog(screen, "filename already exist Overwrite it", "warning",
								JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if(resultCode==JOptionPane.YES_OPTION) {
							fos = new FileOutputStream(saveFile);
							out = new ObjectOutputStream(fos);
							out.writeObject(lib);
							fos.close();
							out.close();
							exit=true;
							
						}
						else {
							abp.getTextAreaLog().append("\n> Save cancelled");
							exit=false;
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				abp.getTextAreaLog().append("\n> Save cancelled.");
				exit=false;

			}
		} else {
			abp.getTextAreaLog().append("\n > Save cancelled");
			exit=false;
		}

	}

	private void listAllBooksInLibrary() {
		abp.getTextAreaLog().setText(" > Listing all books in library...");
		abp.getTextAreaLog().append("\n> " + lib.toString());
	}

	private void addVimFilesInvimCacheToBookAndAddBookToLibrary() {
		boolean ISBNAlreadyExist =false;
		boolean AllFieldsAreFilled=false;
		int isbn=0;
		double price=0.0;
		Book b;
		
		if(!abp.getTextFieldIsbn().getText().trim().contentEquals("") &&
				!abp.getTextFieldTitle().getText().trim().contentEquals("") &&
				!abp.getTextFieldAuthor().getText().trim().contentEquals("") &&
				!abp.getTextFieldPrice().getText().trim().contentEquals("") )
		{ AllFieldsAreFilled=true;}
			
		if(AllFieldsAreFilled) {
		try {
	 isbn = Integer.parseInt(abp.getTextFieldIsbn().getText().trim());
	 price=Double.parseDouble(abp.getTextFieldPrice().getText().trim());
	
	 ISBNAlreadyExist=lib.doesISBNAlreadyExist(isbn); 
		if(ISBNAlreadyExist) 
	{
			JOptionPane.showMessageDialog(screen,isbn + "Already exists!!\n please use another isbn!" );
	}else {
		b=new Book(isbn,abp.getTextFieldTitle().getText().trim(),abp.getTextFieldAuthor().getText().trim(),price);
	
		for(int i=0;i<vimCache.size();i++)
	{
			b.addVIM(vimCache.get(i));
	}
	
	lib.addBook(b);
	abp.getTextFieldIsbn().getText().setText(""); 
	abp.getTextFieldTitle().getText().setText("") ;
	abp.getTextFieldAuthor().getText().setText("") ;
	abp.getTextFieldPrice().getText().setText(""); )
	abp.getTextAreaLog().append("\n"+b.getTitle()+"has been added to the library!");
		
	 vimCache = new ArrayList<VIM>();
	 
		}catch(NumberFormatException e) 
		{
			JOptionPane.showMessageDialog(screen,"Isbn or price is not a number!" );
			
		} 
		
			
			
		}
		} else {
				JOptionPane.showMessageDialog(screen,"Please fill out all non optional fields" );
			}
		
			
			}
		}
		

	private void addVimFileTovimCache() {
		if(vimFile!=null) {
			
		for (int i = 0; i < validFileTypes.length; i++) {
			if (abp.getTextFieldFile().getText().trim().endsWith(validFileTypes[i])) {
				o byte[] data; data = new byte[(int) vimFile.length()];
				try {
					fis = new FileInputStream(vimFile);
					fis.read(data);
					fis.close();

				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(screen, "File not found!");
 
				} catch (IOException e) {
					JOptionPane.showMessageDialog(screen, "Error reading file!");

				}

				VIM v = new VIM(abp.getTextFieldFile().getText().trim(), data);
				vimCache.add(v);
				vimFile=null;
				abp.getTextAreaLog().append("\n> " + abp.getTextFieldFile().getText().trim() + "is ready to be added to book.");
				abp.getTextFieldFile().setText("");
				
				return;

			}
		}
		
		} else {
			JOptionPane.showMessageDialog(screen,"something went wrong!\n please browse again your file");
		}
			
	}
	}

	private AbstractButton getTextFieldIsbn() {
		// TODO Auto-generated method stub
		return null;
	}

	private void openChooserAndSetTheVIMFile() {
		resultCode = chooser.showOpenDialog(screen);
		if (resultCode == JFileChooser.APPROVE_OPTION) {
			vimFile = chooser.getSelectedFile();
			abp.getTextFieldFile().setText(vimFile.getName());
		}

	}

	{
		// from add book tab to browse library tab

		/*
		 * if(screen.getTabbedPane().getSelectedIndex() == 1) {
		 * screen.getTabbedPane().setTitleAt(1, screen.getFiller() + screen.getFiller()
		 * + "  Browse Library   " + screen.getFiller()); screen.setSize(360,440); }
		 * else {
		 * 
		 * screen.getTabbedPane().setTitleAt(1, screen.getFiller() + "Browse Library"
		 * +screen.getFiller()+"   "); screen.setSize(320,640); }
		 */

	}

	public void stateChanged(ChangeEvent e) {

	}

}
