/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service;
 
import org.springframework.ui.Model;

public interface IMesageService {
    void putMesageSuccess(Model theModel,String mesage);
    void putMesageDanger(Model theModel,String mesage);
    void putMesageWarning(Model theModel,String mesage);
}
