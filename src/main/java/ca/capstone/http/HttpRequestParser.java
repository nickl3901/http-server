package ca.capstone.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestParser {

    public static HttpRequest parse(BufferedReader reader) throws IOException {
        // look at reader.lines() which returns a Stream<String> or use a loop and use readLine()
        // the reader will be the request from the browser and will follow the HTTP spec as detailed
        // in https://tools.ietf.org/html/rfc2616#section-4
        // once you have that return a HttpRequest using an nested class (inner, anonymous etc) or a concrete outer
        // class see - https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
        String requestLine = reader.readLine();
        HttpRequest.HttpMethod method = HttpRequest.HttpMethod.valueOf(requestLine.split(" ")[0]);
        String path = requestLine.split(" ")[1].replaceFirst("/", "");
        String line = "";
        Map<String,String> headers = new HashMap<>();
        do {
            line = reader.readLine();
            if (!line.isEmpty()) {
                var header = line.split(":");
                headers.put(header[0].trim().toLowerCase(), header[1].trim());
            }
        } while (!line.isEmpty());
        return new HttpRequest() {
            @Override
            public HttpMethod getMethod() {
                return method;
            }

            @Override
            public String getPath() {
                return path;
            }

            @Override
            public String getHeader(String header) {
                return headers.get(header.toLowerCase());
            }
        };
    }

}
