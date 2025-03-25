package nl.oose.han.resources;

import nl.oose.han.services.serviceinterfaces.iPlayListService;
import nl.oose.han.services.serviceinterfaces.iTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlayListResourceTest {

    @Mock
    private iPlayListService playListService;

    @Mock
    private iTokenService tokenService;

    @InjectMocks
    PlayListResource sut;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


}
