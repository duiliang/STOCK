package com.tianyu.stock.mapper;

import com.tianyu.stock.pojo.entity.StockOuterMarketIndexInfo;

/**
* @author duiliang
* @description 针对表【stock_outer_market_index_info(外盘详情信息表)】的数据库操作Mapper
* @createDate 2024-03-17 17:30:07
* @Entity com.tianyu.stock.pojo.entity.StockOuterMarketIndexInfo
*/
public interface StockOuterMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockOuterMarketIndexInfo record);

    int insertSelective(StockOuterMarketIndexInfo record);

    StockOuterMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockOuterMarketIndexInfo record);

    int updateByPrimaryKey(StockOuterMarketIndexInfo record);

}
