package cn.mrxu.service.impl;

import cn.mrxu.common.pojo.EasyUIDataGridResult;
import cn.mrxu.mapper.TbItemMapper;
import cn.mrxu.pojo.TbItem;
import cn.mrxu.pojo.TbItemExample;
import cn.mrxu.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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


    /**
     * 查询分页信息
     * @param page 页数
     * @param rows 行数
     * @return
     */
    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {

        // 设置分页信息
        PageHelper.startPage(page, rows);

        // 执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> tbItemList = tbItemMapper.selectByExample(example);

        // 创建返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(tbItemList);
        // 取分页结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);
        // 取总记录数
        long total = pageInfo.getTotal();

        result.setTotal(total);
        return result;
    }
}
