package Lib_View;

import javax.swing.table.DefaultTableModel;

public class My_Table_Model extends DefaultTableModel {

	
	private static final long serialVersionUID = 1L;
	public My_Table_Model(String[][] data,String[] columns) {
		super(data, columns);
	}
	@Override
	public boolean isCellEditable(int row, int column) {
		
		return false;
	}
	

}
