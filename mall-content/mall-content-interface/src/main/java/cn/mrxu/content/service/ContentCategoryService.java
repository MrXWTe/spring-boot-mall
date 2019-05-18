package cn.mrxu.content.service;

import cn.mrxu.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCatList(long parentId);
}
