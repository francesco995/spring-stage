package com.objectway.stage.model;

@SuppressWarnings("serial")
public class Comune extends AbstractModelObject {
	private Provincia provincia;

	public Comune(String codice, String descrizione, Provincia provincia) {
		super(codice, descrizione);
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "Comune [provincia=" + provincia + ", getCodice()=" + getCodice() + ", getDescrizione()="
				+ getDescrizione() + "]";
	}
}
