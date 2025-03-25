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
import nl.oose.han.services.serviceinterfaces.iTokenService;
import nl.oose.han.services.serviceinterfaces.iTrackService;

@Path("/tracks")
public class TrackResource {

    @Inject
    private iTrackService trackService;

    @Inject
    private iTokenService tokenService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksNotInPlaylist(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token) {
        tokenService.validateToken(token);
        return Response.ok(trackService.getAllTracksNotInPlaylist(playlistID, token)).build();
    }
}
