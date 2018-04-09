package Lib_View;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.sun.prism.paint.Color;

public class Load_Screen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JLabel jlmessage;
	private JButton jbNew, jbLoad, jbExit;
	
	public Load_Screen(String title) {
		super(title);
		
		initWidgets();
		addWidgets();
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(320, 115);
		setResizable(false);
	}
	
	private void initWidgets() {
		panel = new JPanel(new FlowLayout());
		
		jlmessage = new JLabel("Would you  like to start a new library? or load one up? ");
		jbNew = new JButton("Start New Library");
		jbLoad = new JButton("Load Saved Library");
		jbExit = new JButton("Exit");
	}

	private void addWidgets() {
		panel.add(jlmessage);
		panel.add(jbLoad);
		panel.add(jbNew);
		panel.add(jbExit);
		
		//panel.setBackground(new Color(194, 230, 248));
		setContentPane(panel);
	}
	
	public void addActionListener(ActionListener l) {
		
		jbNew.addActionListener(l);
		jbLoad.addActionListener(l);
		jbExit.addActionListener(l);
		
	}
	
	public JButton getButtonLoad() {
		return jbLoad;
	}
	
	public JButton getButtonNew() {
		return jbNew;
	}
	
	public JButton getButtonExit() {
		
		return jbExit;
	}
}
