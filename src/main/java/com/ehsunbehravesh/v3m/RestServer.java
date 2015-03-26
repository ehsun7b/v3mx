package com.ehsunbehravesh.v3m;

import com.ehsunbehravesh.v3m.web.resource.StaticResource;
import com.ehsunbehravesh.v3m.web.resource.WebResources;
import java.util.HashMap;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

/**
 *
 * @author Ehsun Behravesh
 */
public class RestServer extends Verticle {

    private HttpServer httpServer;
    private RestRequestHandler requestHandler;
    private HashMap<String, WebResources> resources;

    public RestServer() {
        initResources();
    }

    @Override
    public void start() {
        requestHandler = new RestRequestHandler();
        httpServer = vertx.createHttpServer();
        httpServer.requestHandler(requestHandler);
        httpServer.listen(8080);
    }

    private void initResources() {
        resources = new HashMap<>();
        resources.put("/", new StaticResource("text/html", "web/index.html"));
        resources.put("/index.html", new StaticResource("text/html", "web/index.html"));
    }

    public class RestRequestHandler implements Handler<HttpServerRequest> {

        @Override
        public void handle(HttpServerRequest req) {
            String uri = req.uri();            
            
            WebResources resource = resources.get(uri);
            System.out.println(uri);

            try {
                System.out.println(resource.getContentType());
                if (resource instanceof StaticResource) {                    
                    req.response().sendFile(((StaticResource) resource).getFile());
                }
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
                req.response().setStatusCode(404);
                req.response().sendFile("web/404.html");
            }
        }

    }

}
