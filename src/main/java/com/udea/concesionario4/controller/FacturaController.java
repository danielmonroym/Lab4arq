package com.udea.concesionario4.controller;

import com.udea.concesionario4.com.udea.concesionario4.modelo.Factura;
import com.udea.concesionario4.repository.FacturaRepository;
import com.udea.concesionario4.controller.util.HeaderUtil;
import static com.udea.concesionario4.security.AuthoritiesConstants.USER;
import org.slf4j.Logger;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
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
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * REST controller for managing Factura.
 */
@Path("/api/factura")
@RolesAllowed(USER)
public class FacturaController {

    @Inject
    private Logger log;

    @Inject
    private FacturaRepository facturaRepository;

    private static final String ENTITY_NAME = "factura";

    /**
     * POST : Create a new factura.
     *
     * @param factura the factura to create
     * @return the Response with status 201 (Created) and with body the
     * new factura, or with status 400 (Bad Request) if the factura has already
     * an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @Operation(summary = "create a new factura", description = "Create a new factura")
    @APIResponse(responseCode = "201", description = "Created")
    @APIResponse(responseCode = "400", description = "Bad Request")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFactura(Factura factura) throws URISyntaxException {
        log.debug("REST request to save Factura : {}", factura);
        facturaRepository.create(factura);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/factura/" + factura.getId())),
                ENTITY_NAME, factura.getId().toString())
                .entity(factura).build();
    }

    /**
     * PUT : Updates an existing factura.
     *
     * @param factura the factura to update
     * @return the Response with status 200 (OK) and with body the updated factura,
     * or with status 400 (Bad Request) if the factura is not valid,
     * or with status 500 (Internal Server Error) if the factura couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @Operation(summary = "update factura", description = "Updates an existing factura")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "400", description = "Bad Request")
    @APIResponse(responseCode = "500", description = "Internal Server Error")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFactura(Factura factura) throws URISyntaxException {
        log.debug("REST request to update Factura : {}", factura);
        facturaRepository.edit(factura);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), ENTITY_NAME, factura.getId().toString())
                .entity(factura).build();
    }

    /**
     * GET : get all the facturas.
     
     * @return the Response with status 200 (OK) and the list of facturas in body
     
     */
    @Timed
    @Operation(summary = "get all the facturas")
    @APIResponse(responseCode = "200", description = "OK")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout
    public List<Factura> getAllFacturas() {
        log.debug("REST request to get all Facturas");
        List<Factura> facturas = facturaRepository.findAll();
        return facturas;
    }

    /**
     * GET /:id : get the "id" factura.
     *
     * @param id the id of the factura to retrieve
     * @return the Response with status 200 (OK) and with body the factura, or with status 404 (Not Found)
     */
    @Timed
    @Operation(summary = "get the factura")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "404", description = "Not Found")
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFactura(@PathParam("id") Long id) {
        log.debug("REST request to get Factura : {}", id);
        Factura factura = facturaRepository.find(id);
        return Optional.ofNullable(factura)
                .map(result -> Response.status(Response.Status.OK).entity(factura).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" factura.
     * 
     * @param id the id of the factura to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @Operation(summary = "remove the factura" )
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "404", description = "Not Found")
    @DELETE
    @Path("/{id}")
    public Response removeFactura(@PathParam("id") Long id) {
        log.debug("REST request to delete Factura : {}", id);
        facturaRepository.remove(facturaRepository.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), ENTITY_NAME, id.toString()).build();
    }

}
