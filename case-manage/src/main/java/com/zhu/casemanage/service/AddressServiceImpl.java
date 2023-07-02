package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.casemanage.dao.AddressDao;
import com.zhu.casemanage.pojo.AddressPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl {
    @Autowired
    private AddressDao addressDao;

    public List<AddressPojo> findUserAddressList(int userId){
        List<AddressPojo> addressPojos = addressDao.selectList(new QueryWrapper<AddressPojo>().eq("user_id", userId));
        return addressPojos;
    }

    public void addAddress(AddressPojo newAddress){
        addressDao.insert(newAddress);
    }

}
