package vistas;

import javax.swing.JFrame;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import dao.PokemonDAO;
import models.Pokemon;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Pokedez {

	private JFrame frame;
	private PokemonDAO pokemonDAO;
	private ArrayList<Pokemon> pokemons;
	private JTextField textNamePok;
	private JTextField textTipoPok;
	private JTextField textAltPok;
	private JTextField textPesoPok;
	private int index;
	private JButton btnAtras;
	private JLabel lblNumPoke;
	private JButton btnSiguiente;
	private JLabel lblNombre;
	private JLabel lblPesoPok;
	private JLabel lblTipoPok;
	private JLabel lblAltPok;
	private JButton btnIrCrear;
	private JButton btnActualizar;
	private JLabel lblPCategoriaPok;
	private JTextField CategoriaField;
	private JTextField HabilidadField;

	/**
	 * Create the application.
	 */
	public Pokedez() {
		initialize();
		this.pokemonDAO = new PokemonDAO();
		this.pokemons = pokemonDAO.getAll();
		
		if (this.pokemons.size() > 0) {
			MostrarPok();
		}else {
			new Crear_Poke();
			frame.dispose();
		}

		index = 0;
		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 762, 582);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textNamePok = new JTextField();
		textNamePok.setEditable(false);
		textNamePok.setBounds(199, 203, 133, 33);
		frame.getContentPane().add(textNamePok);
		textNamePok.setColumns(10);

		textTipoPok = new JTextField();
		textTipoPok.setEditable(false);
		textTipoPok.setBounds(199, 267, 133, 30);
		frame.getContentPane().add(textTipoPok);
		textTipoPok.setColumns(10);

		textAltPok = new JTextField();
		textAltPok.setEditable(false);
		textAltPok.setBounds(199, 333, 133, 32);
		frame.getContentPane().add(textAltPok);
		textAltPok.setColumns(10);

		textPesoPok = new JTextField();
		textPesoPok.setEditable(false);
		textPesoPok.setBounds(199, 409, 133, 30);
		frame.getContentPane().add(textPesoPok);
		textPesoPok.setColumns(10);

		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirAtras();

			}
		});
		btnAtras.setBounds(29, 41, 106, 61);
		frame.getContentPane().add(btnAtras);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirSiguiente();
			}
		});
		btnSiguiente.setBounds(577, 41, 106, 61);
		frame.getContentPane().add(btnSiguiente);
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(40, 203, 95, 30);
		frame.getContentPane().add(lblNombre);

		lblTipoPok = new JLabel("Tipo");
		lblTipoPok.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoPok.setBounds(40, 267, 95, 30);
		frame.getContentPane().add(lblTipoPok);

		lblAltPok = new JLabel("Altura");
		lblAltPok.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAltPok.setBounds(40, 333, 95, 30);
		frame.getContentPane().add(lblAltPok);

		lblPesoPok = new JLabel("Peso");
		lblPesoPok.setHorizontalAlignment(SwingConstants.LEFT);
		lblPesoPok.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPesoPok.setBounds(40, 409, 95, 30);
		frame.getContentPane().add(lblPesoPok);

		lblNumPoke = new JLabel("");
		lblNumPoke.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNumPoke.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumPoke.setBounds(276, 41, 188, 61);
		frame.getContentPane().add(lblNumPoke);

		btnIrCrear = new JButton("Crear");
		btnIrCrear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIrCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Crear_Poke();

			}
		});
		btnIrCrear.setBounds(50, 476, 85, 40);
		frame.getContentPane().add(btnIrCrear);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Act_Poke(pokemons.get(index));

			}
		});
		btnActualizar.setBounds(291, 480, 106, 33);
		frame.getContentPane().add(btnActualizar);

		lblPCategoriaPok = new JLabel("Categoria");
		lblPCategoriaPok.setHorizontalAlignment(SwingConstants.LEFT);
		lblPCategoriaPok.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPCategoriaPok.setBounds(411, 267, 95, 30);
		frame.getContentPane().add(lblPCategoriaPok);

		CategoriaField = new JTextField();
		CategoriaField.setText((String) null);
		CategoriaField.setEditable(false);
		CategoriaField.setColumns(10);
		CategoriaField.setBounds(538, 267, 133, 30);
		frame.getContentPane().add(CategoriaField);

		JLabel lblPHabilidPok = new JLabel("Habilidad");
		lblPHabilidPok.setHorizontalAlignment(SwingConstants.LEFT);
		lblPHabilidPok.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPHabilidPok.setBounds(411, 333, 95, 30);
		frame.getContentPane().add(lblPHabilidPok);

		HabilidadField = new JTextField();
		HabilidadField.setText((String) null);
		HabilidadField.setEditable(false);
		HabilidadField.setColumns(10);
		HabilidadField.setBounds(538, 339, 133, 26);
		frame.getContentPane().add(HabilidadField);

		JLabel lblNumPok = new JLabel("N\u00BA");
		lblNumPok.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumPok.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumPok.setBounds(196, 56, 70, 46);
		frame.getContentPane().add(lblNumPok);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pokemonDAO.delete(pokemons.get(index));
				pokemons.remove(pokemons.get(index));
				if (pokemons.size() > 0) {
					imprimirAtras();
				} else {
					JOptionPane.showMessageDialog(btnBorrar, "Te has quedado sin pokemons");
					new Crear_Poke();
					frame.dispose();

				}

			}
		});
		btnBorrar.setBounds(530, 486, 106, 33);
		frame.getContentPane().add(btnBorrar);
	}

	public void MostrarPok() {
		if (pokemons.size() > 0) {
			Pokemon a = pokemons.get(index);
			textNamePok.setText(a.getNombre());
			textTipoPok.setText(String.valueOf(a.getTipo()));
			textAltPok.setText(String.valueOf(a.getAltura()));
			textPesoPok.setText(String.valueOf(a.getPeso()));
			CategoriaField.setText(String.valueOf(a.getCategoria()));
			HabilidadField.setText(String.valueOf(a.getHabilidad()));
			lblNumPoke.setText(String.valueOf(a.getNumero()));

		}

	}

	public void imprimirSiguiente() {
		index++;
		if (index == pokemons.size())
			index = 0;
		MostrarPok();

	}

	public void imprimirAtras() {
		index--;
		if (index < 0)
			index = pokemons.size() - 1;
		MostrarPok();

	}

}
