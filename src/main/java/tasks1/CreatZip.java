package tasks1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreatZip {

	private String path;
	private String zipName;
	private ArrayList<String> listDownload;

	public CreatZip(String path, String zipName, ArrayList<String> listDownload) {

		this.path = path;
		this.zipName = zipName;
		this.listDownload = listDownload;
	}

	CreatZip() {

	};

	public void zipPath(String path, String zipName, ArrayList<String> listDownload) {

		try {
			
			FileOutputStream fos = new FileOutputStream(path + zipName + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			Iterator<String> ilistDownload = listDownload.iterator();
			while (ilistDownload.hasNext()) {
				String nameFile = ilistDownload.next();
				add(zos, nameFile, path + nameFile);
			}
			
			zos.close();
			fos.close();

		} catch (Exception e) {
		}
	}

	public void zipPath() {

		try {
			FileOutputStream fos = new FileOutputStream("C:/WorkFolder/samples.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			String file1 = "Desert - копия.jpg";
			String file2 = "Hydrangeas.jpg";
			String patch1 = "C:/WorkFolder/Desert - копия.jpg";
			String parch2 = "C:/WorkFolder/Hydrangeas.jpg";

			add(zos, file1, patch1);
			add(zos, file2, parch2);

			zos.close();
			fos.close();

		} catch (Exception e) {
		}
	}

	public void add(ZipOutputStream zos, String fileName, String patch) throws IOException {
		File file = new File(patch);
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileName);
		try {
			zos.putNextEntry(zipEntry);
		} catch (IOException e) {
			System.out.println("zipEntry error");
			e.printStackTrace();
		}
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}
		zos.closeEntry();
		fis.close();

	}
}
