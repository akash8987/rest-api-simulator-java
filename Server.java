import java.util.HashMap;
import java.util.Map;

class Request {
    String path;
    String method;

    Request(String method, String path) {
        this.method = method;
        this.path = path;
    }
}

class Response {
    int status;
    String body;

    Response(int status, String body) {
        this.status = status;
        this.body = body;
    }
}

public class Server {
    private final Map<String, String> routes = new HashMap<>();

    public Server() {
        routes.put("/health", "OK");
        routes.put("/version", "1.0.0");
    }

    public Response handle(Request req) {
        if ("GET".equals(req.method) && routes.containsKey(req.path)) {
            return new Response(200, routes.get(req.path));
        }
        return new Response(404, "Not Found");
    }

    public static void main(String[] args) {
        Server server = new Server();
        Response res = server.handle(new Request("GET", "/health"));
    }
}
