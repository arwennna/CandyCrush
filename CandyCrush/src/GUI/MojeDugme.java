package GUI;

import java.awt.Color;

import javax.swing.JButton;

/**
 * @author Sara-Farah
 */

public class MojeDugme extends JButton {
	
	/**
	 * x koordinata dugmeta.
	 */
	private int x;
	
	/**
	 * y koordinata dugmeta.
	 */
	private int y;
	
	/**
	 * Boja dodijeljena dugmetu.
	 */
	private int boja;
	
	/**
	 * Konstruktor za klasu MojeDugme. Pravi novo dugme sa odgovarajucim parametrima.
	 * @param x1 x koordinata dugmeta. 
	 * @param y1 y koordinata dugmeta.
	 * @param boja1 Boja dugmeta.
	 */
	public MojeDugme(int x1, int y1, int boja1) {
		super();
		x = x1;
		y = y1;
		boja = boja1;
		oboji(boja);
	}
	
	/**
	 * Getter za x koordinatu. Naziv odstupa od ostalih jer u suprotnom dolazi do konflikta sa getterom naziva "getX()" koji je implementiran za 
	 * klasu JButton, koju ova klasa nasljedjuje.
	 * @return x koordinata dugmeta.
	 */
	public int vratiX() {
		return x;
	}

	/**
	 * Getter za y koordinatu. Naziv odstupa od ostalih jer u suprotnom dolazi do konflikta sa getterom naziva "getY()" koji je implementiran za 
	 * klasu JButton, koju ova klasa nasljedjuje.
	 * @return y koordinata dugmeta.
	 */
	public int vratiY() {
		return y;
	}
	
	/**
	 * Getter za boju dugmeta.
	 * @return Boja dugmeta.
	 */
	public int getBoja() {
		return boja;
	}
	
	/**
	 * Setter za boju dugmeta.
	 * @param boja1 Boja koju postavljamo.
	 */
	public void setBoja(int boja1) {
		boja = boja1;
		oboji(boja);
	}
	
	/**
	 * Setter za x koordinatu.
	 * @param x1 x koordinata koju postavljamo.
	 */
	public void setX(int x1) {
		x = x1;
	}
	
	/**
	 * Setter za y koordinatu.
	 * @param y1 y koordinata koju postavljamo.
	 */
	public void setY(int y1) {
		y = y1;
	}
	
	/**
	 * Metoda kojom JButton-ima dodjeljujemo RGB vrijednosti boja kojim ih bojimo.
	 * @param vrijednost Brojcana vrijednost koja odgovara boji kojom bojimo dugme.
	 */
	private void oboji(int vrijednost) {
		switch (vrijednost) {
		  case 0:
			this.setBackground(new Color(245, 245, 245));
		    break;
		  case 1:
			  this.setBackground(new Color(72, 181, 163));
		    break;
		  case 2:
			  this.setBackground(new Color(210, 145, 188 ));
		    break;
		  case 3:
			  this.setBackground(new Color(100, 87, 82));
		    break;
		  case 4:
			  this.setBackground(new Color(254, 235, 201));
		    break;
		  case 5:
			  this.setBackground(new Color(191, 213, 232));
		    break;

		}
	}

}
