package GUI;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Sara-Farah
 *
 */

/**
 * Prozor kojim korisniku oznacavamo da je kraj igre.
 *
 */
public class MojProzorGotovaIgra extends JFrame {
	
	/**
	 * Poruka koja se ispisuje korisniku.
	 */
	JLabel poruka;
	
	/**
	 * Rezultat nakon odigrane igre.
	 */
	int rezultat;

	/**
	 * Metoda koja postavlja vrijednost rezultata, namjesta layout prozora, dodaje odgovarajucu poruku.
	 * @param rezultat1 Rezultat koji je korisnik ostvario tokom igrice.
	 */
	private void build(int rezultat1) {
		rezultat = rezultat1;
		this.setVisible(true);
		this.setSize(300, 70);
		this.setLayout(new GridLayout(1, 1));
		String s = "Gotova igra. Vas rezultat iznosi " + rezultat + " bodova.";
		poruka = new JLabel(s);
		this.add(poruka);
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Konstruktor za ovaj prozor.
	 * @param rezultat1 Rezultat koji je korisnik ostvario tokom igrice.
	 */
	public MojProzorGotovaIgra(int rezultat1) {
		build(rezultat1);
	}

}
