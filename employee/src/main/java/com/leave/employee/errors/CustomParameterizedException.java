package com.leave.employee.errors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom, parameterized exception, which can be translated on the client side.
 * For example:
 *
 * <pre>
 * throw new CustomParameterizedException(&quot;myCustomError&quot;, &quot;hello&quot;, &quot;world&quot;);
 * </pre>
 *
 * Can be translated with:
 *
 * <pre>
 * "error.myCustomError" :  "The server says {{param0}} to {{param1}}"
 * </pre>
 */
public class CustomParameterizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String exception;

	private final String exceptionMessage;

	private final List<String> paramList = new ArrayList<>();
	
	private Map<String,List<String>> data = new HashMap<String,List<String>>();
	
	public CustomParameterizedException(String exception, String exceptionMessage, String... params) {
		super(exception);
		this.exception = exception;
		this.exceptionMessage = exceptionMessage;
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				paramList.add(params[i]);
			}
		}
	}
	
	public CustomParameterizedException(String exception, String exceptionMessage, Exception detailedException,String... params) {
		super(exception);
		this.exception = exception;
		this.exceptionMessage = exceptionMessage;
		logger.info("exception {}",detailedException);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				paramList.add(params[i]);
			}
		}
	}

	public CustomParameterizedException(String exception, String exceptionMessage, List<String> paramList) {
		super(exception);
		this.exception = exception;
		this.exceptionMessage = exceptionMessage;
		this.paramList.addAll(paramList);
	}
	
	public CustomParameterizedException(String exception, String exceptionMessage,Map<String,List<String>> data,String... params) {
		super(exception);
		this.exception = exception;
		this.exceptionMessage = exceptionMessage;
		this.data = data;
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				paramList.add(params[i]);
			}
		}		
	}

	public CustomParameterizedException(String exception, String exceptionMessage) {
		super(exception);
		this.exception = exception;
		this.exceptionMessage = exceptionMessage;
	}

	public ParameterizedErrorVM getErrorVM() {
		return new ParameterizedErrorVM(exception, exceptionMessage,data,paramList);
	}
	
	//this method used when throws exception by Supplier class
	public CustomParameterizedException printException(Exception exception){
		logger.info("exception {}",exception);
		return this;
	}
}
