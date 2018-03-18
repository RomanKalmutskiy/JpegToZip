package tasks1;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class FileList {

	private File file;
	GetFileExtension getExtention;
	GetFromEXIF getFromEXIF;
	Jpeg jpeg;
	Jpeg temp = new Jpeg();

	ArrayList<String> folders;
	ArrayList<String> files;
	ArrayList<Jpeg> jpegList;

	public FileList(File file, GetFromEXIF getFromEXIF, GetFileExtension getExtention, ArrayList<Jpeg> jpegList) {
		this.file = file;
		this.getFromEXIF = getFromEXIF;
		this.getExtention = getExtention;
	}

	public ArrayList<Jpeg> GetFileList() {
		String[] arr = file.list();

		ArrayList<String> folders = new ArrayList<String>();
		ArrayList<String> files = new ArrayList<String>();

		for (String temp : arr) {
			if (new File(file.getAbsolutePath() + "" + temp).isDirectory()) {
				folders.add(temp);
			} else {

				if (getFileExtension(temp).toLowerCase().equals("jpg")
						|| getFileExtension(temp).toLowerCase().equals("jpeg")) {

					files.add(temp);
				}
			}
		}

	//	Iterator<String> iFolder = folders.iterator();
	//	while (iFolder.hasNext()) {
			
	//	}

		Iterator<String> iFile = files.iterator();
		jpegList = new ArrayList<Jpeg>();
		while (iFile.hasNext()) {
			String name = iFile.next();
			File filePTH = new File("c:/WorkFolder/" + name);
			getFromEXIF.GetExifInformation(filePTH, name);

			jpegList.add(getFromEXIF.getJpeg());
		}

		Iterator<Jpeg> iJpegList = jpegList.iterator();

		while (iJpegList.hasNext()) {
			Jpeg jpeg = iJpegList.next();

		}

		Collections.sort(jpegList);
		return jpegList;
	}

	public String getFileExtension(String fileName) {

		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";

	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
