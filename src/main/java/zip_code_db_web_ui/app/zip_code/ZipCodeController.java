package zip_code_db_web_ui.app.zip_code;

import java.util.List;

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
 * 住所検索サービスを管理する。
 */
@Controller
public class ZipCodeController {
    @Autowired
    ZipCodeService service;
    @Autowired
    ZipCodeHelper helper;

    /**
     * 検索フォームを表示する。
     *
     * @param zipcode 検索フォームから送信されたフォームオブジェクトを受け取る。
     * @param mav     {@link org.springframework.web.servlet.ModelAndView}
     * @return view index.html
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("formModel") ZipCode zipcode, ModelAndView mav) {
        mav.setViewName("index");
        return mav;
    }

    /**
     * 検索フォームへ転送する。
     *
     * @param mav {@link org.springframework.web.servlet.ModelAndView}
     * @return view index.html
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView getSearchPage(ModelAndView mav) {
        mav = new ModelAndView("redirect:/");
        return mav;
    }

    /**
     * 住所検索を実行し、検索結果をテーブル表示する。
     * 
     * @param request 検索フォームから送信された POST リクエストを受け取る。
     * @param mav     {@link org.springframework.web.servlet.ModelAndView}
     * @return view search.html
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView postSearchPage(HttpServletRequest request, ModelAndView mav) {
        final List<ZipCode> result = service.find(helper.convertToZipCode(request));

        mav.addObject("result", result);
        mav.setViewName("search");
        return mav;
    }
}
