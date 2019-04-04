package com.algonquincollge.cst8277.rest;

import static com.algonquincollege.cst8277.security.Assignment4Constants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.security.Assignment4Constants.USER_ROLENAME;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationPath("/api/v1")
@OpenAPIDefinition(info = @Info(
    title = "Rest'ful API Assignment 4",
    version = "1.0.0",
    description = "API resource for Shopping Cart Application"
    ),
    servers = {
        @Server(url = "/shopping")
    }
)
@DeclareRoles({USER_ROLENAME, ADMIN_ROLENAME})
public class RestConfig extends Application {

}
