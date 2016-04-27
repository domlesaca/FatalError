package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
public abstract class MessageBoxState extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8244504120202183073L;
	protected String titleText;
	protected JLabel titleLabel = new JLabel();
	final static String cancel = "Cancel";
	//protected static int BAR_HEIGHT = 60;
	protected int BAR_WIDTH = 400;
	protected JPanel panel = new JPanel(new BorderLayout());
	protected JPanel titlePanel = new JPanel();
	protected JPanel textFieldPanel = new JPanel();
	protected JPanel buttonsPanel = new JPanel(new FlowLayout());
	protected static StateManager sm;
	protected static String initTextField;
	
	public MessageBoxState(){
		titlePanel.setBorder(new EmptyBorder(Consts.EMPTY_BORDER_TOP,0,0,0));
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, 24));
		titlePanel.add(titleLabel);
		titlePanel.setBackground(Consts.BLUE_PANEL_COLOUR_DARK);
		textFieldPanel.setBackground(Consts.BLUE_PANEL_COLOUR_DARK);
		buttonsPanel.setBackground(Consts.BLUE_PANEL_COLOUR_DARK);
		panel.add(titlePanel,BorderLayout.NORTH);
		panel.add(buttonsPanel,BorderLayout.SOUTH);
		add(panel);
	}
	
	protected void drawButton(CustomButton b){
		b.setGradientBackground(Consts.BUTTON_COLOUR_LIGHT,
				Consts.BUTTON_COLOUR_DARK, false);
		b.setBoarderDetails(Consts.BUTTON_COLOUR_BORDER, 2);
	}
	protected abstract void close();
	protected abstract void open();
	
}