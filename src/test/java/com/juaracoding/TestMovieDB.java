package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestMovieDB {
    String nowPlaying = "https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1";
    String popular = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1";
    String addRating = "https://api.themoviedb.org/3/movie/575264/rating";
    String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0OWIzOTBiMjhlZDc4Yzk0ZTc3M2Y4MjVjN2FkMGM1ZiIsInN1YiI6IjY1MmZjY2NmYTgwMjM2MDExYWM4MjQyOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.R3ciialLuzkv_zaHQUCXmJw6h3xNG91kK-Ne0Gn7FFM";

    @Test
    public void testGetNowPlaying(){
        Response response = RestAssured.given()
                        .header("Authorization", token)
                        .when()
                        .get(nowPlaying);
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getHeaders());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void testGetPopular(){
        Response response = RestAssured.given()
                .header("Authorization", token)
                .when()
                .get(popular);
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getHeaders());
        int statusCodePopular = response.getStatusCode();
        Assert.assertEquals(statusCodePopular, 200);
    }

    @Test
    public void testAddRating(){
        JSONObject request = new JSONObject();
        request.put("value", 9.5);
        System.out.println(request.toJSONString());
        given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(request.toJSONString())
                .when()
                .post(addRating)
                .then()
                .statusCode(201)
                .log().all();
    }
}
