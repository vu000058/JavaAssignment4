package com.algonquincollge.cst8277.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import com.algonquincollge.cst8277.ejb.ItemBean;
import com.algonquincollge.cst8277.model.Item;

@Path("item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
    
    @EJB
    protected ItemBean itemBean;
    
    @GET
    @Operation(description = "Retrieves list of Items")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning items")
    })
    public Response getItems() {
        List<Item> items = itemBean.getItems();
        return Response.ok(items).build();
    }
    
    @POST
    @Operation(description = "Adds a new Item")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning Item's ID")
    })
    public Response addItem(@Parameter(description = "a given Item", required = true) Item item) {
        int id = itemBean.addItem(item);
        return Response.ok(id).build();
    }
    
    @GET
    @Path("/{id}")
    @Operation(description = "Gets an Item by ID")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning Item"),
        @APIResponse(responseCode = "404", description = "We could not find Item")
    })
    public Response getItem(@PathParam("id") int id) {
        Item item = itemBean.getItem(id);
        return Response.ok(item).build();
    }
    
    @PUT
    @Path("/{id}")
    @Operation(description = "Updates an Item")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful")
    })
    public Response updateItem(@PathParam("id") int id, 
            @Parameter(description = "a given Item", required = true) Item item) {
        item.setId(id);
        itemBean.updateItem(item);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    @Operation(description = "Delete an Item")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful"),
        @APIResponse(responseCode = "404", description = "We could not find Item")
    })
    public Response deleteItem(@PathParam("id") int id) {
        itemBean.deleteItem(id);
        return Response.ok().build();
    }
}
