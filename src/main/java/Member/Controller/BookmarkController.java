package Member.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Member.DAO.BookmarkDAO;
import Member.DTO.BookmarkDTO;

@WebServlet("/Bookmark")
public class BookmarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("insert")) {
            BookmarkDTO dto = new BookmarkDTO();
            dto.setNickname(req.getParameter("nickname"));
            dto.setRecipeId(Integer.parseInt(req.getParameter("recipeId")));
            dto.setC_NUM(Integer.parseInt(req.getParameter("cNum")));
            
            BookmarkDAO dao = new BookmarkDAO();
            dao.insertBookmark(dto);
        }
        
        if (action != null && action.equals("delete")) {
            int bookmarkId = Integer.parseInt(req.getParameter("bookmarkId"));
            BookmarkDAO dao = new BookmarkDAO();
            dao.deleteBookmark(bookmarkId);
        }
        
        resp.sendRedirect("list");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("nickname");
        BookmarkDAO dao = new BookmarkDAO();
        List<BookmarkDTO> bookmarkList = dao.selectBookmarkList(nickname);
        
        req.setAttribute("bookmarkList", bookmarkList);
        req.getRequestDispatcher("/bookmark/bookmarkList.jsp").forward(req, resp);
    }
}