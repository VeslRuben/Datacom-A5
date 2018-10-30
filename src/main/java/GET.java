import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

class GET {

    /**
     * Send HTTP GET
     *
     * @param path     Relative path in the API.
     */
    static String sendGet(String path) {
        // Base URL (address) of the server
        String BASE_URL = "http://" + "104.248.47.74" + ":" + "80" + "/";
        try {
            String url = BASE_URL + path;
            URL urlObj = new URL(url);
            System.out.println("Sending HTTP GET to " + url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Server reached");
                // Response was OK, read the body (data)
                InputStream stream = con.getInputStream();
                String responseBody = convertStreamToString(stream);
                stream.close();
                System.out.println("Response from the server:");
                //System.out.println(responseBody);
                return responseBody;
            } else {
                String responseDescription = con.getResponseMessage();
                System.out.println("Request failed, response code: " + responseCode + " (" + responseDescription + ")");
                return null;
            }
        } catch (ProtocolException e) {
            System.out.println("Protocol not supported by the server");
            return null;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Read the whole content from an InputStream, return it as a string
     * @param is Inputstream to read the body from
     * @return The whole body as a string
     */
    private static String convertStreamToString(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append('\n');
            }
        } catch (IOException ex) {
            System.out.println("Could not read the data from HTTP response: " + ex.getMessage());
        }
        return response.toString();
    }
}
