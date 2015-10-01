package kfs.kfsPhoneService.smss;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kfs.kfsPhoneService.service.PhoneServletService;
import kfs.kfsPhoneService.service.xchange.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author pavedrim
 */
public abstract class sms extends HttpServlet {

    private PhoneServletService smsService;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ApplicationContext ac = WebApplicationContextUtils
                .getRequiredWebApplicationContext(servletContext);
        smsService = ac.getBean(PhoneServletService.class);
        super.init();
    }

    @Override
    public String getServletInfo() {
        return smsService.getServletInfo();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userId = req.getUserPrincipal().getName();
        String cmd = getCommand(req);
        Object o = null;
        if (smsService.getCmdSmsOutgoing().equals(cmd)) {
            o = smsService.smsOutgoingSending(userId);
        } else if (smsService.getCmdLogout().equals(cmd)) {
            doLogout(userId, req, resp);
        }
        out(resp, o);
    }

    protected abstract String getSmsIcomingStatusName();
    protected abstract String getCallIcomingStatusName();
    protected abstract String getCallMissedStatusName();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userId = req.getUserPrincipal().getName();
        String cmd = getCommand(req);
        Gson gs = new Gson();
        Object o = null;
        if (smsService.getCmdSmsCommit().equals(cmd)) {
            o = smsService.smsOutgoingCommit(userId, gs.fromJson(req.getParameter(smsService.getVarName()),
                    smsCommitOut.class));
        } else if (smsService.getCmdSmsIncoming().equals(cmd)) {
            o = smsService.smsIncoming(userId, gs.fromJson(req.getParameter(smsService.getVarName()),
                    smsIncoming.class), getSmsIcomingStatusName());
        } else if (smsService.getCmdSmsOutgoing().equals(cmd)) {
            o = smsService.smsOutgoingSending(userId);
        } else if (smsService.getCmdSmsFail().equals(cmd)) {
            o =  smsService.smsOutgoingFail(userId, gs.fromJson(req.getParameter(smsService.getVarName()),
                    smsFailOut.class));
        } else if (smsService.getCmdLogout().equals(cmd)) {
            doLogout(userId, req, resp);
        } else if (smsService.getCmdCallMissed().equals(cmd)) {
            o = smsService.callMissed(userId, gs.fromJson(req.getParameter(smsService.getVarName()),
                    call.class), getCallMissedStatusName());
        } else if (smsService.getCmdCallIncoming().equals(cmd)) {
            o = smsService.callIncoming(userId, gs.fromJson(req.getParameter(smsService.getVarName()),
                    call.class), getCallIcomingStatusName());
        } else if (smsService.getCmdCallOutgoing().equals(cmd)) {
            o = smsService.callOutgoing(userId, gs.fromJson(req.getParameter(smsService.getVarName()),
                    call.class));
        }
        out(resp, o);
    }

    private void out(HttpServletResponse resp, Object o) throws IOException {
        Gson gs = new Gson();
        resp.setContentType(smsService.getCntType());
        PrintWriter out = resp.getWriter();
        out.print(gs.toJson(o));
        out.close();
    }

    private String getCommand(HttpServletRequest req) {
        String cmd = req.getPathInfo();
        if (cmd != null) {
            if (cmd.startsWith("/")) {
                cmd = cmd.substring(1);
            }
        }
        return cmd;
    }

    private void doLogout(String userId, HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/");
    }


}
