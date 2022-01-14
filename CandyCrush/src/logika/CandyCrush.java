package logika;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Sara-Farah
 */

/**
 * Glavna klasa. Sadrzi informacije o parametrima igre - dimenziji ploce, tipu igre, itd. Sadrzi metode za provjeru i 
 * izvrsenje poteza.
 */
public class CandyCrush {

	/**
	 * Broj razlicitih tipova slatkisa u levelu.
	 */
	int slatkisi;
	
	/**
	 * Visina ploce u levelu.
	 */
	int visina; 
	
	/**
	 * Sirina ploce u levelu.
	 */
	int sirina;
	
	/**
	 * Broj dostupnih poteza u levelu.
	 */
	int brojPoteza; 
	
	
	/**
	 * Odrednica za tip igre.
	 */
	int tip;

	/**
	 * Matrica u koju spasavamo sadrzaj igrace ploce.
	 */
	int[][] plocaZaIgru;
	
	/**
	 * Do sada ostvareni bodovi u igrici.
	 */
	int rezultat = 0;

	/**
	 * Konstruktor za glavnu klasu. U sklopu konstruktora dodjeljuju se vrijednosti dobavljene iz specifikacijskih file-ova, te se ploca priprema za igru.
	 * @param tip Oznacava jedan od tipova igre koji su specificirani.
	 */
	public CandyCrush(int tip) {
		super(); 
		this.tip = tip;
		int [] specifikacije = vratiSpecifikacije();
		slatkisi = specifikacije[0];
		sirina = specifikacije[1];
		visina = specifikacije[2];
		brojPoteza = specifikacije[3];
		plocaZaIgru = new int [visina][sirina];
		popuniPlocu();
		ocistiCijeluPlocu();
	}

	/**
	 * Metoda kojom plocu za igru popunjavamo random vrijednostima ali u odgovarajucem opsegu.
	 */
	private void popuniPlocu() {
		for (int i=0; i< visina; i++)
			for (int j=0; j< sirina; j++) {
				int pom = (int)(Math.random()*(slatkisi));
				plocaZaIgru[i][j] = pom;
			} 
	}
	
	
	/**
	 * Tokom igre, nakon validno odigranog poteza, polja na kojima je konstruisana neka od figura koja treba da se ukloni, (tj. niz 
	 * ili kolona sa tri ili vise uzastopnih, istovrijednosnih slatkisa) trebaju da se poniste, te popune novim vrijednostima. U prvoj
	 * fazi njima su dodijeljene vrijednosti -1. Ovom metodom generisemo nove vrijednosti na takvim poljima.
	 */
	private void popraviPlocu() {
		for (int i=0; i<visina; i++)
			for (int j=0; j<sirina; j++) {
				if (plocaZaIgru[i][j] < 0) {
					int pom = (int)(Math.random()*(slatkisi));
					plocaZaIgru[i][j] = pom;
				}
			} 
	}
	
	/**
	 * Metoda za ispis sadrzaja ploce.
	 */
	public void ispisiPlocu() {
		for (int i=0; i<visina; i++) {
			for (int j=0; j<sirina; j++) {
				System.out.print(plocaZaIgru[i][j] + " ");
			}
			System.out.println("");
		}	
	}
	
	/**
	 * Getter za broj poteza u igrici.
	 * @return Broj specificiranih poteza.
	 */
	public int getPotezi() {
		return brojPoteza;
	}
	
	/**
	 * Getter za rezultat - osvojene bodove.
	 * @return Trenutni rezultat.
	 */
	public int getRezultat() {
		return rezultat;
	}
	
	/**
	 * Getter za plocu.
	 * @return Sadrzaj ploce.
	 */
	public int[][] getPloca() {
		return plocaZaIgru;
	}
	
