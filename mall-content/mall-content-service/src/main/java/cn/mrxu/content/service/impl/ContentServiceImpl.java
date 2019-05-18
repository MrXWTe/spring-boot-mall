package cn.mrxu.content.service.impl;

import cn.mrxu.common.utils.E3Result;
import cn.mrxu.content.service.ContentService;
import cn.mrxu.mapper.TbContentMapper;
import cn.mrxu.pojo.TbContent;
import cn.mrxu.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 内容管理Service
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    /**
     * 添加某个内容列表对应的内容
     * @param content 要添加的内容
     * @return E3Result对象
     */
    @Override
    public E3Result addContent(TbContent content) {
        // 将内容数据插入到内容表
        content.setCreated(new Date());
        content.setUpdated(new Date());
        // 插入到数据库
        contentMapper.insert(content);
        return E3Result.ok();
    }

    /**
     * 根据内容分类ID查询
     * @param cid 内容cid
     * @return 内容列表
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        // 设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        // 执行查询
        List<TbContent> tbContentList = contentMapper.selectByExampleWithBLOBs(example);
        return tbContentList;
    }
}
