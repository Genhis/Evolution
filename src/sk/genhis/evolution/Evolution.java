package sk.genhis.evolution;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import sk.genhis.evolution.listener.BtnBestListener;
import sk.genhis.evolution.listener.BtnInstantListener;
import sk.genhis.evolution.listener.BtnMoreListener;
import sk.genhis.evolution.listener.BtnNewListener;
import sk.genhis.evolution.listener.BtnNextListener;
import sk.genhis.evolution.listener.BtnPBackListener;
import sk.genhis.evolution.listener.BtnPEndListener;
import sk.genhis.evolution.listener.BtnPForwardListener;
import sk.genhis.evolution.listener.BtnPLoadListener;
import sk.genhis.evolution.listener.BtnPSpeedListener;
import sk.genhis.evolution.listener.BtnPStartListener;
import sk.genhis.evolution.listener.BtnPlayListener;
import sk.genhis.evolution.listener.BtnRecordListener;
import sk.genhis.evolution.listener.SlcDisplayListener;
import sk.genhis.evolution.util.ModifiedFlowLayout;

public final class Evolution extends JFrame {
	private static final long serialVersionUID = 0L;
	private static Evolution evolution;
	private final static OrganismInfo organismInfo = new OrganismInfo();
	
	public Population pop;
	public LinkedHashMap<Integer, Population> prec = new LinkedHashMap<Integer, Population>();
	public final Record rec = new Record();
	
	public final PopulationDisplay dPop = new PopulationDisplay();
	public final Choice slcDisplay = new Choice();
	public final JLabel lblGen = new JLabel();
	public final JLabel lblInfo = new JLabel("Prejdi myšou po organizme pre dodatočné informácie");
	public final JButton btnNew = new JButton("Nová generácia");
	public final JButton btnBest = new JButton("Vybrať najlepších");
	public final JButton btnNext = new JButton("Ďalšia generácia");
	public final JButton btnMore = new JButton("Niekoľko generácií");
	public final JButton btnInstant = new JButton("Do nekonečna");
	public final JButton btnRecord = new JButton("Nahrať záznam");
	public final JButton btnPLoad = new JButton("Načítať zo súboru");
	public final JButton btnPSpeed = new JButton("Rýchlosť prehrávania");
	public final JButton btnPStart = new JButton("|<<");
	public final JButton btnPBack = new JButton("<<");
	public final JButton btnPlay = new JButton("PLAY");
	public final JButton btnPForward = new JButton(">>");
	public final JButton btnPEnd = new JButton(">>|");
	
	public final BtnBestListener btnBestL = new BtnBestListener();
	public final BtnMoreListener btnMoreL = new BtnMoreListener();
	public final BtnRecordListener btnRecordL = new BtnRecordListener();
	public final BtnPLoadListener btnPLoadL = new BtnPLoadListener();
	public final BtnPSpeedListener btnPSpeedL = new BtnPSpeedListener();
	public final BtnPlayListener btnPlayL = new BtnPlayListener();
	