	/**
	 * Dobavljanje specifikacija iz specifikacijskih file-ova.
	 * @return Niz sa vrijednostima koje se odnose na specifikacijske parametre igrice.
	 */
	private int [] vratiSpecifikacije() {
		int zaVracanje[] = new int[4];
		String putanjaDoFoldera = "tipovi/";
		int brojBoja = 0;
		int dimX = 0;
		int dimY = 0;
		int brojPoteza = 0;
		try {
			Scanner sc = new Scanner(new File(putanjaDoFoldera + "tip" + tip + "/setup"));
			brojBoja = sc.nextInt();
			zaVracanje[0] = brojBoja;
			dimX = sc.nextInt();
			zaVracanje[1] = dimX;
			dimY = sc.nextInt();
			zaVracanje[2] = dimY;
			brojPoteza = sc.nextInt();
			zaVracanje[3] = brojPoteza;
			sc.close();	
		} catch (FileNotFoundException e) {
			zaVracanje[0] = 5;
			zaVracanje[1] = 10;
			zaVracanje[2] = 10;
			zaVracanje[3] = 20;
		}
		return zaVracanje;
	}
	
	/**
	 * Ovom varijablom oznacavamo je li potez stvorio horizontalnu ili vertikalnu figuru:
	 * 0 - vertikalna, 1 - horizontalna.
	 */
	int potez;
		
	/**
	 * Getter za visinu - broj vertikalnih polja.
	 * @return Visina ploce.
	 */
	public int getVisina() {
		return this.visina;
	}
	
	/**
	 * Getter za sirinu - broj horizontalnih polja.
	 * @return Sirina ploce.
	 */
	public int getSirina() {
		return this.sirina;
	}
	
	/**
	 * Ako bilo koji od poteza ukljucuje neku od pozicija koja izlazi van opsega ploce, vracamo false.
	 * @param min_x Minimalna x koordinata.
	 * @param min_y Minimalna y koordinata.
	 * @param max_x Maksimalna x koordinata.
	 * @param max_y Maksimalna y koordinata.
	 * @param x_1 x koordinata prve pozicije.
	 * @param x_2 x koordinata druge pozicije.
	 * @param y_1 y koordinata prve pozicije.
	 * @param y_2 y koordinata druge pozicije.
	 * @return Ako su pozicije u zadanom opsegu, vracamo true. U suprotnom false.
	 */
	private boolean daLiJeUOpsegu(int min_x, int min_y, int max_x, int max_y, int x_1, int x_2, int y_1, int y_2) {
		if((x_1 < min_x) || (y_1 < min_y) || (x_2 < min_x) || (y_2 < min_y) || 
			(x_1 > max_x) || (y_1 > max_y) || (x_2 > max_x) || (y_2 > max_y)) {
				return false;
			}
		return true;
	}
	
	/**
	 * Ako odabrane pozicije nisu ni u istom redu ni u istoj koloni onda potez nema smisla.
	 * @param x_1 x koordinata prve pozicije.
	 * @param x_2 x koordinata druge pozicije.
	 * @param y_1 y koordinata prve pozicije.
	 * @param y_2 y koordinata druge pozicije.
	 * @return Ako se razlikuju i kolona i red tada je potez ilegalan i vracamo false. U suprotnom true.
	 */
	private boolean daLiSuLegalneKoloneIRedovi(int x_1, int x_2, int y_1, int y_2) {
		 if(x_1 != x_2 && y_1 != y_2) {
			return false;
		}
		return true;
	}
	
