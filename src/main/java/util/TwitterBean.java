package util;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import org.json.JSONObject;

/**
 *
 * @author rktds
 */
@Named
@ViewScoped
public class TwitterBean implements Serializable {

    private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/2/tweets";

    private static final String apiKey = System.getenv("TWITTER_API_KEY");
    private static final String apiSecret = System.getenv("TWITTER_API_SECRET");
    private static final String accessTokenStr = System.getenv("TWITTER_ACCESS_TOKEN");
    private static final String accessTokenSecretStr = System.getenv("TWITTER_ACCESS_TOKEN_SECRET");

    private final OAuth10aService service;
    private final OAuth1AccessToken accessToken;

    public TwitterBean() {
        service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(TwitterApi.instance());
        accessToken = new OAuth1AccessToken(accessTokenStr, accessTokenSecretStr);
    }

    public String postTwitter(String tweetText) throws Exception {
        final OAuthRequest request = new OAuthRequest(Verb.POST, PROTECTED_RESOURCE_URL);

        request.addHeader("Content-Type", "application/json");

        String payload = "{\"text\": \"" + tweetText.replace("\"", "\\\"") + "\"}";
        request.setPayload(payload);

        service.signRequest(accessToken, request);

        try (Response response = service.execute(request)) {
            System.out.println("HTTP response code: " + response.getCode());
            System.out.println("Response body: " + response.getBody());

            if (response.getCode() == 201) {
                JSONObject jsonResponse = new JSONObject(response.getBody());
                String tweetId = jsonResponse.getJSONObject("data").getString("id");
                String tweetUrl = "https://twitter.com/i/web/status/" + tweetId;
                System.out.println("Link to the tweet: " + tweetUrl);
                return tweetUrl;
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
