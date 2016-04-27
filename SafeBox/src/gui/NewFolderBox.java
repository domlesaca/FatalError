package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewFolderBox extends ModifyFolderBox {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7873243537744491713L;
	private final String createRecord = "Create Record";
	private final String createFolder = "Create Folder";
	private final String titleString = "Name of Record/Folder";
	
	
	public NewFolderBox(final StateManager sm) {
		super();
		titleText = "Name your folder/record";
		button1Text = createFolder;
		button2Text = createRecord;
		state = sm;
		setStrings(button1Text,button2Text,titleText, titleText);
		init();
	}
	
	@Override
	protected void resetBox(){
		this.setVisible(false);
		this.textField.setText(titleString);
	}

	private boolean checkForValidText(String text){
		if(text.equals(titleString) || "".equals(text) || text.equals(" ")){
			return false;
		}
		else{
			return true;
		}
	}
	
	private void notValidText(StateManager sm){
		JOptionPane.showMessageDialog(sm.window, "Not a valid name.", null, JOptionPane.PLAIN_MESSAGE);
	}

	private void createRecord(String name, StateManager sm){
		sm.getESM().getFileSystemHandler().createRecord(sm.getESM().getFileSystemHandler().getCurrent(), name);
		sm.update();
	}
	
	private void createFolder(String name, StateManager sm){
		sm.getESM().getFileSystemHandler().createFolder(sm.getESM().getFileSystemHandler().getCurrent(), name);
		sm.update();
	}

	@Override
	protected void button1Action() {
		if(checkForValidText(textField.getText())){
			createFolder(textField.getText(), state);
			resetBox();
		}
		else{
			notValidText(state);
			return;
		}
	}

	@Override
	protected void button2Action() {
		if(checkForValidText(textField.getText())){
			createRecord(textField.getText(), state);
			resetBox();
		}
		else{
			notValidText(state);
			return;
		}
	}

	@Override
	protected void textFieldAction() {
		if (textField.getText().equals(titleString)) {
			textField.setText("");
		}
	}


}
