package visualCalculator;

//Java swing wildcard for all
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //Action listener allows the user to click on and listen for an input


public class visualCalculator extends JFrame implements ActionListener {
	public static void main(String[] args) {
		new visualCalculator();
	}
	//Creation of calculator frame
	public JFrame frame;
	
	//Creation of text field
	private JTextField textField;
	
	//Store operator and operand
	String operator;
	double num1, num2, result;
	
	public static double addition (double num1, double num2) {
		return num1 + num2;
	}
	public static double subtraction (double num1, double num2) {
		  	return num1 - num2;
	}
	public static double divide (double num1, double num2) {
		  	return num1 / num2;
		  }
	public static double multiply (double num1, double num2) {
		  	return num1 * num2;
		  }
	
	//Create constructor
	visualCalculator() {
		frame = new JFrame("Calculator");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Arial", Font.BOLD, 24));
		
		//Create buttons via a loop
		JButton[] numberButtons = new JButton[10];
		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
		}
		//Declaring buttons individually
		JButton divButton = new JButton("/");
		JButton mulButton = new JButton("*");
		JButton subButton = new JButton("-");
		JButton addButton = new JButton("+");
		JButton eqButton = new JButton("=");
		JButton clrButton = new JButton("C");
		
	//Add action listeners
		divButton.addActionListener(this);
		mulButton.addActionListener(this);
		subButton.addActionListener(this);
		addButton.addActionListener(this);
		eqButton.addActionListener(this);
		clrButton.addActionListener(this);
		
		//Add elements to panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,4,10,10));
		panel.add(clrButton);
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(divButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(mulButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(subButton);
		panel.add(addButton);
		panel.add(numberButtons[0]);
		panel.add(eqButton);
		
		//Add elements to frame
		frame.setLayout(new BorderLayout());
		frame.add(textField, BorderLayout.NORTH);
		frame.add(panel);
		
		frame.setSize(500,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.matches("[0-9]")) { //if number
			textField.setText(textField.getText() + command);
		}
		else if (command.equals("C")) { //Clear
			textField.setText("");
			num1 = num2 = result = 0;
			operator = "";
		}
		else if (command.equals("=")) {
		try {
			num2 = Double.parseDouble(textField.getText());
			switch (operator) {
				case "+": result = addition(num1, num2); break;
				case "-": result = subtraction(num1, num2); break;
				case "*": result = multiply(num1, num2); break;
				case "/":
					if (num2 == 0) {
						textField.setText("ERROR: Tried to divide by zero ");
						return;
					}
					result = divide(num1, num2); break;
			}
			textField.setText(String.valueOf(result));
			num1 = result;
		} catch (NumberFormatException ex) {
			textField.setText("ERROR ");
		}
		}
		else {
			try {
				num1 = Double.parseDouble(textField.getText());
				operator = command;
				textField.setText("");
			} catch (NumberFormatException ex) {
				textField.setText("Error ");
			}
		}
	
		
			}
	}

