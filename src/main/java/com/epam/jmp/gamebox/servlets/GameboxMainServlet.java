package com.epam.jmp.gamebox.servlets;

import com.epam.jmp.gamebox.*;
import com.epam.jmp.gamebox.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GameboxMainServlet", urlPatterns = "/")
public class GameboxMainServlet extends HttpServlet {

    private GameRepository gameRepository;
    private GameLocator gameLocator;
    private GameLoader gameLoader;
    private GameRepositoryConfiguration gameRepositoryConfiguration;

    public GameboxMainServlet() {
        gameRepositoryConfiguration = new SystemPropertiesGameRepositoryConfiguration();
        gameRepository = new FileSystemGameRepository();
        gameLocator = new WarGameLocator();

        gameLoader = new WarGameLoader();

        WarXmlManifestGameDescriptorLocator xmlManifestGameDescriptorLocator = new WarXmlManifestGameDescriptorLocator();
        ((WarGameLoader)gameLoader).setGameDescriptorLocator(xmlManifestGameDescriptorLocator);

        gameRepository.init(gameRepositoryConfiguration,
                gameLocator, gameLoader);

        List<GameDescriptor> gameDescriptors = gameRepository.getAllGameDescriptors();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getQueryString());

        String servletPath = req.getServletPath();

        String[] pathElements = servletPath.split("/");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }


    /*
        List<GameDescriptor> gameDescriptors = gameRepository.getAllGameDescriptors();

        PrintWriter out = resp.getWriter();

        out.print("<ul>");
        for (GameDescriptor gameDescriptor : gameDescriptors) {
            out.print("<li>");
            out.print(gameDescriptor.getGameName());
            out.print("</li>");
        }
        out.print("</ul>");

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
    * */
}
