package com.fullstack.backend.config;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Configuration
public class FlickrConfiguration {
    @Value("4d5034eeb1c3be72fae4162163034e7e")
    private String apiKey;
    @Value("bdcca8b64c2f6de3")
    private String apiSecret;
    @Value("72157720868330525-538e9d33ae2ef346")
    private String appKey;
    @Value("5e63dddac57fd890")
    private String appSecret;

    //@Bean public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
    //    Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
    //    OAuth10aService service = new ServiceBuilder(apiKey)
    //            .apiSecret(apiSecret)
    //            .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));
//
    //    final Scanner scanner = new Scanner(System.in);
    //    final OAuth1RequestToken requestToken = service.getRequestToken();
    //    final String authUrl = service.getAuthorizationUrl(requestToken);
//
    //    System.out.println(authUrl);
    //    System.out.println("Copier le code ici !");
//
    //    final String authVerifier = scanner.nextLine();
//
    //    OAuth1AccessToken accessToken = service.getAccessToken(requestToken, authVerifier);
//
    //    System.out.println(accessToken.getToken());
    //    System.out.println(accessToken.getTokenSecret());
//
    //    Auth auth = flickr.getAuthInterface().checkToken(accessToken);
//
    //    System.out.println("---------------------------");
    //    System.out.println(auth.getToken());
    //    System.out.println(auth.getTokenSecret());
//
    //    return flickr;
    //}

    @Bean
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
        Auth auth = new Auth();
        auth.setPermission(Permission.DELETE);
        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);
        flickr.setAuth(auth);
        return flickr;
    }
}
