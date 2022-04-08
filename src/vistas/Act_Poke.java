package vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.PokemonDAO;
import enums.Categoria;
import enums.Tipo;
import models.Pokemon;

public class Act_Poke {

	private JFrame frame;

	private JTextField textNamePok;
	private JTextField textTipoPok;
	private JTextField textAltPok;
	private JTextField textPesoPok;
	
	private JLabel lblNumPoke;
	private JLabel lblNombre;
	private JLabel lblPesoPok;
	private JLabel lblTipoPok;
	private JLabel lblAltPok;
	private JButton btnActualizar;
	private JLabel lblPCategoriaPok;
	private JTextField CategoriaField;
	private JTextField HabilidadField;
	private JButton btnPokedez;
	private Pokemon pokAct;
	private PokemonDAO pokemonDAO;

	/**
	 * Create the application.
	 */
	public Act_Poke(Pokemon a) {
		initialize();
		pokAct = a;
		
		MostrarPok();
		pokemonDAO=  new PokemonDAO();

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
		textTipoPok.setBounds(199, 267, 133, 30);
		frame.getContentPane().add(textTipoPok);
		textTipoPok.setColumns(10);

		textAltPok = new JTextField();
		textAltPok.setBounds(199, 337, 133, 32);
		frame.getContentPane().add(textAltPok);
		textAltPok.setColumns(10);

		textPesoPok = new JTextField();
		textPesoPok.setBounds(199, 409, 133, 30);
		frame.getContentPane().add(textPesoPok);
		textPesoPok.setColumns(10);
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

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textNamePok.setText(pokAct.getNombre());

				lblNumPoke.setText(String.valueOf(pokAct.getNumero()));
				String newTipo = new String(textTipoPok.getText());
				String altu = new String(textAltPok.getText());
				String peso = new String(textPesoPok.getText());
				String newCategoria = new String(CategoriaField.getText());
				String habil = new String(HabilidadField.getText());
				/*
				 * control de error en caso de que el tipo no coincida con los existentes
				 */
				if (buscartipo(newTipo)) {
					pokAct.setTipo(Tipo.valueOf(newTipo));

				} else {
					JOptionPane.showMessageDialog(textTipoPok,
							"Los tipos posibles son:Bicho, Dragón, Eléctrico, Hada, Lucha, Fuego,\n Volador, Fantasma, Planta, Tierra, Hielo, Normal, Veneno, Psíquico, Roca, Acero ,Agua");
				}
				/*
				 * comprobar que la categoria introducidad existe
				 */
				if (buscarcate(newCategoria)) {
					pokAct.setCategoria(Categoria.valueOf(newCategoria));
				} else {
					JOptionPane.showMessageDialog(CategoriaField,
							"Lagartija, Semilla, Tortuga, Gusano, Mariposa, Oruga, Capullo,\n Pajaro, Abeja, Raton, Serpiente, Cobra, Hada, Zorro,");
				}
				/*
				 * cambiar la altura
				 */
				try {
					double alt = Double.parseDouble(altu);
					if (alt / 1 == alt) {
						pokAct.setAltura(alt);
					}
				} catch (Exception a) {
					JOptionPane.showMessageDialog(textAltPok, "La altura introducidad no es un valor valido");
				}

				/*
				 * Cambiar el peso
				 */

				double pes = Double.parseDouble(peso);
				if (pes / 1 == pes) {
					pokAct.setPeso(pes);

				} else {
					JOptionPane.showMessageDialog(textAltPok, "la peso ha de ser un numero ");

				}
				/*
				 * cambiar la habilidad
				 */

				pokAct.setHabilidad(habil);
				
				
				pokemonDAO.update(pokAct);
				frame.dispose();
				new Pokedez();

			}
		});
		btnActualizar.setBounds(474, 480, 106, 33);
		frame.getContentPane().add(btnActualizar);

		lblPCategoriaPok = new JLabel("Categoria");
		lblPCategoriaPok.setHorizontalAlignment(SwingConstants.LEFT);
		lblPCategoriaPok.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPCategoriaPok.setBounds(411, 267, 95, 30);
		frame.getContentPane().add(lblPCategoriaPok);

		CategoriaField = new JTextField();
		CategoriaField.setText((String) null);
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
		HabilidadField.setColumns(10);
		HabilidadField.setBounds(538, 340, 133, 26);
		frame.getContentPane().add(HabilidadField);

		JLabel lblNumPok = new JLabel("N\u00BA");
		lblNumPok.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumPok.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumPok.setBounds(196, 56, 70, 46);
		frame.getContentPane().add(lblNumPok);

		btnPokedez = new JButton("Pokedez");
		btnPokedez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Pokedez();

			}
		});
		btnPokedez.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPokedez.setBounds(100, 480, 106, 33);
		frame.getContentPane().add(btnPokedez);
	}

	public void MostrarPok() {
		
			Pokemon a = pokAct;
			textNamePok.setText(a.getNombre());
			textTipoPok.setText(String.valueOf(a.getTipo()));
			textAltPok.setText(String.valueOf(a.getAltura()));
			textPesoPok.setText(String.valueOf(a.getPeso()));
			CategoriaField.setText(String.valueOf(a.getCategoria()));
			HabilidadField.setText(String.valueOf(a.getHabilidad()));
			lblNumPoke.setText(String.valueOf(a.getNumero()));

		
	}

	public boolean buscartipo(String a) {
		boolean mielda = false;

		enums.Tipo[] tipos = enums.Tipo.values();
		for (int i = 0; i < tipos.length; i++) {
			String t = new String(String.valueOf(tipos[i]));
			if (t.equals(a)) {

				mielda = true;
			}

		}

		return mielda;

	}

	public boolean buscarcate(String a) {
		boolean mielda = false;

		enums.Categoria[] cate = enums.Categoria.values();
		for (int i = 0; i < cate.length; i++) {
			String t = new String(String.valueOf(cate[i]));
			if (t.equals(a)) {

				mielda = true;
			}

		}

		return mielda;

	}
}
