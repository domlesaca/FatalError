package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import core.Node;
import core.Record;

	

public class RecordDisplay extends BackgroundPanel {
	private static final int DISPLAY_WIDTH = 250;
	private Border border;
	private int boarderWidth = 3;
	
	private StateManager sm;
	
	private GridBagConstraints c = new GridBagConstraints();
	private GridBagConstraints g = new GridBagConstraints();
	
	private JPanel toolBar;
	private JPanel fViewer;
	private JPanel fieldPanel;
	private JScrollPane scrollPane;
	private RecordToolBar recordToolBar;
	private CustomButton addFieldButton;
	
	private Node currentRecord;
	
	protected RecordDisplay(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(MiscUtils.ORANGE_PANEL_COLOUR_LIGHT, MiscUtils.ORANGE_PANEL_COLOUR_DARK, DISPLAY_WIDTH, sm.window.getHeight(), true));
		this.sm = sm;
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, MiscUtils.ORANGE_PANEL_COLOUR_DARK);
		setBorder(border);
		setOpaque(true);
		
		setLayout(new GridBagLayout());
		
		toolBar = new JPanel(new BorderLayout(5,5));
		fViewer = new JPanel(new BorderLayout());
		fieldPanel = new JPanel(new GridBagLayout());
		scrollPane = new JScrollPane(fViewer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);
		recordToolBar = new RecordToolBar(sm, DISPLAY_WIDTH, 40);
		
		
		addFieldButton = new CustomButton("Add Field", 0, 0, 100, 30);
		addFieldButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		addFieldButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		
		fViewer.add(fieldPanel, BorderLayout.NORTH);
		fViewer.add(addFieldButton, BorderLayout.CENTER);
		
		
		
		setTransparentAdd(true);
		g.weightx = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.ipady = 10;
		g.gridx = 0;
		g.gridy = 0;
		add(recordToolBar, g);
		g.weightx = 1;
		g.weighty = 1;
		g.fill = GridBagConstraints.BOTH;
		g.gridx = 0;
		g.gridy = 1;
		add(scrollPane, g);
		
	}
	
	protected void init(){
		setVisible(false);
	}
	
	protected void update(){
		currentRecord = sm.getESM().getFileSystemHandler().getCurrentRecord();
		if(currentRecord != null){
			setVisible(true);
			recordToolBar.update();
			fieldPanel.removeAll();
			fieldPanel.repaint();
			
			///-------------------------------------------------------------
			
			Record recordData = (Record)currentRecord.getData();
			if (recordData.isRecord()){
				FieldBox fb;
				for(int i = 0; i < recordData.getFields().size(); i ++){
					fb = new FieldBox(0,0,600, 100, i, sm);
					fb.setAlignmentY(TOP_ALIGNMENT);
					c.weightx = 1;
					c.fill = GridBagConstraints.HORIZONTAL;
					c.ipady = 10;
					c.gridx = 0;
					c.gridy = i;
					fieldPanel.add(fb, c);
					fieldPanel.revalidate();
					fieldPanel.repaint();
				}
			}

			//-------------------------------------------------------------
			
			revalidate();
			repaint();
		}
	}
	
}


