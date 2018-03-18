package tasks1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Win extends JFrame {

	ArrayList<Jpeg> jpegSortList;
	DefaultTableModel model;
	private ArrayList<String> listDownload = null;
	JTextField zipName;
	JTextField pathFolder;
	CreatZip creatZip = new CreatZip();
	ArrayList<Jpeg> jpegList = new ArrayList<Jpeg>();
	String path;
	Boolean flag = true;
	GetFromEXIF getFromEXIF;
	GetFileExtension getExtention;
	JTable table;
	JScrollPane scroll;

	public Win(GetFromEXIF getFromEXIF, GetFileExtension getExtention, ArrayList<Jpeg> jpegList2) {
		this.getFromEXIF = getFromEXIF;
		this.getExtention = getExtention;

		// the form

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 850, 500);
		setTitle("Jpeg table");
		getContentPane().setLayout(null);

		// add scrollpane
		scroll = new JScrollPane();
		scroll.setBounds(70, 80, 700, 300);
		getContentPane().add(scroll);

		// the Table
		table = new JTable();
		scroll.setViewportView(table);

		JLabel foldLabel = new JLabel("Enter the path to folder:");
		foldLabel.setBounds(70, 380, 150, 30);
		getContentPane().add(foldLabel);

		pathFolder = new JTextField("c:/WorkFolder/");
		pathFolder.setBounds(250, 380, 520, 30);
		getContentPane().add(pathFolder);

		JLabel zipNameLabel = new JLabel("Enter name of Zip fail:");
		zipNameLabel.setBounds(70, 420, 150, 30);
		getContentPane().add(zipNameLabel);

		zipName = new JTextField("");
		zipName.setBounds(250, 420, 520, 30);
		getContentPane().add(zipName);

		// the model of our table
		model = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class; // name
				case 1:
					return String.class; // date
				case 2:
					return String.class; // size
				case 3:
					return String.class; // height
				case 4:
					return String.class; // width

				default:
					// cheack box
					return Boolean.class;

				}
			}
		};

		// ASSIGN the model to table
		table.setModel(model);

		model.addColumn("Name");
		model.addColumn("Date");
		model.addColumn("Size");
		model.addColumn("Height");
		model.addColumn("Width");
		model.addColumn("Select");

		// the row
		path = pathFolder.getText();
		File file = new File(path);
		FileList fileList = new FileList(file, getFromEXIF, getExtention, jpegList);
		jpegList = fileList.GetFileList();

		ArrayList<Jpeg> jpegSortList = jpegList;

		Iterator<Jpeg> iJpegSortList = jpegSortList.iterator();
		int i = 0;
		while (iJpegSortList.hasNext()) {

			Jpeg jpeg = iJpegSortList.next();
			model.addRow(new Object[0]);
			model.setValueAt(jpeg.getName(), i, 0);// name
			model.setValueAt(jpeg.getTime(), i, 1);// Time
			model.setValueAt(jpeg.getSize(), i, 2);// size
			model.setValueAt(jpeg.getHeight(), i, 3);// height
			model.setValueAt(jpeg.getWidth(), i, 4);// width
			model.setValueAt(false, i, 5);
			i++;

		}

		// obtain selected row
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {

			// private ArrayList<String> listDownload;

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				listDownload = new ArrayList<String>();
				// get selected row
				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(table.getValueAt(i, 5).toString());
					String col = table.getValueAt(i, 0).toString();
					// DISPLAY
					if (checked) {
						listDownload.add(col);
						// JOptionPane.showMessageDialog(null, col);
					}
				}
				printZip();
				creatZip.zipPath(pathFolder.getText(), zipName.getText(), listDownload);

			}
		}

		);
		this.listDownload = listDownload;

		// ADD button to form
		btnDownload.setBounds(70, 30, 150, 30);
		getContentPane().add(btnDownload);

		JButton btnSet = new JButton("Select All");

		// ADD button to Set
		btnSet.setBounds(250, 30, 150, 30);
		getContentPane().add(btnSet);
		btnSet.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// get selected row
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt(true, i, 5);
				}
			}
		}

		);

		// ADD button to update
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(450, 30, 150, 30);
		getContentPane().add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				setFlag(true);
				// System.out.println("Update"+flag);
				while (table.getRowCount() > 0) {
					model.removeRow(0);
				}

				update();

			}
		}

		);

	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Boolean getFlag() {
		return flag;
	}

	public ArrayList<String> getListDownload() {
		return listDownload;
	}

	public void printZip() {
		if (listDownload != null) {
			Iterator<String> iListDownload = listDownload.iterator();

			while (iListDownload.hasNext()) {
				String listDown = iListDownload.next();
				// System.out.println("Zip" + listDown);
			}
		}
	}

	public void update() {
		path = pathFolder.getText();
		File file = new File(path);
		FileList fileList = new FileList(file, getFromEXIF, getExtention, jpegList);
		jpegList = fileList.GetFileList();

		ArrayList<Jpeg> jpegSortList = jpegList;

		Iterator<Jpeg> iJpegSortList = jpegSortList.iterator();
		int i = 0;
		while (iJpegSortList.hasNext()) {

			Jpeg jpeg = iJpegSortList.next();
			model.addRow(new Object[0]);
			model.setValueAt(jpeg.getName(), i, 0);// name
			model.setValueAt(jpeg.getTime(), i, 1);// Time
			model.setValueAt(jpeg.getSize(), i, 2);// size
			model.setValueAt(jpeg.getHeight(), i, 3);// height
			model.setValueAt(jpeg.getWidth(), i, 4);// width
			model.setValueAt(false, i, 5);
			i++;

		}

	}
}
