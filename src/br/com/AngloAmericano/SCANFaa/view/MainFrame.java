package br.com.AngloAmericano.SCANFaa.view;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


import net.miginfocom.swing.MigLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class MainFrame {

	public JFrame frmScanfaaSistema;
	private TabbedPane tabbedPane;

	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmScanfaaSistema = new JFrame();
		frmScanfaaSistema.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/br/com/AngloAmericano/SCANFaa/icons/special-offer.png")));
		frmScanfaaSistema
				.setTitle("SCANFaa - Sistema de Calculo Num\u00E9rico da FAA");
		frmScanfaaSistema.setSize(800, 600);
		frmScanfaaSistema.setResizable(true);
		criarMenu();
		frmScanfaaSistema.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmScanfaaSistema.getContentPane().setLayout(
				new MigLayout("", "[]", "[]"));
		this.tabbedPane = new TabbedPane();
		frmScanfaaSistema.getContentPane().add(this.tabbedPane,
				"w 100%, h 100%");
	}

	private void abrirArquivo() {
		this.tabbedPane.addTab();
	}

	private void sair() {
		System.exit(0);
	}

	private void adicionaFuncao() {
		this.tabbedPane.editTab();
	}
	private void dicotomia(){
		this.tabbedPane.dicotomia();
	}

	// Métodos que criam Menu

	/**
	 * Launch the application.
	 */

	public TabbedPane gettabbedPane() {
		return tabbedPane;
	}

	public void settabbedPane(TabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	private JMenuBar criarMenu() {

		JMenuBar menuBar = new JMenuBar();
		frmScanfaaSistema.setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setIcon(new ImageIcon(MainFrame.class.getResource("/br/com/AngloAmericano/SCANFaa/icons/archives.png")));
		menuBar.add(mnArquivo);

		JMenuItem mntmNovoGrfico = new JMenuItem("Novo Gr\u00E1fico");

		mntmNovoGrfico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirArquivo();
			}
		});

		mntmNovoGrfico.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mnArquivo.add(mntmNovoGrfico);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});

		JMenuItem mntmAdicionarFuno = new JMenuItem(
				"Adicionar Fun\u00E7\u00E3o");
		mntmAdicionarFuno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionaFuncao();
			}
		});

		mntmAdicionarFuno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		mnArquivo.add(mntmAdicionarFuno);
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				InputEvent.ALT_MASK));
		mnArquivo.add(mntmSair);

		JMenu mnMetodos = new JMenu("Metodos");
		mnMetodos.setIcon(new ImageIcon(MainFrame.class.getResource("/br/com/AngloAmericano/SCANFaa/icons/edit.png")));
		menuBar.add(mnMetodos);

		JMenuItem mntmDicotomia = new JMenuItem("Dicotomia");
		mntmDicotomia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dicotomia();
			}
		});
		mntmDicotomia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnMetodos.add(mntmDicotomia);

		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setIcon(new ImageIcon(MainFrame.class.getResource("/br/com/AngloAmericano/SCANFaa/icons/help-icon.png")));
		menuBar.add(mnAjuda);

		JMenuItem mntmSobre = new JMenuItem("Sobre ");
		mntmSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnAjuda.add(mntmSobre);

		JMenuItem mntmCrditos = new JMenuItem("Cr\u00E9ditos");
		mnAjuda.add(mntmCrditos);
		return menuBar;
	}
}
