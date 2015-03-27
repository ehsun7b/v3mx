package com.ehsunbehravesh.v3m;

import com.ehsunbehravesh.v3m.exceptions.MethodNotAllowedException;
import com.ehsunbehravesh.v3m.web.resource.HttpMethod;
import com.ehsunbehravesh.v3m.web.resource.ResourceMapping;
import com.ehsunbehravesh.v3m.web.resource.StaticResource;
import com.ehsunbehravesh.v3m.web.resource.WebResource;
import java.util.HashSet;
import java.util.Set;
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
  private Set<ResourceMapping> resources;

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
    resources = new HashSet<>();
    resources.add(new ResourceMapping("/", new StaticResource("text/html", "web/index.html"), HttpMethod.GET));
    resources.add(new ResourceMapping("/index.html", new StaticResource("text/html", "web/index.html"), HttpMethod.GET));
  }

  public class RestRequestHandler implements Handler<HttpServerRequest> {

    @Override
    public void handle(HttpServerRequest req) {
      String uri = req.uri();

      ResourceMapping mapping = getResourceByURI(resources, uri);
      //System.out.println(uri);

      try {
        WebResource resource = mapping.getResource();
        HttpMethod method = mapping.getMethod();      
        
        if (!req.method().equalsIgnoreCase(method.getValue())) {
          throw new MethodNotAllowedException("Method ".concat(req.method()).concat(" not allowed!").concat(" ").concat(uri));
        }
        
        
        System.out.println(resource.getContentType());
        System.out.println(req.method());
        
        if (resource instanceof StaticResource) {
          req.response().sendFile(((StaticResource) resource).getFile());
        }
      } catch (NullPointerException ex) {
        System.out.println(ex.getMessage());
        req.response().setStatusCode(404);
        req.response().sendFile("web/404.html");
      } catch (MethodNotAllowedException ex) {
        System.out.println(ex.getMessage());
        req.response().setStatusCode(405);
        req.response().sendFile("web/405.html");
      }
    }

    private ResourceMapping getResourceByURI(Set<ResourceMapping> resources, String uri) {
      for (ResourceMapping mapping : resources) {
        if (mapping.getValue().equals(uri)) {
          return mapping;
        }
      }
      
      return null;
    }

  }

}
