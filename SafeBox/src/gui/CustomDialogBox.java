package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;

public abstract class CustomDialogBox extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 641677878658751060L;
	
	private int width;
	private int height;
	
	BackgroundPanel dialogPanel;
	
	protected CustomDialogBox(StateManager sm, int w, int h, Color c1, Color c2){
		dialogPanel = new BackgroundPanel(MiscUtils.getBufferedGradImage(c1, c2, w, h, true));
		dialogPanel.setLayout(new BorderLayout());
		add(dialogPanel);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	protected void open(){
		setVisible(true);
		init();
	}
	
	protected void close(){
		setVisible(false);
	}
	
	protected abstract void init();
}
