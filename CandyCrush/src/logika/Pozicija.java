package logika;

/**
 * @author Sara-Farah
 *
 */

/**
 * Ovom klasom oznacavamo pozicije odabranih polja koja potencijalno trebaju biti zamijenjena.
 */
public class Pozicija {
	
	/**
	 * x koordinata razmatrane pozicije.
	 */
	private int x;
	/**
	 * y koordinata razmatrane pozicije.
	 */
	private int y;
	
	/**
	 * Konstruktor za klasu Pozicija.
	 * @param x1 x koordinata pozicije.
	 * @param y1 y koordinata pozicije.
	 */
	public Pozicija(int x1, int y1) {
		x = x1;
		y = y1;
	}
	
	/**
	 * Getter za x koordinatu.
	 * @return x koordinata.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter za y koordinatu
	 * @return y koordinata
	 */
	public int getY() {
		return y;
	}
	
}
