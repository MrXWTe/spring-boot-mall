package cn.mrxu.content.service.impl;

import cn.mrxu.common.jedis.JedisClient;
import cn.mrxu.common.utils.E3Result;
import cn.mrxu.content.service.ContentService;
import cn.mrxu.mapper.TbContentMapper;
import cn.mrxu.pojo.TbContent;
import cn.mrxu.pojo.TbContentExample;
import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 内容管理Service
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;


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

        // 插入数据后，需要缓存同步， 删除缓存中对应的数据
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());

        return E3Result.ok();
    }

    /**
     * 根据内容分类ID查询
     * @param cid 内容cid
     * @return 内容列表
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        // 查询缓存
        try{
            // 如果缓存中有，就直接响应结果
            String json = jedisClient.hget(CONTENT_LIST, cid + "");
            if(StringUtils.isEmpty(json)){
                 List<TbContent> list = (List<TbContent>)JSONUtils.parse(json);
                 return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        // 如果没有，查询数据库
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        // 设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        // 执行查询
        List<TbContent> tbContentList = contentMapper.selectByExampleWithBLOBs(example);

        // 把结果添加到缓存
        try {
            jedisClient.hset(CONTENT_LIST, cid + "", JSONUtils.toJSONString(tbContentList));
        }catch (Exception e){
            e.printStackTrace();
        }

        return tbContentList;
    }
}
