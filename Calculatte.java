import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Calculatte extends JFrame{
	
	private final String[] buttonsNames = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "."};
	
	private JButton[] buttons = new JButton[buttonsNames.length];
	private JButton addButton = new JButton("+");
	private JButton subButton = new JButton("-");
	private JButton timesButton = new JButton("*");
	private JButton divButton = new JButton("/");
	private JButton equalButton = new JButton("=");
	private JButton clearButton = new JButton("C");
	
	private JTextField resultat = new JTextField(10);
	private JPanel panel = new JPanel();
	
	private double firstNumber;
	private Action action;
	
	private boolean actioned;
	
	public enum Action{
		ADD,
		SUB,
		TIMES,
		DIV;
	}
	
	public Calculatte()
	{
		this.setTitle("Calc!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		actioned = false;
		
		panel.setBackground(Color.white);
		panel.setLayout(new BorderLayout());
		resultat.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		resultat.setText("0");
		
		panel.add(resultat, BorderLayout.PAGE_START);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(4, 3));
		
		for(int i = 0; i < buttonsNames.length; i++)
		{
			buttons[i] = new JButton(buttonsNames[i]);
			buttonsPanel.add(buttons[i]);
			buttons[i].addActionListener(new dispButtons()); 
		}
		buttonsPanel.add(equalButton);
		equalButton.addActionListener(new equalListener());
		
		JPanel restButtonsPanel = new JPanel();
		restButtonsPanel.setLayout(new BoxLayout(restButtonsPanel, BoxLayout.PAGE_AXIS));
		restButtonsPanel.add(clearButton);
		restButtonsPanel.add(addButton);
		restButtonsPanel.add(subButton);
		restButtonsPanel.add(timesButton);
		restButtonsPanel.add(divButton);
		
		addButton.addActionListener(new addListener());
		subButton.addActionListener(new subListener());
		timesButton.addActionListener(new timesListener());
		divButton.addActionListener(new divListener());
		clearButton.addActionListener(new clearListener());
		
		panel.add(restButtonsPanel, BorderLayout.EAST);
		panel.add(buttonsPanel, BorderLayout.CENTER);
		
		this.getContentPane().add(panel);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	public class dispButtons implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			String str = (((Double.valueOf(resultat.getText()) == 0) | actioned) ?
							"" : resultat.getText()) + e.getActionCommand();
			actioned = false;
			resultat.setText(str);			
		}
	}
	public class equalListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			double secondNumber = Double.valueOf(resultat.getText());
			double result;
			
			switch(action){
			case ADD:
				result = firstNumber + secondNumber;
				resultat.setText(String.valueOf(result));
				actioned = true;
				break;
			case SUB:
				result = firstNumber - secondNumber;
				resultat.setText(String.valueOf(result));
				actioned = true;
				break;
			case TIMES:
				result = firstNumber * secondNumber;
				resultat.setText(String.valueOf(result));
				actioned = true;
				break;
			case DIV:
				try{
					result = firstNumber / secondNumber;
					resultat.setText(String.valueOf(result));
				}catch(ArithmeticException e){
					e.printStackTrace();
					resultat.setText("Arithmetic ERROR!");
				}finally{
					actioned = true;
				}
				
				break;
			}
		}
	}
	public class clearListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			resultat.setText("0");
		}
	}
	public class addListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			action = Action.ADD;
			firstNumber = Double.valueOf(resultat.getText());
			resultat.setText("0");
		}
	}
	public class subListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			action = Action.SUB;
			firstNumber = Double.valueOf(resultat.getText());
			resultat.setText("0");
		}	
	}
	public class timesListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			action = Action.TIMES;
			firstNumber = Double.valueOf(resultat.getText());
			resultat.setText("0");
		}	
	}
	public class divListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			action = Action.DIV;
			firstNumber = Double.valueOf(resultat.getText());
			resultat.setText("0");
		}
	}
}
