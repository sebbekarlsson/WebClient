package se.spaider.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BrowserWindow implements KeyListener {

	protected int WIDTH = 1080;
	protected int HEIGHT = WIDTH / 16 * 9;
	protected Dimension FRAMESIZE = new Dimension(WIDTH,HEIGHT);
	protected JFrame frame = new JFrame("PlainAccess Browser");
	protected JPanel panel = new JPanel();
	protected JTextField field_search = new JTextField();
	protected JButton button_search = new JButton("Go");
	protected JEditorPane editor_browser = new JEditorPane();
	protected JScrollPane editor_scroll = new JScrollPane(editor_browser);
	protected JLabel label_bottom = new JLabel("");
	
	public BrowserWindow(){
		frame.setSize(FRAMESIZE);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		
		field_search.addKeyListener(this);
		
		field_search.setPreferredSize(new Dimension(WIDTH-86,22));
		field_search.setFocusable(true);
		field_search.requestFocus();
		button_search.setPreferredSize(new Dimension(48,22));
		button_search.setFont(new Font(Font.SERIF,11,11));
		button_search.setBackground(Color.white);
		editor_browser.setPreferredSize(new Dimension(WIDTH-32,HEIGHT-100));
		editor_browser.setEditable(false);
		editor_browser.setContentType("text/html");
		editor_browser.setText("<html><h1>Gathering Information</h1>Please Wait..</html>");
		
		panel.setBackground(Color.WHITE);
		
		frame.add(panel);
		panel.add(field_search);
		panel.add(button_search);
		panel.add(editor_scroll);
		panel.add(label_bottom);
		
		frame.setVisible(true);
		
		button_search.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				sendHttpQuery();
			}});
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			sendHttpQuery();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void sendHttpQuery(){
		String request = field_search.getText();
		
		Main.output.println("httpquery,"+request+","+Main.id);
		field_search.setText("");
	}
}
