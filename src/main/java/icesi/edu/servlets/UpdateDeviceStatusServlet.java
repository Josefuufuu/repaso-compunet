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

@WebServlet("/devices/update-status")
public class UpdateDeviceStatusServlet extends HttpServlet {

    private DeviceService deviceService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext ctx =
            WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        this.deviceService = ctx.getBean(DeviceService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(
            "<html><body>" +
            "<h2>Actualizar estado de dispositivo</h2>" +
            "<form method='POST'>" +
            "ID del dispositivo: <input type='number' name='id'><br>" +
            "Nuevo estado: <input type='text' name='status'><br>" +
            "<button type='submit'>Actualizar</button>" +
            "</form>" +
            "<br><a href='/repaso/devices'>Volver a la lista</a>" +
            "</body></html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String status = req.getParameter("status");

            deviceService.updateStatus(id, status);

            resp.sendRedirect(req.getContextPath() + "/repaso/devices");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("El id debe ser un número válido.");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(e.getMessage());
        }
    }
}