	public Evolution() {
		this.setTitle("Evolution simulator v" + Evolution.getVersion() + " | Created by Genhis \u00a9 2013");
		this.getContentPane().setLayout(new BorderLayout(5, 5));
		this.setSize(1024, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pop = new Population();
		
		JPanel cp = new JPanel();
		cp.setLayout(new BorderLayout());
		
		JPanel cp01 = new JPanel();
		cp01.setLayout(new BorderLayout());
		
		JPanel cp1 = new JPanel();
		cp1.setLayout(new FlowLayout());
		
		this.lblGen.setFont(new Font(this.lblGen.getFont().getFamily(), Font.BOLD, 20));
		cp1.add(this.lblGen);
		
		JPanel cp2 = new JPanel();
		cp2.setLayout(new FlowLayout());
		
		this.slcDisplay.addItem("Pohlavie");
		this.slcDisplay.addItem("Veľkosť");
		this.slcDisplay.addItem("Rýchlosť");
		this.slcDisplay.addItem("Sila");
		this.slcDisplay.addItem("Odolnosť");
		this.slcDisplay.addItem("Rozmnožovanie");
		this.slcDisplay.addItem("Šanca na mutáciu");
		this.slcDisplay.addItemListener(new SlcDisplayListener());
		cp2.add(this.slcDisplay);
		
		this.btnNew.addActionListener(new BtnNewListener());
		cp2.add(this.btnNew);
		
		this.btnBest.addActionListener(this.btnBestL);
		cp2.add(this.btnBest);
		
		this.btnNext.addActionListener(new BtnNextListener());
		cp2.add(this.btnNext);
		
		this.btnMore.addActionListener(this.btnMoreL);
		cp2.add(this.btnMore);
		
		this.btnInstant.addActionListener(new BtnInstantListener());
		cp2.add(this.btnInstant);
		
		JPanel cp3 = new JPanel();
		cp3.setLayout(new FlowLayout());
		
		this.btnRecord.addActionListener(this.btnRecordL);
		cp3.add(this.btnRecord);
		
		this.btnPLoad.addActionListener(this.btnPLoadL);
		cp3.add(this.btnPLoad);
		
		this.btnPSpeed.addActionListener(this.btnPSpeedL);
		cp3.add(this.btnPSpeed);
		
		JPanel cp4 = new JPanel();
		cp4.setLayout(new FlowLayout());
		
		this.btnPStart.addActionListener(new BtnPStartListener());
		cp4.add(this.btnPStart);
		
		this.btnPBack.addActionListener(new BtnPBackListener());
		cp4.add(this.btnPBack);
		
		this.btnPlay.addActionListener(this.btnPlayL);
		cp4.add(this.btnPlay);
		
		this.btnPForward.addActionListener(new BtnPForwardListener());
		cp4.add(this.btnPForward);
		
		this.btnPEnd.addActionListener(new BtnPEndListener());
		cp4.add(this.btnPEnd);
		
		cp01.add(cp1, BorderLayout.NORTH);
		cp01.add(cp2, BorderLayout.CENTER);
		cp01.add(cp3, BorderLayout.SOUTH);
		
		cp.add(cp01, BorderLayout.NORTH);
		cp.add(cp4, BorderLayout.SOUTH);
		
		this.dPop.setLayout(new ModifiedFlowLayout());
		final JScrollPane spPop = new JScrollPane(this.dPop, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.getContentPane().add(spPop, BorderLayout.CENTER);
		
		this.getContentPane().add(cp, BorderLayout.NORTH);
		this.getContentPane().add(this.lblInfo, BorderLayout.SOUTH);
		
		this.dPop.repaintContent();
		
		this.toggleBtn();
	}
	
	public static void main(String[] args) {
		(Evolution.evolution = new Evolution()).setVisible(true);
	}
	
	public void toggleBtn() {
		this.btnNew.setEnabled(!this.btnPLoadL.isActive() && !this.btnRecordL.isActive());
		this.btnBest.setEnabled(!this.btnPLoadL.isActive());
		this.btnNext.setEnabled(!this.btnPLoadL.isActive() && this.pop.org.size() > 0);
		this.btnMore.setEnabled(!this.btnPLoadL.isActive() && this.pop.org.size() > 0);
		this.btnInstant.setEnabled(!this.btnPLoadL.isActive() && this.pop.org.size() > 0);
		this.btnRecord.setEnabled(!this.btnPLoadL.isActive() && this.pop.org.size() > 0);
		this.btnPLoad.setEnabled(!this.btnRecordL.isActive());
		this.btnPSpeed.setEnabled(!this.btnMoreL.isActive());
		this.btnPStart.setEnabled(this.btnPLoadL.isActive());
		this.btnPBack.setEnabled(this.btnPLoadL.isActive());
		this.btnPlay.setEnabled(this.btnPLoadL.isActive());
		this.btnPForward.setEnabled(this.btnPLoadL.isActive());
		this.btnPEnd.setEnabled(this.btnPLoadL.isActive());
	}
	
	public void btn_setEnabled(boolean enabled) {
		this.btnNew.setEnabled(enabled);
		this.btnBest.setEnabled(enabled);
		this.btnNext.setEnabled(enabled);
		this.btnMore.setEnabled(enabled);
		this.btnInstant.setEnabled(enabled);
		this.btnRecord.setEnabled(enabled);
		this.btnPLoad.setEnabled(enabled);
		this.btnPSpeed.setEnabled(enabled);
		this.btnPStart.setEnabled(enabled);
		this.btnPBack.setEnabled(enabled);
		this.btnPlay.setEnabled(enabled);
		this.btnPForward.setEnabled(enabled);
		this.btnPEnd.setEnabled(enabled);
	}
	
	public static Evolution get() {
		return Evolution.evolution;
	}
	
	public static String getVersion() {
		return "1.1.0";
	}
	
	public static void showOrganismInfo(Organism o) {
		Evolution.organismInfo.showInfo(o);
	}
}
