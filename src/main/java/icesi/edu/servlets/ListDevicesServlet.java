package icesi.edu.servlets;

import java.io.IOException;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import icesi.edu.models.Device;
import icesi.edu.services.DeviceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/devices")
public class ListDevicesServlet extends HttpServlet {

    private DeviceService deviceService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        this.deviceService = ctx.getBean(DeviceService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        List<Device> devices = deviceService.getAll();

        StringBuilder html = new StringBuilder();

        html.append("<html><body>");
        html.append("<h2>Lista de Dispositivos IoT</h2>");
        html.append("<table border='1'>");
        html.append("<tr>");
        html.append("<th>ID</th>");
        html.append("<th>Nombre</th>");
        html.append("<th>Serial</th>");
        html.append("<th>Ubicación</th>");
        html.append("<th>Tipo</th>");
        html.append("<th>Estado</th>");
        html.append("</tr>");

        for (Device d : devices) {
            html.append("<tr>");
            html.append("<td>").append(d.getId()).append("</td>");
            html.append("<td>").append(d.getName()).append("</td>");
            html.append("<td>").append(d.getSerialNumber()).append("</td>");
            html.append("<td>").append(d.getLocation()).append("</td>");
            html.append("<td>").append(d.getType()).append("</td>");
            html.append("<td>").append(d.getStatus()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("</body></html>");

        resp.getWriter().println(html.toString());
    }
}
