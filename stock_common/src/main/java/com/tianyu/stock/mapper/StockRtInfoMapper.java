package com.tianyu.stock.mapper;

import com.tianyu.stock.pojo.entity.StockRtInfo;

/**
* @author duiliang
* @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
* @createDate 2024-03-17 17:30:07
* @Entity com.tianyu.stock.pojo.entity.StockRtInfo
*/
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);

}
