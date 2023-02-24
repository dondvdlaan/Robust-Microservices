package dev.manyroads.microservice.backend;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class for receiving a test request and responding with a HTML message.
 * Run with "mvn clean wildfly:run" command at the root
 *
 * The {@link TestService } is injected by CDI.
 */
@SuppressWarnings("serial")
@WebServlet("/hello")
public class TestServlet extends HttpServlet {

    // ---- Attributes ----
    static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";
    static String PAGE_FOOTER = "<h5>This is am a footer....</h5></body></html>";
    @Inject
    TestService helloService;

    // ---- Routes ----
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Route: TestServlet doGet");

        // ---- Attributes ----
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        // Delay execution in seconds
        threadDelay(5);

        // Responding with HTML message
        writer.println(PAGE_HEADER);
        writer.println("<h1>" + helloService.createHelloMessage("World") + "</h1>");
        writer.println(PAGE_FOOTER);
        writer.close();
    }

    // ----Methods ----
    public void threadDelay(int delayExecution){

        // Delay
        try {
            Thread.sleep(delayExecution * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
