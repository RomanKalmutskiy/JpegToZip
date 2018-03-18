package tasks1;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;


public class App {
	public static void main(String[] args) {

		
		File file = new File("c:/WorkFolder/");
		
		final GetFromEXIF getFromEXIF = new GetFromEXIF();
		
		final GetFileExtension getExtention = new GetFileExtension();
		
		final ArrayList<Jpeg> jpegList = new ArrayList<Jpeg>();
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Win win = new Win(getFromEXIF, getExtention, jpegList);
				win.setVisible(true);
				;
			}

		});
		
	}

}
