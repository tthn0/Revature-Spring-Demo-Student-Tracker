package io.github.tthn0.demo.withSwaggerAndGql;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Customer's information")
public class CustomerWriteDto {
    @NotNull
    @Schema(description = "Customer's name", example = "Alan Turing")
    private String name;

    @NotNull
    @Schema(description = "Customer's email", example = "customer@email.com")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
