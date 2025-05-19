package be.ucll.integration;

import be.ucll.repository.DbInitializer;
import be.ucll.model.Pony;
import be.ucll.repository.PonyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Sql("classpath:schema.sql")
public class HttpTest {
    @Autowired
    private DbInitializer dbInitializer;
    @BeforeEach
    public void setup() {
        dbInitializer.initialize();
    }

    @Autowired
    private WebTestClient client;

    @Autowired
    private PonyRepository ponyRepository;

    @Test
    public void given3Ponies_whenInvokingGetPony_then3PoniesAreReturned() {
        client.get()
                .uri("/ponies/all")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("[{\"name\":\"Tornado\",\"age\":5,\"size\":140},{\"name\":\"Bella\",\"age\":6,\"size\":135}]");
    }

    @Test
    public void testAddPony() {
        Pony newPony = new Pony("Rainbow", 4, 120);

        client.post()
                .uri("/ponies")
                .bodyValue(newPony)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Rainbow")
                .jsonPath("$.age").isEqualTo(4)
                .jsonPath("$.size").isEqualTo(120);
    }

    @Test
    public void testUpdatePony() {
        // First add a pony
        Pony original = new Pony("Dusty", 7, 110);
        client.post()
                .uri("/ponies")
                .bodyValue(original)
                .exchange()
                .expectStatus().is2xxSuccessful();

        // Now update that pony
        Pony updated = new Pony("DustyUpdated", 8, 125);
        client.put()
                .uri("/ponies/Dusty")
                .bodyValue(updated)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("DustyUpdated")
                .jsonPath("$.age").isEqualTo(8)
                .jsonPath("$.size").isEqualTo(125);
    }

    @Test
    public void testDeletePony() {
        // Add pony to be deleted
        Pony pony = new Pony("Shadow", 9, 130);
        client.post()
                .uri("/ponies")
                .bodyValue(pony)
                .exchange()
                .expectStatus().is2xxSuccessful();

        // Now delete it
        client.delete()
                .uri("/ponies/Shadow")
                .exchange()
                .expectStatus().is2xxSuccessful();

    }
}