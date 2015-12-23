package sk.genhis.evolution;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class Record implements Runnable {
	private ObjectOutputStream oos = null;
	
	private int pos = 1;
	
	public void start() {
		try {
			this.oos = new ObjectOutputStream(new FileOutputStream(Evolution.get().btnRecordL.getFile()));
			this.add(Evolution.get().pop);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void add(Population p) {
		try {
			if(this.oos != null)
				this.oos.writeObject(new Population(p));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			if(this.oos != null) {
				this.oos.close();
				this.oos = null;
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void load() {
		final Record r = this;
		new Thread() {
			@Override
			public void run() {
				try {
					Population pop = null;
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Evolution.get().btnPLoadL.getFile()));
					try {
						for(int i = 1; (pop = (Population)ois.readObject()) != null; i++)
							Evolution.get().prec.put(i, pop);
					}
					catch(EOFException ex) {}
					ois.close();
					r.playStart();
					Evolution.get().btnPLoad.setText("Vypn˙ù mÛd prehr·vania");
					Evolution.get().toggleBtn();
				}
				catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}.start();
	}
	
	public void play() {
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			do {
				this.playNext();
				Thread.sleep(Evolution.get().btnPSpeedL.getSpeed());
			}
			while(Evolution.get().btnPlayL.isActive() && Evolution.get().btnPLoadL.isActive() && this.pos != Evolution.get().prec.size());
			
			if(Evolution.get().btnPlayL.isActive()) {
				Evolution.get().btnPlayL.toggle();
				Thread.sleep(1000);
				this.playEnd();
			}
		}
		catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean playNext() {
		return this.playGen(++this.pos) ? true : this.playStart();
	}
	
	public boolean playPrev() {
		return this.playGen(--this.pos) ? true : this.playEnd();
	}
	
	public boolean playStart() {
		return this.playGen(this.pos = 1);
	}
	
	public boolean playEnd() {
		return this.playGen(this.pos = Evolution.get().prec.size());
	}
	
	private boolean playGen(int gen) {
		if(!Evolution.get().prec.containsKey(gen))
			return false;
		
		Evolution.get().dPop.repaintContent(Evolution.get().prec.get(gen));
		return true;
	}
}
