package ca.capstone.http;

import java.io.InputStream;

public interface HttpResponse {

	String contentType();
	
	InputStream file();
	
}
