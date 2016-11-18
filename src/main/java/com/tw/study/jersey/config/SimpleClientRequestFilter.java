package com.tw.study.jersey.config;

import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by hwwei on 2016/11/17.
 */
@Provider
public class SimpleClientRequestFilter implements ContainerRequestFilter, ContainerResponseFilter, Feature {
    {
        System.out.println("===============");
    }


    @Override
    public boolean configure(FeatureContext context) {
        context.register(new SimpleClientRequestFilter());
        context.register(LoggingFeature.class);
        return true;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("filter container request->" + requestContext.getUriInfo());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        System.out.println("filter container response->" + responseContext.getEntity());
    }
}
