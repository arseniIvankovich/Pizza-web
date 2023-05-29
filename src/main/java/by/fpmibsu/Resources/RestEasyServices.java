package by.fpmibsu.Resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestEasyServices extends Application {
    private Set<Object> singletons = new HashSet<>();

    public RestEasyServices() {
        singletons.add(new UserApi());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
}
    }
