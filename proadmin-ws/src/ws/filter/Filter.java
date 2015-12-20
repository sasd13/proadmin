package ws.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.pattern.filter.MultiAndCriteria;

public abstract class Filter<T> {
	
	protected static final String ENCODING = "UTF-8";
	
	protected Map<String, String[]> mapParameters;
	protected MultiAndCriteria<T> multiAndCriteria;
	
	public Filter(Map<String, String[]> mapParameters) {
		this.mapParameters = mapParameters;
		this.multiAndCriteria = new MultiAndCriteria<>();
		
		String[] values;
		for (String key : mapParameters.keySet()) {
			values = mapParameters.get(key);
			
			try {
				for (int i=0; i<values.length; i++) {
					values[i] = URLDecoder.decode(values[i], ENCODING);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			mapParameters.replace(key, values);
		}
	}
	
	public List<T> filter(List<T> list) {
		return multiAndCriteria.meetCriteria(list);
	}
}
