package zip_code_db_web_ui.app.zip_code;

import java.util.List;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import zip_code_db_web_ui.domain.model.ZipCode;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeService;

/**
 * ルーティングとリクエストを管理する。
 */
@Controller
public class ZipCodeController {
    @Autowired
    ZipCodeService service;

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
        final ZipCode zipCode = new ZipCode();
        zipCode.setZipCode(request.getParameter("zipCode"));
        zipCode.setPrefecture(request.getParameter("prefecture"));
        zipCode.setCity(request.getParameter("city"));
        zipCode.setArea(request.getParameter("area"));

        final Predicate<String> isNotEmpty = value -> {
            boolean result = false;

            if (value != null && !value.isEmpty()) {
                result = true;
            }

            return result;
        };

        if (!isNotEmpty.test(zipCode.getZipCode()) && !isNotEmpty.test(zipCode.getPrefecture())
                && !isNotEmpty.test(zipCode.getCity()) && !isNotEmpty.test(zipCode.getArea())) {
            mav = new ModelAndView("redirect:/");
            return mav;
        } else {
            final List<ZipCode> result = service.find(zipCode);
            mav.addObject("result", result);
            mav.setViewName("search");
            return mav;
        }

    }

}
