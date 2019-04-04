package com.algonquincollge.cst8277.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollge.cst8277.ejb.CartBean;
import com.algonquincollge.cst8277.model.Cart;
import com.algonquincollge.cst8277.model.CartItem;

@Path("cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {
    
    @EJB
    protected CartBean cartBean;
    
    @GET
    @Path("/{id}")
    @Operation(description = "Gets a Cart by ID")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning Cart"),
        @APIResponse(responseCode = "404", description = "We could not find Cart")
    })
    public Response getCart(@PathParam("id") int id) {
        Cart cart = cartBean.getCart(id);
        return Response.ok(cart).build();
    }
    
    @POST
    @Path("/{id}/item/{itemId}")
    @Operation(description = "Adds/Updates Items in Cart")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful")
    })
    public Response addItemToCart(@PathParam("id") int id, @PathParam("itemId") int itemId,
            @Parameter(description = "a given user", required = true) CartItem cartItem) {
        cartBean.addItemToCart(id, itemId, cartItem);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}/item/{itemId}")
    @Operation(description = "Delete an Item in Cart")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful")
    })
    public Response deleteItemInCart(@PathParam("id") int id, @PathParam("itemId") int itemId) {
        cartBean.deleteItemInCart(id, itemId);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    @Operation(description = "Deletes a Cart")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful"),
        @APIResponse(responseCode = "404", description = "We could not find Cart")
    })
    public Response deleteCart(@PathParam("id") int id) {
        cartBean.deleteCart(id);
        return Response.ok().build();
    }
}
