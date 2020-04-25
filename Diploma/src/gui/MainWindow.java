package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FilenameUtils;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import data_processing.JsonProcessing;
import saving_projects.ProjectSaving;

import java.awt.Font;

public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private JsonProcessing jsonProcessing;
	private String file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setTitle("Export Json Schema Versions");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainWindow() {
		Init();
	}

	/**
	 * Create the frame.
	 */
	public void Init() {
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jsonLabel = new JLabel("{ JSON : \"JavaScript Object Notation\" }");
		jsonLabel.setForeground(Color.WHITE);
		jsonLabel.setBackground(Color.BLACK);
		jsonLabel.setFont(jsonLabel.getFont().deriveFont(jsonLabel.getFont().getSize() + 13f));
		jsonLabel.setBounds(92, 44, 498, 231);
		jsonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(jsonLabel);
		
		JButton btnChooseFile = new JButton("Upload File");
		btnChooseFile.setContentAreaFilled(false);
		btnChooseFile.setBounds(102, 271, 210, 65);
		contentPane.add(btnChooseFile);
		try {
	 		btnChooseFile.setBorder(new EmptyBorder(0, 0, 0, 0));
    		Icon icon = new ImageIcon("images/upload_file.png");
    		btnChooseFile.setIcon(icon);
  		} catch (Exception e) {
  			System.out.println(e);
  		}
		btnChooseFile.addActionListener(this);
		
		JButton btnViewFile = new JButton("View File");
		btnViewFile.setContentAreaFilled(false);
		btnViewFile.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnViewFile.setBounds(365, 271, 225, 65);
		contentPane.add(btnViewFile);
		try {
			btnViewFile.setBorder(new EmptyBorder(0, 0, 0, 0));
    		Icon icon = new ImageIcon("images/view_versions.png");
    		btnViewFile.setIcon(icon);
  		} catch (Exception e) {
  			System.out.println(e);
  		}
		btnViewFile.addActionListener(this);
		
		JButton btnSaveFile = new JButton("Save Project");
		btnSaveFile.setContentAreaFilled(false);
		btnSaveFile.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSaveFile.setBounds(102, 392, 210, 65);
		contentPane.add(btnSaveFile);
		try {
			btnSaveFile.setBorder(new EmptyBorder(0, 0, 0, 0));
    		Icon icon = new ImageIcon("images/save_project.png");
    		btnSaveFile.setIcon(icon);
  		} catch (Exception e) {
  			System.out.println(e);
  		}
		btnSaveFile.addActionListener(this);
		
		JButton btnUploadProject = new JButton("Upload Project");
		btnUploadProject.setContentAreaFilled(false);
		btnUploadProject.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnUploadProject.setBounds(364, 392, 225, 65);
		contentPane.add(btnUploadProject);
		try {
			btnUploadProject.setBorder(new EmptyBorder(0, 0, 0, 0));
    		Icon icon = new ImageIcon("images/upload_project.png");
    		btnUploadProject.setIcon(icon);
  		} catch (Exception e) {
  			System.out.println(e);
  		}
		btnUploadProject.addActionListener(this);
		
		JLabel lblRights = new JLabel("\u00A9 2020 Siozos Thomas All Rights Reserved");
		lblRights.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 14));
		lblRights.setHorizontalAlignment(SwingConstants.CENTER);
		lblRights.setForeground(Color.WHITE);
		lblRights.setBounds(102, 568, 498, 32);
		contentPane.add(lblRights);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("images/back.png"));
		lblNewLabel.setBounds(0, -22, 734, 761);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String choice = e.getActionCommand();
		JFileChooser fc = new JFileChooser();
		int returnVal;
		switch (choice) {
		case "Upload File":
			returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fc.getSelectedFile();
	            file = selectedFile.getAbsolutePath();
	            String extension = FilenameUtils.getExtension(file);
	            if (!extension.equals("json")) {
	            	JOptionPane.showMessageDialog(this,
						    "File extension should be .json, try again",
						    "File Extension Error",
						    JOptionPane.WARNING_MESSAGE);
	            } else {
		            jsonProcessing = new JsonProcessing();
					jsonProcessing.processingJsonFile(file);
	            }
	        } else {
	            System.out.println("Open file canceled by user...");
	        }
			break;
		case "View File":
			if (jsonProcessing == null) {
				JOptionPane.showMessageDialog(this,
					    "You didn't upload file...",
					    "Upload File Error",
					    JOptionPane.WARNING_MESSAGE);
			} else {
				File htmlFile = new File("index.html");
				try {
					Desktop.getDesktop().browse(htmlFile.toURI());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		case "Save Project":
			if (jsonProcessing == null) {
				JOptionPane.showMessageDialog(this,
					    "You didn't upload file...",
					    "Upload File Error",
					    JOptionPane.WARNING_MESSAGE);
			} else {
				String projectName = JOptionPane.showInputDialog(this,
						"Give a name for your project: ");
				ProjectSaving projectSaving = new ProjectSaving();
				if (projectSaving.createProject(projectName)) {
					JOptionPane.showMessageDialog(this,
							"Your project saved succesfully...");
				} else {
					JOptionPane.showMessageDialog(this,
							"Your project didn't save...");
				}
			}
			break;
		case "Upload Project":
			JOptionPane.showMessageDialog(this,
					"Choose your project directory and then choose " +
						"the 'index.html' file");
			fc = new JFileChooser();
			returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fc.getSelectedFile();
	            if (!selectedFile.getName().equals("index.html")) {
	            	JOptionPane.showMessageDialog(this,
						    "The selected file should be the 'index.html' file",
						    "Not Valid File Error",
						    JOptionPane.WARNING_MESSAGE);
	            } else {
	            	File htmlFile = new File(selectedFile.getName());
	            	try {
						Desktop.getDesktop().browse(htmlFile.toURI());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        } else {
	            System.out.println("Open file canceled by user...");
	        }
			break;
		default:
			System.out.println("Not valid choice...");
			break;
		}
	}
}
