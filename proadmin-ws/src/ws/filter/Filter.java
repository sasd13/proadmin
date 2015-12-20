package ws.filter;

import java.util.List;
import java.util.Map;

import com.sasd13.javaex.pattern.filter.MultiAndCriteria;

public abstract class Filter<T> {

	protected Map<String, String[]> mapParameters;
	protected MultiAndCriteria<T> multiAndCriteria;
	
	public Filter(Map<String, String[]> mapParameters) {
		this.mapParameters = mapParameters;
		this.multiAndCriteria = new MultiAndCriteria<>();
	}
	
	public List<T> filter(List<T> list) {
		return multiAndCriteria.meetCriteria(list);
	}
}
