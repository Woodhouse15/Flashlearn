import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

class Slice{
	double value;
	Color color;
	public Slice(double value, Color color) {
		this.value = value;
		this.color = color;
	}
}
class MyComponent extends JComponent{
	public int correct = LearnMode.returnNumberCorrect();
	public int total = LearnMode.returnTotal();
	double percentageCorrect = (correct/total)*100;
	double percentageIncorrect = 100-percentageCorrect; //Creating the values the pie chart will display
	Slice[] slices = {
			new Slice(percentageCorrect, Color.green), new Slice(percentageIncorrect, Color.red) //Asigning values for each slice
	};
	MyComponent(){}
	public void paint(Graphics g) {
		drawPie((Graphics2D) g, getBounds(), slices);
	}
	void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
		double total = 0.0D;
		for(int i = 0;i<slices.length; i++) {
			total+= slices[i].value;
		}
		double curValue = 0.0D;
		int startAngle = 0;
		for(int i=0; i<slices.length;i++) { //Calculating the dimensions of each arc
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (slices[i].value *360 / total);
			g.setColor(slices[i].color);
			g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle); //Filling in each arc drawn
			curValue += slices[i].value;
		}
	}
}
public class ResultsGUI extends JFrame {

	private JPanel contentPane;
	public int correct = LearnMode.returnNumberCorrect();
	public int total = LearnMode.returnTotal();
	double percentageCorrect = (correct/total)*100;
	double percentageIncorrect = 100-percentageCorrect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultsGUI frame = new ResultsGUI();
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
	public ResultsGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Ellie\\Work\\ProgrammingProject\\Interface design\\Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton ExitButton = new JButton("Exit");
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ExitButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ExitButton.setBounds(5, 529, 131, 34);
		contentPane.add(ExitButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(146, 162, 935, 496);
		contentPane.add(panel);
		
		JLabel TitleLabel = new JLabel("Results:" + correct+"/"+total);
		TitleLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		TitleLabel.setBounds(465, 53, 155, 67);
		contentPane.add(TitleLabel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Which is: "+percentageCorrect+"%");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(265, 181, 182, 29);
		panel.add(lblNewLabel);
		
		JLabel Score = new JLabel("You got: ");
		Score.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Score.setBounds(265, 54, 231, 96);
		panel.add(Score);
		
		
		MyComponent myComponent = new MyComponent();
		myComponent.setBounds(517, 11, 1, 1);
		panel.add(myComponent); //Adding the piechart to be displayed in the JPanel
	}
	public void exit() {
		MenuGUI obj = new MenuGUI();
		obj.setVisible(true);
		this.dispose();
	}
}
