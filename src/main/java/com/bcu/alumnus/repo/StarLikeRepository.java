package com.bcu.alumnus.repo;

import com.bcu.alumnus.entity.StarLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarLikeRepository extends JpaRepository<StarLike,String> , JpaSpecificationExecutor {
    /**
     * 1. 添加点赞记录 @UseToken(level=1)
     * 2. 通过明星编号查找点赞记录
     * 3. 通过明星编号统计点赞数量
     * 4. 通过用户编号查找点赞记录
     * 5. 通过用户编号统计点赞数量
     * 6. 通过点赞编号、用户编号删除该点赞记录 @UseToken(level=1)
     * 7. ...
     */
    @Query(value = "insert into star_like values(?,?)",nativeQuery = true)
    int insertStarLike(Integer starId, String userId);

    List<StarLike> getStarLikeByStarId(String starId);

    @Query(value = "select count(id) from star_like where star_id=?1",nativeQuery = true)
    int getStarLikeCountByStarId(String starId);

    List<StarLike> getStarLikeByUserId(String userId);

    @Query(value = "select count(id) from star_like where user_id=?1",nativeQuery = true)
    int getStarLikeCountByUserId(String userId);

    @Query(value = "delete from star_like where id=?1 and user_id=?2",nativeQuery = true)
    int deleteStarLikeByIdAndUserId(String id, String userId);
}
