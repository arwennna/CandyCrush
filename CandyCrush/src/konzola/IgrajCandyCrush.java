package konzola;

import java.util.Scanner;

import logika.CandyCrush;
import logika.Pozicija;

/**
 * @author Sara-Farah
 *
 */

/**
 * Klasa pomocu koje korisniku dozvoljavamo da igra igricu kroz konzolu.
 *
 */
public class IgrajCandyCrush {

	/**
	 * U main-u omogucavamo igru kroz konzolu.
	 * @param args Argumenti main-a.
	 */
	public static void main(String[] args) {
			
		Scanner sc = new Scanner(System.in);
		Pozicija prva;
		Pozicija druga;
		int tip;
		int x;
		int y;
		
		System.out.println("Unesite zeljeni tip igrice. Podrzani su tipovi 1 i 2.");
		tip = sc.nextInt();
		
		CandyCrush igrica = new CandyCrush(tip);
		int br_poteza = igrica.getPotezi();
		
		while (br_poteza >= 0) {
			igrica.ispisiPlocu();
			
			System.out.println("Unesite x koordinatu prve pozicije.");
			x = sc.nextInt();
			System.out.println("Unesite y koordinatu prve pozicije.");
			y = sc.nextInt();
			prva = new Pozicija(x,y);
			
			System.out.println("Unesite x koordinatu druge pozicije.");
			x = sc.nextInt();
			System.out.println("Unesite y koordinatu druge pozicije.");
			y = sc.nextInt();
			druga = new Pozicija(x,y);
			
			if (igrica.jeLiLegalanPotez(prva, druga)) {
				igrica.izvrsiPotez(prva, druga);
				br_poteza--;
			} else {
				System.out.println("Potez nije moguc. Pokusajte ponovo.");
			}
			igrica.ispisiPlocu();
			System.out.println("Rezultat je " + igrica.getRezultat());
			System.out.println("Broj preostalih poteza je " + br_poteza + ".");
			
			if (br_poteza == 0) {
				System.out.println("Igrica je gotova. Ukoliko zelite ponovo igrati, unesite zeljeni tip igrice. Podrzani su tipovi 1 i 2.");
				tip = sc.nextInt();
				if (tip != 1 || tip !=2) {
					System.exit(0);
				}
				igrica = new CandyCrush(tip);
				br_poteza = igrica.getPotezi();
			}
		}
	}
}
