package cn.mrxu.content.service.impl;


import cn.mrxu.common.pojo.EasyUITreeNode;
import cn.mrxu.content.service.ContentCategoryService;
import cn.mrxu.mapper.TbContentCategoryMapper;
import cn.mrxu.pojo.TbContentCategory;
import cn.mrxu.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理service
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(long parentId) {
        // 根据parentId查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();

        // 设置查询条件
        criteria.andParentIdEqualTo(parentId);

        // 执行查询
        List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);

        // 转换成EasyUITreeNode的列表
        List<EasyUITreeNode> nodeList = new ArrayList<>();
        for(TbContentCategory category : catList){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent() ? "closed" : "open");

            nodeList.add(node);
        }

        return nodeList;
    }
}
