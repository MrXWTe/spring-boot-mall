package cn.mrxu.content.service;

import cn.mrxu.common.utils.E3Result;
import cn.mrxu.pojo.TbContent;

import java.util.List;

public interface ContentService {

    E3Result addContent(TbContent content);

    List<TbContent> getContentListByCid(long cid);
}
