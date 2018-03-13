package br.com.AngloAmericano.SCANFaa.control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;

import javax.swing.JPanel;

/**
 * 
 * @author fabio
 */

public class Desenha extends JPanel {
	// Variáveis para uso Geral
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	Dimension d = new Dimension();

	// Variável para Proporção do Desenho
	double var = 31.00F;
	double rWidth = var;
	double rHeight = var;

	// Variáveis para uso do Desenho;
	double hWidth; // Metade da Largura da Tela (Computacional)
	double hHeight; // Metade da Altura da Tela (Computacional)

	int maxX; // Largura da Tela (Computacional)
	int maxY; // Largura da Tela (Computacional)

	double pixelWidth; // Largura de um Pixel na Tela (Real)
	double pixelHeight; // Altura de um Pixel na Tela (Real)

	// Variável para o Desenho da Função
	double[] fun = null; // Função para ser Desenhada

	// Varíavel para aplicar o método da dicotomia
	double intervA = 0;
	double intervB = 0;
	double precisao = 0;
	
	int k;
	double xm;

	
	

	/**
	 *  Constructor para não receber Funções
	 */
	public Desenha() {
	}

	// Inicializa todos os Valores (Reais e Computacionais)
	/**
	 * 
	 */
	void init() {
		maxX = d.width - 1;
		maxY = d.height - 1;
		pixelWidth = rWidth / maxX;
		pixelHeight = rHeight / maxY;
		hWidth = maxX / 2;
		hHeight = maxY / 2;
	}

	// Método que desenha na tela
	@Override
	/**
	 * descrever metodo
	 * 
	 */
	protected void paintComponent(Graphics g) {
		Graphics2D g2d;
		super.paintComponent(g);
		d = this.getSize();
		g2d = (Graphics2D) g.create();
		desenhaFundo(g2d, rHeight, rWidth, d);
		desenhaFuncao(g2d);
		dicotomia(g2d);
	}

	// Método que Desenha o Fundo do Gráfico com suas devidas Linhas e Colunas
	public void desenhaFundo(Graphics2D g2d, double rHeight, double rWidth,
			Dimension d) {
		init();

		g2d.setColor(Color.gray);
		g2d.setStroke(new BasicStroke(1));

		int aux = 0;
		while (aux < var / 2) {
			Line2D hLine = new Line2D.Double(hWidth - 4, iY(aux), hWidth + 4,
					iY(aux));
			Line2D hLineDown = new Line2D.Double(hWidth - 4, iY(aux * (-1)),
					hWidth + 4, iY(aux * (-1)));
			Line2D vLine = new Line2D.Double(iX(aux), hHeight - 4, iX(aux),
					hHeight + 4);
			Line2D vLineDown = new Line2D.Double(iX(aux * (-1)), hHeight - 4,
					iX(aux * (-1)), hHeight + 4);
			g2d.draw(hLine);
			g2d.draw(hLineDown);
			g2d.draw(vLine);
			g2d.draw(vLineDown);

			aux++;
		}
		for (int i = 0; i < maxY; i++) {
			if (fy(i) == 0) {
				g2d.setColor(Color.black);
				g2d.setStroke(new BasicStroke(1));
				Line2D line = new Line2D.Double(0, i, maxX, i);
				g2d.draw(line);
			}
		}
		for (int i = 0; i < maxX; i++) {
			if (fx(i) == 0) {
				g2d.setColor(Color.black);
				g2d.setStroke(new BasicStroke(1));
				Line2D line = new Line2D.Double(i, 0, i, maxY);
				g2d.draw(line);
			}
		}
	}

