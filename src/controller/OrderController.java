package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import model.Cart;
import model.CartItem;
import model.OrderStatus;
import model.User;
import service.CartItemService;
import service.CartService;
import service.UserService;
import service.impl.CartServiceImpl;
import service.impl.CartServiceItemImpl;
import service.impl.UserServiceImpl;
import tools.SendMail;
import util.RandomUUID;

import momo.allinone.models.CaptureMoMoResponse;
import momo.allinone.processor.allinone.CaptureMoMo;
import momo.shared.sharedmodels.Environment;
import momo.shared.utils.LogUtils;

@WebServlet(urlPatterns = "/member/order")
public class OrderController extends HttpServlet {
	UserService userService = new UserServiceImpl();
	CartService cartService = new CartServiceImpl();
	CartItemService cartItemService = new CartServiceItemImpl();
	long time = System.currentTimeMillis();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("account");
		User buyer = (User) obj;
		Cart cart = new Cart();
		cart.setBuyer(buyer);
		cart.setBuyDate(new java.sql.Date(time));
		cartService.insert(cart);
		
		LogUtils.init();
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = "SFCS" + getAlphaNumericString(10);
        long amount = 0;
        
        String orderInfo = "Thanh toán với MoMo";
        String returnURL = "http://localhost:8080/SFCS";
        String notifyURL = "http://localhost:8080/SFCS";
        String extraData = "";
        // String bankCode = "SML";

		Object objCart = session.getAttribute("cart");
		if (objCart != null) {
			Map<Integer, CartItem> map = (Map<Integer, CartItem>) objCart;
			for (CartItem cartItem : map.values()) {
				cartItem.setCart(cart);
				amount+=cartItem.getUnitPrice()*cartItem.getQuantity()*(100-cartItem.getProduct().getDiscount())/100;
				cartItemService.insert(cartItem);
			}
			
		}
		
        Environment environment = Environment.selectEnv("dev", Environment.ProcessType.PAY_GATE);
        
        CaptureMoMoResponse captureMoMoResponse;
		try {
			captureMoMoResponse = CaptureMoMo.process(environment, orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, extraData);
	        String url = captureMoMoResponse.getPayUrl();
	        session.removeAttribute("cart");
	        resp.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	static String getAlphaNumericString(int n) { 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
}
