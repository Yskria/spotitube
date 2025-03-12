package nl.oose.han.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.oose.han.datalayer.DTO.UserDTO;
import nl.oose.han.services.PlayListService;

import java.util.List;

import static jakarta.ws.rs.core.Response.status;

@Path("/playlists")
public class PlayListResource {

    private PlayListService playlistService;

    @Inject
    public PlayListResource(PlayListService playlistService) {
        this.playlistService = playlistService;
    }

    public PlayListResource() {
        this.playlistService = new PlayListService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        return Response.ok(playlistService.getPlaylists(token)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response.ok(playlistService.deletePlayList(id,token)).build();
    }
}