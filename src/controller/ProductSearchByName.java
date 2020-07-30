package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.Product;
import model.Stall;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.StallServiceImpl;
@WebServlet(urlPatterns="/product/search")
public class ProductSearchByName extends HttpServlet {
	ProductService productService=new ProductServiceImpl();
	CategoryServiceImpl categoryService = new CategoryServiceImpl();
	StallServiceImpl stallService = new StallServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		List<Product> productSearchByName =productService.searchByName(name);
		req.setAttribute("productSearchByName", productSearchByName);
		List<Category> categoryList = categoryService.getAll();
		req.setAttribute("categoryList", categoryList);
		List<Stall> stallList = stallService.getAll();
		req.setAttribute("stallList", stallList);
		req.getRequestDispatcher("/view/client/view/productSearchByName.jsp").forward(req, resp);
	}
}
