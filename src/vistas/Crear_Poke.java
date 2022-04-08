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

public class Crear_Poke {

	private JFrame frame;
	private PokemonDAO pokemonDAO;
	

	private JTextField textNamePok;
	private JTextField textTipoPok;
	private JTextField textAltPok;
	private JTextField textPesoPok;
	private int index;
	private JLabel lblNombre;
	private JLabel lblPesoPok;
	private JLabel lblTipoPok;
	private JLabel lblAltPok;
	private JButton btnActualizar;
	private JLabel lblPCategoriaPok;
	private JTextField CategoriaField;
	private JTextField HabilidadField;
	private JButton btnPokedez;
	private JTextField numField;

	/**
	 * Create the application.
	 */
	public Crear_Poke() {
		initialize();
		this.pokemonDAO= new PokemonDAO();

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

		btnActualizar = new JButton("Crear");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String namepok = new String(textNamePok.getText());
				String newNum = new String(numField.getText());
				String newTipo = new String(textTipoPok.getText());
				String altu = new String(textAltPok.getText());
				String peso = new String(textPesoPok.getText());
				String newCategoria = new String(CategoriaField.getText());
				String habil = new String(HabilidadField.getText());
				Pokemon newPoke = new Pokemon(index, null, null, index, index, null, null);

				/*
				 * comprobacion de la existencia del nombre del nuevo pokemon
				 */

				if (!pokemonDAO.compNom(namepok)) {
					newPoke.setNombre(namepok);
				}
				/*
				 * comprobaciones de la existencia del numero del pokemon creado
				 */

				try {
					int num = Integer.parseInt(newNum);
					if (!pokemonDAO.compNum(num)) {
						newPoke.setNumero(num);
					} else {
						JOptionPane.showMessageDialog(numField, "ese numero ya esta en uso");
					}
				} catch (Exception a) {
					JOptionPane.showMessageDialog(textAltPok, "El valor introducido no es un valor valido");
				}

				/*
				 * control de error en caso de que el tipo no coincida con los existentes
				 */
				if (buscartipo(newTipo)) {
					newPoke.setTipo(Tipo.valueOf(newTipo));

				} else {
					JOptionPane.showMessageDialog(textTipoPok,
							"Los tipos posibles son:Bicho, Dragón, Eléctrico, Hada, Lucha, Fuego,\n Volador, Fantasma, Planta, Tierra, Hielo, Normal, Veneno, Psíquico, Roca, Acero ,Agua");
				}
				/*
				 * comprobar que la categoria introducidad existe
				 */
				if (buscarcate(newCategoria)) {
					newPoke.setCategoria(Categoria.valueOf(newCategoria));
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
						newPoke.setAltura(alt);
					}
				} catch (Exception a) {
					JOptionPane.showMessageDialog(textAltPok, "La altura introducidad no es un valor valido");
				}

				/*
				 * Cambiar el peso
				 */

				double pes = Double.parseDouble(peso);
				if (pes / 1 == pes) {
					newPoke.setPeso(pes);

				} else {
					JOptionPane.showMessageDialog(textAltPok, "el peso ha de ser un numero ");

				}
				/*
				 * cambiar la habilidad
				 */

				newPoke.setHabilidad(habil);

				pokemonDAO.insert(newPoke);
				JOptionPane.showMessageDialog(lblNombre, "Pokemon añadido a la pokedez");

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

		numField = new JTextField();
		numField.setBounds(316, 46, 96, 52);
		frame.getContentPane().add(numField);
		numField.setColumns(10);
	}

	public void MostrarPok() {

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
