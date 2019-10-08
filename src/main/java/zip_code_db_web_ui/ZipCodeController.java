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
     * @return ModelAndView POST 送信時にパラメータが指定されていない場合は index.html へリダイレクトし、
     *         パラメータが指定されている場合は search.html を返す。
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView postSearchPage(HttpServletRequest request, ModelAndView mav) {
        final String zipCode = request.getParameter("zipCode");
        final String prefecture = request.getParameter("prefecture");
        final String city = request.getParameter("city");
        final String area = request.getParameter("area");

        if (zipCode.isEmpty() && prefecture.isEmpty() && city.isEmpty() && area.isEmpty()) {
            mav = new ModelAndView("redirect:/");
            return mav;
        }

        if (!zipCode.isEmpty()) {
            final List<ZipCode> result = repository.findByZipCode(zipCode);
            mav.addObject("result", result);
        } else if (!prefecture.isEmpty() && !city.isEmpty() && !area.isEmpty()) {
            final List<ZipCode> result = repository.findByPrefectureAndCityLikeAndAreaLike(prefecture, city, area);
            mav.addObject("result", result);
        } else if (!prefecture.isEmpty() && !city.isEmpty() && area.isEmpty()) {
            final List<ZipCode> result = repository.findByPrefectureAndCityLike(prefecture, city);
            mav.addObject("result", result);
        } else if (!prefecture.isEmpty() && city.isEmpty() && !area.isEmpty()) {
            final List<ZipCode> result = repository.findByPrefectureAndAreaLike(prefecture, area);
            mav.addObject("result", result);
        } else if (prefecture.isEmpty() && !city.isEmpty() && !area.isEmpty()) {
            final List<ZipCode> result = repository.findByCityLikeAndAreaLike(city, area);
            mav.addObject("result", result);
        } else if (!prefecture.isEmpty() && city.isEmpty() && area.isEmpty()) {
            final List<ZipCode> result = repository.findByPrefecture(prefecture);
            mav.addObject("result", result);
        } else if (prefecture.isEmpty() && !city.isEmpty() && area.isEmpty()) {
            final List<ZipCode> result = repository.findByCityLike(city);
            mav.addObject("result", result);
        } else if (prefecture.isEmpty() && city.isEmpty() && !area.isEmpty()) {
            final List<ZipCode> result = repository.findByAreaLike(area);
            mav.addObject("result", result);
        }

        mav.setViewName("search");
        return mav;
    }

}
