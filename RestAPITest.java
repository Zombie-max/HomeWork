import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RestAPITest {

    OkHttpClient client = new OkHttpClient();

    @Test
    @DisplayName("Case 5 – REST API testing")
    void apiCase() throws IOException {

        // Create and send request:

        // Host: https://jsonplaceholder.typicode.com
        // Endpoint: /users
        String BASE_URL = "https://jsonplaceholder.typicode.com";
        String endpoint = "/users";

        // Request type: GET
        // GET https://jsonplaceholder.typicode.com/users
        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        // Assert response contains any data
        assert response.body() != null;
        // Extract Json from response body
        // Parse response to Json format
        String json = response.body().string();

        // JSONArray processing
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {

            // Log only the names and emails from the response data
            String name = arr.getJSONObject(i).getString("name");
            String email = arr.getJSONObject(i).getString("email");
            System.out.println(name + " | " + email);

            //  Verify the first email address contains ‘@’
            if (i == 0) {
                assertTrue(email.contains("@"));
            }
        }
    }
}