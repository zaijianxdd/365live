package com.zjxdd.mvp.model;

import com.zjxdd.mvp.contract.IPoetryContract;
import com.zjxdd.mvp.entity.PoetryEntity;
import com.zjxdd.mvp.iApiService.IGetPoetryEntity;
import com.zjxdd.mvp.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:31
 */
public class PoetryModel implements IPoetryContract.IpoetryModel {
   private static PoetryModel instance = null;

   private PoetryModel(){
   }

   public static PoetryModel getInstance() {
       synchronized (PoetryModel.class) {
           if (instance == null) {
               instance = new PoetryModel();
           }
       }
       return instance;
   }


    @Override
    public Observable<PoetryEntity> getPoetry() {
        return RetrofitManager.getInstance().createRs(IGetPoetryEntity.class).getPoetry();
    }
}
