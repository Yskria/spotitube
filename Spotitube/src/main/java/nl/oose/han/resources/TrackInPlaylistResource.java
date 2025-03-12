package nl.oose.han.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.oose.han.Track;
import nl.oose.han.services.TrackInPlaylistService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/playlists")
public class TrackInPlaylistResource {

    @Inject
    private TrackInPlaylistService trackInPlaylistService;

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        List<Track> tracks = trackInPlaylistService.getAllSongsInPlaylist(playlistId, token);
        Map<String, List<Track>> response = new HashMap<>();
        response.put("tracks", tracks);
        return Response.ok(response).build();
    }
}