package nl.oose.han.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.oose.han.services.TokenService;
import nl.oose.han.services.TrackService;

@Path("/tracks")
public class TrackResource {

    @Inject
    private TrackService trackService;

    @Inject
    private TokenService tokenService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksNotInPlaylist(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token) {
        tokenService.validateToken(token);
        return Response.ok(trackService.getAllTracksNotInPlaylist(playlistID, token)).build();
    }

}
