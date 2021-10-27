package com.quarkus.reactive.crud.demo.swagger;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "product", description = "Quarkus crud demo using swagger"),
        },
        info = @Info(
                title = "Product API using swagger",
                version = "0.0.1",
                contact = @Contact(
                        name = "Alam",
                        url = "https://github/malam84"))
)
public class SwaggerConfig extends Application {

}
