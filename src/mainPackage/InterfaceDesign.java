package mainPackage;

import java.sql.*; 

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Point;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfaceDesign {

	private JFrame frame;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtPname;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JTextField txtSearchId;
	private JTextField txtSellId;
	private JTextField txtSellQuantity;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceDesign window = new InterfaceDesign();
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
	public InterfaceDesign() {
		initialize();
		buildConnection();
		loadTable();
	}
	
	Connection con;
	PreparedStatement prestm;
	ResultSet rst;
	
	public void buildConnection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/invmng", "root", "Ranbeer@1217");
			System.out.println("Done with the stable conncetion");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadTable() {
		
		try {
			prestm = con.prepareStatement("select * from InventoryTable");
			
			rst = prestm.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rst));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.getContentPane().setBounds(new Rectangle(20, 0, 0, 0));
		frame.setBounds(100, 100, 829, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventory Management System");
		lblNewLabel.setBounds(0, 0, 842, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBounds(30, 39, 302, 255);
		panel.setRequestFocusEnabled(false);
		panel.setPreferredSize(new Dimension(250, 100));;
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Items" ,TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name");
		lblNewLabel_1.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(17, 27, 110, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(27, 88, 66, 21);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price per Item");
		lblNewLabel_1_2.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(17, 141, 110, 17);
		panel.add(lblNewLabel_1_2);
		
		txtPname = new JTextField();
		txtPname.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtPname.setBounds(137, 23, 151, 25);
		panel.add(txtPname);
		txtPname.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtQuantity.setBounds(138, 84, 151, 25);
		panel.add(txtQuantity);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtPrice.setBounds(137, 139, 151, 25);
		panel.add(txtPrice);
				
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,quantity, price;
				
				name = txtPname.getText();
				quantity = txtQuantity.getText();
				price = txtPrice.getText();
				
				
				try {
					prestm = con.prepareStatement("insert into inventorytable(ProductName, Quantity, PricePerItem)values(?,?,?)");
					
					prestm.setString(1, name);
					prestm.setString(2, quantity);
					prestm.setString(3, price);
					
					
					prestm.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Item Added!!");
					
					loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					
					txtPname.requestFocus();
					
							
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				

				
			}
		});
		
		btnAddItem.setBounds(20, 178, 125, 53);
		panel.add(btnAddItem);
		
		JButton btnClear = new JButton("Clear");
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtPname.setText("");
				txtQuantity.setText("");
				txtPrice.setText("");
				
				txtPname.requestFocus();
				
			}
		});
		
		
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnClear.setBounds(163, 178, 125, 53);
		panel.add(btnClear);
		
		panel_1 = new JPanel();
		panel_1.setBounds(30, 311, 302, 114);
		panel_1.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel_1);
		panel_1.setPreferredSize(new Dimension(250,10));
		panel_1.setBorder(new TitledBorder(null, "Item Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Enter Item Id");
		lblNewLabel_1_2_1.setBounds(27, 23, 97, 19);
		lblNewLabel_1_2_1.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_1_2_1);
		
		txtSearchId = new JTextField();
		txtSearchId.setColumns(10);
		txtSearchId.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtSearchId.setBounds(141, 22, 151, 25);
		panel_1.add(txtSearchId);
		
		JButton btnSearch = new JButton("Search");
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchId = txtSearchId.getText();
				
				try {
					prestm = con.prepareStatement("select * from InventoryTable where ID=?");
					
					prestm.setString(1, searchId);

					rst = prestm.executeQuery();
					
					if(rst.next()) {
						
						txtPname.setText(rst.getString(2));
						txtQuantity.setText(rst.getString(3));
						txtPrice.setText(rst.getString(4));
						
					}else {
						txtPname.setText("");
						txtQuantity.setText("");
						txtPrice.setText("");
						
						txtPname.requestFocus();
					}
					
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnSearch.setBounds(46, 66, 211, 38);
		panel_1.add(btnSearch);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(30, 444, 302, 99);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Modify Record", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnDelete = new JButton("Delete");
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtSearchId.getText();
				
				try {
					prestm = con.prepareStatement("delete from InventoryTable where ID=?");
					prestm.setString(1, id);
					
					prestm.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Item Deleted!!");
					
					loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					
					txtPname.requestFocus();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnDelete.setBounds(157, 20, 124, 63);
		panel_2.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,quantity, price, id;
				
				name = txtPname.getText();
				quantity = txtQuantity.getText();
				price = txtPrice.getText();
				
				id = txtSearchId.getText();
				
				
				
				try {
					prestm = con.prepareStatement("update InventoryTable set ProductName=?, Quantity=?, PricePerItem=? where ID=?");
					
					prestm.setString(1, name);
					prestm.setString(2, quantity);
					prestm.setString(3, price);
					prestm.setString(4, id);
					
					
					prestm.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Item Updated!!");
					
					loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					
					txtPname.requestFocus();
					
							
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnUpdate.setBounds(23, 20, 124, 63);
		panel_2.add(btnUpdate);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(358, 69, 429, 338);
		panel_3.setBorder(new TitledBorder(null, "Inventory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 409, 303);
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		    	System.exit(0);
		    	
		    }
		});

		btnExit.setBounds(693, 31, 73, 32);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		frame.getContentPane().add(btnExit);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(358, 429, 429, 114);
		panel_4.setBorder(new TitledBorder(null, "Sell Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Enter Item Id");
		lblNewLabel_1_2_1_1.setBounds(20, 39, 97, 19);
		lblNewLabel_1_2_1_1.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		panel_4.add(lblNewLabel_1_2_1_1);
		
		txtSellId = new JTextField();
		txtSellId.setColumns(10);
		txtSellId.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtSellId.setBounds(127, 38, 151, 25);
		panel_4.add(txtSellId);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(41, 68, 66, 21);
		panel_4.add(lblNewLabel_1_1_1);
		
		txtSellQuantity = new JTextField();
		txtSellQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String sellId = txtSellId.getText();
				String sellQuantity = txtSellQuantity.getText();
				
				try {
					prestm = con.prepareStatement("select * from InventoryTable where ID=?");
					
					prestm.setString(1, sellId);

					rst = prestm.executeQuery();
					
					
					
					if(rst.next()) {
						
						if(Integer.parseInt(sellQuantity)<=Integer.parseInt(rst.getString(3))) {
							txtPname.setText(rst.getString(2));
							txtQuantity.setText(rst.getString(3));
							txtPrice.setText(rst.getString(4));
						}else {
							txtPname.setText("");
							txtQuantity.setText("");
							txtPrice.setText("");
							
							txtPname.requestFocus();
						}
						
					}else {
						txtPname.setText("");
						txtQuantity.setText("");
						txtPrice.setText("");
						
						txtPname.requestFocus();
					}
					
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		txtSellQuantity.setColumns(10);
		txtSellQuantity.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtSellQuantity.setBounds(127, 68, 151, 25);
		panel_4.add(txtSellQuantity);
		
		JButton btnSellItem = new JButton("Sell Items");
		
		btnSellItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,quantity, price, id;
				
				name = txtPname.getText();
				quantity = txtQuantity.getText();
				price = txtPrice.getText();
				
				id = txtSellId.getText();
				String sellQuantity = txtSellQuantity.getText();
				
				Integer diffQuantity = Integer.parseInt(quantity) - Integer.parseInt(sellQuantity);
				
				
				
				try {
					prestm = con.prepareStatement("update InventoryTable set Quantity=? where ID=?");
					
					prestm.setString(1, diffQuantity.toString());
					prestm.setString(2, id);
					
					prestm.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Items Sold!!");
					
					loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					
					txtPname.requestFocus();
					
							
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		
		btnSellItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSellItem.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnSellItem.setBounds(288, 39, 125, 53);
		panel_4.add(btnSellItem);
	}

	public JPanel getPanel() {
		return panel;
	}
}