	/**
	 * Provjera da li su odabrane pozicije u odgovarajucem odnosu, tj. ako su u istom redu onda im se kolona mora razlikovati za 1,
	 * odnosno ako su u istoj koloni onda im se red mora razlikovati za 1.
	 * U zavisnosti od provjere kolona odnosno redova parametri imaju drugacije znacenje.
	 * @param prva_a x/y koordinata prve pozicije.
	 * @param druga_a x/y koordinata druge pozicije.
	 * @param prva_b x/y koordinata prve pozicije.
	 * @param druga_b x/y koordinata druge pozicije.
	 * @return Ako jesu u odgovarajucem odnosu u redu odnosno koloni, vracamo true.
	 */
	private boolean daLiSuLegalneKoloneIliRedovi(int prva_a, int druga_a, int prva_b, int druga_b) {
		if(prva_a == druga_a) {
			if((prva_b + 1 != druga_b && prva_b - 1 != druga_b) || (prva_b == druga_b)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Nema smisla da je potez dozvoljen ako bismo mijenjali dva istovrijednosna slatkisa.
	 * @param x_1 x koordinata prve pozicije.
	 * @param x_2 x koordinata druge pozicije.
	 * @param y_1 y koordinata prve pozicije.
	 * @param y_2 y koordinata druge pozicije.
	 * @return Ako se na pozicijama nalaze isti slatkisi tada potez nema smisla.
	 */
	private boolean daLiSuOdgovarajuciSlatkisi(int x_1, int x_2, int y_1, int y_2) {
		if(plocaZaIgru[y_1][x_1] == plocaZaIgru[y_2][x_2]) {
			return false;
		}
		return true;
	}
	
	/**
	 * Ukoliko je validna razmjena, tada se formirala trojka odgovarajucih elemenata ili u jednoj od te dvije kolone, ili u jednom od ta dva reda.
	 * U ovoj metodi vrsimo provjeru za red.
	 * @param prva_x x koordinata prve pozicije.
	 * @param prva_y y koordinata prve pozicije.
	 * @param druga_x x koordinata druge pozicije.
	 * @param druga_y y koordinata druge pozicije.
	 * @return Da li se formirala figura u odgovarajucem redu.
	 */
	private boolean daLiJeRed(int prva_x, int prva_y, int druga_x, int druga_y) {
		
		int bojaPrva = plocaZaIgru[prva_y][prva_x];
		int bojaDruga = plocaZaIgru[druga_y][druga_x];
		plocaZaIgru[prva_y][prva_x] = bojaDruga;
		plocaZaIgru[druga_y][druga_x] = bojaPrva;
		
		for (int i = 0; i < sirina - 2; i++) {
			int a = plocaZaIgru[druga_y][i];
			int b = plocaZaIgru[druga_y][1 + i];
			int c = plocaZaIgru[druga_y][2 + i];
			
			if (a == b && b == c) {	
				potez = 1;
				plocaZaIgru[prva_y][prva_x] = bojaPrva;
				plocaZaIgru[druga_y][druga_x] = bojaDruga;
				return true;
			}
		}
		plocaZaIgru[prva_y][prva_x] = bojaPrva;
		plocaZaIgru[druga_y][druga_x] = bojaDruga;
		return false;
	}
	
	/**
	 * /**
	 * Ukoliko je validna razmjena, tada se formirala trojka odgovarajucih elemenata ili u jednoj od te dvije kolone, ili u jednom od ta dva reda.
	 * U ovoj metodi vrsimo provjeru za kolonu.
	 * @param prva_x x koordinata prve pozicije.
	 * @param prva_y y koordinata prve pozicije. 
	 * @param druga_x x koordinata druge pozicije.
	 * @param druga_y y koordinata druge pozicije.
	 * @return Da li se formirala figura u odgovarajucoj koloni.
	 */
	private boolean daLiJeKolona(int prva_x, int prva_y, int druga_x, int druga_y) {
		
		int bojaPrva = plocaZaIgru[prva_y][prva_x];
		int bojaDruga = plocaZaIgru[druga_y][druga_x];
		plocaZaIgru[prva_y][prva_x] = bojaDruga;
		plocaZaIgru[druga_y][druga_x] = bojaPrva;
		
		for (int i = 0; i < visina - 2; i++) {
			int a = plocaZaIgru[i][druga_x];
			int b = plocaZaIgru[1 + i][druga_x];
			int c = plocaZaIgru[2 + i][druga_x];
			
			if (a == b && b == c) {
				potez = 0;
				plocaZaIgru[prva_y][prva_x] = bojaPrva;
				plocaZaIgru[druga_y][druga_x] = bojaDruga;
				return true;
			}
		}
		plocaZaIgru[prva_y][prva_x] = bojaPrva;
		plocaZaIgru[druga_y][druga_x] = bojaDruga;
		return false;
	}
	
	/**
	 * Metoda u kojoj su objedinjene sve prethodne provjere o legalnosti poteza. 		
	 * @param prva Prva pozicija.
	 * @param druga Druga pozicija.
	 * @return Vrijednost koja govori je li potez legalan.
	 */
	public boolean jeLiLegalanPotez(Pozicija prva, Pozicija druga) {
		
		int prva_x = prva.getX();
		int prva_y = prva.getY();
		
		int druga_x = druga.getX();
		int druga_y = druga.getY();
		
		int min_x = 0;
		int min_y = 0;

		int max_x = getSirina() - 1;
		int max_y = getVisina() - 1;
		
		if (!(daLiJeUOpsegu(min_x, min_y, max_x, max_y, prva_x, druga_x, prva_y, druga_y))  ||
			!(daLiSuLegalneKoloneIRedovi(prva_x, druga_x, prva_y, druga_y)) ||
			!(daLiSuLegalneKoloneIliRedovi(prva_x, druga_x, prva_y, druga_y)) ||
			!(daLiSuLegalneKoloneIliRedovi(prva_y, druga_y, prva_x, druga_x)) ||
			!(daLiSuOdgovarajuciSlatkisi(prva_x, druga_x, prva_y, druga_y))) {
			return false;
		}
			
		if (daLiJeRed(prva_x, prva_y, druga_x, druga_y) || daLiJeKolona(prva_x, prva_y, druga_x, druga_y) ||
			daLiJeRed(druga_x, druga_y, prva_x, prva_y)	|| daLiJeKolona(druga_x, druga_y, prva_x, prva_y))
				return true;
		
		return false;
	}
	

	/**
	 * Ako smo ustanovili da je legalan potez, izvrsavamo ga. To jeste, mijenjamo vrijednosti sa prve i druge pozicije. 
	 * @param prva Prva pozicija.
	 * @param druga Druga pozicija.
	 */
	public void izvrsiPotez(Pozicija prva, Pozicija druga) {
		
		int prva_x = prva.getX();
		int prva_y = prva.getY();
		
		int druga_x = druga.getX();
		int druga_y = druga.getY();
		
		
		if (jeLiLegalanPotez(prva, druga)) {
			razmijeniSlatkise(prva_y, prva_x, druga_y, druga_x);	
			srediPlocu(druga);
		} 
	}
	
	
	/**
	 * Metoda koja objedinjuje poziv metoda koje ciste plocu od trenutnog poteza i od poteza uzrokovanih trenutnim.
	 * @param poz Pozicija u odnosu na koju se desila promjena.
	 */
	private void srediPlocu(Pozicija poz) {
		ocistiPlocu(poz);
		popraviPlocu();
		ocistiCijeluPlocu();
	}
	
	/**
	 * Metoda kojom vrsimo zamjenu slatkisa koji zauzimaju odabrane pozicije.
	 * @param prva_y y koordinata prvog slatkisa.
	 * @param prva_x x koordinata prvog slatkisa.
	 * @param druga_y y koordinata drugog slatkisa.
	 * @param druga_x x koordinata drugog slatkisa.
	 */
	private void razmijeniSlatkise(int prva_y, int prva_x, int druga_y, int druga_x) {
		int pom1 = plocaZaIgru[prva_y][prva_x];
		int pom2 = plocaZaIgru[druga_y][druga_x];
		int temp = pom1;
		plocaZaIgru[prva_y][prva_x] = pom2;
		plocaZaIgru[druga_y][druga_x] = temp;
	}
	
	/**
	 * Metoda kojom prolazimo kroz redove ploce i ukidamo trenutne figurice ukoliko se u jednom redu na barem tri uzastopna polja nalaze slatkisi iste boje.
	 * @param lijeva_x x koordinata lijeve pozicije.
	 * @param lijeva_y y koordinata lijeve pozicije.
	 * @param desna_x x koordinata desne pozicije.
	 * @param desna_y y koordinata desne pozicije.
	 * @param boja Boja.
	 */
	private void cistiRed(int lijeva_x, int lijeva_y, int desna_x, int desna_y, int boja) {
		while((lijeva_x-1 >= 0) && (plocaZaIgru[lijeva_y][lijeva_x-1] == boja)) {
			plocaZaIgru[lijeva_y][lijeva_x-1] = -1;
			lijeva_x = lijeva_x - 1;
			rezultat += 50;
		}
		while((desna_x+1 < sirina) && (plocaZaIgru[desna_y][desna_x+1] == boja)) {
			plocaZaIgru[desna_y][desna_x+1] = -1;
			desna_x = desna_x + 1;
			rezultat += 50;
		}
	}
	
	/**
	 * Metoda kojom prolazimo kroz kolone ploce i ukidamo trenutne figurice ukoliko se u jednoj koloni na barem tri uzastopna polja nalaze slatkisi iste boje.
	 * @param gornja_x x koordinata gornje pozicije.
	 * @param gornja_y y koordinata gornje pozicije.
	 * @param donja_x x koordinata donje pozicije.
	 * @param donja_y y koordinata donje pozicije.
	 * @param boja Boja.
	 */
	private void cistiKolonu(int gornja_x, int gornja_y, int donja_x, int donja_y, int boja) {
		while((gornja_y-1 >= 0) && (plocaZaIgru[gornja_y-1][gornja_x] == boja)) {
			plocaZaIgru[gornja_y-1][gornja_x] = -1;
			gornja_y = gornja_y - 1;
			rezultat += 50;
		}
		while((donja_y+1 < visina) && (plocaZaIgru[donja_y+1][donja_x] == boja)) {
			plocaZaIgru[donja_y+1][donja_x] = -1;
			donja_y = donja_y + 1;
			rezultat += 50;
		}
	}
	
	/**
	 * U ovoj metodi u zavisnosti od tipa poteza, cistimo redove, odnosno kolone.
	 * @param poz Pozicija u odnosu na koju krecemo sa ciscenjem.
	 */
	
	private void ocistiPlocu(Pozicija poz) {
		
		int pozicija_x = poz.getX();
		int pozicija_y = poz.getY();
		
		int boja = plocaZaIgru[pozicija_y][pozicija_x];
		plocaZaIgru[pozicija_y][pozicija_x] = -1;
		
		int gornja_x, lijeva_x, donja_x, desna_x;
		int gornja_y, lijeva_y, donja_y, desna_y;
		
		gornja_x = lijeva_x = donja_x = desna_x = pozicija_x;
		gornja_y = lijeva_y = donja_y = desna_y = pozicija_y;
		

		if (potez == 0) {
			cistiKolonu(gornja_x, gornja_y, donja_x, donja_y, boja);
		} else if (potez == 1) {
			cistiRed(lijeva_x, lijeva_y, desna_x, desna_y, boja);
		}	
	}
	
	
	/**
	 * Void metoda koja prolazi kroz citavu plocu i ukida figure od po minimalno tri ista uzastopna slatkisa u istom redu odnosno koloni.
	 */
	private void ocistiCijeluPlocu() {
		
		int broj_ciscenja = Integer.MAX_VALUE;
		
		while(true) {
			
			if(broj_ciscenja == 0)
				break;
			
			broj_ciscenja = 0;
			
			for (int i = 0; i < visina; i++) {
				for (int j = 0; j < sirina; j++) {
				
					int boja = plocaZaIgru[i][j];
					int prva_x, druga_x;
					int prva_y, druga_y;
					
					prva_x = druga_x = j;
					prva_y = druga_y = i;
					
					if (i - 2 >= 0 && plocaZaIgru[i][j] == plocaZaIgru[i-1][j] && plocaZaIgru[i-1][j] == plocaZaIgru[i-2][j] ||
						i + 2 < visina && plocaZaIgru[i][j] == plocaZaIgru[i+1][j] && plocaZaIgru[i+1][j] == plocaZaIgru[i+2][j]) {
						broj_ciscenja++;
						plocaZaIgru[i][j] = -1;
						cistiKolonu(prva_x, prva_y, druga_x, druga_y, boja);
					} else if (j - 2 >= 0 && plocaZaIgru[i][j] == plocaZaIgru[i][j-1] && plocaZaIgru[i][j-1] == plocaZaIgru[i][j-2] ||
							   j + 2 < sirina && plocaZaIgru[i][j] == plocaZaIgru[i][j+1] && plocaZaIgru[i][j+1] == plocaZaIgru[i][j+2]) {
						broj_ciscenja++;
						plocaZaIgru[i][j] = -1;
						cistiRed(prva_x, prva_y, druga_x, druga_y, boja);
					}
					popraviPlocu();
				}
			} 
		}
	}	
}
