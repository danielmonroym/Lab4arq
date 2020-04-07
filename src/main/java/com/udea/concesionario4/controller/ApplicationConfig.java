package com.udea.concesionario4.controller;

import static com.udea.concesionario4.security.AuthoritiesConstants.ADMIN;
import static com.udea.concesionario4.security.AuthoritiesConstants.USER;
import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

@LoginConfig(
    authMethod = "MP-JWT",
    realmName = "MP-JWT"
)
@DeclareRoles({ADMIN, USER})
@ApplicationPath("resources")
public class ApplicationConfig extends Application {
}
