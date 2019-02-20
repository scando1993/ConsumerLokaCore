package EventHub;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.net.URL;

public class EventHub{
    private static final String EVENT_HUB_NAME = "iot-button";
    private static final String EVENT_HUB_ROUTE = "coldchaintrack/messages";
    private static final String EVENT_HUB_KEY = "k/pPlayZW9uE6rFpWvPUj3udfXcdGPEddo+X1nTimw4=";
    private static final String EVENT_HUB_KEY_NAME = "WifiManageSharedAccessKey";

    public EventHub() {
    }

    public boolean sendMessage(EventHubMessage hubMessage){
        try {
            URL url = new URL(String.format("https://%s.servicebus.windows.net/%s", EVENT_HUB_NAME, EVENT_HUB_ROUTE));
            String          postUrl        = url.toString();// put in your url
            Gson            gson           = new Gson();
            HttpClient      httpClient     = HttpClientBuilder.create().build();
            HttpPost        post           = new HttpPost(postUrl);
            try {
                StringEntity    postingString  = new StringEntity(gson.toJson(hubMessage));//gson.tojson() converts your pojo to json
                String          token          = GetSASToken(postUrl, EVENT_HUB_KEY_NAME, EVENT_HUB_KEY);
                post.setEntity(postingString);
                post.setHeader("Content-type", "application/json");
                post.setHeader("Authorization", token);
                try{
                    HttpResponse  response = httpClient.execute(post);
                    if (response.getStatusLine().getStatusCode() == 201)
                        return true;
                }catch (ClientProtocolException excl){
                    System.out.println(excl);
                    excl.printStackTrace();
                }catch (IOException ioex){
                    System.out.println(ioex.toString());
                    ioex.printStackTrace();
                }
            }catch (UnsupportedEncodingException ex){
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }catch (MalformedURLException e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return false;
    }

    private static String GetSASToken(String resourceUri, String keyName, String key)
    {
        long epoch = System.currentTimeMillis()/1000L;
        int week = 60*60*24*7;
        String expiry = Long.toString(epoch + week);

        String sasToken = null;
        try {
            String stringToSign = URLEncoder.encode(resourceUri, "UTF-8") + "\n" + expiry;
            String signature = getHMAC256(key, stringToSign);
            sasToken = "SharedAccessSignature sr=" + URLEncoder.encode(resourceUri, "UTF-8") +"&sig=" +
                    URLEncoder.encode(signature, "UTF-8") + "&se=" + expiry + "&skn=" + keyName;
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        return sasToken;
    }


    private static String getHMAC256(String key, String input) {
        Mac sha256_HMAC = null;
        String hash = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            Base64.Encoder encoder = Base64.getEncoder();

            hash = new String(encoder.encode(sha256_HMAC.doFinal(input.getBytes("UTF-8"))));

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return hash;
    }
}
