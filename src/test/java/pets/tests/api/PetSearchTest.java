package pets.tests.api;

import org.junit.jupiter.api.Test;
import pets.spec.BaseSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetSearchTest {

    @Test
    void findPetsByStatusAvailable() {
        given().spec(BaseSpec.getRequestSpecification())
                .queryParam("status", "available")
                .when().get("/pets/findByStatus")
                .then().spec(BaseSpec.ok200())
                .body("$", not(empty()))
                .body("[0].status", equalTo("available"));
    }
}
