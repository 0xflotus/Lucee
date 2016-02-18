package lucee.runtime.extension;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import lucee.commons.lang.StringUtil;

public class ExtensionDefintion {

	private String id;
	private Map<String,String> params=new HashMap<String, String>();

	public ExtensionDefintion() {}
	
	public ExtensionDefintion(String id, String version) {
		this.id=id;
		setParam("version", version);
	}
	
	public void setId(String id) {
		this.id=id;
	}
	public String getId() {
		return id;
	}
	public void setParam(String name, String value) {
		params.put(name,value);
	}
	
	public Map<String,String> getParams() {
		return params;
	}

	public String getVersion() {
		String version = params.get("version");
		if(StringUtil.isEmpty(version))
			version = params.get("extension-version");
		if(StringUtil.isEmpty(version)) return null;
		return version;
	}
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append(getId());
		Iterator<Entry<String, String>> it = params.entrySet().iterator();
		Entry<String, String> e;
		while(it.hasNext()){
			e = it.next();
			sb.append(';').append(e.getKey()).append('=').append(e.getValue());
		}
		return sb.toString();
	}
	

	public boolean equals(Object other){
		if(other instanceof ExtensionDefintion) {
			ExtensionDefintion ed=(ExtensionDefintion) other;
			if(!ed.getId().equalsIgnoreCase(getId())) return false;
			if(ed.getVersion()==null || getVersion()==null) return true;
			return ed.getVersion().equalsIgnoreCase(getVersion());
		}
		else if(other instanceof RHExtension) {
			RHExtension ed=(RHExtension) other;
			if(!ed.getId().equalsIgnoreCase(getId())) return false;
			if(ed.getVersion()==null || getVersion()==null) return true;
			return ed.getVersion().equalsIgnoreCase(getVersion());
		}
		return false;
	}
}
