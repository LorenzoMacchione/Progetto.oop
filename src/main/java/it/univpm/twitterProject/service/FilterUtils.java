package it.univpm.twitterProject.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import org.json.simple.JSONArray;

public class FilterUtils<T> {
	public static boolean check(Object value, String operator, Object th) {
		if (th instanceof Number && value instanceof Number) {
			Double thC = ((Number) th).doubleValue();
			Double valuec = ((Number) value).doubleValue();
			if (operator.equals("=="))
				return value.equals(th);
			else if (operator.equals("$gt"))
				return valuec > thC;
			else if (operator.equals("$gte"))
				return valuec >= thC;
			else if (operator.equals("$lt"))
				return valuec < thC;
			else if (operator.equals("$lte"))
				return valuec <= thC;
		} else if (th instanceof String && value instanceof String)
			return value.equals(th);

		else if (operator.equals("$bt")) {
			Long valuec = ((Number) value).longValue();
			Long[] thC1 = new Long[2];
			thC1[0] = (Long) ((JSONArray) th).get(0);
			thC1[1] = (Long) ((JSONArray) th).get(1);
			return thC1[0] < valuec && valuec < thC1[1];
		}
		return false;
	}

	public Collection<T> select(Collection<T> src, String fieldName, String operator, Object value) {
		Collection<T> out = new ArrayList<T>();
		for (T item : src) {
			try {
				Method m = item.getClass()
						.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);
				try {
					Object tmp = m.invoke(item);
					if (FilterUtils.check(tmp, operator, value))
						out.add(item);
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
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return out;
	}
}