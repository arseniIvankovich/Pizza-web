package by.fpmibsu.Resources;

import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Path("/users")
public class UserApi {
    private UserService userService = new UserService();
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getUser(@PathParam("id") Long id) throws JsonProcessingException {
        User user = userService.findEntityById(id);

        if (user.getEmail() == null)
            return Response.status(400).build();

        String json = new ObjectMapper().writeValueAsString(user);
        return Response.status(200).entity(json).build();
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
    public Response getUser (User user) throws JsonProcessingException {
        String result = "User created " + user;
        userService.createUser(user);
        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response deleteUser (@PathParam("id") Long id) {
        User user = userService.findEntityById(id);
        String result = "User deleted " + user;

        if (user.getEmail() == null)
            return Response.status(400).build();

        userService.deleteById(id);
        return Response.status(202).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateUser (@PathParam("id") Long id,User user) throws JsonProcessingException {
        userService.edit(id,user);
        User user1 = userService.findEntityById(id);

        if (user1.getEmail() == null)
            return Response.status(400).build();

        String result = "User updated " + user1;
        return Response.status(201).entity(result).build();
    }
}
