package com.socket.demo.model;

import javax.validation.constraints.*;

public class RespProduct {
    private long id;

    @Size(min = 5, max = 10)
    private String title;
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    private String description;

    public RespProduct(String tilte, String description) {
        this.title = tilte;
        this.description = description;
    }


    public RespProduct() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
