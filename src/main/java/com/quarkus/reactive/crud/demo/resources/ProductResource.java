package com.quarkus.reactive.crud.demo.resources;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
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

import com.quarkus.reactive.crud.demo.exception.ExceptionHandler;
import com.quarkus.reactive.crud.demo.exception.RecordNotFoundException;
import com.quarkus.reactive.crud.demo.model.Product;
import com.quarkus.reactive.crud.demo.service.ProductService;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

/**
 * 
 * @author malam84
 * 
 * */

@RequestScoped
@Path("/api/v1/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
	
	private final ProductService prodService;

    @Inject
    public ProductResource(ProductService prodService) {
        this.prodService = prodService;
    }

    @GET
    @Operation(summary = "Get Products", description = "List of existing products")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))))
     public List<Product> getProductlst() {
        return prodService.getAllProducts();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Fetch Product", description = "Fetch product by product id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @APIResponse(responseCode = "404", description="Product not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Product getProduct(@PathParam("id") int id) throws RecordNotFoundException {
        return prodService.getProductById(id);
    }

    @POST
    @Operation(summary = "Create Product", description = "Insert product into database")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))))
    public Product createProduct(@Valid Product product) {
        return prodService.saveProduct(product);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update Product", description = "Update existing product by product id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @APIResponse(responseCode = "404", description="Product not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Product updateProduct(@PathParam("id") int id, @Valid Product product) throws RecordNotFoundException {
        return prodService.updateProduct(id, product);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete Product", description = "Delete product from database")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Success"),
            @APIResponse(responseCode = "404", description="Product not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Response deleteProduct(@PathParam("id") int id) throws RecordNotFoundException {
    	prodService.deleteProduct(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
