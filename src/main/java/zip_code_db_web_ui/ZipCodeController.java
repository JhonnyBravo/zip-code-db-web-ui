package zip_code_db_web_ui;

import java.util.List;

import javax.annotation.PostConstruct;
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
     * 動作検証用ダミーレコードを登録する。
     */
    @PostConstruct
    public void init() {
        ZipCode z1 = new ZipCode();

        z1.setZipCode("4901103");
        z1.setPrefecture("愛知県");
        z1.setCity("あま市");
        z1.setArea("栄");

        repository.saveAndFlush(z1);

        ZipCode z2 = new ZipCode();

        z2.setZipCode("4901213");
        z2.setPrefecture("愛知県");
        z2.setCity("あま市");
        z2.setArea("乙之子");

        repository.saveAndFlush(z2);

        ZipCode z3 = new ZipCode();

        z3.setZipCode("7460019");
        z3.setPrefecture("山口県");
        z3.setCity("周南市");
        z3.setArea("臨海町");

        repository.saveAndFlush(z3);

        ZipCode z4 = new ZipCode();

        z4.setZipCode("0100956");
        z4.setPrefecture("秋田県");
        z4.setCity("秋田市");
        z4.setArea("山王臨海町");

        repository.saveAndFlush(z4);
    }

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
        String zipCode = request.getParameter("zipCode");
        String prefecture = request.getParameter("prefecture");
        String city = request.getParameter("city");
        String area = request.getParameter("area");

        if (zipCode.isEmpty() && prefecture.isEmpty() && city.isEmpty() && area.isEmpty()) {
            mav = new ModelAndView("redirect:/");
            return mav;
        }

        if (!zipCode.isEmpty()) {
            List<ZipCode> result = repository.findByZipCode(zipCode);
            mav.addObject("result", result);
        } else if (!prefecture.isEmpty() && !city.isEmpty() && !area.isEmpty()) {
            List<ZipCode> result = repository.findByPrefectureAndCityLikeAndAreaLike(prefecture, city, area);
            mav.addObject("result", result);
        } else if (!prefecture.isEmpty() && !city.isEmpty() && area.isEmpty()) {
            List<ZipCode> result = repository.findByPrefectureAndCityLike(prefecture, city);
            mav.addObject("result", result);
        } else if (!prefecture.isEmpty() && city.isEmpty() && !area.isEmpty()) {
            List<ZipCode> result = repository.findByPrefectureAndAreaLike(prefecture, area);
            mav.addObject("result", result);
        } else if (prefecture.isEmpty() && !city.isEmpty() && !area.isEmpty()) {
            List<ZipCode> result = repository.findByCityLikeAndAreaLike(city, area);
            mav.addObject("result", result);
        } else if (!prefecture.isEmpty() && city.isEmpty() && area.isEmpty()) {
            List<ZipCode> result = repository.findByPrefecture(prefecture);
            mav.addObject("result", result);
        } else if (prefecture.isEmpty() && !city.isEmpty() && area.isEmpty()) {
            List<ZipCode> result = repository.findByCityLike(city);
            mav.addObject("result", result);
        } else if (prefecture.isEmpty() && city.isEmpty() && !area.isEmpty()) {
            List<ZipCode> result = repository.findByAreaLike(area);
            mav.addObject("result", result);
        }

        mav.setViewName("search");
        return mav;
    }

}
