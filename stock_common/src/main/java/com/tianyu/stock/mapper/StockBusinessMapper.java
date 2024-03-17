package com.tianyu.stock.mapper;

import com.tianyu.stock.pojo.entity.StockBusiness;

/**
* @author duiliang
* @description 针对表【stock_business(主营业务表)】的数据库操作Mapper
* @createDate 2024-03-17 17:30:07
* @Entity com.tianyu.stock.pojo.entity.StockBusiness
*/
public interface StockBusinessMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBusiness record);

    int insertSelective(StockBusiness record);

    StockBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBusiness record);

    int updateByPrimaryKey(StockBusiness record);

}
