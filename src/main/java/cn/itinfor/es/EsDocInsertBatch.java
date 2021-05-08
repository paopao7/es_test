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

        //这种形式会报错
        //request.add(new IndexRequest().index(user).id(1001).source(XContentType.JSON, name, 张三));

        request.add(new IndexRequest("user","doc","1001").source(XContentType.JSON, "name", "张三"));
        request.add(new IndexRequest("user","doc","1001").source(XContentType.JSON, "name", "李四"));
        request.add(new IndexRequest("user","doc","1001").source(XContentType.JSON, "name", "王五"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(response.getTook());
        System.out.println(Arrays.toString(response.getItems()));

        //关闭es客户端
        esClient.close();
    }
}
