package com.example.natour21;

import com.example.natour21.Class.SearchFilterMock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Class test for filter map (a map for query to filter search):
 *
 * We Mocked the method for a easy reuse (i.e @Before annotation).
 *
 * All parameter are converted in string for a easy manipulation.
 *
 * A specific query is valuated with "LIKE" keyword in SQL, so the empty string mean "*"
 * in Regex.
 *
 * - Length is a double >= 0, else is converted automatically in 100.0
 * (illegal argument if is not a double);
 * - Time have a standard format hh:mm:ss, if is empty is automatically converted in 23:59:00
 * (illegal argument if don't match with "^(\d\d:[0-5]\d:[0-5]\d)" );
 * - Disable Access is a boolean
 * (case_insensitive, true==True/false==False, illegal argument if is not a boolean);
 * - Difficulty have only 5 values that is the grade scale of Trekking, if value
 * is "Qualsiasi" string is automatically converted in empty string;
 *
 * Other parameters are string and they can have any string value (not only numbers),
 * empty string and null (server have a controller for this).
 *
 * Query (insert in the map for a easy retry of query url in case of reuse):
 * ?titolo=&puntoinizio=&puntofine=&durata=&lenght=&difficulty=&accessodisabili=
 *
 * Strategy used for testing:
 * Black Box - WECT
 */
public class FilterQueryTesting {

    Map<String,String> queryMapper;
    List<String> parameters;
    SearchFilterMock searchFilterMock;
    String goodQuery =
            "?titolo=Viaggio a Napoli" +
            "&puntoinizio=Via Roma, 80133, NA" +
            "&puntofine=Mergellina, 80122, NA" +
            "&durata=03:30:00" +
            "&length=44.0" +
            "&difficulty=T-Turistico" +
            "&accessodisabili=true" +
            "&areageografica=Napoli";

    @Before
    public void setQuery() {
        queryMapper = new HashMap<>();
        searchFilterMock = new SearchFilterMock();
        parameters = new ArrayList<>();
        parameters.add("Viaggio a Napoli");         //Itinerario titolo             (id=0)
        parameters.add("Via Roma, 80133, NA");      //Punto di Inizio               (id=1)
        parameters.add("Mergellina, 80122, NA");    //Punto di Fine                 (id=2)
        parameters.add("03:30:00");                 //Durata (standard hh:mm:ss)    (id=3)
        parameters.add("44.0");                     //Distanza (in km)              (id=4)
        parameters.add("T-Turistico");              //Difficoltà                    (id=5)
        parameters.add("true");                     //Accesso disabili              (id=6)
        parameters.add("Napoli");                   //Area geografica               (id=7)
    }

    @Test
    public void allGoodParameter() {
        queryMapper = searchFilterMock.getMap(parameters);
        assertEquals(goodQuery,queryMapper.get("url"));
    }

    @Test
    public void anyParamNull() {
        parameters.set(0,null);
        queryMapper = searchFilterMock.getMap(parameters);
        assertNull(queryMapper.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void titoloNumeric() {
        parameters.set(0,"123");
        queryMapper = searchFilterMock.getMap(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void puntoInizioNumeric() {
        parameters.set(1,"123");
        queryMapper = searchFilterMock.getMap(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void puntoFineNumeric() {
        parameters.set(2,"123");
        queryMapper = searchFilterMock.getMap(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void durataNoMatch() {
        parameters.set(3,"321:88:");
        queryMapper = searchFilterMock.getMap(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void durataNoMatchHH() {
        parameters.set(3,"321:30:00");
        queryMapper = searchFilterMock.getMap(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void durataNoMatchMM() {
        parameters.set(3,"03:88:00");
        queryMapper = searchFilterMock.getMap(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void durataNoMatchSS() {
        parameters.set(3,"03:30:77");
        queryMapper = searchFilterMock.getMap(parameters);
    }

    @Test
    public void durataEmpty() {
        parameters.set(3,"");
        queryMapper = searchFilterMock.getMap(parameters);
        assertEquals("23:59:00",queryMapper.get("durata"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void lengthNotNumber() {
        parameters.set(4,"CENTO");
        queryMapper = searchFilterMock.getMap(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lengthLowerThenZero() {
        parameters.set(4,"-1");
        queryMapper = searchFilterMock.getMap(parameters);
    }

    @Test
    public void lengthEmpty() {
        parameters.set(4,"");
        queryMapper = searchFilterMock.getMap(parameters);
        assertEquals("100.0",queryMapper.get("length"));
    }

    @Test
    public void difficultyQualsiasi() {
        parameters.set(5,"Qualsiasi");
        queryMapper = searchFilterMock.getMap(parameters);
        assertEquals("",queryMapper.get("difficulty"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void accessoDiabiliNotBoolean() {
        parameters.set(6,"vero");
        queryMapper = searchFilterMock.getMap(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void areaGeograficaNumeric() {
        parameters.set(7,"123");
        queryMapper = searchFilterMock.getMap(parameters);
    }
}
