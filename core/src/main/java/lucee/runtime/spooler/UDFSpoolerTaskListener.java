package lucee.runtime.spooler;

import lucee.commons.io.SystemUtil.TemplateLine;
import lucee.runtime.PageContext;
import lucee.runtime.exp.PageException;
import lucee.runtime.type.Struct;
import lucee.runtime.type.UDF;

public class UDFSpoolerTaskListener extends CFMLSpoolerTaskListener {

	private static final long serialVersionUID = 1262226524494987654L;
	private UDF udf;

	public UDFSpoolerTaskListener(TemplateLine currTemplate, SpoolerTask task, UDF udf) {
		super(currTemplate,task);
		this.udf=udf;
	}

	@Override
	public void _listen(PageContext pc, Struct args) throws PageException {
		udf.callWithNamedValues(pc, args, true);
	}

}
