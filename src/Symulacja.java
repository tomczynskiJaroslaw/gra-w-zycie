
public class Symulacja {
	private static Plotno plotno;
	private static boolean on=false;

	static void start() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				while (on) {
					plotno.nastepnyKrok();
					System.out.println("s");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					plotno.repaint();
				}
			}
		}.start();
	}

	public static boolean isOn() {
		return on;
	}

	public static void setOn(boolean stop) {
		Symulacja.on = stop;
	}

	public static void setPlotno(Plotno plotno) {
		Symulacja.plotno = plotno;
	}
	
}
