package com.cal.view;

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

import com.cal.controller.ControllerInterface;
import com.cal.model.Observer;

public class CalcView implements Observer{
	private final String[] buttonsNames = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "."};
	
	private JFrame viewFrame;
	
	private JButton[] buttons;
	private JButton addButton;
	private JButton subButton;
	private JButton timesButton;
	private JButton divButton;
	private JButton equalButton;
	private JButton clearButton;
	
	private JTextField resultField;
	private JPanel panel;
	
	private ControllerInterface controller;
	
	public CalcView(ControllerInterface controller){
		this.controller = controller;
		createView();
	}
	
	public void createView(){
		viewFrame = new JFrame("Calculator");
		viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewFrame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new BorderLayout());
		
		resultField = new JTextField(10);
		resultField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		resultField.setText("0");
		panel.add(resultField, BorderLayout.PAGE_START);
		
		buttons = new JButton[buttonsNames.length];
		addButton = new JButton("+");
		subButton = new JButton("-");
		timesButton = new JButton("*");
		divButton = new JButton("/");
		equalButton = new JButton("=");
		clearButton = new JButton("C");
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(4, 3));
		
		for(int i = 0; i < buttonsNames.length; i++)
		{
			buttons[i] = new JButton(buttonsNames[i]);
			buttonsPanel.add(buttons[i]);
			buttons[i].addActionListener(new dispButtons()); 
		}
		buttonsPanel.add(equalButton);
		equalButton.addActionListener(new opListener());
		
		JPanel restButtonsPanel = new JPanel();
		restButtonsPanel.setLayout(new BoxLayout(restButtonsPanel, BoxLayout.PAGE_AXIS));
		restButtonsPanel.add(clearButton);
		restButtonsPanel.add(addButton);
		restButtonsPanel.add(subButton);
		restButtonsPanel.add(timesButton);
		restButtonsPanel.add(divButton);
		addButton.addActionListener(new opListener());
		subButton.addActionListener(new opListener());
		timesButton.addActionListener(new opListener());
		divButton.addActionListener(new opListener());
		clearButton.addActionListener(new clearListener());
		
		panel.add(restButtonsPanel, BorderLayout.EAST);
		panel.add(buttonsPanel, BorderLayout.CENTER);
		
		viewFrame.getContentPane().add(panel);
		viewFrame.pack();
		viewFrame.setResizable(false);
		viewFrame.setVisible(true);
		
	}
	
	public class dispButtons implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			controller.numberClicked( ( (JButton)e.getSource() ).getText() );
		}
	}
	public class opListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			controller.operatorClicked( ( (JButton)e.getSource() ) .getText() );
		}
	}
	
	public class clearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			controller.reset();
		}
		
	}
	@Override
	public void update(String str) {
		resultField.setText(str);
	}
}
