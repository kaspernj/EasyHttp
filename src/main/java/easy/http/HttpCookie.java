package easy.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class HttpCookie{
  public static SimpleDateFormat HTTP_DATETIME_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");

  String name;
  String value;
  private Timestamp expires;

  public HttpCookie(String name, String value){
    setName(name);
    setValue(value);
  }

  public String getName(){
    return name;
  }
  
  public void setName(String nameIn){
    name = nameIn;
  }

  public String getValue(){
    return value;
  }
  
  public void setValue(String valueIn){
    value = valueIn;
  }
  
  public Timestamp getExpires(){
    return expires;
  }
  
  public void setExpires(Timestamp expiresIn){
    expires = expiresIn;
  }

  public String getHeaderLine(){
    try{
      String headerLine = "Set-Cookie: ";
      headerLine += URLEncoder.encode(name, "ISO-8859-1");
      headerLine += "=";
      headerLine += URLEncoder.encode(value, "ISO-8859-1");
      
      if (expires != null){
        headerLine += "; Expires=" + HTTP_DATETIME_FORMAT.format(expires);
      }
      
      headerLine += "\r\n";

      return headerLine;
    }catch(UnsupportedEncodingException e){
      throw new HttpError("Unsupported encoding: " + e.getMessage());
    }
  }
}
