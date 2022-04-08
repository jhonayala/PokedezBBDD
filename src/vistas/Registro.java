package vistas;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import dao.UsuarioDAO;
import models.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registro {

	private JFrame frame;
	private JTextField UserNameField;
	private JPasswordField passwordField;
	private JButton btnVolverLogin;
	private JButton btnRegistro;
	private JPasswordField RepPasswordField;
	 private UsuarioDAO usuarioDAO;

	/**
	 * Create the application.
	 */
	public Registro() {
		initialize();
		frame.revalidate();
		frame.repaint();
		usuarioDAO= new UsuarioDAO();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		UserNameField = new JTextField();
		UserNameField.setBounds(162, 96, 96, 19);
		frame.getContentPane().add(UserNameField);
		UserNameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(162, 150, 96, 19);
		frame.getContentPane().add(passwordField);

		JLabel lblRegistro = new JLabel("Regitro de usuario");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblRegistro.setToolTipText("");
		lblRegistro.setBounds(112, 30, 196, 19);
		frame.getContentPane().add(lblRegistro);

		JLabel lblInUsername = new JLabel("Username");
		lblInUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblInUsername.setBounds(162, 71, 96, 13);
		frame.getContentPane().add(lblInUsername);

		JLabel lblInPassword = new JLabel("Password");
		lblInPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblInPassword.setBounds(162, 127, 91, 13);
		frame.getContentPane().add(lblInPassword);

		btnVolverLogin = new JButton("Volver al Login");
		btnVolverLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				new Login();

			}
		});
		btnVolverLogin.setBounds(29, 201, 108, 21);
		frame.getContentPane().add(btnVolverLogin);
		frame.getRootPane().setDefaultButton(btnVolverLogin);

		 btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = UserNameField.getText();
				String password = new String(passwordField.getPassword());
				String confirmarPassword = new String(RepPasswordField.getPassword());
				if(!usuarioDAO.compUsername(username)) {
					
				
				if(password.equals(confirmarPassword)) {
					if(!username.isEmpty() && !password.isEmpty() && !confirmarPassword.isEmpty()) {
						Usuario u = new Usuario( username, password);
						usuarioDAO.register(u);
						JOptionPane.showMessageDialog(btnRegistro, "Usuario registrado correctamente");
						new Login();
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(btnRegistro, "Rellena todos los campos");
					}
				} else {
					JOptionPane.showMessageDialog(lblRegistro, "Las contraseñas no coinciden");
				}
			}
			else {
				JOptionPane.showMessageDialog(lblInUsername, "ese nombre de usuaio ya existe");
			}}
		});
		btnRegistro.setBounds(311, 201, 85, 21);
		frame.getContentPane().add(btnRegistro);

		JLabel lblInRepetirPassword = new JLabel("Repetir Password");
		lblInRepetirPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblInRepetirPassword.setBounds(167, 179, 91, 13);
		frame.getContentPane().add(lblInRepetirPassword);

		RepPasswordField = new JPasswordField();
		RepPasswordField.setBounds(162, 202, 96, 19);
		frame.getContentPane().add(RepPasswordField);
	}

	public void AbrirRegistro() {
		frame.setVisible(true);
	}

}
