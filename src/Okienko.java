import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

public class Okienko {
	public Okienko() {
		JFrame okienko = new JFrame("");
		
		Plotno plotno = new Plotno();
		okienko.add(plotno);
		
		JButton start = new JButton("next ->");
		
		JButton startSymulacja = new JButton("start");
		
		JSpinner poziom = new JSpinner();
		poziom.setModel(new SpinnerNumberModel(1, 1, null, 1));
		
		okienko.add(start,BorderLayout.NORTH);
		okienko.add(poziom,BorderLayout.SOUTH);
		okienko.add(startSymulacja,BorderLayout.WEST);
		
//		okienko.add(startSymulacja);
		okienko.setSize(600, 400);
		okienko.setLocationRelativeTo(null);
		okienko.setVisible(true);
		okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ileEwolucji = (int) poziom.getValue();
				for (int i=0;i<ileEwolucji;i++) {
					plotno.nastepnyKrok();
				}
				plotno.repaint();
			}
		});
		
		Symulacja.setPlotno(plotno);
		
		startSymulacja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Symulacja.isOn()) {
					Symulacja.setOn(true);
					Symulacja.start();
					startSymulacja.setText("stop");
				}else {
					Symulacja.setOn(false);
					startSymulacja.setText("start");
				}
				
			}
		});

	}
}
