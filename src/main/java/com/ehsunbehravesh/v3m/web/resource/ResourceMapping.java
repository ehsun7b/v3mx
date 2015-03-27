package com.ehsunbehravesh.v3m.web.resource;

/**
 *
 * @author ehsun7b
 */
public class ResourceMapping {

  protected final String value;
  protected final WebResource resource;
  protected final HttpMethod method;

  public ResourceMapping(String value, WebResource resource, HttpMethod method) {
    this.value = value;
    this.resource = resource;
    this.method = method;
  }

  public String getValue() {
    return value;
  }

  public WebResource getResource() {
    return resource;
  }

  public HttpMethod getMethod() {
    return method;
  }
  
}
