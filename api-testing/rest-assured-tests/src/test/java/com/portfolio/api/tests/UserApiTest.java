package com.portfolio.api.tests;

import com.portfolio.api.clients.UserClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class UserApiTest {
    private UserClient userClient;

    @BeforeClass
    public void setup() {
        userClient = new UserClient();
    }

    @Test(description = "Validar que la API retorne una lista de usuarios de la página 2")
    public void testGetUsersList() {
        Response response = userClient.getUsers(2);

        // Aserción estándar de código de estado
        Assert.assertEquals(response.getStatusCode(), 200, "El código de estado debería ser 200 OK");

        // Aserciones avanzadas en el cuerpo del JSON usando RestAssured Hamcrest Matchers
        response.then()
                .body("page", equalTo(2))
                .body("data", notNullValue())
                .body("data[0].first_name", notNullValue())
                .body("support.url", containsString("reqres.in"));
    }

    @Test(description = "Validar la creación exitosa de un usuario mediante método POST")
    public void testCreateUserSuccess() {
        String expectedName = "Marina QA";
        String expectedJob = "SDET Leader";

        Response response = userClient.createUser(expectedName, expectedJob);

        // Validar código 201 Created
        Assert.assertEquals(response.getStatusCode(), 201, "El código de estado debería ser 201 Created");

        // Validar que los datos enviados coincidan con la respuesta de la API
        String actualName = response.jsonPath().getString("name");
        String actualJob = response.jsonPath().getString("job");
        String id = response.jsonPath().getString("id");

        Assert.assertEquals(actualName, expectedName, "El nombre en la respuesta no coincide");
        Assert.assertEquals(actualJob, expectedJob, "El puesto en la respuesta no coincide");
        Assert.assertNotNull(id, "El ID generado no debería ser nulo");
    }
}
