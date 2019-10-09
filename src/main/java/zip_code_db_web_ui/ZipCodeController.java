package zip_code_db_web_ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * ルーティングとリクエストを管理する。
 */
@Controller
public class ZipCodeController {
    @Autowired
    ZipCodeRepository repository;

    /**
     * @param zipCode ZipCode エンティティを受取る。
     * @param mav     ModelAndView を受取る。
     * @return ModelAndView index.html を返す。
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("formModel") ZipCode zipcode, ModelAndView mav) {
        mav.setViewName("index");
        return mav;
    }

    /**
     * @param mav ModelAndView を受取る。
     * @return ModelAndView index.html を返す。
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView getSearchPage(ModelAndView mav) {
        mav = new ModelAndView("redirect:/");
        return mav;
    }

    /**
     * @param request HttpServletRequest を受取る。
     * @param mav     ModelAndView を受取る。
     * @return ModelAndView レコード検索を実行し、その結果を search.html へ渡してページ遷移させる。
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView postSearchPage(HttpServletRequest request, ModelAndView mav) {
        final ZipCode zipCode = new ZipCode();

        zipCode.setZipCode(request.getParameter("zipCode"));
        zipCode.setPrefecture(request.getParameter("prefecture"));
        zipCode.setCity(request.getParameter("city"));
        zipCode.setArea(request.getParameter("area"));

        final List<ZipCode> result = repository.find(zipCode);

        mav.addObject("result", result);
        mav.setViewName("search");
        return mav;
    }
}
