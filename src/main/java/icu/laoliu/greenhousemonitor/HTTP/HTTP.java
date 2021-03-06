/*** Repacked HTTP Lib
 * @author Lao-Liu233
 */

package icu.laoliu.greenhousemonitor.HTTP;

import okhttp3.*;

import java.io.IOException;

public class HTTP {
    private final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final String url;

    /*** Initial HTTP
     * @param url The url of request
     */
    public HTTP(String url) {
        this.url = url;
    }

    /*** HTTP GET
     * @return Respond body, null for error
     */
    public String get() {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*** HTTP POST
     * @param data Request data
     * @return Respond body, null for error
     */
    public String post(String data) {
        RequestBody requestBody = RequestBody.create(JSON, data);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute();) {
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
