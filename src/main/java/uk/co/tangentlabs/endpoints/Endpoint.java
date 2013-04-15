package uk.co.tangentlabs.endpoints;

import java.util.Collection;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.json.JSONArray;
import com.json.JSONException;
import com.json.JSONObject;
import com.json.XML;

public abstract class Endpoint {
	protected String returnType;
	
	@Context 
    public UriInfo info;
	
	public void getTransaction(Session session){
		Transaction trans = session.getTransaction();
//		trans.
		trans.commit();
	}
	
	
	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	protected String marshall(Collection<?> objects) throws JSONException{
		return marshall(objects.toArray());
	}
	
	protected String marshall(Object[] objects) throws JSONException{
		JSONArray array = new JSONArray(objects);
		if (returnType.equalsIgnoreCase("xml")) {
			
			JSONObject json = new JSONObject();
			JSONObject wrapper = new JSONObject();
			wrapper.put("item", array);
			json.put("list", wrapper);
			return "<?xml version=\"1.0\"?>\n" + XML.toString(json);
		}
		return array.toString();
	}

	protected String marshall(Map<?, ?> object) throws JSONException{
		JSONObject json = new JSONObject(object);
		if (returnType.equalsIgnoreCase("xml")) {
			JSONObject wrapper = new JSONObject();
			wrapper.put("object", json);
			return "<?xml version=\"1.0\"?>\n" + XML.toString(wrapper);
		}
		return json.toString();
	}
	
	protected String getSort(MultivaluedMap<String, String> map){
		String sort = null;
		if (map.containsKey("sort")){
			sort = map.getFirst("sort");
			if (sort.trim().equals("")) return null;
		}
		return sort;
	}
	
	protected String getSortDirection(MultivaluedMap<String, String> map){
		String direction = "desc";
		if (map.containsKey("direction")){
			String dir = map.getFirst("direction").toLowerCase().trim();
			if (dir.equals("asc") || dir.equals("desc")){
				direction = dir;
			}
		}
		return direction;
	}
}
