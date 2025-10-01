package dev.pets.helpers;

import dev.pets.model.Pet;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class DataGen {
    private DataGen() {}

    public static long id() {
        return ThreadLocalRandom.current().nextLong(1_000_000, 9_999_999);
    }

    public static String name(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static Pet newPet(String prefix, String status) {
        return new Pet()
                .withId(id())
                .withName(name(prefix))
                .withStatus(status)
                .withCategory(1L, "demo")
                .withPhotoUrls(List.of("https://example.test/" + prefix + ".png"))
                .withTags(List.of(Pet.tag(101, "tag-" + prefix)));
    }
}
