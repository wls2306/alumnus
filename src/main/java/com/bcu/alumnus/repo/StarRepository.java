package com.bcu.alumnus.repo;

import com.bcu.alumnus.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarRepository extends JpaRepository<Star,String> , JpaSpecificationExecutor {
    /**
     * 1. 校园明星发布，初始发布状态后台设置为0，待审核。 @UseToken(level=2)
     * 2. 通过编号进行校园明星审核（将状态改为1）@UseToken(level=4)
     * 3. 删除校园明星 @UseToken(level=4)
     * 4. 通过学部编号查找状态为可见的校园明星，时间倒序排列
     * 5. 查找所有状态为可见的校园明星，时间倒序排列
     * 6. 通过校园明星编号查询校园明星
     * 7. 通过用户编号查询校园明星
     * 8. 通过学部编号查询校园明星
     * 9. 根据可见状态查找校园明星
     * 10. 通过校园明星编号更新点赞数量
     * 11. 通过主人公编号查询校园明星
     * 12. ...
     */
    @Query(value = "update Star set starStatus=?1 where starId=?2")     
    int updateStarStatusByStarId(String starStatus, String starId);

    int deleteByStarId(String starId);

    @Query(value = "select * from star where star_part_id=?1 and star_status=1 order by star_create_date desc ",nativeQuery = true)
    List<Star> getStarByStarPartIdAndAndStarStatus(String partId);

    @Query(value = "select * from star where star_status=?1 order by star_create_date desc ",nativeQuery = true)
    List<Star> getAllStar(String starStatus);

    Star getStarByStarId(String starId);

    //List<Star> getStarByUserId(String userId);

    List<Star> getStarByStarPartId(String starPartId);

    List<Star> getStarByStarStatus(String starStatus);

    @Query(value = "update Star set starLikeCount=?1 where starId=?2")
    int updateStarLikeCountByStarId(String starLikeCount, String starId);

    List<Star> getStarByStarUserId(String starUserId);
}
