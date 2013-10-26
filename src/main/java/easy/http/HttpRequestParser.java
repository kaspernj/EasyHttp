package easy.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import easy.sockets.EasySocketClient;

public class HttpRequestParser {
	private static Pattern STATUS_LINE_PATTERN = Pattern.compile("(GET|POST)\\s+(.+)\\s+HTTP\\/([\\d\\.]+)(\\r\\n|\\n)$");
	private static Pattern HEADER_PATTERN = Pattern.compile("^(.+?): (.+)(\\r\\n|\\n)$");
	private EasySocketClient socket;
	private HttpRequest request;

	public HttpRequestParser(EasySocketClient socketIn) {
		request = new HttpRequest();
		socket = socketIn;
		parseStatusLine();
		parseHeaders();
	}
	
	public HttpRequest getRequest(){
		return request;
	}

	private void parseStatusLine() {
		String statusLine = socket.gets();
		Matcher matchStatusLine = STATUS_LINE_PATTERN.matcher(statusLine);
		if (!matchStatusLine.matches()) throw new RuntimeException("Could not parse status line: '" + statusLine + "'.");
		
		request.type = matchStatusLine.group(1);
		request.path = matchStatusLine.group(2);
		request.httpVersion = matchStatusLine.group(3);
	}

	private void parseHeaders() {
		while(true){
			String headerStr = socket.gets();
			if (headerStr.equals("\r\n") || headerStr.equals("\n")) break;
			Matcher matchHeader = HEADER_PATTERN.matcher(headerStr);
			if (!matchHeader.matches()) throw new RuntimeException("Could not match headers from: " + headerStr);
			
			HttpHeader header = new HttpHeader(matchHeader.group(1), matchHeader.group(2));
			request.addHeader(header);
		}
	}
}
