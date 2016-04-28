package gui;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.EncryptedStorageManager;
import core.HintManager;
import core.WindowSizeManager;

public class StateManager extends JPanel{
	protected JFrame window;

	protected final String PASSWORD_STATE = "passwordState";
	protected final String MAIN_SCREEN_STATE = "mainScreenState";
	
	private core.EncryptedStorageManager eSM;
	private PasswordState passwordState;
	private MainScreenState mainScreenState;
	
	static JPanel cards;
	static CardLayout cl;
	
	private boolean successfullyDecrypted = false;
	
	PlainMessageDialog plainMessageDialog;
	
	boolean isSuccessfullyDecrypted() {
		return successfullyDecrypted;
	}

	void setSuccessfullyDecrypted(boolean successfullyDecrypted) {
		this.successfullyDecrypted = successfullyDecrypted;
	}
	
	void showPlainMessage(String message){
		plainMessageDialog.setMessage(message);
		plainMessageDialog.open();
	}

	/**
	 * @param window
	 */
	protected StateManager(final JFrame window){
		this.window = window;
		eSM = new EncryptedStorageManager();
		//Initialize cards
		//cards = new JPanel(new CardLayout());
		setLayout(new CardLayout());
		cl = (CardLayout) getLayout();
		
		populateStates();
		
		window.getContentPane().add(this);
		cl.show(this, PASSWORD_STATE);
		
		if(!eSM.fileSystemExists()){
			System.out.println("This is the first time running");
		}else{
			System.out.println("A file system Exists");
		}
		
		plainMessageDialog = new PlainMessageDialog(this, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, 450, 200, "");
		
		init();
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
		    @Override
		    public void run(){
		    	if (successfullyDecrypted){
			    	System.out.println("Saving...");
			        eSM.saveFileSystemHandler();
			        WindowSizeManager.setSize(window.getWidth(), window.getHeight());
			        System.out.println("Saved.");
		    	}
		    }
		});
		
		
	}
	
	/**
	 * adds all states
	 */
	private void populateStates(){
		
		passwordState = new PasswordState(this);
		mainScreenState = new MainScreenState(this);
		
		add(mainScreenState, MAIN_SCREEN_STATE);
		add(passwordState, PASSWORD_STATE);
	}
	
	protected void setState(String stateName){
		cl.show(this, stateName);
	}
	
	protected EncryptedStorageManager getESM(){
		return eSM;
	}
	
	protected void init(){
		mainScreenState.init();
		passwordState.init();
	}
	
	protected void update(){
		mainScreenState.update();
		passwordState.update();
	}
	

}
