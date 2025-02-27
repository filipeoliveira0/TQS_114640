package ex2_3;

import java.io.IOException;

public interface ISimpleHttpClient {
    String doHttpGet(String url) throws IOException;;
}
