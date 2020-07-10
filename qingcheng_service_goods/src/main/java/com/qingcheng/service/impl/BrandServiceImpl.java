package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.BrandMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public PageResult<Brand> findPage(int page, int size) {
        /*第25行和26行一起用的，25行定义了所查分页的信息，26行定义了分页源头*/
        PageHelper.startPage(page,size);
        Page<Brand> pageResult=(Page<Brand>) brandMapper.selectAll();
        /*由于Page<Brand>的对象已经接受到了数据，所以我们再把分页信息取出一部分包装到我们自己写的分页对象里面即可*/
        return new PageResult<>(pageResult.getTotal(),pageResult.getResult());
    }
    /*条件查询*/
    @Override
    public List<Brand> findList(Map<String, Object> map) {
        Example example = createExample(map);
        return brandMapper.selectByExample(example);
    }

    private Example createExample(Map<String, Object> map){
        /*创建条对象*/
        Example example=new Example(Brand.class);
        /*在条件对象中做限制对象*/
        Example.Criteria criteria=example.createCriteria();
        /*对限制对象进行条件的添加*/
        if (map!=null){
            if (map.get("name")!=null&&!"".equals(map.get("name"))){
                criteria.andLike("name","%"+(String)map.get("name")+"%");
            }
            if (map.get("letter")!=null&&!"".equals(map.get("letter"))){
                criteria.andEqualTo("letter",(String)map.get("letter"));

            }
        }
        return example;

    }

    @Override
    public PageResult<Brand> findPage(Map<String, Object> map, int page, int size) {
        Example example = createExample(map);
        PageHelper.startPage(page,size);
        Page<Brand> pageResult = (Page<Brand>) brandMapper.selectByExample(example);//先条件查出来
        return new PageResult<>(pageResult.getTotal(),pageResult.getResult());
    }

    @Override
    public Brand findById(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }
}
