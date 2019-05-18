package cn.mrxu.content.service.impl;


import cn.mrxu.common.pojo.EasyUITreeNode;
import cn.mrxu.common.utils.E3Result;
import cn.mrxu.content.service.ContentCategoryService;
import cn.mrxu.mapper.TbContentCategoryMapper;
import cn.mrxu.pojo.TbContentCategory;
import cn.mrxu.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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


    @Override
    public E3Result addContentCategory(long parentId, String name) {
        // 创建一个 tb_content_category表对应的pojo
        TbContentCategory contentCategory = new TbContentCategory();
        // 设置pojo的属性
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        contentCategory.setStatus(1);       // 1正常；2删除
        contentCategory.setSortOrder(1);    // 默认排序为1
        contentCategory.setIsParent(false); // 新添加的节点一定是叶子节点
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        // 插入数据库
        contentCategoryMapper.insert(contentCategory);
        // 判断父节点的isparent属性，如果不是true，改为true
        // 根据parentId查询父节点
        TbContentCategory contentCategoryParent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if(!contentCategoryParent.getIsParent()){
            contentCategoryParent.setIsParent(true);
            // 更新到数据库中
            contentCategoryMapper.updateByPrimaryKey(contentCategoryParent);
        }
        // 返回结果，返回E3Result
        return E3Result.ok(contentCategory);
    }
}
