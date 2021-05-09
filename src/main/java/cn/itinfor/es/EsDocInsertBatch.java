package cn.itinfor.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.Arrays;

/**
 * 批量插入数据
 */
public class EsDocInsertBatch {
    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        BulkRequest request = new BulkRequest();

        request.add(new IndexRequest().index("user").id("1001") .source(XContentType.JSON,"name", "张三"));
        request.add(new IndexRequest().index("user").id("1002") .source(XContentType.JSON,"name", "李四"));
        request.add(new IndexRequest().index("user").id("1003") .source(XContentType.JSON,"name", "王五"));
        request.add(new IndexRequest().index("user").id("1004") .source(XContentType.JSON,"name", "赵六"));
        request.add(new IndexRequest().index("user").id("1005") .source(XContentType.JSON,"name", "王二麻子"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(response.getTook());
        System.out.println(Arrays.toString(response.getItems()));

        //关闭es客户端
        esClient.close();
    }
}
