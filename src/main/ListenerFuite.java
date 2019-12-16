package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import personnage.PersMain;

public class ListenerFuite implements ActionListener {

	private PersMain persPrinc;
	public ListenerFuite(PersMain persPrinc) {
		// TODO Auto-generated constructor stub
		this.persPrinc = persPrinc;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		persPrinc.fuite();
		
	}

}
