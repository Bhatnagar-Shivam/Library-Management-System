import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainSystem {

	static String fileName = null;
	static Library lib = new Library();
	static Scanner in = new Scanner(System.in);
	static Boolean running = true;

	public static void main(String[] args) {
		while (running) {
			System.out
					.println("\nEnter 0 for load a library."
							+ "\nEnter 1 for save and quit"
							+ "\nEnter 2 for list all books in library"
							+ "\nEnter 3 for add book to library"
							+ "\nEnter 4 for import and save video/image/music (vim) file to book"
							+ "\nEnter 5 for load and export vim file from book and play it");

			int answer = in.nextInt();
			switch (answer) {
			case 0:
				System.out.println("Enter the file name to load");
				loadScript(in.next());
				break;

			case 1:
				saveAndQuit();
				break;
			case 2:
				System.out.println(lib.toString());
				break;
			case 3:
				addBook();
				break;
			case 4:
				importOpenAndSave();
				break;
			case 5:
				loadOpenAndExport();
				break;
			}
		}
		System.exit(0);
	}

	private static void loadOpenAndExport() {
		// TODO Auto-generated method stub
		String vimName = null;
		String bookTitle = null;
		Book book;
		VIM vim = null;
		byte[] data = null;
		File file;
		FileOutputStream out = null;

		System.out.println("\nEnter book title: ");
		bookTitle = in.next();
		book = lib.getBookByName(bookTitle);
		if (book == null) {
			System.out.println("Book does not exist!");
		} else {
			System.out.println(book.toString());
			System.out.println("Enter the complete file name: ");
			vimName = in.next();
			vim = book.getVIMByName(vimName);
			if (vim == null){
				System.out.println("File does not exist!");
			} else{
				data = vim.getData();
				file = new File(vim.getName());
				try {
					out = new FileOutputStream(file);
					out.write(data);
					out.close();
					Desktop.getDesktop().open(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}

	private static void importOpenAndSave() {
		// TODO Auto-generated method stub
		JFileChooser chooser;
		FileFilter filter;
		FileInputStream fis = null;

		Book book;
		String bookTitle = null;
		VIM vim;
		File file = null;
		byte[] data = null;

		boolean stop = false;

		System.out.println("\nEnter book title to put the file on: ");
		bookTitle = in.next();
		book = lib.getBookByName(bookTitle);
		if (book == null) {
			System.out.println("Book does not exist!");
		} else {
			System.out.println("\nChoose your video/image/music file to add: ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			chooser = new JFileChooser();
			filter = new FileNameExtensionFilter("Video/Image/Music Files",
					"wav", "mp3", "avi", "mp4", "png", "jpeg");
			chooser.addChoosableFileFilter(filter);
			chooser.setFileFilter(filter);
			int resultCode = chooser.showOpenDialog(null);
			if (resultCode == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				data = new byte[(int) file.length()];
			} else {
				System.out.println("You cancelled addding a vim!");
				stop = true;
			}

			if (!stop) {
				try {
					fis = new FileInputStream(file);
					fis.read(data);
					fis.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				vim = new VIM(file.getName(), data);
				book.addVIM(vim);

				System.out.println("You have added " + file.getName()
						+ " to book " + bookTitle + "\n");
			}

		}
	}

	private static void addBook() {
		// TODO Auto-generated method stub
		int isbn;
		String title, author;
		double price;

		System.out.println("\nEnter Title: ");
		title = in.next();

		System.out.println("\nEnter Author: ");
		author = in.next();

		System.out.println("\nEnter ISBN: ");
		isbn = in.nextInt();

		System.out.println("\nEnter Price: ");
		price = in.nextDouble();

		Book b = new Book(isbn, title, author, price);
		lib.addBook(b);
	}

	private static void saveAndQuit() {
		// TODO Auto-generated method stub
		System.out.println("Enter file name: ");
		fileName = in.next() + ".ser";
		running = false;
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(lib);
			fos.close();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void loadScript(String name) {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		ObjectInputStream in = null;
		File file = new File(name + ".ser");
		if (file.exists()) {
			try {
				fis = new FileInputStream(file);
				in = new ObjectInputStream(fis);
				lib = (Library) in.readObject();
				fis.close();
				in.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("\nThe file does not exist!");
		}
	}

}
