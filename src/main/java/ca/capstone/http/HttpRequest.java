package ca.capstone.http;

public interface HttpRequest {
	
	public HttpMethod getMethod();
	
	public String getPath();
	
	public String getHeader(String header);
	
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
