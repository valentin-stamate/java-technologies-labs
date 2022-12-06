package com.example.backend;

import com.example.backend.filters.binding.AllowCors;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@AllowCors
public class ServerController extends Application { }