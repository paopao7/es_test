package cn.itinfor.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.naming.directory.SearchResult;

/**
 * 全量查询数据
 */
public class EsDocQuery {
    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //全量查询数据
        SearchRequest request = new SearchRequest();
        request.indices("user");

        //全量匹配
        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        //获取数据
        SearchHits hits = response.getHits();

        //打印数据总条数
        System.out.println(hits.getTotalHits());
        //打印花费的时间
        System.out.println(response.getTook());

        //循环输出
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        //关闭es客户端
        esClient.close();
    }
}
