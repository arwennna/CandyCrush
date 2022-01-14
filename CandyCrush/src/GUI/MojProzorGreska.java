package GUI;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Sara-Farah
 *
 */


/**
 * Prozor koji se otvara ukoliko je korisnik izvrsio pogresan potez.
 *
 */
public class MojProzorGreska extends JFrame {
	
	/**
	 * Poruka koja se ispisuje korisniku.
	 */
	JLabel poruka = new JLabel("Nemoguc potez. Pokusajte ponovo.");
	
	/**
	 * Konstruktor za ovaj prozor.
	 */
	public MojProzorGreska() {
		build();
	}
	
	/**
	 * Metoda koja postavlja vidljivost, dimenziju, poziciju prozora. Postavlja odgovarajucu poruku.
	 */
	private void build() {
		this.setVisible(true);
		this.setSize(300, 70);
		this.setLayout(new GridLayout(1, 1));
		this.add(poruka);
		this.setLocationRelativeTo(null);
	}

}
