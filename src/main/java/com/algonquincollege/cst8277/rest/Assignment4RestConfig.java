package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.Assignment4Constants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.SERVER_API_DESC;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.SERVER_URL;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.USER_ROLENAME;

//import java.util.Set;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationPath("/api/v1")
@OpenAPIDefinition(info = @Info(
    title = "Rest'ful Demo API",
    version = "1.0.0",
    description = SERVER_API_DESC
    ),
    servers = {
        @Server(url = SERVER_URL)
    }
)
@DeclareRoles({USER_ROLENAME, ADMIN_ROLENAME})
public class Assignment4RestConfig extends Application {
}