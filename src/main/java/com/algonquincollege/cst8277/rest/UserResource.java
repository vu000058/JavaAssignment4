package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.Assignment4Constants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.FORBIDDEN;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.ID_PATH;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.USER_CART_PATH;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.USER_PATH;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.USER_ROLENAME;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.UserBean;
import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.PlatformUser;


@Path(USER_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    
    @EJB
    protected UserBean userBean;
    
    @Inject
    protected SecurityContext sc;
    
    @GET
    @Operation(description = "Retrieves list of Users")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning users")
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getUsers() {
        List<PlatformUser> users = userBean.getUsers();
        return Response.ok(users).build();
    }
    
    @POST
    @Operation(description = "Adds a new User")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning User's ID")
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response addUser(@Parameter(description = "a given user", required = true) PlatformUser user) {
        int id = userBean.addUser(user);
        return Response.ok(id).build();
    }
    
    @PUT
    @Path(ID_PATH)
    @Operation(description = "Updates a User")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful"),
        @APIResponse(responseCode = "404", description = "We could not find User")
    })
    @RolesAllowed({ADMIN_ROLENAME, USER_ROLENAME})
    public Response updateUser(@PathParam("id") int id, 
            @Parameter(description = "a given user", required = true) PlatformUser user) {
        user.setId(id);
        userBean.updateUser(user);
        return Response.ok().build();
    }
    
    @GET
    @Path(ID_PATH)
    @Operation(description = "Gets a User by ID")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning User"),
        @APIResponse(responseCode = "404", description = "We could not find User")
    })
    @RolesAllowed({ADMIN_ROLENAME, USER_ROLENAME})
    public Response getUser(@PathParam("id") int id) {
        Principal principal = sc.getCallerPrincipal();
        PlatformUser platformUser = (PlatformUser) principal;
        Response response = null;
        
        if (!sc.isCallerInRole(ADMIN_ROLENAME) && platformUser.getId() != id) {
            response = Response.status(Status.FORBIDDEN).entity(FORBIDDEN).build(); 
        } else {
            response = Response.ok(platformUser).build();
        }
        
        return response;
    }
    
    
    @GET
    @Path(USER_CART_PATH)
    @Operation(description = "Gets list of Carts of a given User")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning carts"),
        @APIResponse(responseCode = "404", description = "We could not find carts")
    })
    @RolesAllowed(USER_ROLENAME)
    public Response getUserCarts(@PathParam("id") int id) {
        Principal principal = sc.getCallerPrincipal();
        PlatformUser platformUser = (PlatformUser) principal;
        Response response = null;
        
        if (!sc.isCallerInRole(ADMIN_ROLENAME) && platformUser.getId() != id) {
            response = Response.status(Status.FORBIDDEN).entity(FORBIDDEN).build(); 
        } else {
            List<Cart> carts = userBean.getUser(id).getCustomer().getCarts();
            response = Response.ok(carts).build();
        }
        
        return response;
    }
    
    @POST
    @Path(USER_CART_PATH)
    @Operation(description = "Adds a Cart to a given User")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning Cart's ID")
    })
    @RolesAllowed(USER_ROLENAME)
    public Response addUserCart(@PathParam("id") int userId, 
            @Parameter(description = "a given user", required = true) Cart cart) {
        cart.setCustomer(userBean.getUser(userId).getCustomer());
        int id = userBean.addUserCart(cart);
        return Response.ok(id).build();
    }
}
