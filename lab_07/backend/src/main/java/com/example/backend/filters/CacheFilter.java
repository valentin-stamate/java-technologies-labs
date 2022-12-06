package com.example.backend.filters;

import com.example.backend.database.models.Document;
import com.example.backend.filters.binding.FileCached;
import com.example.backend.service.CachingService;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@FileCached
@Provider
@Priority(Priorities.USER)
public class CacheFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    private CachingService cachingService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String verb = requestContext.getRequest().getMethod();

        System.out.printf("Cache Filter [%s]\n", verb);
        var path = requestContext.getUriInfo().getPathSegments();

        if (path.size() != 3) {
            return;
        }

        String fileId = path.get(2).getPath();

        byte[] buffer = cachingService.getFile(fileId);

        if (buffer == null) {
            return;
        }

        if (verb.equals("GET")) {
            requestContext.abortWith(Response.ok()
                    .entity(new ByteArrayInputStream(buffer))
                    .header("Content-Type", "application/octet-stream")
                    .build());
        } else if (verb.equals("DELETE")) {
            cachingService.removeFile(fileId);
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

    }
}
