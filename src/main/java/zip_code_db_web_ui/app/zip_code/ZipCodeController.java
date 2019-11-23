package zip_code_db_web_ui.app.zip_code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
    @Autowired
    ZipCodeValidator zipCodeValidator;

    /**
     * 独自実装した Validator を登録する。
     *
     * @param binder {@link org.springframework.web.bind.WebDataBinder}
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(zipCodeValidator);
    }

    /**
     * 検索フォームを表示する。
     *
     * @param form 検索フォームから送信されたフォームオブジェクトを受け取る。
     * @return view index.html
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute("form") ZipCodeForm form) {
        return "index";
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
     * @param form          検索フォームから送信されたフォームオブジェクトを受け取る。
     * @param bindingResult {@link org.springframework.validation.BindingResult}
     * @param model         {@link org.springframework.ui.Model}
     * @return view 以下の条件に該当する場合は index.html へ転送する。該当しない場合は search.html へ転送する。
     *         <ul>
     *         <li>バリデーションエラーが発生した場合</li>
     *         <li>検索結果が 0 件だった場合</li>
     *         <li>検索結果が 1000 件を超えた場合</li>
     *         </ul>
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String postSearchPage(@ModelAttribute("form") @Validated ZipCodeForm form, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return index(form);
        }

        final List<ZipCode> result = service.find(form);
        model.addAttribute("result", result);

        if (result.size() == 0) {
            model.addAttribute("error", "該当する住所が見つかりませんでした。");
            return index(form);
        } else if (result.size() > 1000) {
            model.addAttribute("error", "該当する住所が 1000 件を超えました。検索範囲を狭めてください。");
            return index(form);
        }

        return "search";
    }
}
