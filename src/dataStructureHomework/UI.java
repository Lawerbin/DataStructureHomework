package dataStructureHomework;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class UI {

	private JFrame frame;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("科学文献查询软件");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 782, 216);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(192, 90, 372, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		JRadioButton radioButton = new JRadioButton("作者");
		buttonGroup.add(radioButton);
		radioButton.setBounds(232, 133, 90, 27);
		panel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("书名");
		buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(448, 133, 90, 27);
		panel.add(radioButton_1);
		
		JButton button = new JButton("确定");
		button.setBounds(306, 176, 113, 27);
		panel.add(button);
	}
}
