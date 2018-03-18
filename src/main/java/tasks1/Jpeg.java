package tasks1;

import java.nio.file.attribute.FileTime;
import java.util.Comparator;
import java.util.Date;

public class Jpeg implements Comparable<Jpeg>{
	
	
	private String name;
	private String extension;
	private FileTime time;
	private String size;
	private String height;
	private String width;
	
	
	public Jpeg(FileTime time) {
		this.time=time;
	}
	
public Jpeg() {
		
	}
	
	public Jpeg(String name, String extension, FileTime time, String size, String height, String width) {
		this.name = name;
		this.extension = extension;
		this.time = time;
		this.size = size;
		this.height = height;
		this.width = width;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}

	public FileTime getTime() {
		return time;
	}

	public void setTime(FileTime time) {
		this.time = time;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "Jpeg [name=" + name + ", extension=" + extension + ", time=" + time + ", size=" + size + ", height="
				+ height + ", width=" + width + "]"+"\n";
	}

	public int compareTo(Jpeg o) {
		int compare = o.time.compareTo(time);
		return compare;
	}


}
