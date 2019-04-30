package cn.mrxu.service.impl;

import cn.mrxu.common.pojo.EasyUITreeNode;
import cn.mrxu.mapper.TbItemCatMapper;
import cn.mrxu.pojo.TbItemCat;
import cn.mrxu.pojo.TbItemCatExample;
import cn.mrxu.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    /**
     * 根据parentId查询子节点列表
     * 把列表转换成EasyUITreeNode列表
     * @param parentId 父节点Id
     * @return EasyUITreeNode结果集
     */
    @Override
    public List<EasyUITreeNode> getItemCatList(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(example);

        // 将查询结果转换成 EasyUITreeNode形式
        List<EasyUITreeNode> easyUITreeNodeList = new ArrayList<>();
        for(TbItemCat tbItemCat : tbItemCatList){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");

            easyUITreeNodeList.add(node);
        }
        return easyUITreeNodeList;
    }
}
