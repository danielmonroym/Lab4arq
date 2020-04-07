package com.udea.concesionario4.controller;

import com.udea.concesionario4.com.udea.concesionario4.modelo.Usuario;
import com.udea.concesionario4.repository.UsuarioRepository;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response.ResponseBuilder;
import com.udea.concesionario4.controller.util.Page;
import com.udea.concesionario4.controller.util.PaginationUtil;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * REST controller for managing Usuario.
 */
@Path("/api/usuario")
@RolesAllowed(USER)
public class UsuarioController {

    @Inject
    private Logger log;

    @Inject
    private UsuarioRepository usuarioRepository;

    private static final String ENTITY_NAME = "usuario";

    /**
     * POST : Create a new usuario.
     *
     * @param usuario the usuario to create
     * @return the Response with status 201 (Created) and with body the
     * new usuario, or with status 400 (Bad Request) if the usuario has already
     * an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @Operation(summary = "create a new usuario", description = "Create a new usuario")
    @APIResponse(responseCode = "201", description = "Created")
    @APIResponse(responseCode = "400", description = "Bad Request")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUsuario(Usuario usuario) throws URISyntaxException {
        log.debug("REST request to save Usuario : {}", usuario);
        usuarioRepository.create(usuario);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/usuario/" + usuario.getId())),
                ENTITY_NAME, usuario.getId().toString())
                .entity(usuario).build();
    }

    /**
     * PUT : Updates an existing usuario.
     *
     * @param usuario the usuario to update
     * @return the Response with status 200 (OK) and with body the updated usuario,
     * or with status 400 (Bad Request) if the usuario is not valid,
     * or with status 500 (Internal Server Error) if the usuario couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @Operation(summary = "update usuario", description = "Updates an existing usuario")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "400", description = "Bad Request")
    @APIResponse(responseCode = "500", description = "Internal Server Error")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(Usuario usuario) throws URISyntaxException {
        log.debug("REST request to update Usuario : {}", usuario);
        usuarioRepository.edit(usuario);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), ENTITY_NAME, usuario.getId().toString())
                .entity(usuario).build();
    }

    /**
     * GET : get all the usuarioes.
     * @param page the pagination information
     * @param size the pagination size information
     
     * @return the Response with status 200 (OK) and the list of usuarioes in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @Timed
    @Operation(summary = "get all the usuarioes")
    @APIResponse(responseCode = "200", description = "OK")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout
    public Response getAllUsuarioes(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Usuarioes");
        List<Usuario> usuarioes = usuarioRepository.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(usuarioes);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, usuarioRepository.count()), "/resources/api/usuario");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" usuario.
     *
     * @param id the id of the usuario to retrieve
     * @return the Response with status 200 (OK) and with body the usuario, or with status 404 (Not Found)
     */
    @Timed
    @Operation(summary = "get the usuario")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "404", description = "Not Found")
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") Long id) {
        log.debug("REST request to get Usuario : {}", id);
        Usuario usuario = usuarioRepository.find(id);
        return Optional.ofNullable(usuario)
                .map(result -> Response.status(Response.Status.OK).entity(usuario).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" usuario.
     * 
     * @param id the id of the usuario to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @Operation(summary = "remove the usuario" )
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "404", description = "Not Found")
    @DELETE
    @Path("/{id}")
    public Response removeUsuario(@PathParam("id") Long id) {
        log.debug("REST request to delete Usuario : {}", id);
        usuarioRepository.remove(usuarioRepository.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), ENTITY_NAME, id.toString()).build();
    }

}
