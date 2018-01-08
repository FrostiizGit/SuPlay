package suplay.potato;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class Suplayer {
 /*
  * COLOR LIST
  *  pink: 240, 29, 100
  *  dark pink: 232, 12, 49
  *  
  */
	protected static int w = 1280;
	protected static int h = 720;
	
	private JFrame frame;
	public static JTable songTable;
	public static JLabel currentSongName;

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
		//Color vWhite = new Color(255, 255, 255);
		Color vMint = new Color(26,188,156);
		
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
		menuPanel.setBounds(0, 0, 1280, 40);
		menuPanel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().add(menuPanel);
		menuPanel.setBackground(vPink);
		
		// ########################## VIEW BUTTON ##########################
		JButton btnView = new JButton("View");
		btnView.setBounds(640, 0, 315, 40);
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
		
		// ########################## FILE BUTTON ##########################
		JButton btnFile = new JButton("File");
		btnFile.setBounds(0, 0, 315, 40);
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
				//File filters for the FileChooser only .mp3 and .wav sounds
				FileFilter filterMp3 = new FileNameExtensionFilter("Music", "mp3", "wav");
				fc.setAcceptAllFileFilterUsed(false);
				fc.addChoosableFileFilter(filterMp3);
				
				int returnVal = fc.showOpenDialog(frame);
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
	               File file = fc.getSelectedFile();
	               System.out.println("File Selected :" + file.getName());
	               ListManager.addSongToList(file, songTable);
	               
	            } else {
	            	System.out.println("FileChooser canceled by user" );           
	            }      
			}
		});
		menuPanel.setLayout(null);
		btnFile.setBorderPainted(false);
		btnFile.setBackground(vPink);
		menuPanel.add(btnFile);
		
		// ########################## EDIT BUTTON ##########################
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(320, 0, 315, 40);
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
		btnEdit.setBackground(vPink);
		menuPanel.add(btnEdit);
		btnView.setBorderPainted(false);
		btnView.setBackground(vPink);
		menuPanel.add(btnView);
		
		// ########################## HELP BUTTON ##########################
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(960, 0, 320, 40);
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
		btnHelp.setBackground(vPink);
		menuPanel.add(btnHelp);
		
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBounds(0, 616, 1280, 76);
		controlPanel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(controlPanel);
		controlPanel.setLayout(null);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListManager.playAllSongs();
			}
		});
		btnPlay.setBackground(Color.DARK_GRAY);
		btnPlay.setForeground(new Color(26,188,156));
		btnPlay.setOpaque(true);
		btnPlay.setBorderPainted(false);
		btnPlay.setBounds(6, 6, 117, 29);
		controlPanel.add(btnPlay);
		
		currentSongName = new JLabel("Not Playing any song");
		currentSongName.setForeground(new Color(26,188,156));
		currentSongName.setBounds(366, 11, 275, 16);
		controlPanel.add(currentSongName);
		
		JScrollPane boardPanel = new JScrollPane();
		boardPanel.setBackground(Color.ORANGE);
		boardPanel.setBounds(0, 40, 1280, 577);
		frame.getContentPane().add(boardPanel);
		
		songTable = ListManager.tableModel();
		songTable.setGridColor(Color.ORANGE);
		songTable.setBackground(Color.ORANGE);
		TableColumn column = null;
		for (int i = 0; i < 3; i++) {
		    column = songTable.getColumnModel().getColumn(i);
		    if (i == 0) {
		        column.setPreferredWidth(1000); //third column is bigger
		    } else {
		        column.setPreferredWidth(140);
		    }
		}
		boardPanel.setViewportView(songTable);
		
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
