package com.algonquincollge.cst8277.rest;

import static com.algonquincollege.cst8277.security.Assignment4Constants.ADMIN_ROLENAME;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollge.cst8277.ejb.UserBean;
import com.algonquincollge.cst8277.model.Cart;
import com.algonquincollge.cst8277.model.PlatformUser;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    
    @EJB
    protected UserBean userBean;
    
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
    @Path("/{id}")
    @Operation(description = "Updates a User")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful"),
        @APIResponse(responseCode = "404", description = "We could not find User")
    })
    public Response updateUser(@PathParam("id") int id, 
            @Parameter(description = "a given user", required = true) PlatformUser user) {
        user.setId(id);
        userBean.updateUser(user);
        return Response.ok().build();
    }
    
    @GET
    @Path("/{id}")
    @Operation(description = "Gets a User by ID")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning User"),
        @APIResponse(responseCode = "404", description = "We could not find User")
    })
    public Response getUser(@PathParam("id") int id) {
        PlatformUser user = userBean.getUser(id);
        return Response.ok(user).build();
    }
    
    
    @GET
    @Path("/{id}/cart")
    @Operation(description = "Gets list of Carts of a given User")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning carts"),
        @APIResponse(responseCode = "404", description = "We could not find carts")
    })
    public Response getUserCarts(@PathParam("id") int id) {
        List<Cart> carts = userBean.getUser(id).getCarts();
        return Response.ok(carts).build();
    }
    
    @POST
    @Path("/{id}/cart")
    @Operation(description = "Adds a Cart to a given User")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning Cart's ID")
    })
    public Response addUserCart(@PathParam("id") int userId, 
            @Parameter(description = "a given user", required = true) Cart cart) {
        cart.setUser(userBean.getUser(userId));
        int id = userBean.addUserCart(cart);
        return Response.ok(id).build();
    }
}
