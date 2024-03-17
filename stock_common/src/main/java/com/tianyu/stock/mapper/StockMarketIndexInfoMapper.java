package com.tianyu.stock.mapper;

import com.tianyu.stock.pojo.entity.StockMarketIndexInfo;

/**
* @author duiliang
* @description 针对表【stock_market_index_info(国内大盘数据详情表)】的数据库操作Mapper
* @createDate 2024-03-17 17:30:07
* @Entity com.tianyu.stock.pojo.entity.StockMarketIndexInfo
*/
public interface StockMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockMarketIndexInfo record);

    int insertSelective(StockMarketIndexInfo record);

    StockMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketIndexInfo record);

    int updateByPrimaryKey(StockMarketIndexInfo record);

}
