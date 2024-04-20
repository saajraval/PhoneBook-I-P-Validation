package com.cse5382.assignment.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cse5382.assignment.Model.UserModel;
import com.cse5382.assignment.Service.UserService;

@Component
public class AuthFilter implements Filter {

	@Autowired
	UserService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String token = req.getHeader("token");
		String url = req.getRequestURL().toString();
		if (!url.contains("/phoneBook/")) {
			// for public url all can access
			chain.doFilter(request, response);
		} else {
			// for private url token must be present
			if (token == null || userService.getUserByToken(token) == null) {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				HashMap<String, String> map = new HashMap<>();
				map.put("ERROR", "UNAUTHORIZED");
				res.getWriter().print(map);
				res.getWriter().flush();
			} else {

				// if token present then we have to validate its correct or not
				UserModel dbUser = userService.getUserByToken(token);
				if (dbUser == null) {
					res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					HashMap<String, String> map = new HashMap<>();
					map.put("ERROR", "UNAUTHORIZED");
					res.getWriter().print(map);
					res.getWriter().flush();
					return;

				} else {
					// token is correct then we have to check role
					String methodType = req.getMethod();// GET PUT POST

					if (dbUser.getRole().equals("R") || dbUser.getRole().equals("RW")) {

						// if user ask an api that is not read api then we check role
						if (!methodType.toLowerCase().equals("get") && dbUser.getRole().equals("R")) {
							res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
							HashMap<String, String> map = new HashMap<>();
							map.put("ERROR", "UNAUTHORIZED");
							res.getWriter().print(map);
							res.getWriter().flush();
							return;
						}
					} else {
						res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						HashMap<String, String> map = new HashMap<>();
						map.put("ERROR", "UNAUTHORIZED");
						res.getWriter().print(map);
						res.getWriter().flush();
						return;
					}
				}
				chain.doFilter(request, response);
			}
		}
	}
}
