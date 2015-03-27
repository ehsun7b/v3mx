package com.ehsunbehravesh.v3m.web.resource;

import java.io.IOException;
import org.vertx.java.core.http.HttpServerResponse;

/**
 *
 * @author Ehsun Behravesh <ehsun.behravesh@mimos.my>
 */
public abstract class WebResource {

    protected String contentType;

    public WebResource(String contentType) {
        this.contentType = contentType;
    }

    //@Deprecated
    //public abstract void writeContent(OutputStream outputStream) throws IOException;

    public abstract void writeContent(HttpServerResponse response) throws IOException;
    
    public abstract long contentLength();

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
        
}
