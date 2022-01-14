package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Sara-Farah
 *
 */

/**
 * Klasa za objekat - prozor koji se otvara prilikom pruzanja opcije za novu igru.
 *
 */
public class MojProzorNovaIgra extends JFrame {
	/**
	 * Panel u koji se smjesta sadrzaj prozora.
	 */
	JPanel panel;
	
	/**
	 * Dugme koje aktivira prvi tip igrice.
	 */
	JButton dugme1;
	/**
	 * Dugme koje aktivira drugi tip igrice.
	 */
	JButton dugme2;
	
	/**
	 * Poruka koja se ispisuje korisniku.
	 */
	JLabel poruka;
	
	/**
	 * Varijabla koja odgovara tipu odabrane igrice.
	 */
	int tip;
	
	/**
	 * Metoda pomocu koje se konstruise sadrzaj prozora.
	 */
	private void build() {
		
		this.setVisible(true);
		this.setSize(300, 70);
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		poruka = new JLabel("Odaberite level");
		
		dugme1 = new JButton("1");
		dugme2 = new JButton("2");
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.BOTH;
		
		panel.add(poruka, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		
		panel.add(dugme1, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		
		panel.add(dugme2, c);
		
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		
		panel.add(dugme2, c);
		
		add(panel);
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Getter za prvo dugme.
	 * @return Prvo dugme.
	 */
	public JButton getDugme1() {
		return dugme1;
	}
	
	/**
	 * Getter za drugo dugme.
	 * @return Drugo dugme.
	 */
	public JButton getDugme2() {
		return dugme2;
	}
	
	/**
	 * Konstruktor za prozor kojim se pokrece nova igra.
	 */
	public MojProzorNovaIgra() {
		build();
	}
	
	/**
	 * Getter za tip igre.
	 * @return Tip igre.
	 */
	public int getTip() {
		return tip;
	}


}