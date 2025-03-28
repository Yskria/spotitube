package nl.oose.han.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.oose.han.domain.dto.PlayListDTO;
import nl.oose.han.services.serviceinterfaces.iPlayListService;
import nl.oose.han.services.serviceinterfaces.iTokenService;

@Path("/playlists")
public class PlayListResource {

    @Inject
    private iPlayListService playlistService;

    @Inject
    private iTokenService tokenService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlayListDTO playlist) {
        tokenService.validateToken(token);
        return Response.ok(playlistService.addPlayList(token, playlist)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        tokenService.validateToken(token);
        return Response.ok(playlistService.getPlaylists(token)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        tokenService.validateToken(token);
        return Response.ok(playlistService.deletePlayList(id, token)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlayListDTO playlist) {
        tokenService.validateToken(token);
        return Response.ok(playlistService.updatePlayList(token, id, playlist)).build();
    }
}