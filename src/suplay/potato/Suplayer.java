package suplay.potato;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import javax.swing.JPanel;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Suplayer {
 /*
  * COLOR LIST
  *  pink: 240, 29, 100
  *  dark pink: 232, 12, 49
  *  
  */
	protected static int w = 1280;
	protected static int h = 720;
	private ArrayList<String> songList = new ArrayList<String>();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Suplayer window = new Suplayer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Suplayer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Color vPink = new Color(240, 29, 100);
		Color vDarkPink = new Color(196, 33, 82);
		Color vWhite = new Color(255, 255, 255);
		Color vMint = new Color(173, 20, 87);
		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.setTitle("Potato Music Player");
		
		frame.setIconImage(new ImageIcon("pepewut.png").getImage());;
		frame.setBackground(Color.DARK_GRAY);
		
		frame.setBounds(100, 100, Suplayer.w, Suplayer.h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// ########################## MENU PANEL ##########################
		JPanel menuPanel = new JPanel();
		menuPanel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuPanel.setBounds(0, 0, Suplayer.w, 40);
		frame.getContentPane().add(menuPanel);
		menuPanel.setLayout(null);
		menuPanel.setBackground(vPink);
		
		// ########################## FILE BUTTON ##########################
		JButton btnFile = new JButton("File");
		btnFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnFile.setBackground(vDarkPink);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnFile.setBackground(vPink);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(frame);
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
	               java.io.File file = fc.getSelectedFile();
	               System.out.println("File Selected :" + file.getName());
	               String fName = file.getName();
	               String fExt =  fName.substring(fName.lastIndexOf("."));
	               System.out.println(fExt);
	               if(fExt.equals(".wav")) {
	            	   songList.add(file.getAbsolutePath());
		               System.out.println(songList);
		               new Thread(new Runnable() {
		            	   // The wrapper thread is unnecessary, unless it blocks on the
		            	   // Clip finishing; see comments.
		            	     public void run() {
		            	       try {
		            	         Clip clip = AudioSystem.getClip();
		            	         AudioInputStream inputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
		            	         clip.open(inputStream);
		            	         clip.start(); 
		            	       } catch (Exception e) {
		            	         System.err.println(e.getMessage());
		            	       }
		            	     }
		            	   }).start();
	               } else if (fExt.equals(".mp3")) {
	            	   System.out.println("mp3");
	               } else {
	            	   System.out.println("Format non valid");
	               }
	            } else {
	            	System.out.println("Open command cancelled by user." );           
	            }      
			}
		});
		btnFile.setBorderPainted(false);
		btnFile.setBounds(0, 0, (Suplayer.w / 4), menuPanel.getHeight());
		btnFile.setBackground(vPink);
		menuPanel.add(btnFile);
		
		// ########################## EDIT BUTTON ##########################
		JButton btnEdit = new JButton("Edit");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEdit.setBackground(vDarkPink);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEdit.setBackground(vPink);
			}
		});
		btnEdit.setBorderPainted(false);
		btnEdit.setBounds((Suplayer.w / 4), 0, (Suplayer.w / 4), menuPanel.getHeight());
		btnEdit.setBackground(vPink);
		menuPanel.add(btnEdit);
		
		// ########################## VIEW BUTTON ##########################
		JButton btnView = new JButton("View");
		btnView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnView.setBackground(vDarkPink);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnView.setBackground(vPink);
			}
		});
		btnView.setBorderPainted(false);
		btnView.setBounds(2 * (Suplayer.w / 4), 0, (Suplayer.w / 4), menuPanel.getHeight());
		btnView.setBackground(vPink);
		menuPanel.add(btnView);
		
		// ########################## HELP BUTTON ##########################
		JButton btnHelp = new JButton("Help");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHelp.setBackground(vDarkPink);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHelp.setBackground(vPink);
			}
		});
		btnHelp.setBorderPainted(false);
		btnHelp.setBounds(3 * (Suplayer.w / 4), 0, (Suplayer.w / 4), menuPanel.getHeight());
		btnHelp.setBackground(vPink);
		menuPanel.add(btnHelp);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 660, (Suplayer.h - 40), 40);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// ########################## RESIZE EVENT ##########################
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				Suplayer.w = frame.getWidth();
				Suplayer.h = frame.getHeight();
				System.out.println("Width: " + Suplayer.w);
				System.out.println("Height: " + Suplayer.h);
				menuPanel.setBounds(0, 0, Suplayer.w, 40);
				btnFile.setBounds(0, 0, (Suplayer.w / 4), menuPanel.getHeight());
				btnEdit.setBounds((Suplayer.w / 4), 0, (Suplayer.w / 4), menuPanel.getHeight());
				btnView.setBounds(2 * (Suplayer.w / 4), 0, (Suplayer.w / 4), menuPanel.getHeight());
				btnHelp.setBounds(3 * (Suplayer.w / 4), 0, (Suplayer.w / 4), menuPanel.getHeight());
			}
		});
	}
}
