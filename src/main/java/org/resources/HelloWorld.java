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
    @Produces("application/json")
    public String getUser(@PathParam("id") Long id) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(userService.findEntityById(id));
        return json;
    }

    @GET
    @Path("")
    @Produces("application/json")
    public String getAllUser () throws IOException {
        List<User> users = userService.findAll();
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, users);

        final byte[] data = out.toByteArray();
        String json = new String(data);
        return json;
    }

    @POST
    @Path("")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getUser (String user) throws JsonProcessingException {
        String result = "User created " + user;
        User user1 = new ObjectMapper().readValue(user,User.class);
        userService.createUser(user1);
        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response deleteUser (@PathParam("id") Long id) {
        User user = userService.findEntityById(id);
        String result = "User deleted " + user;
        userService.deleteById(id);
        return Response.status(202).entity(result).build();
    }

    @PUT
    @Path("update/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateUser (@PathParam("id") Long id,String jsonUser) throws JsonProcessingException {
        User user = new ObjectMapper().readValue(jsonUser,User.class);
        userService.edit(id,user);
        User user1 = userService.findEntityById(id);
        String result = "User updated " + user1;
        return Response.status(201).entity(result).build();
    }
}
