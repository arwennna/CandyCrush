package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Sara-Farah
 *
 */

/**
 * Klasa kojom otvaramo prozore i omogucavamo igru.
 *
 */
public class IgrajCandyCrush {
	
	/**
	 * Unutar main-a otvaramo prozor kojim korisniku omogucavamo odabir levela.
	 * @param args Defaultni parametar main-a.
	 */
	public static void main(String[] args) {
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
	 * Metoda kojom ucitavamo odgovarajucu igracu plocu na osnovu tipa.
	 * @param tip Tip na osnovu kojeg ucitavamo odgovarajucu igracu plocu.
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
}
