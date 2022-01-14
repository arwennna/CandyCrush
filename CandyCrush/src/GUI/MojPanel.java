package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logika.CandyCrush;
import logika.Pozicija;


public class MojPanel extends JPanel {
	/**
	 * Prozor unutar kojeg se odvija igra.
	 */
	JFrame okvir;
	
	/**
	 * Objekat koji oznacava pokrenutu igricu.
	 */
	CandyCrush candyCrush;
	
	/**
	 * Visina.
	 */
	int visina;
	
	/**
	 * Sirina.
	 */
	int sirina;
	
	/**
	 * Matrica dugmadi tipa MojeDugme.
	 */
	MojeDugme[][] tabelaDugmadi;
	
	/**
	 * Varijabla pomocu koje odredjujemo da li je ploca kliknuta prvi ili drugi put.
	 */
	boolean vec_kliknuto = false;
	
	/**
	 * x koordinata prvog kliknutog polja.
	 */
	int x1;
	/**
	 * y koordinata prvog kliknutog polja.
	 */
	int y1;
	
	/**
	 * x koordinata drugog kliknutog polja.
	 */
	int x2;
	
	/**
	 * y koordinata drugog kliknutog polja.
	 */
	int y2;
	
	/**
	 * Boja prvog kliknutog polja.
	 */
	int boja1;
	
	/**
	 * Boja drugog kliknutog polja.
	 */
	int boja2;
	
	/**
	 * Prvo kliknuto dugme.
	 */
	MojeDugme dugme1;
	
	/**
	 * Drugo kliknuto dugme.
	 */
	MojeDugme dugme2;
	
	/**
	 * Panel u koji biljezimo statistiku - rezultate i broj preostalih poteza.
	 */
	JPanel stat;
	
	/**
	 * Labela za rezultat.
	 */
	JLabel labelRezultat;
	
	/**
	 * Labela za preostale poteze.
	 */
	JLabel labelPotezi;
	
	/**
	 * Polje koje sadrzi vrijednost rezultata.
	 */
	JTextField poljeRezultat;
	
	/**
	 * Polje koje sadrzi broj preostalih poteza.
	 */
	JTextField poljePotezi;
	
	/**
	 * Metoda kojom zapocinjemo novu igru.
	 * @param tip Tip igre koju igramo.
	 */
	private static void zapocniIgru(int tip) {
		JFrame okvir = new JFrame("Candy Crush");
		okvir.setDefaultCloseOperation(okvir.EXIT_ON_CLOSE);
		MojPanel mojPanel = new MojPanel(tip, okvir);
		okvir.setContentPane(mojPanel);
		okvir.setSize(50*mojPanel.sirina, 50*mojPanel.visina);
		okvir.setVisible(true);
		okvir.setLocationRelativeTo(null);
	}
	
	/**
	 * Konstruktor za panel na kojem se odvija igra.
	 * @param tip Tip igre koja se igra.
	 * @param okvir1 Prozor unutar kojeg se odvija igra.
	 */
	public MojPanel(int tip, JFrame okvir1) {
		super();
		okvir = okvir1;
		candyCrush = new CandyCrush(tip);
		visina = candyCrush.getVisina();
		sirina = candyCrush.getSirina();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		tabelaDugmadi = new MojeDugme[visina][sirina];
		JPanel novi = new JPanel();

		konstruisiPomocni(novi);
		
		for (int i=0; i<visina; i++) {
			for (int j=0; j<sirina; j++) {
				int vrijednost = candyCrush.getPloca()[i][j];
				MojeDugme dugme = new MojeDugme(j, i, vrijednost);
				dugme.setSize(50, 50);
				c.gridx = j;
				c.gridy = i;
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 1.0;
				c.weighty = 1.0;
				dugme.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if (vec_kliknuto) {
							x2 = dugme.vratiX();
							y2 = dugme.vratiY();
							boja2 = dugme.getBoja();
							dugme2 = dugme;
							
							Pozicija prva = new Pozicija(x1, y1);
							Pozicija druga = new Pozicija(x2, y2);
							
							if (candyCrush.jeLiLegalanPotez(prva, druga)) {
								candyCrush.izvrsiPotez(prva, druga);
								
								obojiPlocu();
								
								int rezultat = candyCrush.getRezultat();
								poljeRezultat.setText(String.valueOf(rezultat));
								
								int brojPoteza = Integer.parseInt(poljePotezi.getText()) - 1;
								if (brojPoteza == 0)  {
									okvir.dispose();
									Timer timer = new Timer();
									MojProzorGotovaIgra prozorcic = new MojProzorGotovaIgra(rezultat);

									timer.schedule(new TimerTask() {
									  @Override
									  public void run() {
										  	prozorcic.dispose();
										  	napraviNovu();
									  }
									}, 5*1000);
								}
								poljePotezi.setText(String.valueOf(brojPoteza));
								
							} else {
								prikaziProzorGreska();
							}
							vec_kliknuto = false;
						}
						
						else {
							x1 = dugme.vratiX();
							y1 = dugme.vratiY();
							boja1 = dugme.getBoja();
							dugme1 = dugme; 
							vec_kliknuto = true;
						}
					}
				});

				add(dugme, c);
				tabelaDugmadi[i][j] = dugme;
			}
			
		}
		c.gridx = 0;
		c.gridy = visina;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = sirina;
		add(novi, c);
	}
	
	/**
	 * Metoda kojom inicijaliziramo novu igru.
	 */
	private void napraviNovu() {
		MojProzorNovaIgra novaIgra = new MojProzorNovaIgra();
		JButton dugmic1 = novaIgra.getDugme1();
		JButton dugmic2 = novaIgra.getDugme2();
		
		dugmic1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				zapocniIgru(1);
			}
		});
		
		dugmic2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				zapocniIgru(2);
			}
		});
	}
	
	/**
	 *  Metoda kojom bojimo plocu odgovarajucim - novim vrijednostima.
	 */
	private void obojiPlocu() {
		int [][] ploca = candyCrush.getPloca();
		
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				tabelaDugmadi[i][j].setBoja(ploca[i][j]);
			}
		}
	}
	
	/**
	 * Metoda kojom pravimo i prikazujemo prozor kojim prijavljujemo gresku.
	 */
	private void prikaziProzorGreska() {

		Timer timer = new Timer();
		MojProzorGreska prozor = new MojProzorGreska();

		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
			  prozor.dispose();
		  }
		}, 3*1000);	
	}
	
	
	
	
	/**
	 * Build metoda za panel u koji smjestamo statistiku igrice.
	 * @param panel Panel u koji smjestamo statistiku igrice.
	 */
	private void konstruisiPomocni(JPanel panel) {
		
		labelRezultat = new JLabel("Rezultat:");
		labelRezultat.setSize(50, 100);
		labelPotezi = new JLabel("Ostalo poteza:");
		
		poljeRezultat = new JTextField("0");
		poljeRezultat.setEditable(false);
		int potezi = candyCrush.getPotezi();
		poljePotezi = new JTextField(String.valueOf(potezi));
		poljePotezi.setEditable(false);
		
		panel.setLayout(new GridLayout(1, 4));
		panel.add(labelRezultat);
		panel.add(poljeRezultat);
		panel.add(labelPotezi);
		panel.add(poljePotezi);
 	}
	
}
