package cn.mrxu.service;

import cn.mrxu.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * 商品分类管理
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(Long parentId);
}
