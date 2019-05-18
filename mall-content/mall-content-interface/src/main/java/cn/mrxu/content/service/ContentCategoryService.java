package cn.mrxu.content.service;

import cn.mrxu.common.pojo.EasyUITreeNode;
import cn.mrxu.common.utils.E3Result;

import java.util.List;

public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCatList(long parentId);

    E3Result addContentCategory(long parentId, String name);
}
