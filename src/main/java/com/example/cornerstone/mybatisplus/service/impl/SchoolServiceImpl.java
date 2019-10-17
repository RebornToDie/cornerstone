package com.example.cornerstone.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cornerstone.mybatisplus.dao.SchoolDao;
import com.example.cornerstone.mybatisplus.entity.School;
import com.example.cornerstone.mybatisplus.service.SchoolService;
import org.springframework.stereotype.Service;

/**
 * @author reborntodie
 * @date 2019/10/17 18:41
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolDao, School> implements SchoolService {
    @Override
    public School getSchoolById(int id) {
        return baseMapper.selectById(id);
    }
}
