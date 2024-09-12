package com.socketorderform;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Implementation of Servlet class OrderSocketServlet
 * Handles socket orders submitted through an HTML form
 */
@WebServlet("/orderSockets")
public class OrderSocketServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * Default Constructor
     */
    public OrderSocketServlet() {
        super();
    }
    

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
    	servletResponse.setContentType("text/html");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        // Get the data from the form and store in the string variables
        String customerName = servletRequest.getParameter("customerName");
        String customerEmail = servletRequest.getParameter("customerEmail");
        String socketType = servletRequest.getParameter("socketType");
        String socketsQunatity = servletRequest.getParameter("socketQuantity");

        // Set the Response type to text/html
        servletResponse.setContentType("text/html");

        // Object creation to display the output
        var display = servletResponse.getWriter();

        // Try catch block to handle the exceptions occurred 
        try {
        	// Convert the socketQuantity data to Integer and store in socketQuantity variable
            int socketQuantity = Integer.parseInt(socketsQunatity);
            
            // Pass parameters socketType and SocketQuantity to socketPriceCalulator to calculate total cost
            double price = socketPriceCalculator(socketType, socketQuantity);
            // Output displayed
            display.println("<html><body>");
            display.println("<h2>Order Details Page ::</h2>");
            display.println("<p> Customer Name  : " + customerName + "</p>");
            display.println("<p> Customer Email : " + customerEmail + "</p>");
            display.println("<p> Socket Type    : " + socketType + "</p>");
            display.println("<p> Socket Quantity: " + socketQuantity + "</p>");
            display.println("<p> Order Total    : $" + price + "</p>");
            display.println("<p>" + customerName + " Your Order has been Placed Successfully !!!!!" +"</p>");
            display.println("</body></html>");

        } 
        // Catch Quantity field exceptions (Number Format Exception)
        catch (NumberFormatException e) {
            display.println("<html><body>");
            // Display Error Message when user enters quantity as non numeric value
            display.println("<p> Error: Quantity should be entered in Numerics Only !!!!. </p>");
            display.println("</body></html>");
        }
    }
    
    /**
     * Method to Calculate the Total Price based on socket type and quantity of sockets
     */
    private double socketPriceCalculator(String socketType, int socketQuantity) {
        double oneSocketPrice = 0.0;
        // Get the type selected by user and calculate the cost
        if (socketType.equals("TYPE A")) {
            oneSocketPrice = 5.0;
        } else if (socketType.equals("TYPE B")) {
            oneSocketPrice = 15.0;
        } else if (socketType.equals("TYPE C")) {
            oneSocketPrice = 25.0;
        }
        // Return the total price
        return oneSocketPrice * socketQuantity;
    }
}
