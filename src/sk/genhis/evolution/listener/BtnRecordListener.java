package sk.genhis.evolution.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import sk.genhis.evolution.Evolution;

public final class BtnRecordListener implements ActionListener {
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
			if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				if(!fc.getSelectedFile().getName().endsWith(".evr"))
					this.file = new File(fc.getSelectedFile().getAbsolutePath() + ".evr");
				else
					this.file = fc.getSelectedFile();
				Evolution.get().btnRecord.setText("Vypn˙ù nahr·vanie");
				Evolution.get().toggleBtn();
				Evolution.get().rec.start();
			}
			else
				this.active = false;
		}
		else {
			Evolution.get().btnRecord.setText("Nahraù z·znam");
			Evolution.get().toggleBtn();
			Evolution.get().rec.stop();
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
