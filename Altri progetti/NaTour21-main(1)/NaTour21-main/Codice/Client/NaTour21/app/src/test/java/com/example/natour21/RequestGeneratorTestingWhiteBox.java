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
 * White Box - All condition coverage.
 */
public class RequestGeneratorTestingWhiteBox {

    RequestGeneratorMock requestGeneratorMock;

    @Before
    public void setup() {
        requestGeneratorMock = new RequestGeneratorMock();
    }

    @Test
    public void FirstTrueSecondTrue() {
        String expected = requestGeneratorMock.getBaseUrl() + "/api/user/";
        requestGeneratorMock.retrofitInstance(API.USER_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void FirstTrueSecondFalse() {
        String expected = requestGeneratorMock.getBaseUrl() + "/api/compilation/";
        requestGeneratorMock.retrofitInstance(API.COMPILATION_API);
        assertEquals(expected,requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void FirstFalseSecondTrue() {
        requestGeneratorMock.setCURRENT_URL(requestGeneratorMock.getBaseUrl() + API.FOTO_ITINERARIO);
        String expected = requestGeneratorMock.getBaseUrl() + "/api/tappa/";
        requestGeneratorMock.retrofitInstance(API.TAPPA_API);
        assertEquals(expected, requestGeneratorMock.getCURRENT_URL());
    }

    @Test
    public void FirstFlaseSecondFalse() {
        requestGeneratorMock.setCURRENT_URL(requestGeneratorMock.getBaseUrl() + API.TAPPA_API);
        String expected = requestGeneratorMock.getBaseUrl() + "/api/tappa/";
        requestGeneratorMock.retrofitInstance(API.TAPPA_API);
        assertEquals(expected, requestGeneratorMock.getCURRENT_URL());
    }

    @Test(expected = IllegalArgumentException.class)
    public void routeIllegalArgumentException() {
        requestGeneratorMock.retrofitInstance(null);
    }
}