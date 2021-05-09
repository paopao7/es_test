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
import org.elasticsearch.search.sort.SortOrder;

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

//        //1、全量查询数据
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        //全量匹配
//        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        //获取数据
//        SearchHits hits = response.getHits();
//
//        //打印数据总条数
//        System.out.println(hits.getTotalHits());
//        //打印花费的时间
//        System.out.println(response.getTook());
//
//        //循环输出
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

//        //2、条件查询查询数据 termQuery
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        //全量匹配
//        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age",30)));
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        //获取数据
//        SearchHits hits = response.getHits();
//
//        //打印数据总条数
//        System.out.println(hits.getTotalHits());
//        //打印花费的时间
//        System.out.println(response.getTook());
//
//        //循环输出
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

//        //3、分页查询查询数据
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//
//        //（当前页面 - 1） * 每页条数
//        builder.from(0);
//        builder.size(2);
//
//        //全量匹配
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        //获取数据
//        SearchHits hits = response.getHits();
//
//        //打印数据总条数
//        System.out.println(hits.getTotalHits());
//        //打印花费的时间
//        System.out.println(response.getTook());
//
//        //循环输出
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

//        //4、查询排序
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//
//        builder.sort("age", SortOrder.DESC);
//
//        //全量匹配
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        //获取数据
//        SearchHits hits = response.getHits();
//
//        //打印数据总条数
//        System.out.println(hits.getTotalHits());
//        //打印花费的时间
//        System.out.println(response.getTook());
//
//        //循环输出
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        //5、筛选字段
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());

        String[] excludes = {}; //排除
        String[] include = {"name"}; //包含
        builder.fetchSource(include, excludes);

        //全量匹配
        request.source(builder);

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
