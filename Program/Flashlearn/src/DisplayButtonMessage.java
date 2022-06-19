import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DisplayButtonMessage extends JFrame {
	int i=0;
	static JPanel panel;
	public DisplayButtonMessage() {
		JButton[] buttons = new JButton[26];
		panel=new JPanel(new GridLayout(4,6));
		//Creating an array
		String  b[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N",
				"O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for(i = 0; i < buttons.length; i++) {
			//Creating the same number JButtons as data in the array
			buttons[i] = new JButton(b[i]);
			buttons[i].setSize(80, 80);
			buttons[i].setActionCommand(b[i]);
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Assinging the string on the JButton clicked to choice
					String choice = e.getActionCommand();
					JOptionPane.showMessageDialog(null, "You have clicked: "+choice);
					}
				});
			//Adding the buttons to a panel on the GUI
			panel.add(buttons[i]);
			}
		}
	public static void main(String[]args){
		DisplayButtonMessage frame=new DisplayButtonMessage();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		}
	}
