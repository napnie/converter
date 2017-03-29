package ku.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * GUI for LengthConverter.
 * @author Nitith Chayakul
 * @version 1.1
 */
public class ConverterUI {
	/** attribute for GUI */
	private JFrame frame;
	/** attribute for converter */
	private UnitConverter ucon;
	/** attribute for input components on the left */
	private JTextField leftInput;
	/** attribute for select unit for leftInput */
	private JComboBox<Unit> leftSelector;
	/** attribute for input components on the right */
	private JTextField rightInput;
	/** attribute for select unit for rightInput */
	private JComboBox<Unit> rightSelector;
	
	/** attribute for input a value to convert */
	private JTextField input;
	/** atribut for select unit for the input */
	private JComboBox<Unit> inSelector;
	/** attribute for output the convertion */
	private JTextField output;
	/** atribut for select unit for the output */
	private JComboBox<Unit> outSelector;
	
	/**
	 * Initialize a ConverterUI with UnitConverter.
	 * @param ucon
	 */
	public ConverterUI( UnitConverter ucon ) {
		this.ucon = ucon;
		frame = new JFrame("Distance Converter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
	}
	
	/**
	 * Initialize and add components in frame attribute.
	 */
	private void initComponents() {
		frame.setLayout(new BorderLayout());
		
		// make convertion panel
		JPanel convertPanel = new JPanel();
		leftInput = new JTextField(18);
		JLabel equal = new JLabel(" = ");
		rightInput = new JTextField(18);
		rightInput.setEditable(false);
		leftSelector = new JComboBox<Unit>();
		rightSelector = new JComboBox<Unit>();
		for (Unit unit : ucon.getUnits() ) {
			leftSelector.addItem(unit);
			rightSelector.addItem(unit);
		}
		JButton convert = new JButton("Convert!");
		JButton clear = new JButton("Clear");
		convertPanel.setLayout(new FlowLayout() );
		convertPanel.add(leftInput);
		convertPanel.add(leftSelector);
		convertPanel.add(equal);
		convertPanel.add(rightInput);
		convertPanel.add(rightSelector);
		convertPanel.add(convert);
		convertPanel.add(clear);
		
		// initialize direction to Left->Right
		input = leftInput;
		output = rightInput;
		inSelector = leftSelector;
		outSelector = rightSelector;
		
		// add action to input and button
		ActionListener convertAction = new ConvertListener();
		ActionListener clearing = new ClearListener();
		convert.addActionListener(convertAction);
		clear.addActionListener(clearing);
		leftInput.addActionListener(convertAction);
		rightInput.addActionListener(convertAction);
		
		// make direction panel
		JPanel direcPanel = new JPanel();
		JRadioButton leftToRight = new JRadioButton("Left->Right");
		ActionListener direcControl = new controlDirection();
		leftToRight.addActionListener( direcControl );
		JRadioButton rightToLeft = new JRadioButton("Right->Left");
		rightToLeft.addActionListener( direcControl );
		leftToRight.setSelected(true);
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(leftToRight);
		radioGroup.add(rightToLeft);
		direcPanel.add(leftToRight);
		direcPanel.add(rightToLeft);
		
		// add panel in the frame
		frame.add(convertPanel, BorderLayout.NORTH);
		frame.add(direcPanel, BorderLayout.SOUTH);
		
		frame.pack();
	}
	
	/**
	 * Run GUI.
	 */
	public void run() {
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
	
	/**
	 * Action for Radio Button to tell which direction to convert.
	 * @author Nitith Chayakul
	 * @version 1.0
	 *
	 */
	public class controlDirection implements ActionListener {
		/** Action for Radio Button to tell which direction to convert. */
		@Override
		public void actionPerformed(ActionEvent event) {
			if ( event.getActionCommand().equals("Left->Right") ) {
				input = leftInput;
				output = rightInput;
				inSelector = leftSelector;
				outSelector = rightSelector;
			} else {
				input = rightInput;
				output = leftInput;
				inSelector = rightSelector;
				outSelector = leftSelector;
			}
			input.setEditable(true);
			output.setEditable(false);
		}
	}
	
	/**
	 * Convert action for input Text Field and convert Button.
	 * @author Nitith Chayakul
	 * @version 1.1
	 *
	 */
	public class ConvertListener implements ActionListener {
		/** Convert action for input Text Field and convert Button. */
		@Override
		public void actionPerformed(ActionEvent e) {
			double amount = 0;
			try {
				amount = Double.parseDouble( input.getText() ) ;
				Unit fromUnit = (Unit) inSelector.getSelectedItem();
				Unit toUnit = (Unit) outSelector.getSelectedItem();
				Double result = ucon.convert(amount, fromUnit, toUnit );
				String resultText = String.format("%.5g", result);
				output.setText( resultText );
				input.setForeground(Color.BLACK);
			} catch (NumberFormatException nf) {
				input.setForeground(Color.RED);
			}
		}
	}
	
	/**
	 * Clear text in both input.
	 * @author Nitith Chayakul
	 * @version 1.0
	 *
	 */
	public class ClearListener implements ActionListener {
		/** Clear text in both input. */
		@Override
		public void actionPerformed(ActionEvent e) {
			leftInput.setText("");
			rightInput.setText("");
		}
	}
}
