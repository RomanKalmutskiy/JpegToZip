package tasks1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class GetFromEXIF {

	File file;
	FileTime time;
	String size;
	String height;
	String width;
	Date date;
	String patch;
	Jpeg jpeg;
	String name;
	String extension;
	GetFileExtension getFileExtension;

	public GetFromEXIF() {

	}

	public GetFromEXIF(File file) {
		this.file = file;

	}

	public void GetExifInformation(File file, String name) {
		this.file = file;
		this.name = name;

		Metadata metadata = null;

		try {
			metadata = ImageMetadataReader.readMetadata(file);
		} catch (ImageProcessingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {

				if (tag.getTagName().equals("Image Height")) {

					height = tag.getDescription();
				}
				if (tag.getTagName().equals("Image Width")) {

					width = tag.getDescription();
				}
				if (tag.getTagName().equals("File Size")) {

					size = tag.getDescription();
				}

			}
			if (directory.hasErrors()) {
				for (String error : directory.getErrors()) {
					System.err.format("ERROR: %s", error);
				}
			}
		}
		extension = getFileExtension.getFileExtension(file);
		GetTime(file);
		getJpeg();

	}

	public void GetTime(File file) {

		Metadata metadataD = null;

		try {
			metadataD = ImageMetadataReader.readMetadata(file);
		} catch (ImageProcessingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		ExifSubIFDDirectory directory = metadataD.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

		if (directory != null) {
			date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);

			if (date != null) {
				FileTime timeD = FileTime.fromMillis(date.getTime());

				time = timeD;
			//	System.out.println("Time formate:=" + timeD);
			}
			;

		} else {
		//	System.out.println("date=null!!!");
			Path path = file.toPath();
			try {
				BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);

				time = attrs.creationTime();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	public Jpeg getJpeg() {

		Jpeg jpeg = new Jpeg(name, extension, time, size, height, width);
		// System.out.println(jpeg);
		return jpeg;
	}

	public FileTime getTime() {
		return time;
	}

	public String getSize() {
		return size;
	}

	public String getHeight() {
		return height;
	}

	public String getWidth() {
		return width;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
