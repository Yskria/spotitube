package nl.oose.han.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.oose.han.datalayer.DTO.TrackDTO;
import nl.oose.han.services.TrackInPlaylistService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/playlists")
public class TrackInPlaylistResource {

    @Inject
    private TrackInPlaylistService trackInPlaylistService;

    @GET
    @Path("/{forPlayList}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("forPlayList") int playlistId, @QueryParam("token") String token) {
        List<TrackDTO> tracks = trackInPlaylistService.getAllSongsInPlaylist(playlistId, token);
        Map<String, List<TrackDTO>> response = new HashMap<>();
        response.put("tracks", tracks);
        return Response.ok(response).build();
    }

    @POST
    @Path("/{forPlayList}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("forPlayList") int playlistId, TrackDTO track) {
        return Response.ok().build();
    }
}