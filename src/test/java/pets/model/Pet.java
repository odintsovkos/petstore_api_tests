package dev.pets.model;

import java.util.List;

public class Pet {
    public Long id;
    public String name;
    public String status;
    public Category category;
    public List<String> photoUrls;
    public List<Tag> tags;

    public static class Category{
        public Long id;
        public String name;
    }

    public static class Tag{
        public Long id;
        public String name;
    }

    public Pet withId(long id) { this.id = id; return this; }
    public Pet withName(String n) { this.name = n; return this; }
    public Pet withStatus(String s) { this.status = s; return this; }
    public Pet withCategory(Long id, String name) {
        this.category = new Category();
        this.category.id = id;
        this.category.name = name;
        return this;
    }
    public Pet withPhotoUrls(List<String> urls) { this.photoUrls = urls; return this; }
    public Pet withTags(List<Tag> tags) { this.tags = tags; return this; }

    public static Tag tag(long id, String name) {
        Tag t = new Tag();
        t.id = id; t.name = name;
        return t;
    }
}