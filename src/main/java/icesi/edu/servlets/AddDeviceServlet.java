package icesi.edu.servlets;

import java.io.IOException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import icesi.edu.services.DeviceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/devices/add")
public class AddDeviceServlet extends HttpServlet {

    private DeviceService deviceService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        this.deviceService = ctx.getBean(DeviceService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(
            "<html>" +
            "<body>" +
            "<h2>Agregar Dispositivo IoT</h2>" +
            "<form method='POST'>" +
            "Nombre: <input type='text' name='name'><br>" +
            "Serial: <input type='text' name='serialNumber'><br>" +
            "Ubicación: <input type='text' name='location'><br>" +
            "Tipo: <input type='text' name='type'><br>" +
            "Estado: <input type='text' name='status'><br>" +
            "<button type='submit'>Guardar</button>" +
            "</form>" +
            "</body>" +
            "</html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String serialNumber = req.getParameter("serialNumber");
        String location = req.getParameter("location");
        String type = req.getParameter("type");
        String status = req.getParameter("status");
        try {
            deviceService.addDevice(name, serialNumber, location, type, status);
            resp.getWriter().println("Device agregado correctamente");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(e.getMessage());
        }
    }
}