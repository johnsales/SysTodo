package com.todo.webservice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class MCORSFilter implements ContainerResponseFilter {
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "*";

    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE = "true";

    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS_VALUE = "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With, Accept";

    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_METHODS_VALUE = "GET, POST, PUT, DELETE, OPTIONS, HEAD";

    public static final String[] ALL_HEADERs = {
            ACCESS_CONTROL_ALLOW_ORIGIN,
            ACCESS_CONTROL_ALLOW_CREDENTIALS,
            ACCESS_CONTROL_ALLOW_HEADERS,
            ACCESS_CONTROL_ALLOW_METHODS
    };
    public static final String[] ALL_HEADER_VALUEs = {
            ACCESS_CONTROL_ALLOW_ORIGIN_VALUE,
            ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE,
            ACCESS_CONTROL_ALLOW_HEADERS_VALUE,
            ACCESS_CONTROL_ALLOW_METHODS_VALUE
    };
    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        for (int i = 0; i < ALL_HEADERs.length; i++) {
            ArrayList<Object> value = new ArrayList<>();
            value.add(ALL_HEADER_VALUEs[i]);
            response.getHttpHeaders().put(ALL_HEADERs[i], value); //using put method
        }
        return response;
    }
}