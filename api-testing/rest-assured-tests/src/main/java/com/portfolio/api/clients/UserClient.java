package com.portfolio.api.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserClient {
    private static final String BASE_URL = "https://reqres.in/api";
    private static final String USERS_ENDPOINT = BASE_URL + "/users";

    /**
     * Obtiene una lista de usuarios por número de página
     */
    public Response getUsers(int page) {
        return RestAssured.given()
                .queryParam("page", page)
                .contentType(ContentType.JSON)
                .when()
                .get(USERS_ENDPOINT);
    }

    /**
     * Crea un nuevo usuario en el sistema
     */
    public Response createUser(String name, String job) {
        String requestBody = String.format("{\n" +
                "    \"name\": \"%s\",\n" +
                "    \"job\": \"%s\"\n" +
                "}", name, job);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(USERS_ENDPOINT);
    }
}
