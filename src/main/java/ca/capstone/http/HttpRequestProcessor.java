package ca.capstone.http;

import java.io.*;
import java.net.Socket;

public class HttpRequestProcessor implements Runnable {

	private static final int EOF = -1;
	
	private final Socket socket;
	private final HttpController controller;
	
	public HttpRequestProcessor(Socket socket) {
		this.socket = socket;
		this.controller = new HttpController();
	}
	
	@Override
	public void run() {
		try {
			HttpRequest request = HttpRequestParser.parse(new BufferedReader(new InputStreamReader(socket.getInputStream())));
			HttpResponse response = controller.requestResponse(request);
			writeResponse(HttpStatus.OK, response);
		} catch (Exception e) {
			e.printStackTrace();
			internalServerError();
		}
		
	}
	
	private void writeResponse(HttpStatus httpStatus, HttpResponse response) throws IOException {
		try (final HttpOutputStream bufferedWriter = new HttpOutputStream(socket.getOutputStream())) {
			bufferedWriter.writeLine(httpStatus.status);
			bufferedWriter.writeLine("Content-Type: "+response.contentType());
			bufferedWriter.writeLine("");
			writeFile(bufferedWriter, response);
		}
	}
	
	private void writeFile(HttpOutputStream bufferedWriter, HttpResponse response) throws IOException {
		try (InputStream inputStream = response.file()) {
			final byte[] buffer = new byte[4096];
			for (int read = inputStream.read(buffer); read != EOF; read = inputStream.read(buffer)) {
				bufferedWriter.write(buffer, 0, read);
			}
		}
	}
	
	private void internalServerError() {
		try (final HttpOutputStream bufferedWriter = new HttpOutputStream(socket.getOutputStream())) {
			bufferedWriter.writeLine(HttpStatus.ERROR.status);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	public static class HttpOutputStream extends BufferedOutputStream {
		
		private static final byte[] CRNL = { '\r', '\n' }; 
		
		public HttpOutputStream(OutputStream outputStream) {
			super(new BufferedOutputStream(outputStream));
		}
		
		public void writeLine(String str) throws IOException {
			super.write(str.getBytes());
			super.write(CRNL);
		}
	}
	
	public enum HttpStatus {
		OK("HTTP/1.1 200 OK"),
		ERROR("HTTP/1.1 500 Server Error");
		
		private final String status;
		
		HttpStatus(String status) {
			this.status = status;
		}
	}
}
