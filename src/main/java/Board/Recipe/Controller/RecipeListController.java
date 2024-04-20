package Board.Recipe.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.Recipe.DAO.RecipeBoardDAO;
import Board.Recipe.DTO.RecipeBoardDTO;
import Common.BoardPage;

@WebServlet("/RecipeList")
public class RecipeListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       RecipeBoardDAO dao = new RecipeBoardDAO();

        // 뷰에 전달할 매개변수 저장용 맵 생성
        Map<String, Object> map = new HashMap<String, Object>();

        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");
        if (searchWord != null) {
            // 검색어가 있다면 map에 저장
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }
        int totalCount = dao.selectCount(map);

        ServletContext application = getServletContext();
        int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
        int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

        // 현재 페이지 확인
        int pageNum = 1;  // 기본값
        String pageTemp = req.getParameter("pageNum");
        if (pageTemp != null && !pageTemp.equals(""))
            pageNum = Integer.parseInt(pageTemp);
        
        // 목록에 출력할 게시물 범위 계산
        int start = (pageNum - 1) * pageSize + 1;  
        int end = pageNum * pageSize;
        map.put("start", start);
        map.put("end", end);

        List<RecipeBoardDTO> recipeLists = dao.selectListPage(map);
        dao.close();

        String pagingImg = BoardPage.pagingStr(totalCount, pageSize,
                blockPage, pageNum, "recipe_board");  // 바로가기 영역 HTML 문자열
        map.put("pagingImg", pagingImg);
        map.put("totalCount", totalCount);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);

        req.setAttribute("recipeLists", recipeLists);
        req.setAttribute("map", map);
        req.getRequestDispatcher("/WEB-INF/Views/Board/Recipe/List.jsp").forward(req, resp);
    }
}
