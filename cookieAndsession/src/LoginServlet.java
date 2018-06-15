import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    //原理：
    //用户登录后服务器会发送保存了用户信息的cookie到浏览器保存下来，
    //用户下次登录时查找本地是否含有同名cookie，有则实现跳转。
    //由于cookie易获取，造成信息不安全，结合保存在服务器的session实现
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        String sessionId = session.getId();

        //本地的sessionId跟本次会话sessionId比较
        for(Cookie cookie : cookies){
            String key = cookie.getName();
            if("sessionId".equals(key) ){
                String value = cookie.getValue();
                if(!sessionId.equals(value)){
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                }
            }
        }

        //本地cookie比较
        for(Cookie cookie : cookies){
            String key = cookie.getName();
            if("user".equals(key)){
                request.getRequestDispatcher("grid.jsp").forward(request, response);
                return;
            }
        }

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User(name, password);
        String userInfo = user.toString();

        //判断用户是否合法
        if(name == null || password == null || !name.equals("user") || !password.equals("123456")){
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        //保存用户信息到cookie
        Cookie userCookie = new Cookie("user", userInfo);
        userCookie.setPath("/");//类所在路径
        userCookie.setMaxAge(60*3);//三分钟过期
        response.addCookie(userCookie);

        Cookie sessionIdCookie = new Cookie("sessionId", sessionId);
        userCookie.setPath("/");//类所在路径
        userCookie.setMaxAge(-1);//关闭浏览器过期
        response.addCookie(sessionIdCookie);

        request.getRequestDispatcher("grid.jsp").forward(request, response);
    }
}
