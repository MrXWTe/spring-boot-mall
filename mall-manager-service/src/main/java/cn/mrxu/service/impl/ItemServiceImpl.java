package cn.mrxu.service.impl;

import cn.mrxu.mapper.TbItemMapper;
import cn.mrxu.pojo.TbItem;
import cn.mrxu.pojo.TbItemExample;
import cn.mrxu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理 Service
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        // 根据主键查询
        //return tbItemMapper.selectByPrimaryKey(itemId);

        // 设置条件查询
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> tbItemList = tbItemMapper.selectByExample(example);

        if(tbItemList!=null && tbItemList.size()>0){
            return tbItemList.get(0);
        }
        return null;
    }
}
