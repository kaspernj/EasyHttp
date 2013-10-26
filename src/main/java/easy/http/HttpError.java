package easy.http;

public class HttpError extends RuntimeException{
  public HttpError(String string){
    super(string);
  }

  private static final long serialVersionUID = 4774789024631748442L;
}
