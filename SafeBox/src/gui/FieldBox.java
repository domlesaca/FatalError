package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import core.Record;

public class FieldBox extends BackgroundPanel{
	
	private int index;
	private int BAR_HEIGHT = 60;

	private Border border;
	private int boarderWidth = 2;
	
	private static final int TEXT_SIZE = 20;
	
	
	public FieldBox( int x, int y, int width, int height, final int index, final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(Consts.ORANGE_PANEL_COLOUR_LIGHT, Consts.ORANGE_PANEL_COLOUR_DARK, width, height, true));
		this.index = index;
		final EditFieldDialog b = new EditFieldDialog(sm, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, 450, 220, index);
		b.setVisible(false);
		setSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout(0,0));
		this.setBorder(new EmptyBorder(10, 20, 10, 20));
		
		JPanel dataPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//----------------------Border---------------------
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, Consts.ORANGE_PANEL_COLOUR_BORDER);
		setBorder(border);
		setOpaque(true);
		setTransparentAdd(true);
		
		JLabel fieldName = new JLabel(((Record) sm.getESM().getFileSystemHandler().getCurrentRecord().getData()).getField(index).getName() + " :");
		fieldName.setFont(new Font("Tahoma", Font.BOLD, TEXT_SIZE));
		fieldName.setForeground(Color.BLACK);
		fieldName.setBackground(Color.WHITE);
		fieldName.setOpaque(false);
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1.0;
		c.gridy = 0;
		dataPanel.add(fieldName, c);
		
		JLabel fieldData = new JLabel("    " + ((Record) sm.getESM().getFileSystemHandler().getCurrentRecord().getData()).getField(index).getData());
		fieldData.setFont(new Font("Tahoma", Font.PLAIN, TEXT_SIZE));
		fieldData.setForeground(Color.BLACK);
		fieldData.setBackground(Color.WHITE);
		fieldData.setOpaque(false);
		fieldData.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				MiscUtils.setClipboard(((Record) sm.getESM().getFileSystemHandler().getCurrentRecord().getData()).getField(index).getData());
			}
		});
		c.gridy = 1;
		dataPanel.add(fieldData, c);
		
		CustomButton editButton = new CustomButton("", 0, 0, 30, 30);
		editButton.setImageFromFile("pen.png", true);
		editButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				b.setVisible(true);
			}
		});
		
		add(editButton, BorderLayout.EAST);
		add(dataPanel, BorderLayout.WEST);
		
	}
}
