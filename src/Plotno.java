import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Plotno extends JPanel{
	final int rozmiarKafelka = 50;
	int[][] tablica = new int[100][100];
	int[][] tablica2 = new int[100][100];
	final Point[] wektory = {
			new Point(-1, 0),//0
			new Point(-1, -1),//1
			new Point(0, -1),//2
			new Point(1, -1),//3
			new Point(1,0),//4
			new Point(1, 1),//5
			new Point(0, 1),//6
			new Point(-1,1)//7
	};
	
	public Plotno() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				if (SwingUtilities.isLeftMouseButton(e)) {
					tablica[e.getX()/rozmiarKafelka][e.getY()/rozmiarKafelka]=1;
				}
				if (SwingUtilities.isRightMouseButton(e)) {
					tablica[e.getX()/rozmiarKafelka][e.getY()/rozmiarKafelka]=2;
				}
				repaint();
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i=0;i<tablica.length;i++) {
			for (int j=0;j<tablica[0].length;j++) {
				if (tablica[i][j]==1) {
					g.setColor(Color.black);
					g.fillRect(i*rozmiarKafelka, j*rozmiarKafelka, rozmiarKafelka, rozmiarKafelka);
				}
				if (tablica[i][j]==2) {
					g.setColor(Color.red);
					g.fillRect(i*rozmiarKafelka, j*rozmiarKafelka, rozmiarKafelka, rozmiarKafelka);
				}
			}
		}
	}
	
	public void nastepnyKrok() {		
		for (int i=1;i<tablica.length-1;i++) {
			for (int j=1;j<tablica[0].length-1;j++) {
				int liczbaSasiadow = 0;
				int ileCzarnych=0;
				int ileCzerwonych=0;
				for (int n=0;n<wektory.length;n++) {
					int x = wektory[n].x+i;
					int y = wektory[n].y+j;
					if (tablica[x][y]==1) {
						
						ileCzarnych++;
						System.out.println("czarne"+ileCzarnych+" i,j,n"+i+j+n);
					}
					if (tablica[x][y]==2) {
						ileCzerwonych++;
					}
					
				}
				
				liczbaSasiadow=ileCzarnych+ileCzerwonych;
				System.out.println(liczbaSasiadow);
				
				if (liczbaSasiadow==3 && tablica[i][j]==0) {
					if (ileCzarnych>ileCzerwonych) {
						tablica2[i][j]=1;
					}else {
						tablica2[i][j]=2;
					}
				}else
				if (!(liczbaSasiadow==2 || liczbaSasiadow==3)) {
					tablica2[i][j]=0;
				}else {
					tablica2[i][j]=tablica[i][j];
				}
			}
		}
		przepiszTablice(tablica, tablica2);
	}
	public void przepiszTablice(int tablica[][],int tablica2[][]) {
		for (int i=0;i<tablica.length;i++) {
			for (int j=0;j<tablica[0].length;j++) {
				tablica[i][j]=tablica2[i][j];
			}
		}
		for (int i=0;i<tablica.length;i++) {
			for (int j=0;j<tablica[0].length;j++) {
				tablica2[i][j]=0;
			}
		}
	}
}
