package nl.oose.han.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.oose.han.datalayer.dto.TrackDTO;
import nl.oose.han.services.TokenService;
import nl.oose.han.services.TrackInPlaylistService;
import nl.oose.han.services.serviceinterfaces.iTokenService;
import nl.oose.han.services.serviceinterfaces.iTrackInPlayListService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/playlists")
public class TrackInPlaylistResource {

    @Inject
    private iTrackInPlayListService trackInPlaylistService;

    @Inject
    private iTokenService tokenService;

    @GET
    @Path("/{forPlayList}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("forPlayList") int playlistId, @QueryParam("token") String token) {
        tokenService.validateToken(token);
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
        tokenService.validateToken(token);
        return Response.ok(trackInPlaylistService.addTrackToPlaylist(playlistId, track, token)).build();
    }

    @DELETE
    @Path("/{forPlayList}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("forPlayList") int playlistId, @PathParam("trackId") int trackId) {
        tokenService.validateToken(token);
        return Response.ok(trackInPlaylistService.deleteTrackFromPlaylist(playlistId, trackId, token)).build();
    }

}