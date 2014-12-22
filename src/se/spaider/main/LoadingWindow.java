package se.spaider.main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public abstract class LoadingWindow implements Runnable {
	
	protected JProgressBar bar = new JProgressBar();
	protected JFrame frame = new JFrame("Please Wait");
	protected JPanel panel = new JPanel();
	protected Thread thread = new Thread(this);
	protected JButton close = new JButton("Close");
	protected JLabel label = new JLabel("Please Wait...");
	protected int value = 0;
	
	
	public LoadingWindow(){
		frame.setSize(new Dimension(200,140));
		frame.setLocationRelativeTo(null);
		

		close.setEnabled(false);
		frame.add(panel);
		panel.add(label);
		panel.add(bar);
		panel.add(close);
		frame.setVisible(true);
		thread.start();
		
		close.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				
			}});
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(true){
				if(value < 100){
					value += 1;
					bar.setValue(value);
				}else{
					onDone();
					label.setText("Done!");
					close.setEnabled(true);
					thread.interrupt();
					thread.stop();
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	abstract void onDone();

}
