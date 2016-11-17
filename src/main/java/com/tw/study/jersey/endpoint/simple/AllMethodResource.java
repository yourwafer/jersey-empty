package com.tw.study.jersey.endpoint.simple;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Created by hwwei on 2016/11/17.
 */
@Path("method")
public class AllMethodResource {


    @GET
    @Path("{id}")
    public Response get(@PathParam("id") int id, @Context HttpHeaders request, @Context UriInfo ui) {
        System.out.printf("request" + request.toString() + ",uri:" + ui);
        return Response.ok().entity("get method ok.").build();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void post(MultivaluedMap<String, String> formParams) {
        System.out.println(formParams.size() + "-->" + formParams.toString());
    }

    @GET
    public void beanParam(@BeanParam MethodParam param) {
        System.out.println(param);
    }

    @Path("sub")
    public SubClassOne sub() {
        return new SubClassOne();
    }


    public static class SubClassOne {

        @GET
        public Response get() {
            return Response.ok("sub class one get").build();
        }

        @POST
        public Response post() {
            return Response.ok("sub class one post").build();
        }
    }


    public static class MethodParam {

        @Context
        public HttpHeaders request;

        @Context
        public UriInfo ui;

        @Override
        public String toString() {
            return "MethodParam{" +
                    "request=" + request +
                    ", ui=" + ui +
                    '}';
        }
    }
}
