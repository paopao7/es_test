package cn.itinfor.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * 客户端创建关闭演示
 */
public class EsClient {
    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //关闭es客户端
        esClient.close();
    }
}
