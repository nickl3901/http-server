package ca.capstone.http;

import java.io.InputStream;

public class HttpController {

	/**
	 * Method to process the http request and return the necessary http response
	 * @param httpRequest The http request
	 * @return The response for the request
	 */
	public HttpResponse requestResponse(HttpRequest httpRequest) {
		// For a given http request return a valid http response
		// Hint: you can switch on the HttpMethod since it's an enum E.g. switch (httpRequest.getMethod()) or you can
		// use it/else if/else if you so choose.
		// I would recommend breaking this into methods E.g. processGet/processPost etc
		// You could embed a full html web page and it's images if you want - look at
		// HttpController.class.getClassLoader().getResourceAsStream() or you can use the java.io.File to
		// point to a location on your filesystem
		// feel free to use something like this:
		/**
		return new HttpResponse() {
			@Override
			public String contentType() {
				return null; // This is f
			}

			@Override
			public InputStream file() {
				return null;
			}
		};
		 */
		throw new UnsupportedOperationException("TO BE IMPLEMENTED");
	}
	
}
