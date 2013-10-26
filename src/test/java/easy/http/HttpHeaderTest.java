package easy.http;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HttpHeaderTest{
  @Test
  public void testBascics(){
    HttpHeader header = new HttpHeader("Key", "Value");
    assertEquals("Key: Value\r\n", header.getHeaderLine());
  }
}
