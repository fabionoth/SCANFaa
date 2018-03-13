package br.com.AngloAmericano.SCANFaa.view;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import br.com.AngloAmericano.SCANFaa.control.Desenha;
import br.com.AngloAmericano.SCANFaa.control.Valida;

public class TabbedPane extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	NumberFormat formataValor = DecimalFormat.getCurrencyInstance(new Locale(
			"pt", "BR"));
	Desenha desenha;
	int index;
	Valida v = new Valida();

	public TabbedPane() {
		super();
		this.setVisible(false);
	}

	public void addTab() {
		int i = this.getTabCount() + 1;
		desenha = new Desenha();
		desenha.setBackground(Color.white);
		this.addTab("Gráfico " + i, desenha);
		index = this.getTabCount() - 1;
		final PainelBotaoFechar pbf = new PainelBotaoFechar(this);
		pbf.setDesenha(desenha);
		this.setTabComponentAt(index, pbf);
		this.setSelectedIndex(index);
		this.setVisible(true);
	}

	public void editTab() {
		double[] funcao = getFuncPane();
		if(funcao == null){
			return;
		}
		index = this.getSelectedIndex();
		
		((PainelBotaoFechar) getTabComponentAt(index)).getDesenha().setFun(
				funcao);
		this.repaint();
	}

	public void dicotomia() {
		getIntervalo();
	}

	public double[] getFuncPane() {
		double[] func = null;
		int tabCount = this.getTabCount();
		if (tabCount <= 0) {
			JOptionPane.showMessageDialog(null, "Crie um gráfico", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			String text = "Informe o grau da Função Polinomial: \n(Digito Inteiro): \nEx: \nGrau um: \nF(x) = a0(x)^0 + a1(x)^1\nGrau dois: \nF(x) = a0(x)^0 + a1(x)^1 + a2(x)^2";
			String valor = JOptionPane.showInputDialog(text, "");
			while (v.validaString(valor) == false) {
				valor = JOptionPane.showInputDialog(null, text, "Novamente",
						JOptionPane.WARNING_MESSAGE);
			}
			int val = Integer.parseInt(valor) + 1;
			func = new double[val];
			String f = null;
			for (int i = 0; i < val; i++) {
				f = JOptionPane.showInputDialog("Informe o valor a" + i + ": ")
						.replaceAll("\\.", "").replace(',', '.');
				while (v.validaS(f) == false) {
					f = JOptionPane
							.showInputDialog(null,
									"Informe o valor a" + i + ": (Digito)",
									"Novamente", JOptionPane.WARNING_MESSAGE)
							.replaceAll("\\.", "").replace(',', '.');
				}
				func[i] = Double.parseDouble(f);
			}

		}
		return func;
	}

	public void getIntervalo() {
		index = this.getSelectedIndex();
		try {
			if (index == -1) {
				JOptionPane.showMessageDialog(null,
						"O sistema não possui gráfico", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if ( ((PainelBotaoFechar) getTabComponentAt(index))
					.getDesenha().getFun() == null) {
				JOptionPane.showMessageDialog(null,
						"O gráfico não possui função", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				float a; // Intervalo Inicial
				float b; // Intervalo Final
				float precisao;
				
				String textA = "Informe o intervalo A: \n (menor valor) | ex: 2.3 ou 1";
				String textB = "Informe o intervalo B: \n (maior valor) | ex: 1.3 ou 7";
				String textPrecisao = "Informe a precisção desejada: \nEx: 0,1 | 0,01 | 0,001";
				String stringPrecisao = JOptionPane.showInputDialog(textPrecisao)
						.replace(',', '.');
				while (v.validaS(stringPrecisao) == false) {
					stringPrecisao = JOptionPane
							.showInputDialog(null, textB, "Novamente",
									JOptionPane.WARNING_MESSAGE)
							.replaceAll("\\.", "").replace(',', '.');
					System.out.println(stringPrecisao);
				}
				String stringA = JOptionPane.showInputDialog(textA)
						.replace(',', '.');
				while (v.validaS(stringA) == false) {
					stringA = JOptionPane
							.showInputDialog(null, textA, "Novamente",
									JOptionPane.WARNING_MESSAGE)
							.replaceAll("\\.", "").replace(',', '.');
					System.out.println(stringA);
				}
				String stringB = JOptionPane.showInputDialog(textB)
						.replace(',', '.');
				while (v.validaS(stringB) == false) {
					stringB = JOptionPane
							.showInputDialog(null, textB, "Novamente",
									JOptionPane.WARNING_MESSAGE)
							.replaceAll("\\.", "").replace(',', '.');
					System.out.println(stringB);
				}
				a = Float.parseFloat(stringA);
				b = Float.parseFloat(stringB);
				precisao = Float.parseFloat(stringPrecisao);
				((PainelBotaoFechar) getTabComponentAt(index)).getDesenha()
						.setIntervA(a);
				((PainelBotaoFechar) getTabComponentAt(index)).getDesenha()
						.setIntervB(b);
				((PainelBotaoFechar) getTabComponentAt(index)).getDesenha()
				.setPrecisao(precisao);
				this.repaint();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O sistema não possui gráfico",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

}