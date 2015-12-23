package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import sk.genhis.evolution.Evolution;
import sk.genhis.evolution.Population;

public final class BtnPLoadListener implements ActionListener {
	private File file = null;
	private boolean active = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.toggle();
	}
	
	public void toggle() {
		if(this.active = !this.active) {
			JFileChooser fc = new JFileChooser("C:\\");
			fc.setFileFilter(new FileNameExtensionFilter("Evolution Record (*.evr)", "evr"));
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				if(!fc.getSelectedFile().getName().endsWith(".evr")) {
					this.active = false;
					return;
				}
				
				this.file = fc.getSelectedFile();
				Evolution.get().rec.load();
				Evolution.get().btnPLoad.setText("NaËÌtava sa ...");
				Evolution.get().btn_setEnabled(false);
			}
			else
				this.active = false;
		}
		else {
			Evolution.get().btnPLoad.setText("NaËÌtaù zo s˙boru");
			Evolution.get().toggleBtn();
			Evolution.get().prec = new LinkedHashMap<Integer, Population>();
			Evolution.get().dPop.repaintContent();
			this.file = null;
		}
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public File getFile() {
		return this.file;
	}
}
