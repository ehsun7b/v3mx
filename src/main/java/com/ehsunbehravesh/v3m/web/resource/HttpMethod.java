package com.ehsunbehravesh.v3m.web.resource;

/**
 *
 * @author ehsun7b
 */
public enum HttpMethod {

  GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE"), OPTIONS("OPTIONS");
  
  private final String value;

  private HttpMethod(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
