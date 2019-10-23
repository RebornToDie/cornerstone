//package com.example.cornerstone.mybatisplus.service.impl;
//
//import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
//import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
//import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
//import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.example.cornerstone.mybatisplus.dao.SchoolDao;
//import com.example.cornerstone.mybatisplus.entity.School;
//import com.example.cornerstone.mybatisplus.service.SchoolService;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//
///**
// * @author reborntodie
// * @date 2019/10/17 18:41
// */
//@Service
//public class SchoolServiceImpl extends ServiceImpl<SchoolDao, School> implements SchoolService {
//    @Override
//    public School getSchoolById(int id) {
//        return baseMapper.selectById(id);
//    }
//
//    @Override
//    public boolean saveBatch(Collection<School> entityList) {
//        return false;
//    }
//
//    @Override
//    public boolean saveOrUpdateBatch(Collection<School> entityList) {
//        return false;
//    }
//
//    @Override
//    public boolean update(com.baomidou.mybatisplus.core.conditions.Wrapper<T> updateWrapper) {
//        return false;
//    }
//
//    @Override
//    public boolean updateBatchById(Collection<School> entityList) {
//        return false;
//    }
//
//    @Override
//    public School getOne(com.baomidou.mybatisplus.core.conditions.Wrapper<T> queryWrapper) {
//        return null;
//    }
//
//    @Override
//    public int count() {
//        return 0;
//    }
//
//    @Override
//    public List<School> list() {
//        return null;
//    }
//
//    @Override
//    public com.baomidou.mybatisplus.core.metadata.IPage<T> page(com.baomidou.mybatisplus.core.metadata.IPage<T> page) {
//        return null;
//    }
//
//    @Override
//    public List<Map<String, Object>> listMaps() {
//        return null;
//    }
//
//    @Override
//    public List<Object> listObjs() {
//        return null;
//    }
//
//    @Override
//    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
//        return null;
//    }
//
//    @Override
//    public List<Object> listObjs(com.baomidou.mybatisplus.core.conditions.Wrapper<T> queryWrapper) {
//        return null;
//    }
//
//    @Override
//    public com.baomidou.mybatisplus.core.metadata.IPage<Map<String, Object>> pageMaps(com.baomidou.mybatisplus.core.metadata.IPage<T> page) {
//        return null;
//    }
//
//    @Override
//    public QueryChainWrapper<School> query() {
//        return null;
//    }
//
//    @Override
//    public LambdaQueryChainWrapper<School> lambdaQuery() {
//        return null;
//    }
//
//    @Override
//    public UpdateChainWrapper<School> update() {
//        return null;
//    }
//
//    @Override
//    public LambdaUpdateChainWrapper<School> lambdaUpdate() {
//        return null;
//    }
//
//    @Override
//    public boolean saveOrUpdate(School entity, com.baomidou.mybatisplus.core.conditions.Wrapper<T> updateWrapper) {
//        return false;
//    }
//}
