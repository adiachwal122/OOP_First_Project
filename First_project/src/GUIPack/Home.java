package GUIPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKmlToCsv = new JLabel("KML to CSV");
		lblKmlToCsv.setBounds(42, 40, 170, 43);
		contentPane.add(lblKmlToCsv);
		lblKmlToCsv.setFont(new Font("Levenim MT", Font.PLAIN, 30));
		lblKmlToCsv.setForeground(UIManager.getColor("Button.background"));
		lblKmlToCsv.setBackground(UIManager.getColor("Button.background"));
		
		JLabel lblDevelopedByAdiel = new JLabel("Developed By: Adiel Matuf");
		lblDevelopedByAdiel.setFont(new Font("Levenim MT", Font.PLAIN, 18));
		lblDevelopedByAdiel.setForeground(UIManager.getColor("Button.background"));
		lblDevelopedByAdiel.setBounds(52, 80, 238, 26);
		contentPane.add(lblDevelopedByAdiel);
		
		JLabel lblAdiAchwal = new JLabel("Adi Achwal");
		lblAdiAchwal.setBounds(187, 100, 102, 26);
		contentPane.add(lblAdiAchwal);
		lblAdiAchwal.setForeground(SystemColor.menu);
		lblAdiAchwal.setFont(new Font("Levenim MT", Font.PLAIN, 18));
		
		JLabel lblYehudaNewmann = new JLabel("Yehuda Newmann");
		lblYehudaNewmann.setBounds(187, 120, 166, 26);
		contentPane.add(lblYehudaNewmann);
		lblYehudaNewmann.setForeground(SystemColor.menu);
		lblYehudaNewmann.setFont(new Font("Levenim MT", Font.PLAIN, 18));
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 224), new Color(255, 255, 224), null, null));
		panel.setBackground(new Color(51, 51, 51));
		panel.setBounds(0, 0, 365, 443);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Earth_logo = new JLabel("");
		Earth_logo.setBounds(-1427, -42, 3000, 3075);
		panel.add(Earth_logo);
		Earth_logo.setIcon(new ImageIcon(Home.class.getResource("/GUIPack/images/sky-earth-galaxy-universe.jpg")));
		
		Button button = new Button("Upload Wiggle Wifi CSV");
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(0, 0, 51));
		button.setFont(new Font("Calibri", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(444, 179, 219, 44);
		contentPane.add(button);
		
		Button Instraction_Button = new Button("Instraction");
		Instraction_Button.setForeground(Color.WHITE);
		Instraction_Button.setFont(new Font("Calibri", Font.PLAIN, 12));
		Instraction_Button.setBackground(new Color(0, 0, 51));
		Instraction_Button.setBounds(444, 229, 219, 44);
		contentPane.add(Instraction_Button);
		
		Button button_2 = new Button("Exit");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			button_2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int yesOrNo = JOptionPane.showConfirmDialog(null, "Do you want to close the application?", "Exit" ,JOptionPane.YES_NO_OPTION);
					switch (yesOrNo) {
					case 0:
						System.exit(0);
						break;

					default:
						break;
					}
				}
			});
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		button_2.setBackground(new Color(0, 0, 51));
		button_2.setBounds(444, 279, 219, 44);
		contentPane.add(button_2);
	}
}
