package easy.http;

import java.util.ArrayList;

public class HttpRequest {
	private ArrayList<HttpHeader> headers = new ArrayList<HttpHeader>();
	public String type;
	public String path;
	public String httpVersion;

	public void addHeader(HttpHeader header){
		headers.add(header);
	}
}
