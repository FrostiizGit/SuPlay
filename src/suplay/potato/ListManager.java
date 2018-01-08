package suplay.potato;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ListManager {
	
	//private static ArrayList<String> songList = new ArrayList<String>();
	public static Object[][] data;
	private static int numOfSongs;
	public static int songIndex = 0;
	//private static ArrayList<String> songList = new ArrayList<String>();
	private static String[] songList = new String[20];
	
	public static JTable tableModel() {
		//String[] columnNames = {"Names", "Time", "Format"};
		DefaultTableModel modelTable = new DefaultTableModel();
	    JTable table = new JTable(modelTable);

	    modelTable.addColumn("Names");
	    modelTable.addColumn("Time");
	    modelTable.addColumn("Format");

		return table;
	}
	
	public static void addSongToList(File song, JTable table){
		TableModel currentModel = table.getModel();
		
		String songName = song.getName().substring(0 ,song.getName().lastIndexOf('.'));
		String songDuration = "8min9";
		String songFormat = song.getName().substring(song.getName().lastIndexOf("."));
		((DefaultTableModel) currentModel).addRow(new Object[] { songName, songDuration, songFormat });
		numOfSongs += 1;
		songList[numOfSongs - 1] = song.getAbsolutePath();
	}
	
	public static void playAllSongs() {
			TableModel cModel = Suplayer.songTable.getModel();
			//System.out.println(cModel.getValueAt(songIndex, 0));
			//System.out.println(cModel.getValueAt(songIndex, 2));
			System.out.println("Song: " + songList[songIndex]);
			System.out.println("SongIndex: " + songIndex + " NumOfSongs: " + numOfSongs);
			if(songIndex == numOfSongs) {
				songIndex = 0;
			}
			String fExt = (String) cModel.getValueAt(songIndex, 2);
			if (cModel.getValueAt(songIndex, 2).equals(".mp3")) {
				System.out.println("MP3");
				SoundManager.soundFormat(songList[songIndex], fExt);
				Suplayer.currentSongName.setText((String) cModel.getValueAt(songIndex, 0));
			} else if (cModel.getValueAt(songIndex, 2).equals(".wav")) {
				System.out.println("WAV");
				SoundManager.soundFormat(songList[songIndex], fExt);
				Suplayer.currentSongName.setText((String) cModel.getValueAt(songIndex, 0));
			} else {
				System.out.println("Error");
			}
			
		
	}
}
