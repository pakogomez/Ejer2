import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Zoo extends JFrame {

	private static JPanel contentPane;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static int cont;

	/**
	 * Launch the application.
	 */
	public static void printSQLException(SQLException ex) {
		ex.printStackTrace(System.err);
		System.err.println("SQLState: " + ex.getSQLState());
		System.err.println("Error code: " + ex.getErrorCode());
		System.err.println("Message: " + ex.getMessage());
		Throwable t = ex.getCause();
		while (t != null) {
		System.out.println("Cause: " + t);
		t = ex.getCause();
		}
		}
	
		public static void consultarAnimales(Connection con, String BDNombre)
		throws SQLException {
		Statement stmt = null;
		String sql= null;
		if (textField_5.getText()!=" "){
		 sql = "select *"+" from " + BDNombre + ".animales where Ubicacion= '"+textField_5.getText()+"'";}
		if (textField_4.getText()!=" "){
			sql = "select *"+" from " + BDNombre + ".animales where Cuidador= '"+textField_4.getText()+"'";}
		try {
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		cont = cont + 1;
		if (rs.relative(cont) ){
			String identificador = rs.getString("Identificador");
			textField.setText(identificador);
			String nombre = rs.getString("Nombre");
			textField_1.setText(nombre);
			String edad = String.valueOf(rs.getInt("Edad"));
			textField_2.setText(edad);
			String color = rs.getString("Color");
			textField_3.setText(color);
			String cuidador = rs.getString("Cuidador");
			textField_4.setText(cuidador);
			String ubicacion = rs.getString("Ubicacion");
			textField_5.setText(ubicacion);
			
		
			
		}} catch (SQLException e) {
		printSQLException(e);

		} finally {
		stmt.close();
		}
		}
		public static void modificarFilaAnimales(Connection con, String BDNombre )
				throws SQLException {
				Statement stmt = null;
				String sql = "select identificador "+" from " + BDNombre + ".animales";
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					boolean Registrado = false;
					String nuevoanimal= textField.getText();
					while (rs.next()){
						String registrada = rs.getString("Identificador");
						if (nuevoanimal.equals(registrada))
							Registrado = true; break;
						
					}
				
				if (Registrado == true){
				stmt.executeUpdate(" UPDATE " + BDNombre+ ".animales SET "
						
						+"Nombre="+"'"+ textField_1.getText()+"'"+","+
						 "Edad="+Integer.parseInt(textField_2.getText())+","+
						"Color="+"'"+ textField_3.getText()+"'"+","
						+"Cuidador="+"'"+ textField_4.getText()+"'"+","+
						"Ubicacion="+"'"+textField_5.getText()+"'"+
						 " WHERE Identificador="+"'"+ textField_1.getText()+"'"+ ";");
				}	
				} catch (SQLException e) {
				printSQLException(e);

				} finally {
				stmt.close();
				}
				}
		
		
		public static void insertarFilaAnimales(Connection con, String BDNombre )
		throws SQLException {
		Statement stmt = null;
		String sql = "select identificador "+" from " + BDNombre + ".animales";
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			boolean NoRegistrado = true;
			String nuevoanimal= textField.getText();
			while (rs.next()){
				String registrada = rs.getString("Identificador");
				if (nuevoanimal.equals(registrada))
					NoRegistrado = false; break;
				
			}
		
		if (NoRegistrado == true){
		stmt.executeUpdate("INSERT INTO " + BDNombre+ ".animales VALUES ("
				+"'"+ textField.getText()+"'"+","
				+"'"+ textField_1.getText()+"'"+","+
				 Integer.parseInt(textField_2.getText())+","+
				"'"+ textField_3.getText()+"'"+","
				+"'"+ textField_4.getText()+"'"+","+
				"'"+textField_5.getText()+"'"+
				 ");");
		}	
		} catch (SQLException e) {
		printSQLException(e);

		} finally {
		stmt.close();
		}
		}
		public static void borrarFilaAnimales(Connection con, String BDNombre )
				throws SQLException {
			Statement stmt = null;
			try {
				stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM " + BDNombre+ ".animales WHERE Identificador = " + "'"+ textField.getText()+"'"+ ";");
				
			} catch (SQLException e) {
			printSQLException(e);

			} finally {
			stmt.close();
			}
			
				}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zoo frame = new Zoo();
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
	public Zoo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdentificador = new JLabel("Identificador");
		lblIdentificador.setBounds(77, 11, 46, 14);
		contentPane.add(lblIdentificador);
		
		textField = new JTextField();
		textField.setBounds(165, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(77, 43, 46, 14);
		contentPane.add(lblNombre);
		
		textField_1 = new JTextField();
		textField_1.setBounds(165, 39, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(77, 68, 46, 14);
		contentPane.add(lblEdad);
		
		textField_2 = new JTextField();
		textField_2.setBounds(165, 65, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(77, 93, 46, 14);
		contentPane.add(lblColor);
		
		textField_3 = new JTextField();
		textField_3.setBounds(165, 90, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCuidador = new JLabel("Cuidador");
		lblCuidador.setBounds(77, 118, 46, 14);
		contentPane.add(lblCuidador);
		
		textField_4 = new JTextField();
		textField_4.setBounds(165, 115, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n");
		lblUbicacin.setBounds(77, 143, 46, 14);
		contentPane.add(lblUbicacin);
		
		textField_5 = new JTextField();
		textField_5.setBounds(165, 140, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoologico", "root", "");
				
				insertarFilaAnimales(con, "zoologico" );			

				} catch (Exception ex) {
				ex.printStackTrace();
				}
			}
		});
		btnInsertar.setBounds(34, 179, 89, 23);
		contentPane.add(btnInsertar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoologico", "root", "");
				
				borrarFilaAnimales(con, "zoologico" );			

				} catch (Exception ex) {
				ex.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(148, 179, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoologico", "root", "");
				
				modificarFilaAnimales(con, "zoologico" );			

				} catch (Exception ex) {
				ex.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(270, 179, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoologico", "root", "");
				
				consultarAnimales(con, "zoologico" );			

				} catch (Exception ex) {
				ex.printStackTrace();
				}
			}
		});
		btnConsultar.setBounds(148, 213, 89, 23);
		contentPane.add(btnConsultar);
	}
}
