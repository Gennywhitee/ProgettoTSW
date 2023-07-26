package Controller;

import Model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig(fileSizeThreshold = 2024 * 2024,
        maxFileSize = 2024 * 2024 * 5,
        maxRequestSize = 2024 * 2024 * 5 * 5)
@WebServlet(name = "addProductServlet", value = "/add-product-servlet")
public class AddProduct extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if (user.isAdmin().equalsIgnoreCase("true")) {
            CategoryDAO service = new CategoryDAO();
            ArrayList<CategoryBean> categoryList = service.doRetrieveAll();

            request.setAttribute("categories", categoryList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/add-product.jsp");
            dispatcher.include(request, response);
        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");


        if (userBean != null && userBean.isAdmin().equalsIgnoreCase("true")) {
            Matcher matcher;
            boolean matchFound;
            int count = 0;


            String name = request.getParameter("name");
            if (name.length() != 0 && name.length() <= 30)
                count++;
            System.out.println(name);

            String quantity = request.getParameter("quantity");
            matcher = int_String.matcher(quantity);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String price = request.getParameter("price");
            matcher = decimal_String.matcher(price);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String category = request.getParameter("category");
            if (category.length() <= 20 && category.length() != 0)
                count++;

            String description = request.getParameter("description");
            if (description.length() <= 255 && category.length() != 0)
                count++;

            Part part = request.getPart("image");
            String subpath;

            if (!part.getSubmittedFileName().isEmpty()) {
                String uploadPath = getServletContext().getRealPath("") + File.separator + "photo" + File.separator + "products";
                String imagepath = uploadPath + File.separator + part.getSubmittedFileName();
                part.write(imagepath);
                subpath = "./photo/products/" + part.getSubmittedFileName();
            } else {
                subpath = "./photo/products/noimage.png";
            }
            ProductDAO service = new ProductDAO();

            if (count == 5 && !service.isAlreadyRegistered(name, description)) {
                ProductBean newProduct = new ProductBean();
                newProduct.setName(name);
                newProduct.setQuantity(Integer.parseInt(quantity));
                newProduct.setPrice(Double.parseDouble(price));
                newProduct.setCategory(category);
                newProduct.setDescription(description);
                newProduct.setImage(subpath);
                newProduct.setSales(0);
                service.doSave(newProduct);

                CategoryDAO serviceCategory = new CategoryDAO();
                ArrayList<CategoryBean> listCategories = serviceCategory.doRetrieveAll();

                request.setAttribute("categories", listCategories);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/add-product.jsp");
                dispatcher.include(request, response);
            } else {

                if (service.isAlreadyRegistered(name, description)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirmPage.jsp");
                    request.setAttribute("type", "alert");
                    request.setAttribute("msg", "Prodotto già presente");
                    request.setAttribute("redirect", "add-product-servlet");
                    dispatcher.include(request, response);
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confirmPage.jsp");
                    request.setAttribute("type", "alert");
                    request.setAttribute("msg", "Errore inserimento");
                    request.setAttribute("redirect", "add-product-servlet");
                    dispatcher.include(request, response);
                }

            }

        } else {

            String address = "/catalog.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request,response);
        }
    }
        final Pattern decimal_String = Pattern.compile("^(\\d+(?:[.,]\\d{2})?)$");
        final Pattern int_String = Pattern.compile("^\\d+$");
}
