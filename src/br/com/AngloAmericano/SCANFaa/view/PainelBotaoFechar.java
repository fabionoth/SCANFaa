package br.com.AngloAmericano.SCANFaa.view;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

import net.miginfocom.swing.MigLayout;
import br.com.AngloAmericano.SCANFaa.control.Desenha;


public class PainelBotaoFechar extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTabbedPane pane;
    private JButton botao;
    Desenha desenha;

    public PainelBotaoFechar(final JTabbedPane pane) {
        super(new MigLayout("", "0[]0[]0", "0[]0"));
        new Desenha();
        
        if (pane == null) {
            throw new NullPointerException("TabbedPane é null");
        }
        this.pane = pane;
        setOpaque(false);
        
        
        JLabel labelTitulo = new JLabel() {
            public String getText() {
                int i = pane.indexOfTabComponent(PainelBotaoFechar.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };
        
        add(labelTitulo);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        botao = new BotaoFechar();
        add(botao);
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

    private class BotaoFechar extends JButton implements ActionListener {


		public BotaoFechar() {
            int size = 17;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("Fechar");

            setUI(new BasicButtonUI());
            setContentAreaFilled(false);
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            setRolloverEnabled(true);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            int i = pane.indexOfTabComponent(PainelBotaoFechar.this);
            if (i != -1) {
                pane.removeTabAt(i);
            }
        }

        @Override
        public void updateUI() {
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            if (getModel().isRollover()) {
                g2.setColor(Color.RED);
                int delta = 5;
                g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
                g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
                g2.dispose();
            }
        }
    }

	public Desenha getDesenha() {
		return desenha;
	}

	public void setDesenha(Desenha desenha) {
		this.desenha = desenha;
	}
    

}


