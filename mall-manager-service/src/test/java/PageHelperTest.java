import cn.mrxu.mapper.TbItemMapper;
import cn.mrxu.pojo.TbItem;
import cn.mrxu.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class PageHelperTest {

    @Test
    public void testPageHelper() {
        // 初始化spring容器
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        // 从容器中获得Mapper代理对象
        TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);


        // 执行sql语句之前，设置分页信息使用PageHelper的startPage方法
        PageHelper.startPage(1, 10);
        // 执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> tbItemList = tbItemMapper.selectByExample(example);
        // 取分页信息，PageInfo。1、总记录数；2、总页数；3、当前页码
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(tbItemList.size());
    }
}
