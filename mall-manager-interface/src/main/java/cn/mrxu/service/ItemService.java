package cn.mrxu.service;

import cn.mrxu.common.pojo.EasyUIDataGridResult;
import cn.mrxu.pojo.TbItem;

public interface ItemService {

    TbItem getItemById(Long itemId);

    /**
     * 查询分页信息
     * @param page 页数
     * @param rows 行数
     * @return
     */
    EasyUIDataGridResult getItemList(int page, int rows);
}
