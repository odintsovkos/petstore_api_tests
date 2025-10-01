package pets.tests.api;

import dev.pets.helpers.DataGen;
import dev.pets.model.Pet;
import dev.pets.spec.BaseSpec;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetCrudTest {
    static long petId;
    static String initialName;

    @Test
    @Order(1)
    @DisplayName("POST /pet — создать питомца")
    void createPet() {
        Pet pet = DataGen.newPet("neo", "available");
        petId = pet.id;
        initialName = pet.name;

        given().spec(BaseSpec.getRequestSpecification())
                .body(pet)
                .when().post("/pet")
                .then().spec(BaseSpec.ok200())
                .body("id", equalTo((int) petId))
                .body("name", equalTo(initialName))
                .body("status", equalTo("available"));
    }

    @Test
    @Order(2)
    @DisplayName("GET /pet/{id} — получить созданного питомца")
    void getCreatedPet() {
        given().spec(BaseSpec.getRequestSpecification())
                .when().get("/pet/{petId}", petId)
                .then().spec(BaseSpec.ok200())
                .body("id", equalTo((int) petId))
                .body("name", equalTo(initialName));
    }

    @Test
    @Order(3)
    @DisplayName("PUT /pet — обновить имя и статус питомца")
    void updatePet() {
        String newName = "trinity";
        Pet updated = new Pet()
                .withId(petId)
                .withName(newName)
                .withStatus("pending")
                .withPhotoUrls(List.of("https://example.test/trinity.png"));

        given().spec(BaseSpec.getRequestSpecification())
                .body(updated)
                .when().put("/pet")
                .then().spec(BaseSpec.ok200())
                .body("name", equalTo(newName))
                .body("status", equalTo("pending"));

        given().spec(BaseSpec.getRequestSpecification())
                .when().get("/pet/{petId}", petId)
                .then().spec(BaseSpec.ok200())
                .body("name", equalTo(newName))
                .body("status", equalTo("pending"));
    }

    @Test
    @Order(4)
    @DisplayName("DELETE /pet/{id} — удалить питомца")
    void  deletePet() {
        given().spec(BaseSpec.getRequestSpecification())
                .when().delete("/pet/{petId}", petId)
                .then().spec(BaseSpec.ok200());

        given().spec(BaseSpec.getRequestSpecification())
                .when().get("/pet/{petId}", petId)
                .then().statusCode(404);
    }
}
