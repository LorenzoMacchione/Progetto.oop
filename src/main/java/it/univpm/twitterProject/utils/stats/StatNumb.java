package it.univpm.twitterProject.utils.stats;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import org.json.simple.JSONObject;
import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.exception.DataIllegalArgumentException;
import it.univpm.twitterProject.exception.IllegalCoordException;
import it.univpm.twitterProject.exception.IllegalStringException;
import it.univpm.twitterProject.model.Metadata;

public class StatNumb<E> implements Stat {

	private String field;
	private double media;
	private double max = 0.0;
	private double min = 0.0;
	private double tot = 0.0;
	private int qtDati = 0;

	public StatNumb(Collection<E> collect, String field) throws DataIllegalArgumentException {
		String s = "";
		for (Metadata x : StartClass.getAllMetadata()) {
			if (x.getAlias().equals(field))
				s = x.getType();
		}
		if (s.equals("String")) {
			throw new IllegalStringException();
		}
		if (s.equals("Coord")) {
			throw new IllegalCoordException();
		}

		Method m = null;
		Object ob = null;
		Double o = 0.0;
		for (E obj : collect) {
			try {
				m = obj.getClass().getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1), null);
				ob = m.invoke(obj);
				o = ((Number) ob).doubleValue();
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (qtDati == 0) {
				min = o;
				max = o;
			}
			qtDati++;

			if (min >= o) {
				min = o;
			}

			if (max <= o) {
				max = o;
			}

			tot += o;
			media = tot / qtDati;
			this.field = field;
		}
	}

	@Override
	public JSONObject getStatJo() {
		JSONObject jO = new JSONObject();
		jO.put("Campo esaminato", field);
		jO.put("Minimo", min);
		jO.put("Massimo", max);
		jO.put("Media", media);
		jO.put("Totale", tot);
		jO.put("Quantità di dati esaminati", qtDati);
		return jO;
	}
}