package com.example.natour21;

import static org.junit.Assert.assertEquals;

import com.example.natour21.Class.RequestGeneratorMock;
import com.example.natour21.Utils.Enumeration.API;

import org.junit.Before;
import org.junit.Test;

/**
 * Class test for Retrofit instance generator:
 *
 * Request generator is a class of Utils package
 * created to instantiate retrofit that returns a
 * connection using base url and a suffix.
 *
 * To mock retrfit we will build a new mock class.
 *
 * Parameters:
 * - Enumeration API with some values:
 *      --FOTO_ITINERARIO;
 *      --INTERESTINGPOINT_API;
 *      --TAPPA_API;
 *      --ITINERARIO_API;
 *      --COMPILATION_API;
 *      --SERVER_API;
 *      --USER_API:
 *
 * - The base url can be changed into code (is a Static Public String).
 *
 * Strategy used for testing:
 * Black Box - SECT
 */
public class RequestGeneratorTestingBlackBox {

    RequestGeneratorMock requestGeneratorMock;

    @Before
    public void setup() {
       requestGeneratorMock = new RequestGeneratorMock();
    }

    @Test
    public void routeFotoItinerario() {
        String expected = requestGeneratorMock.getBaseUrl() + "/api/fotoItinerario/";
        requestGeneratorMock.retrofitInstance(API.FOTO_ITINERARIO);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeInterestingPoint() {
        String expected = requestGeneratorMock.getBaseUrl() + "/api/interestingpoint/";
        requestGeneratorMock.retrofitInstance(API.INTERESTINGPOINT_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeTappa() {
        String expected = requestGeneratorMock.getBaseUrl() + "/api/tappa/";
        requestGeneratorMock.retrofitInstance(API.TAPPA_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeItinerario() {
        String expected = requestGeneratorMock.getBaseUrl() + "/api/itinerario/";
        requestGeneratorMock.retrofitInstance(API.ITINERARIO_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeCompilation() {
        String expected = requestGeneratorMock.getBaseUrl() + "/api/compilation/";
        requestGeneratorMock.retrofitInstance(API.COMPILATION_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }


    @Test
    public void routeServer() {
        String expected = requestGeneratorMock.getBaseUrl() + "/";
        requestGeneratorMock.retrofitInstance(API.SERVER_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeUser() {
        String expected = requestGeneratorMock.getBaseUrl() + "/api/user/";
        requestGeneratorMock.retrofitInstance(API.USER_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeFotoItinerarioCurrentNoNull() {
        requestGeneratorMock.setCURRENT_URL(requestGeneratorMock.getBaseUrl() + "/api/fotoItinerario/");
        String expected = requestGeneratorMock.getBaseUrl() + "/api/fotoItinerario/";
        requestGeneratorMock.retrofitInstance(API.FOTO_ITINERARIO);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeInterestingPointCurrentNoNull() {
        requestGeneratorMock.setCURRENT_URL(requestGeneratorMock.getBaseUrl() + "/api/fotoItinerario/");
        String expected = requestGeneratorMock.getBaseUrl() + "/api/interestingpoint/";
        requestGeneratorMock.retrofitInstance(API.INTERESTINGPOINT_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeTappaCurrentNoNull() {
        requestGeneratorMock.setCURRENT_URL(requestGeneratorMock.getBaseUrl() + "/api/fotoItinerario/");
        String expected = requestGeneratorMock.getBaseUrl() + "/api/tappa/";
        requestGeneratorMock.retrofitInstance(API.TAPPA_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeItinerarioCurrentNoNull() {
        requestGeneratorMock.setCURRENT_URL(requestGeneratorMock.getBaseUrl() + "/api/fotoItinerario/");
        String expected = requestGeneratorMock.getBaseUrl() + "/api/itinerario/";
        requestGeneratorMock.retrofitInstance(API.ITINERARIO_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeCompilationCurrentNoNull() {
        requestGeneratorMock.setCURRENT_URL(requestGeneratorMock.getBaseUrl() + "/api/fotoItinerario/");
        String expected = requestGeneratorMock.getBaseUrl() + "/api/compilation/";
        requestGeneratorMock.retrofitInstance(API.COMPILATION_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }


    @Test
    public void routeServerCurrentNoNull() {
        requestGeneratorMock.setCURRENT_URL(requestGeneratorMock.getBaseUrl() + "/api/fotoItinerario/");
        String expected = requestGeneratorMock.getBaseUrl() + "/";
        requestGeneratorMock.retrofitInstance(API.SERVER_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }


    @Test
    public void routeUserCurrentNoNull() {
        requestGeneratorMock.setCURRENT_URL(requestGeneratorMock.getBaseUrl() + "/api/fotoItinerario/");
        String expected = requestGeneratorMock.getBaseUrl() + "/api/user/";
        requestGeneratorMock.retrofitInstance(API.USER_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void routeUndefined() {
        String expected = requestGeneratorMock.getBaseUrl() + "/api/undefinited/";
        requestGeneratorMock.retrofitInstance(API.CHAT_API);
        assertEquals(expected, requestGeneratorMock.getCURRENT_URL());
    }

    @Test (expected = IllegalArgumentException.class)
    public void routeIllegalArgumentException() {
        requestGeneratorMock.retrofitInstance(null);
    }
}
