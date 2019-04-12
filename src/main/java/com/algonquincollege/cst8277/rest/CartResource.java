package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.Assignment4Constants.CART_PATH;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.FORBIDDEN;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.CART_ITEM_PATH;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.ID_PATH;
import static com.algonquincollege.cst8277.utils.Assignment4Constants.USER_ROLENAME;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import com.algonquincollege.cst8277.ejb.CartBean;
import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.CartItem;
import com.algonquincollege.cst8277.models.PlatformUser;


@Path(CART_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {
    
    @EJB
    protected CartBean cartBean;
    
    @Inject
    protected SecurityContext sc;
    
    @GET
    @Path(ID_PATH)
    @Operation(description = "Gets a Cart by ID")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning Cart"),
        @APIResponse(responseCode = "404", description = "We could not find Cart")
    })
    @RolesAllowed({ADMIN_ROLENAME, USER_ROLENAME})
    public Response getCart(@PathParam("id") int id) {
        Response response = null;
        
        if (!sc.isCallerInRole(ADMIN_ROLENAME) && !isCartOwner(id)) {
            response = Response.status(Status.FORBIDDEN).entity(FORBIDDEN).build(); 
        } else {
            Cart cart = cartBean.getCart(id);
            response = Response.ok(cart).build();
        }
        
        return response;
    }
    
    @POST
    @Path(CART_ITEM_PATH)
    @Operation(description = "Adds/Updates Items in Cart")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful")
    })
    @RolesAllowed(USER_ROLENAME)
    public Response addItemToCart(@PathParam("id") int id, @PathParam("itemId") int itemId,
            @Parameter(description = "a given user", required = true) CartItem cartItem) {

        Response response = null;
        
        if (!isCartOwner(id)) {
            response = Response.status(Status.FORBIDDEN).entity(FORBIDDEN).build(); 
        } else {
            cartBean.addItemToCart(id, itemId, cartItem);
            response = Response.ok().build();
        }
        
        return response;
    }
    
    @DELETE
    @Path(CART_ITEM_PATH)
    @Operation(description = "Delete an Item in Cart")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful")
    })
    @RolesAllowed(USER_ROLENAME)
    public Response deleteItemInCart(@PathParam("id") int id, @PathParam("itemId") int itemId) {
        Response response = null;
        
        if (!isCartOwner(id)) {
            response = Response.status(Status.FORBIDDEN).entity(FORBIDDEN).build(); 
        } else {
            cartBean.deleteItemInCart(id, itemId);
            response = Response.ok().build();
        }
        
        return response;
    }
    
    @DELETE
    @Path(ID_PATH)
    @Operation(description = "Deletes a Cart")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful"),
        @APIResponse(responseCode = "404", description = "We could not find Cart")
    })
    @RolesAllowed(USER_ROLENAME)
    public Response deleteCart(@PathParam("id") int id) {
        Response response = null;
        
        if (!isCartOwner(id)) {
            response = Response.status(Status.FORBIDDEN).entity(FORBIDDEN).build(); 
        } else {
            cartBean.deleteCart(id);
            response = Response.ok().build();
        }
        
        return response;
    }
    
    /**
     * Checks if the caller is the owner of the cart or not
     * @param cartId - id of the cart
     * @return true if the caller is the owner, false otherwise
     */
    private boolean isCartOwner(int cartId) {
        Principal principal = sc.getCallerPrincipal();
        PlatformUser platformUser = (PlatformUser) principal;
        Cart cart = cartBean.getCart(cartId);
        
        return platformUser.getId() == cart.getCustomer().getPlatformUser().getId();
    }
}
