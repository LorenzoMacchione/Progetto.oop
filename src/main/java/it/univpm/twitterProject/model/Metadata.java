package it.univpm.twitterProject.model;

/**
 * Classe i cui oggetti contengono i dati di un metadata
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class Metadata {
	private String alias;
	private String type;
	private String sourceField;

	public Metadata(String alias, String type, String sourceField) {
		super();
		this.alias = alias;
		this.type = type;
		this.sourceField = sourceField;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSourceField() {
		return sourceField;
	}

	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}
}