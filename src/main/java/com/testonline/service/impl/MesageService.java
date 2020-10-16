/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testonline.service.impl;

import com.testonline.service.IMesageService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MesageService implements IMesageService{
 
    @Override
    public void putMesageSuccess(Model model, String string) {
        model.addAttribute("mesage", string);
        model.addAttribute("mesageColor", "alert-success");
    }

    @Override
    public void putMesageDanger(Model model, String string) {
        model.addAttribute("mesage", string);
        model.addAttribute("mesageColor", "alert-danger");
    }

    @Override
    public void putMesageWarning(Model model, String string) {
        model.addAttribute("mesage", string);
        model.addAttribute("mesageColor", "alert-warning");
    }
    
}
