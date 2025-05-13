package cn.vgonet.mirror;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import cn.vgonet.mirror.context.core.ContextResetter;
import cn.vgonet.mirror.context.core.Operator;
import cn.vgonet.mirror.frameworks.domain.core.IdAndName;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/**")
public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        ContextResetter.reset(new Operator("john", "John", new IdAndName("baidu", "Baidu")));
        filterChain.doFilter(request, response);
    }
}
