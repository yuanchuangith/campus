package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.model.entity.Position;
import com.example.server.model.entity.Provicegroupinfo;
import com.example.server.model.view.workEntity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
@Mapper
public interface workMapper extends BaseMapper<Position> {
    //获取统计省份id
    Provicegroupinfo getProviceId();
    //工具统计省份id获取算法详细信息
    List<proviceInfo> getProviceInfo(String id);
    //获得工作教育要求
    List<educationRequire> geteducationRequires();
    //work页面随机工作展示
    List<Position> getPostionS();
    //work页面随工作机评论展示
    List<workContent> getContent();
    //获取各个职位数量详情
    List<positionNumRank> getpositionNumRank();
    //获得大行业的数量详情
    List<positionBigClass> getpositionBigNum();
    //workIndex获取省份
    List<provice> getprovices();

    //获取省份分类后的数量
    positionTotal getPositionProviceTotal(String proviceName);
    //获取全部职位数量
    positionTotal getPositionTotal();
    //获取根据行业分类职位数量
    positionTotal getPositionIndustryTotal(String industryID);
    //获取根据行业分类和省份职位数量
    positionTotal getPositionIndustryIDAndProviceTotal(String proviceName,String industryID);
    //获取根据工作名字搜索的数量 参数 搜索关键字，省份，是否使用是否，是否使用Like查询

    positionTotal getSearchTatolByTag(String keyWord,String provicName,int model,int whetherProvice);
    //获取根据公司名字搜索的数量 参数 搜索关键字，省份，是否使用是否，是否使用Like查询
    positionTotal getSearchTatolByConpany(String keyWord,String provicName,int model,int whetherProvice);
    //调用全部职位分页查询存储过程
    ArrayList<positionSimply> selectPosition(int satrt,int end);
    //调用根据省份分类分页查询存储过程
    ArrayList<positionSimply> selectPositionByProvice(String proviceName,int satrt,int end);
    //调用根据省份和行业分类分页查询存储过程
    ArrayList<positionSimply> selectPositionByProviceandIndustryID(String proviceName,String industryID,int satrt,int end);
    //调用根据行业分类分页查询存储过程
    ArrayList<positionSimply> selectPositionByIndustryID( String industryID,int satrt,int end);
    //获取行业分类
    ArrayList<classificationEntity> getPositionclassifications();
    //获取行业分类
    ArrayList<classificationEntity> getPositionmiddleclassifications();
    //获取行业分类
    ArrayList<classificationEntity> getPositionbigclassifications();
    //根据公司名字搜索
    ArrayList<searchKey> searchByConpanyNmae(String name);
    //根据工作名字搜索
    ArrayList<searchKey> searchByTagNmae(String name);
    //根据工作名字模糊搜索
    ArrayList<searchKey> searchByLikeTagNmae(String name);
    //根据公司名字模糊搜索
    ArrayList<searchKey> searchByLikeConpanyNmae(String name);

    //根据公司名字模糊搜索和省份精确搜索职位
    ArrayList<positionSimply> getSearchByconpanyNameAndProviceNamePosition(String proviceName,String conpanyName,int satrt,int end);


    //根据公司名字模糊搜索搜索职位
    ArrayList<positionSimply> getSearchByconpanyNamePosition(String keyWord,int satrt,int end);
    //根据Lkie匹配
    ArrayList<positionSimply> getSearchByLikeConpanyNamePositionOrProvice(String proviceName,String keyWord ,int satrt,int end);
    //根据职位和省份模糊搜索搜索职位
    ArrayList<positionSimply> getSearchByProviceNameAndTagPosition(String keyWord,String tag ,int satrt,int end);
    //根据公司名字模糊Like搜索职位
    ArrayList<positionSimply> getSearchByProviceNameAndLikeTagPosition(String proviceName,String keyWord ,int satrt,int end);

    ArrayList<positionSimply> getSearchByLikeTagPosition(String keyWord ,int satrt,int end);

    ArrayList<positionSimply> getSearchByTagPosition(String keyWord ,int satrt,int end);
    ArrayList<Position> getPositionDetail(String jobID);
}
