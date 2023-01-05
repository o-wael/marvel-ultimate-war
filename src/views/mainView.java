package views;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class mainView extends JFrame implements ActionListener, MouseListener {

	private static String p1;
	private static String p2;
	
	public Font customFont1 = Font.createFont(Font.TRUETYPE_FONT, new File("src//Avengeance-E1zj.otf")).deriveFont(75f);
	public Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("src//Avengeance-E1zj.otf")).deriveFont(45f);

	private JPanel buttonPanel;
	private JPanel prev;

	private JLabel mainScreen;
	private JLabel instruction;

	private JButton playButton;
	private JButton exit;
	private JButton howToPlay;
	private JButton previous;

	public mainView() throws UnsupportedAudioFileException, IOException, LineUnavailableException, FontFormatException {

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(customFont1);
	    ge.registerFont(customFont2);

		ImageIcon logo = new ImageIcon("src/logo.png");
		this.setIconImage(logo.getImage());
		this.setTitle("Marvel Ultimate War");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();

		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(null);

		mainScreen = new JLabel();

		ImageIcon v = new ImageIcon("src//Marvel.png");
		Image scaleImageV = v.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		v = new ImageIcon(scaleImageV);

		mainScreen.setIcon(v);
		mainScreen.setLayout(new BorderLayout());

		playButton = new JButton();
		playButton.setText("PLAY");
		playButton.setForeground(Color.red);
		playButton.setFont(customFont1);

		playButton.setOpaque(false);
		playButton.addActionListener(this);
		playButton.setFocusable(false);

		playButton.setBorder(null);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		playButton.setFocusPainted(false);

		playButton.setBounds((int) ((width / 2) - 118), height / 2 - 100, 200, 150);
		buttonPanel.add(playButton);

		exit = new JButton();
		exit.setBackground(Color.red);
		exit.setText("EXIT");
		exit.setFont(customFont2);

		exit.setFocusable(false);
		exit.addActionListener(this);
		exit.setOpaque(false);

		exit.setBorder(null);
		exit.setForeground(Color.red);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);

		exit.setBounds((int) ((width / 2) - 100), (int) ((height / 2) + (height / 10.8)), 150, 80);
		buttonPanel.add(exit);

		howToPlay = new JButton();
		howToPlay.setBackground(Color.red);
		howToPlay.setText("How to play");
		howToPlay.setFont(customFont2);

		howToPlay.setFocusable(false);
		howToPlay.addActionListener(this);
		howToPlay.setOpaque(false);

		howToPlay.setBorder(null);
		howToPlay.setForeground(Color.red);
		howToPlay.setContentAreaFilled(false);
		howToPlay.setBorderPainted(false);
		howToPlay.setFocusPainted(false);

		howToPlay.setBounds((width / 2) - 135, (int) ((height / 2) + (height / 7.2)), 220, 150);
		buttonPanel.add(howToPlay);

		buttonPanel.setBounds(width / 8, height / 5, 100, 100);

		mainScreen.add(buttonPanel);
		this.add(mainScreen);

		this.setVisible(true);
		this.validate();
	}

	public void EnterNames() throws IOException {

		p1 = JOptionPane.showInputDialog("Enter first player's Name:");
		
		if(p1.length() > 10 ) {
			JOptionPane.showMessageDialog(this, "Player names should have a maximum of 10 characters", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		p2 = JOptionPane.showInputDialog("Enter Second player's Name:");
		
		if(p2.length() > 10 ) {
			JOptionPane.showMessageDialog(this, "Player names should have a maximum of 10 characters", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (p1 == null || p2 == null || p1.length() == 0 || p2.length() == 0) {
			JOptionPane.showMessageDialog(this, "You haven't entered your name", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		new ChampionsView();

		this.dispose();
	}

	public void howToPlay() {

		buttonPanel.setVisible(false);
		mainScreen.setVisible(false);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();

		instruction = new JLabel();

		ImageIcon v = new ImageIcon("src//instructions.jpg");
		Image scaleImageV = v.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		v = new ImageIcon(scaleImageV);

		instruction.setIcon(v);
		instruction.setLayout(new BorderLayout());

		previous = new JButton();
		previous.setOpaque(false);
		previous.setContentAreaFilled(false);
		previous.setForeground(Color.white);
		previous.setText("PREVIOUS");
		previous.setFocusable(false);
		previous.addActionListener(this);

		prev = new JPanel();
		prev.setLayout(new FlowLayout());
		prev.setOpaque(false);
		prev.setVisible(true);
		prev.add(previous);
		instruction.add(prev, BorderLayout.SOUTH);

		this.add(instruction);

	}

	public static String getP1() {
		return p1;
	}

	public static String getP2() {
		return p2;
	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, FontFormatException {
		new mainView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playButton) {
			try {
				EnterNames();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == exit) {
			System.exit(0);
		} else if (e.getSource() == howToPlay)
			howToPlay();
		else if (e.getSource() == previous) {
			prev.setVisible(false);
			instruction.setVisible(false);

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int height = (int) screenSize.getHeight();
			int width = (int) screenSize.getWidth();

			buttonPanel = new JPanel();
			buttonPanel.setOpaque(false);
			buttonPanel.setLayout(null);

			mainScreen = new JLabel();
			ImageIcon v = new ImageIcon("src//Marvel.png");
			Image scaleImageV = v.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
			v = new ImageIcon(scaleImageV);

			mainScreen.setIcon(v);
			mainScreen.setLayout(new BorderLayout());

			playButton = new JButton();
			playButton.setText("PLAY");
			playButton.setForeground(Color.red);
			playButton.setFont(customFont1);

			playButton.setOpaque(false);
			playButton.addActionListener(this);
			playButton.setFocusable(false);

			playButton.setBorder(null);
			playButton.setContentAreaFilled(false);
			playButton.setBorderPainted(false);
			playButton.setFocusPainted(false);

			playButton.setBounds((int) ((width / 2) - 118), height / 2 - 100, 200, 150);
			buttonPanel.add(playButton);

			exit = new JButton();
			exit.setBackground(Color.red);
			exit.setText("EXIT");
			exit.setFont(customFont2);

			exit.setFocusable(false);
			exit.addActionListener(this);
			exit.setOpaque(false);

			exit.setBorder(null);
			exit.setForeground(Color.red);
			exit.setBorderPainted(false);
			exit.setContentAreaFilled(false);
			exit.setFocusPainted(false);

			exit.setBounds((int) ((width / 2) - 100), (int) ((height / 2) + (height / 10.8)), 150, 80);
			buttonPanel.add(exit);

			howToPlay = new JButton();
			howToPlay.setBackground(Color.red);
			howToPlay.setText("How to play");
			howToPlay.setFont(customFont2);

			howToPlay.setFocusable(false);
			howToPlay.addActionListener(this);
			howToPlay.setOpaque(false);

			howToPlay.setBorder(null);
			howToPlay.setForeground(Color.red);
			howToPlay.setContentAreaFilled(false);
			howToPlay.setBorderPainted(false);
			howToPlay.setFocusPainted(false);

			howToPlay.setBounds((width / 2) - 135, (int) ((height / 2) + (height / 7.2)), 220, 150);
			buttonPanel.add(howToPlay);

			buttonPanel.setBounds(width / 8, height / 5, 100, 100);

			mainScreen.add(buttonPanel);
			this.add(mainScreen);

		}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
