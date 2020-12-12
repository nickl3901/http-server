package ca.capstone.http;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequestParser {

	public static HttpRequest parse(BufferedReader reader) throws IOException {
		// look at reader.lines() which returns a Stream<String> or use a loop and use readLine()
		// the reader will be the request from the browser and will follow the HTTP spec as detailed
		// in https://tools.ietf.org/html/rfc2616#section-4
		// once you have that return a HttpRequest using an nested class (inner, anonymous etc) or a concrete outer
		// class see - https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
		throw new UnsupportedOperationException("TO BE IMPLEMENTED");
	}
	
}
