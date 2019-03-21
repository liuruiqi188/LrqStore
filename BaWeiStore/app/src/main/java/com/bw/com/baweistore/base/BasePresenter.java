package com.bw.com.baweistore.base;

/**
 * @author liuruiqi
 * @fileName BasePresenter
 * @package com.bw.com.baweistore.base
 * @date 2019/3/20 14:38
 **/
public class BasePresenter<V> {
    public V view;
       public void attach(V view){
           this.view=view;
       }
}
