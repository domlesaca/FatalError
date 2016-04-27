package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
public class AddRecordFolder extends MessageBoxState {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7623013096298220204L;
	private final JTextField textField = new JTextField();
	private final String initTextField = "Name";
	private final CustomButton cancelButton = new CustomButton("Cancel", 0, 0, Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);
	private final CustomButton recordButton = new CustomButton("Create Record", 0, 0, Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);
	private final CustomButton folderButton = new CustomButton("Create Folder", 0, 0, Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);
	public AddRecordFolder(StateManager state){
		super();
		sm = state;
		textField.setText(initTextField);
		titleLabel.setText(Consts.addRecordFolderTitle);
		setSize(450,180);
		drawButton(cancelButton);
		drawButton(recordButton);
		drawButton(folderButton);
		buttonsPanel.add(cancelButton);
		buttonsPanel.add(folderButton);
		buttonsPanel.add(recordButton);
		textFieldPanel.add(textField, BorderLayout.CENTER);
		textField.setPreferredSize(new Dimension(350, Consts.CUSTOMBOX_HEIGHT1 /4));
		textFieldPanel.setPreferredSize(new Dimension(450, Consts.CUSTOMBOX_HEIGHT1 /3));
		panel.add(textFieldPanel, BorderLayout.CENTER);
		
		cancelButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
		recordButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
		folderButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textFieldAction();
			}
		});
	}
	
	@Override
	protected void close() {
		textField.setText(null);
		setVisible(false);
		//sm.update();
	}

	@Override
	protected void open() {
		textField.setText(initTextField);
		setVisible(true);
	}
	
	protected void textFieldAction(){
		if(textField.getText().equals(initTextField)){
			textField.setText(null);
		}
	}
	
	public static void main(String[] args){
		AddRecordFolder test = new AddRecordFolder(s);
		test.setVisible(true);
		return;
	}

}
