package lucee.runtime.db;

import java.net.URLDecoder;

import org.w3c.dom.Element;

import lucee.print;
import lucee.commons.lang.StringUtil;
import lucee.commons.net.URLEncoder;
import lucee.runtime.exp.PageException;
import lucee.runtime.op.Caster;
import lucee.runtime.type.Struct;

public class ParamSyntax {

	public static final ParamSyntax DEFAULT = new ParamSyntax("?","&","=");
	public final String leadingDelimiter;
	public final String delimiter;
	public final String separator;

	private ParamSyntax(String leadingDelimiter, String delimiter, String separator) {
		this.leadingDelimiter=leadingDelimiter;
		this.delimiter=delimiter;
		this.separator=separator;
	}
	
	public static ParamSyntax toParamSyntax(Struct sct) throws PageException {
		String del = Caster.toString(sct.get("delimiter"));
		String ledel = Caster.toString(sct.get("leadingDelimiter",null),null);
		if(StringUtil.isEmpty(ledel)) ledel=del;
		return new ParamSyntax(ledel, del, Caster.toString(sct.get("separator")));
	}
	
	public static ParamSyntax toParamSyntax(Struct sct, ParamSyntax defaultValue) {
		String del = Caster.toString(sct.get("param_delimiter",null),null);
		String sep=Caster.toString(sct.get("param_separator",null),null);
		if(del==null || sep==null) 
			return defaultValue;
		
		String ledel = Caster.toString(sct.get("param_leadingDelimiter",null),null);
		if(StringUtil.isEmpty(ledel)) ledel=del;
		return new ParamSyntax(ledel, del, sep);
	}

	public static ParamSyntax toParamSyntax(Element el, ParamSyntax defaultValue) {
		if(!el.hasAttribute("param-delimiter") || !el.hasAttribute("param-separator")) 
			return defaultValue;
		String del = URLDecoder.decode(el.getAttribute("param-delimiter"));
		String ledel = el.getAttribute("param-leading-delimiter");
		String sep = el.getAttribute("param-separator");
		if(StringUtil.isEmpty(ledel)) ledel=del;
		return new ParamSyntax(ledel, del, sep);
	}
	
	public String toString() {
		return "delimiter:"+delimiter+";leadingDelimiter:"+leadingDelimiter+";separator:"+separator;
	}

}
