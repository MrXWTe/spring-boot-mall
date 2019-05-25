import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrJ {

    @Test
    public void addDocument() throws Exception{
        // 创建一个SolrServer对象，创建连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.1.165:8080/solr");
        // 创建一个文档对象SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();
        // 向文档对象中添加域，文档中必须包含一个id域，所有域的名称必须在schema.xml中定义
        document.addField("id", "doc01");
        document.addField("item_title", "测试商品01");
        document.addField("item_price", 1000);
        // 把文档写入索引库
        solrServer.add(document);
        // 提交
        solrServer.commit();
    }
}
