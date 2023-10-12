package br.com.kauacnok.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.com.kauacnok.todolist.user.IUserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

	@Autowired
	private IUserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		var servletPath = request.getServletPath();

		if (servletPath.equals("/tasks/create") || servletPath.equals("/tasks/get-user-tasks") || servletPath.startsWith("/tasks/update-task/")) {
			
			var authorization = request.getHeader("Authorization");
			var authEncoded = authorization.substring("Basic".length()).trim();

			byte[] authDecode = Base64.getDecoder().decode(authEncoded);

			var authString = new String(authDecode);

			String[] credentials = authString.split(":");
			String username = credentials[0];
			String password = credentials[1];

			var user = this.userRepository.findByUsername(username);
			if (user == null) {
				response.sendError(401);
			} else {
				var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

				if (passwordVerify.verified) {
					request.setAttribute("idUser", user.getId());
					filterChain.doFilter(request, response);
				} else {
					response.sendError(401);
				}
				
			}
		} else {
			filterChain.doFilter(request, response);
		}

	}
	
}