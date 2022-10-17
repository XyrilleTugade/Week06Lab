/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Xyrille
 */
public class ShoppingListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String username;
        
        ArrayList<String> items = (ArrayList<String>)session.getAttribute("items");
        if (items == null)
            items = new ArrayList<>();
        
        System.out.println(items.toString());
        
        String action = request.getParameter("action");
        
        if (action != null && action.equals("logout")) {
            session.invalidate();
            session = request.getSession();
        }
        
        else if (action != null && action.equals("register")) {
            username = request.getParameter("username");
            session.setAttribute("username", username);
        }
        
        else if (action != null && action.equals("add") && !items.contains(request.getParameter("item"))) {
            items.add(request.getParameter("item"));
            session.setAttribute("items", items);
        }
        
        else if (action != null && action.equals("delete")) {
            items.remove(request.getParameter("itemList"));
        }
        
        username = (String)session.getAttribute("username");
        
        System.out.println(username);
        
        if (username != null) {
            request.setAttribute("username", username);
            request.setAttribute("items", items);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
        
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
