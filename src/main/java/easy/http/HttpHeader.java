package easy.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HttpHeader {
	private String key;
	private String val;

	public HttpHeader(String key, String val){
		this.key = key;
		this.val = val;
	}
  
  public String getKey(){
    return key;
  }
  
  public String getValue(){
    return val;
  }
  
  public String getHeaderLine(){
    try{
      return URLEncoder.encode(key, "ISO-8859-1") + ": " + URLEncoder.encode(val, "ISO-8859-1") + "\r\n";
    }catch(UnsupportedEncodingException e){
      throw new HttpError("Unsupported encoding: " + e.getMessage());
    }
  }
}
