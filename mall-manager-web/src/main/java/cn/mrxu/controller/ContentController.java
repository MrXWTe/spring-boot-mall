package cn.mrxu.controller;

import cn.mrxu.common.utils.E3Result;
import cn.mrxu.content.service.ContentService;
import cn.mrxu.pojo.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容管理Controller
 */
@Controller
public class ContentController {

    private ContentService contentService;

    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result addContent(TbContent content){
        // 调用服务，把内容数据保存到数据库
        E3Result e3Result = contentService.addContent(content);
        return e3Result;
    }
}
