package com.expensetracker.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Environment util.
 */
@Component
public class EnvUtil {
    @Autowired
    Environment environment;

    private String port;
    private String hostname;

    /**
     * Get port.
     *
     * @return
     */
    public String getPort() {
        if (port == null) port = environment.getProperty("server.port");
        return port;
    }

    /**
     * Get port, as Integer.
     *
     * @return
     */
    public Integer getPortAsInt() {
        return Integer.valueOf(getPort());
    }

    /**
     * Get hostname.
     *
     * @return
     */
    public String getHostname() throws UnknownHostException {
        // TODO ... would this cache cause issue, when network env change ???
        if (hostname == null) hostname = InetAddress.getLocalHost().getHostAddress();
        return hostname;
    }

    public String getServerUrlPrefi() throws UnknownHostException {
//        return "https://prakashr-expense-tracker-api.herokuapp.com/api";
    	 return "http://localhost:8080/api";
    }
    
    public String getAngularUrl() throws UnknownHostException {
    	
//  	 return "http://localhost:4200/success";
  	 return "https://prakashr-expense-tracker.herokuapp.com/success";
  }
}