	// Caso a função seja diferente de Null, ela sera desenhada na tela e suas
	// devidas Funções;
	public void desenhaFuncao(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(1));
		if (fun == null) {
			return;
		}
		String s = "Sua função é: ";
		for (int i = 0; i < fun.length; i++) {
			if (i == 0) {
				s += "(" + fun[i] + "*X^" + i + ")";
			} else {
				s += "+(" + fun[i] + "*X^" + i + ")";
			}
		}
		g2d.setColor(Color.red);
		g2d.drawString(s, 10, maxY - 30);
		g2d.setColor(Color.BLACK);
		for (int i = 0; i < maxX; i++) {
			if (i + 1 == maxX) {
				return;
			}
			double rYFunc = calcFuncao(fx(i), fun);
			double rYFunca = calcFuncao(fx(i + 1), fun);
			Line2D line = new Line2D.Double(i, iY(rYFunc), i + 1, iY(rYFunca));
			g2d.draw(line);
		}
	}

	// Este método retora um Y para cada X enviado.
	public double calcFuncao(double e, double[] fun) {
		double y = 0;
		int exp = 0;
		while (exp < fun.length) {
			y += fun[exp] * (Math.pow(e, exp));
			exp++;
		}
		return y;
	}

	// Método que faz Implementa a Dicotomia e descreve na tela
	public void dicotomia(Graphics2D g2d) {
		if (fun == null) {
			return;
		} else if (intervA == 0 && intervB == 0) {
			return;
		} else {
//			double precisao = 0.0001; // Precisão do Cálculo da Dicotomia
			k = 0; // Número de Interações
			xm = 0;
			g2d.setColor(Color.black);
			g2d.drawString(
					"Fase I - Isolamento da Raíz (De acordo com intervalo)",
					10, 10);
			g2d.drawString("- valores " + intervA + "; " + intervB, 20, 25);
			g2d.setColor(Color.gray);
			float[] dash = { 2f };
			g2d.setStroke(new BasicStroke(0.1f, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND, 0, dash, 0f));
			Line2D lineA = new Line2D.Double(iX(intervA), 0, iX(intervA), maxY);
			Line2D lineB = new Line2D.Double(iX(intervB), 0, iX(intervB), maxY);
			g2d.draw(lineA);
			g2d.draw(lineB);
			if (calcFuncao(intervA, fun) * calcFuncao(intervB, fun) < 0) {
				g2d.setColor(Color.blue);
				g2d.drawString(
						"- F(a)*F(b) < 0 - O Intervalo possui uma ou mais raizes",
						20, 40);
				g2d.setColor(Color.black);
			} else {
				g2d.setColor(Color.red);
				g2d.drawString("- F(a)*F(b) > 0 - Talvez não exista uma raiz",
						20, 40);
				g2d.setColor(Color.black);
			}
			g2d.drawString("Fase II - Refinando | Critério de Parada (K)", 10,
					55);
			DecimalFormat format = new DecimalFormat("0.0000");
			g2d.drawString("- Precisão: " + format.format(precisao), 20, 70);
			g2d.drawString("- K > (Log(" + intervB + " - " + intervA
					+ ") - Log(" + format.format(precisao) + "))/Log(2)", 20,
					85);
			while (k < (Math.log(intervB - intervA) - Math.log(precisao))
					/ Math.log(2)) {
				k++;
			}
			g2d.drawString("- K = " + k, 20, 100);
			
			
			
			
			
			
			g2d.drawString("Fase III - Método Aplicado", 10, 115);
			DecimalFormat decimal = new DecimalFormat("0.0000");
			
			//Dicotomia
			double a = intervA;
			double b = intervB;
			int i = 1;
			double fa = calcFuncao(a, fun);
			double x = 0;
			double fx = 0;
			int valor = 130;
			while (i < k) {
				x = (a + b)/2;
				fx = calcFuncao(x, fun);
				if(fx == 0 || (b - a) < precisao){
					k = i;
					break;
				}else {
					if(fa * fx > 0){
						a = x; 
						fa = fx;
					}else {
						b = x;
					}
					i++;
				}
				String text = "A = "+decimal.format(a)+" | X = "+decimal.format(x)+" | B = "+decimal.format(b);
				g2d.drawString(text, 20, valor);
				valor = valor + 15;
			}
			g2d.drawString("Apos " + k+ " interações a raiz é: " + decimal.format(x), 20, valor);
		}
	}

	// Retorna a Largura Computacional enviando um valor Real
	public int iX(double x) {
		return (int) Math.round((x / pixelWidth) + hWidth);
	}

	// Retorna a Altura Computacional enviando um valor Real
	public int iY(double y) {
		return (int) (maxY - Math.round((y / pixelHeight) + hHeight));
	}

	// Retorna a Altura Real enviando um valor Computacional
	public double fx(int x) {
		return (x * pixelWidth) - (rWidth / 2);
	}

	// Retorna a Largura Real enviando um valor Computacional
	public double fy(int y) {
		return (maxY - y) * pixelHeight - (rHeight / 2);
	}

	// Gets e Sets de Funções
	public double[] getFun() {
		return fun;
	}

	public void setFun(double[] fun) {
		this.fun = fun;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public double getVar() {
		return var;
	}

	public void setVar(float var) {
		this.var = var;
	}

	public double getIntervA() {
		return intervA;
	}

	public void setIntervA(double intervA) {
		this.intervA = intervA;
	}

	public double getIntervB() {
		return intervB;
	}

	public void setIntervB(double intervB) {
		this.intervB = intervB;
	}
	public double getPrecisao() {
		return precisao;
	}

	public void setPrecisao(double precisao) {
		this.precisao = precisao;
	}
}
