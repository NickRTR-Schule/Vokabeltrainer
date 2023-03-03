package benutzerschnitstelle;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;

public class GUI extends JFrame
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GUI frame = new GUI();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel Container = new JPanel();
		Container.setBackground(Color.GRAY);
		Container.setBounds(116, 11, 732, 497);
		contentPane.add(Container);

		JPanel panel_home = new JPanel();
		panel_home.setBackground(Color.ORANGE);
		
		JPanel panel_Kategorie = new JPanel();
		panel_Kategorie.setLayout(null);
		panel_Kategorie.setBackground(Color.ORANGE);
		
		JLabel lblKategorie = new JLabel("Kategorie");
		lblKategorie.setBounds(272, 34, 138, 40);
		panel_Kategorie.add(lblKategorie);


		JPanel panel_dashboard = new JPanel();
		panel_dashboard.setBackground(Color.CYAN);
		panel_dashboard.setVisible(false);
		panel_home.setLayout(null);

		JLabel lblHome = new JLabel("Home");
		lblHome.setBounds(314, 11, 46, 14);
		panel_home.add(lblHome);

		JLabel lbldashboard = new JLabel("Dashboard");
		panel_dashboard.add(lbldashboard);
		Container.setLayout(new CardLayout(0, 0));
		Container.add(panel_home, "name_336841862459700");
		Container.add(panel_Kategorie, "name_336841886354300");
		Container.add(panel_dashboard, "name_336841899066200");

		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (panel_dashboard.isVisible())
				{
					panel_dashboard.setVisible(false);
				}
				else
				{
					panel_dashboard.setVisible(true);
					panel_home.setVisible(false);

				}
			}
		});
		btnDashboard.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		btnDashboard.setBounds(10, 45, 89, 23);
		contentPane.add(btnDashboard);

		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
//				 panel_home.setVisible(!panel_home.isVisible());
				if (panel_home.isVisible())
				{
					panel_home.setVisible(false);
				}
				else
				{
					panel_home.setVisible(true);
					panel_dashboard.setVisible(false);
				}
			}
		});
		btnHome.setBounds(10, 11, 89, 23);
		contentPane.add(btnHome);
		
		JButton btnTest = new JButton("Kategorie");
		btnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (panel_Kategorie.isVisible())
				{
					panel_Kategorie.setVisible(false);
				}
				else
				{
					panel_Kategorie.setVisible(true);
					panel_home.setVisible(false);
					panel_dashboard.setVisible(false);
				}
			}
		});
		btnTest.setBounds(10, 84, 89, 23);
		contentPane.add(btnTest);
	}
}
