import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Lab 6");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);

		CardLayout cardLayout = new CardLayout();
		JPanel container = new JPanel(cardLayout);

		JPanel mainMenu = new JPanel(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();
		contraints.gridx = 0;
		contraints.gridy = GridBagConstraints.RELATIVE;
		contraints.insets = new Insets(8, 0, 8, 0);
		contraints.fill = GridBagConstraints.VERTICAL;

		JButton btn1 = new JButton("Open Task 1");
		JButton btn2 = new JButton("Open Task 2");
		JButton btn3 = new JButton("Open Task 3");

		mainMenu.add(btn1, contraints);
		mainMenu.add(btn2, contraints);
		mainMenu.add(btn3, contraints);

		container.add(mainMenu, "main");

		container.add(createTask1Menu(cardLayout, container), "task1");
		container.add(createTask2Menu(cardLayout, container), "task2");
		container.add(createTask3Menu(cardLayout, container), "task3");

		btn1.addActionListener(e -> cardLayout.show(container, "task1"));
		btn2.addActionListener(e -> cardLayout.show(container, "task2"));
		btn3.addActionListener(e -> cardLayout.show(container, "task3"));

		frame.add(container);
		frame.setVisible(true);
	}

	private static JPanel createTask1Menu(CardLayout layout, JPanel container)
	{
		JPanel panel = new JPanel(new BorderLayout());

		JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton backButton = new JButton("<- Back");
		backButton.addActionListener(event -> layout.show(container, "main"));
		topBar.add(backButton);
		panel.add(topBar, BorderLayout.NORTH);

		JPanel contentPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 0; constraints.gridy = 0; constraints.anchor = GridBagConstraints.WEST;

		JTextField inputField = new JTextField(10);
		String[] options = {"Celsius -> Fahrenheit", "USD -> UAH", "EUR -> UAH"};
		JComboBox<String> comboBox = new JComboBox<>(options);
		JButton calculateBtn = new JButton("Calculate");
		JLabel resultLabel = new JLabel("Result: ");

		contentPanel.add(new JLabel("Input:"), constraints);
		constraints.gridx = 1;
		contentPanel.add(inputField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		contentPanel.add(new JLabel("Conversion:"), constraints);

		constraints.gridx = 1;
		contentPanel.add(comboBox, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		contentPanel.add(calculateBtn, constraints);

		constraints.gridx = 1;
		contentPanel.add(resultLabel, constraints);

		panel.add(contentPanel, BorderLayout.CENTER);

		calculateBtn.addActionListener(event -> {
			try {
				double value = Double.parseDouble(inputField.getText());
				String selected = (String) comboBox.getSelectedItem();
				double res = switch(selected)
				{
					case "Celsius -> Fahrenheit" -> (value * 9 / 5) + 32;
					case "USD -> UAH" -> value * 41.5;
					case "EUR -> UAH" -> value * 43.0;
					default -> 0;
				};
				resultLabel.setText("Result: " + String.format("%.2f", res));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(panel, "Error! Please input correct numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		return panel;
	}

	private static JPanel createTask2Menu(CardLayout layout, JPanel container)
	{
		JPanel panel = new JPanel(new BorderLayout());

		JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton backButton = new JButton("<- Back");
		backButton.addActionListener(e -> layout.show(container, "main"));
		topBar.add(backButton);
		panel.add(topBar, BorderLayout.NORTH);

		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.anchor = GridBagConstraints.WEST;

		JTextField nameField = new JTextField(15);
		JTextField emailField = new JTextField(15);

		JRadioButton rbClient = new JRadioButton("Client", true);
		JRadioButton rbPartner = new JRadioButton("Partner");
		ButtonGroup accountGroup = new ButtonGroup();
		accountGroup.add(rbClient);
		accountGroup.add(rbPartner);

		JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		radioPanel.add(rbClient);
		radioPanel.add(rbPartner);

		JCheckBox cbService1 = new JCheckBox("Extra Support");
		JCheckBox cbService2 = new JCheckBox("Extended Warranty");
		JCheckBox cbService3 = new JCheckBox("Subscribe to our newsletter");

		JPanel checkPanel = new JPanel(new GridLayout(3, 1, 0, 0));
		checkPanel.add(cbService1);
		checkPanel.add(cbService2);
		checkPanel.add(cbService3);

		JTextArea commentArea = new JTextArea(4, 15);
		commentArea.setLineWrap(true);
		commentArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(commentArea);

		JButton saveButton = new JButton("Save");
		JButton clearButton = new JButton("Clear");
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnPanel.add(saveButton);
		btnPanel.add(clearButton);

		int y = 0;
		gbc.gridx = 0; gbc.gridy = y; formPanel.add(new JLabel("Full Name:"), gbc);
		gbc.gridx = 1; formPanel.add(nameField, gbc);

		y++;
		gbc.gridx = 0; gbc.gridy = y; formPanel.add(new JLabel("Email:"), gbc);
		gbc.gridx = 1; formPanel.add(emailField, gbc);

		y++;
		gbc.gridx = 0; gbc.gridy = y; formPanel.add(new JLabel("Account Type:"), gbc);
		gbc.gridx = 1; formPanel.add(radioPanel, gbc);

		y++;
		gbc.gridx = 0; gbc.gridy = y; formPanel.add(new JLabel("Options:"), gbc);
		gbc.gridx = 1; formPanel.add(checkPanel, gbc);

		y++;
		gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.NORTHWEST;
		formPanel.add(new JLabel("Comment:"), gbc);
		gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
		formPanel.add(scrollPane, gbc);

		y++;
		gbc.gridx = 1; gbc.gridy = y; formPanel.add(btnPanel, gbc);

		panel.add(new JScrollPane(formPanel), BorderLayout.CENTER);

		saveButton.addActionListener(e -> {
			StringBuilder sb = new StringBuilder("--- Summary ---\n");
			sb.append("Full Name: ").append(nameField.getText()).append("\n");
			sb.append("Email: ").append(emailField.getText()).append("\n");

			String accType = rbClient.isSelected() ? "Client" : "Partner";
			sb.append("Account Type: ").append(accType).append("\n");

			sb.append("Selected Options:\n");
			if (cbService1.isSelected())
				sb.append(" - ").append(cbService1.getText()).append("\n");
			if (cbService2.isSelected())
				sb.append(" - ").append(cbService2.getText()).append("\n");
			if (cbService3.isSelected())
				sb.append(" - ").append(cbService3.getText()).append("\n");

			sb.append("Comments: ").append(commentArea.getText());

			JOptionPane.showMessageDialog(panel, sb.toString(), "result Saved", JOptionPane.INFORMATION_MESSAGE);
		});

		clearButton.addActionListener(e -> {
			nameField.setText("");
			emailField.setText("");
			rbClient.setSelected(true);
			cbService1.setSelected(false);
			cbService2.setSelected(false);
			cbService3.setSelected(false);
			commentArea.setText("");
		});

		return panel;
	}

	private static JPanel createTask3Menu(CardLayout layout, JPanel container)
	{
		JPanel panel = new JPanel(new BorderLayout());

		JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton backButton = new JButton("<- Back");
		backButton.addActionListener(e -> layout.show(container, "main"));
		topBar.add(backButton);
		panel.add(topBar, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem itemClearTable = new JMenuItem("Clear Table");
		JMenuItem itemExit = new JMenuItem("Exit");
		menuFile.add(itemClearTable);
		menuFile.addSeparator();
		menuFile.add(itemExit);

		JMenu menuHelp = new JMenu("Information");
		JMenuItem itemAbout = new JMenuItem("About the app");
		menuHelp.add(itemAbout);

		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		panel.add(menuBar, BorderLayout.SOUTH);

		String[] columns = {"ID", "Full Name", "Group", "Average Score"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
		JTable table = new JTable(tableModel);
		JScrollPane tableScroll = new JScrollPane(table);

		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JTextField tfId = new JTextField(4);
		JTextField tfName = new JTextField(10);
		JTextField tfGroup = new JTextField(5);
		JTextField tfScore = new JTextField(5);

		JButton addBtn = new JButton("Add Row");
		JButton deleteBtn = new JButton("Remove Selected");

		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(tfId);

		inputPanel.add(new JLabel("Full Name:"));
		inputPanel.add(tfName);

		inputPanel.add(new JLabel("Group:"));
		inputPanel.add(tfGroup);

		inputPanel.add(new JLabel("Score:"));
		inputPanel.add(tfScore);

		inputPanel.add(addBtn);
		inputPanel.add(deleteBtn);

		JPanel centerContainer = new JPanel(new BorderLayout());
		centerContainer.add(tableScroll, BorderLayout.CENTER);
		centerContainer.add(inputPanel, BorderLayout.SOUTH);

		panel.add(centerContainer, BorderLayout.CENTER);

		addBtn.addActionListener(e -> {
			String id = tfId.getText();
			String name = tfName.getText();
			String group = tfGroup.getText();
			String score = tfScore.getText();

			if (!id.isEmpty() && !name.isEmpty() && !group.isEmpty() && !score.isEmpty()) {
				tableModel.addRow(new Object[]{id, name, group, score});
				tfId.setText(""); tfName.setText(""); tfGroup.setText(""); tfScore.setText("");
			} else {
				JOptionPane.showMessageDialog(panel, "Fill all fields!", "Error", JOptionPane.WARNING_MESSAGE);
			}
		});

		deleteBtn.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				tableModel.removeRow(selectedRow);
			} else {
				JOptionPane.showMessageDialog(panel, "Select a row first", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});

		itemClearTable.addActionListener(e -> tableModel.setRowCount(0));
		itemExit.addActionListener(e -> System.exit(0));
		itemAbout.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Student List Manager", "About the app", JOptionPane.INFORMATION_MESSAGE));

		return panel;
	}
}