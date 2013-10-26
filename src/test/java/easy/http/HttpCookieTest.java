package easy.http;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;

public class HttpCookieTest{
  @Test
  public void testBasics(){
    HttpCookie cookie = new HttpCookie("Key", "Value");
    assertEquals("Set-Cookie: Key=Value\r\n", cookie.getHeaderLine());
  }
  
  @Test
  public void testExpires(){
    Timestamp ts = new Timestamp(System.currentTimeMillis());

    HttpCookie cookie = new HttpCookie("Key", "Value");
    cookie.setExpires(ts);
    assertEquals("Set-Cookie: Key=Value; Expires=" + HttpCookie.HTTP_DATETIME_FORMAT.format(ts) + "\r\n", cookie.getHeaderLine());
  }
}
