package com.ehsunbehravesh.v3m.web.resource;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Ehsun Behravesh <ehsun.behravesh@mimos.my>
 */
public abstract class WebResources {

    protected String contentType;

    public WebResources(String contentType) {
        this.contentType = contentType;
    }

    public abstract void writeContent(OutputStream outputStream) throws IOException;

    public abstract long contentLength();

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
        
}
