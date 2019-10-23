//package com.example.cornerstone.mybatisplus.controller;
//
//import com.example.cornerstone.mybatisplus.entity.School;
//import com.example.cornerstone.mybatisplus.service.SchoolService;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author reborntodie
// * @date 2019/10/17 18:43
// */
//@RestController
//@RequestMapping("/")
//public class SampleController {
//
//
//    @Autowired
//    private SchoolService schoolService;
//
//
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @ApiOperation(value = "测试")
//    @GetMapping("/home")
//    String home() {
//        return "Hello World!";
//    }
//
//
//
//    @ApiOperation(value = "采取mybatis方式查询")
//    @ApiImplicitParam(name = "id", value = "用户id", paramType = "path", required = true)
//    @GetMapping("/getSchool/maybatis/{id}")
//    public School helloSchool1(@PathVariable("id") int id) {
//        School school = schoolService.getSchoolById(id);
//        System.out.println(school);
//        return school;
//    }
//
////    @ApiOperation(value = "采取mybatis-plus方式查询")
////    @ApiImplicitParam(name = "id", value = "用户id", paramType = "path", required = true)
////    @GetMapping("/getSchool/maybatis-plus/{id}")
////    public School helloSchool2(@PathVariable("id") int id) {
////        School school = schoolService.selectById(id);
////        System.out.println(school);
////        return school;
////    }
////
////    @ApiOperation(value = "插入数据")
////    @ApiImplicitParam(name = "school", value = "学校信息", paramType = "body", required = true)
////    @PostMapping("/insert/school")
////    public Boolean insertSchool(@RequestBody School school) {
////        Boolean tag = schoolService.insert(school);
////        System.out.println(tag);
////        return tag;
////    }
//}
