package com.google.gwt.maps.client.services;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.workaround.WorkAroundUtils;

/**
 * A service for computing directions between two or more places.
 * <br><br>
 * See <a href="https://developers.google.com/maps/documentation/javascript/reference#DirectionsService">DirectionsService API Doc</a>
 */
public class DirectionsService extends JavaScriptObject {
  
  /**
   * use newInstance();
   */
  protected DirectionsService() {}

  /**
   * Creates a new instance of a DirectionsService that sends directions queries to Google servers.
   */
  public static final DirectionsService newInstance() {
    JavaScriptObject jso = createJso();
    WorkAroundUtils.removeGwtObjectId(jso);
    return jso.cast();
  }

  private static final native JavaScriptObject createJso() /*-{
    return new $wnd.google.maps.DirectionsService();
  }-*/;
  
  /**
   * Issue a directions search request.
   * 
   *  TODO I can't test this in dev mode, but seems to be working in production deployed. maps api can't find <__gwt_ObjectId>
   * 
   * @param request
   * @param handler
   */
  public final native void route(DirectionsRequest request, DirectionsResultHandler handler) /*-{
    var callback = function(result, status) {
      
      // debug
      //@com.google.gwt.maps.client.services.DirectionsService::test(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("result=", result);
      //@com.google.gwt.maps.client.services.DirectionsService::test(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("status=", status);

      // process callback
      @com.google.gwt.maps.client.services.DirectionsService::routeImpl(Lcom/google/gwt/maps/client/services/DirectionsResult;Ljava/lang/String;Lcom/google/gwt/maps/client/services/DirectionsResultHandler;)(result, status, handler);
    };
    
//    // this works - but the incoming request2 jso, matches exactly yet, won't work, ????
//    var request2 = {
//      origin: "Arlington, WA",
//      destination: "Seattle, WA",
//      travelMode: "DRIVING" // or is $wnd.google.maps.TravelMode.DRIVING
//    };
    
    // output the object for debugging
    //@com.google.gwt.maps.client.services.DirectionsService::test(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("request", request);
    //@com.google.gwt.maps.client.services.DirectionsService::test(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("request2=", request2);
    
    this.route(request, callback);
  }-*/;
  
  private static final void routeImpl(DirectionsResult result, String status, DirectionsResultHandler handler) {
    handler.onCallback(result, DirectionsStatus.fromValue(status));
  }
  
//  public static final void test(String msg, JavaScriptObject jso) {
//    JSONObject j = new JSONObject(jso);
//    GWT.log("msg= "+ msg + " jso=" + j.toString());
//  }
//  
 
}
