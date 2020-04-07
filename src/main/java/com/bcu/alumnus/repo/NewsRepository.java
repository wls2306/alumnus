package com.bcu.alumnus.repo;

import com.bcu.alumnus.entity.News;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor {
    /**
     * 1. 校园新闻发布 @UseToken(level=3)
     * 2. 校园新闻查询，通过学部编号查询以及校级新闻，且状态为可见的新闻，时间倒序排列
     * 3. 通过新闻编号查看新闻详情
     * 4. 通过新闻编号删除新闻 @UseToken(level=3)
     * 5. 根据新闻状态查找新闻
     * 6. 通过学部编号查找新闻
     * 7. 通过新闻类别查找新闻
     * 8. 查找所有的新闻 @UseToken(level=4)
     */
    @Query(value = "select news_id,news_title,news_author,news_date,news_title_img from news where news_part_id=?1 and news_status=1 or news_type=2 and news_status=1 order by news_date desc",nativeQuery = true)
    List<News> getNewsForAlumnus(String partId);

    @Query(value = "select news_id,news_title,news_author,news_date,news_title_img from news where news_type=?1 and news_status=?2 order by news_date desc",nativeQuery = true)
    List<News> getNewsByNewsTypeAndNewsStatus(String newsType,String newsStatus);



    News getNewsByNewsId(String newsId);

    int deleteByNewsId(String newsId);

    List<News> getNewsByNewsPartId(String partId);

    List<News> getNewsByNewsType(String newsType);

    List<News> findAll();

    /**
     * 审核
     */
    @Query("update News set newsStatus=?1 where newsId=?2")
    int updateNewsStatusByNewsId(String newsStatus,String newsId);


}
