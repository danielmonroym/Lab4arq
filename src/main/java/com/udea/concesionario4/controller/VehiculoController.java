package com.udea.concesionario4.controller;

import com.udea.concesionario4.com.udea.concesionario4.modelo.Vehiculo;
import com.udea.concesionario4.repository.VehiculoRepository;
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
 * REST controller for managing Vehiculo.
 */
@Path("/api/vehiculo")
@RolesAllowed(USER)
public class VehiculoController {

    @Inject
    private Logger log;

    @Inject
    private VehiculoRepository vehiculoRepository;

    private static final String ENTITY_NAME = "vehiculo";

    /**
     * POST : Create a new vehiculo.
     *
     * @param vehiculo the vehiculo to create
     * @return the Response with status 201 (Created) and with body the
     * new vehiculo, or with status 400 (Bad Request) if the vehiculo has already
     * an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @Operation(summary = "create a new vehiculo", description = "Create a new vehiculo")
    @APIResponse(responseCode = "201", description = "Created")
    @APIResponse(responseCode = "400", description = "Bad Request")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVehiculo(Vehiculo vehiculo) throws URISyntaxException {
        log.debug("REST request to save Vehiculo : {}", vehiculo);
        vehiculoRepository.create(vehiculo);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/vehiculo/" + vehiculo.getId())),
                ENTITY_NAME, vehiculo.getId().toString())
                .entity(vehiculo).build();
    }

    /**
     * PUT : Updates an existing vehiculo.
     *
     * @param vehiculo the vehiculo to update
     * @return the Response with status 200 (OK) and with body the updated vehiculo,
     * or with status 400 (Bad Request) if the vehiculo is not valid,
     * or with status 500 (Internal Server Error) if the vehiculo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @Operation(summary = "update vehiculo", description = "Updates an existing vehiculo")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "400", description = "Bad Request")
    @APIResponse(responseCode = "500", description = "Internal Server Error")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVehiculo(Vehiculo vehiculo) throws URISyntaxException {
        log.debug("REST request to update Vehiculo : {}", vehiculo);
        vehiculoRepository.edit(vehiculo);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), ENTITY_NAME, vehiculo.getId().toString())
                .entity(vehiculo).build();
    }

    /**
     * GET : get all the vehiculoes.
     
     * @return the Response with status 200 (OK) and the list of vehiculoes in body
     
     */
    @Timed
    @Operation(summary = "get all the vehiculoes")
    @APIResponse(responseCode = "200", description = "OK")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout
    public List<Vehiculo> getAllVehiculoes() {
        log.debug("REST request to get all Vehiculoes");
        List<Vehiculo> vehiculoes = vehiculoRepository.findAll();
        return vehiculoes;
    }

    /**
     * GET /:id : get the "id" vehiculo.
     *
     * @param id the id of the vehiculo to retrieve
     * @return the Response with status 200 (OK) and with body the vehiculo, or with status 404 (Not Found)
     */
    @Timed
    @Operation(summary = "get the vehiculo")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "404", description = "Not Found")
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehiculo(@PathParam("id") Long id) {
        log.debug("REST request to get Vehiculo : {}", id);
        Vehiculo vehiculo = vehiculoRepository.find(id);
        return Optional.ofNullable(vehiculo)
                .map(result -> Response.status(Response.Status.OK).entity(vehiculo).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" vehiculo.
     * 
     * @param id the id of the vehiculo to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @Operation(summary = "remove the vehiculo" )
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "404", description = "Not Found")
    @DELETE
    @Path("/{id}")
    public Response removeVehiculo(@PathParam("id") Long id) {
        log.debug("REST request to delete Vehiculo : {}", id);
        vehiculoRepository.remove(vehiculoRepository.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), ENTITY_NAME, id.toString()).build();
    }

}
