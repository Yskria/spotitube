package nl.oose.han.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.oose.han.datalayer.dto.PlayListDTO;
import nl.oose.han.services.PlayListService;

import java.util.Objects;

import static jakarta.ws.rs.core.Response.status;

@Path("/playlists")
public class PlayListResource {

    @Inject
    private PlayListService playlistService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlayListDTO playlist) {
        return Response.ok(playlistService.addPlayList(token, playlist)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        if(Objects.equals(token, "1234-1234-1234")) {
            return Response.ok(playlistService.getPlaylists(token)).build();
        }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response.ok(playlistService.deletePlayList(id, token)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlayListDTO playlist) {
        return Response.ok(playlistService.updatePlayList(token, id, playlist)).build();
    }
}