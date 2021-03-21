package ca.capstone.http;

public interface HttpRequest {

	/**
	 * Get the type of request - the Http Method
 	 * @return The HttpMethod
	 */
	HttpMethod getMethod();

	/**
	 * Get the path from the URI (E.g. http://theregister.co.uk/articles/today.html) would return /articles/today.html)
	 * @return The path
	 */
	String getPath();

	/**
	 * Returns the value of the requested header (Note: headers are in the form of <Name>: <Value> -
	 * E.g Content-Type: text/html
	 * @param header The header's name
	 * @return The headers value
	 */
	String getHeader(String header);

	/**
	 * The Http Methods
	 */
	enum HttpMethod {
		GET,
		POST,
		PUT,
		DELETE,
		HEAD,
		TRACE,
		CONNECT,
		PATCH
	}
	
}
