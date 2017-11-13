package com.objectway.stage.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonDomainObjectsCatalog implements DomainObjectsCatalog {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonDomainObjectsCatalog.class);
	private static final String PROPERTY_CM = "cm";
	private static final String PROPERTY_SIGLA = "sigla";
	private static final String PROPERTY_CODICE_CATASTALE = "codiceCatastale";
	private static final String PROPERTY_NOME = "nome";
	private static final String PROPERTY_PROVINCIA = "provincia";
	
	private List<Comune> comuni;
	private List<Provincia> province;
	
	@SuppressWarnings("unchecked")
	public DomainObjectsCatalog init() {
		JSONParser parser = new JSONParser();
		// source --> https://raw.githubusercontent.com/matteocontrini/comuni-json/master/comuni.json
        try {
            Object obj = parser.parse(new InputStreamReader(JsonDomainObjectsCatalog.class.getResourceAsStream("/json/comuni.json")));
            
            Set<Provincia> provinceSet = new HashSet<>();
            List<Comune> comuni = new ArrayList<>();

            JSONArray jsonArray = (JSONArray) obj;
            for(Iterator<JSONObject> it = jsonArray.iterator(); it.hasNext(); ) {
            	JSONObject current = (JSONObject) it.next();
            	JSONObject provincia = (JSONObject) current.get(PROPERTY_PROVINCIA);
            	
            	String nomeComune = (String) current.get(PROPERTY_NOME);
            	String codiceComune = (String) current.get(PROPERTY_CODICE_CATASTALE);
            	
            	String codiceProvincia = (String) current.get(PROPERTY_SIGLA);
            	String nomeProvincia = (String) provincia.get(PROPERTY_NOME);
            	
            	if(nomeProvincia == null || nomeProvincia.trim().isEmpty()) {
            		nomeProvincia = (String) ((JSONObject) current.get(PROPERTY_CM)).get(PROPERTY_NOME);
            	}
            	Provincia p = new Provincia(codiceProvincia, nomeProvincia);
            	Comune c = new Comune(codiceComune, nomeComune, p);
            	
            	if(!provinceSet.contains(p)) {
            		provinceSet.add(p);
            	}
            	comuni.add(c);
            }

            List<Provincia> province = new ArrayList<>(provinceSet);
            Comparator<Provincia> provinceComparator = (Provincia o1, Provincia o2) 
            		-> {return o1.getDescrizione().compareTo(o2.getDescrizione());};
            Collections.sort(province, provinceComparator);
            
            if(LOGGER.isDebugEnabled()) {
//            	for(Provincia iProvincia : province) 
//            		LOGGER.debug(iProvincia.toString());
            	LOGGER.debug(String.format("Province recuperate: %d", province.size()));
            }
            
            if(LOGGER.isDebugEnabled()) {
//            	for(Comune iComune : comuni) 
//            		LOGGER.debug(iComune.toString());
            	LOGGER.debug(String.format("Comuni recuperati: %d", comuni.size()));
            }
            this.province = Collections.unmodifiableList(province);
            this.comuni = Collections.unmodifiableList(comuni);
        } catch (IOException  | ParseException e) {
            LOGGER.error("Errore nel recupero di coumi e province!", e);
        }
        return this;
	}
	
	public List<Comune> getComuni() {
		return comuni;
	}

	public List<Provincia> getProvince() {
		return province;
	}
}
