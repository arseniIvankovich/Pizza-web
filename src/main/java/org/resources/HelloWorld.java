package org.resources;

import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class HelloWorld {
    private UserService userService = new UserService();
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public String getUser(@PathParam("id") Long id) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(userService.findEntityById(id));
        return json;
    }

    @GET
    @Path("")
    @Produces({ MediaType.APPLICATION_JSON})
    public String getAllUser () throws IOException {
        List<User> users = userService.findAll();
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, users);

        final byte[] data = out.toByteArray();
        String json = new String(data);
        return json;
    }
}
