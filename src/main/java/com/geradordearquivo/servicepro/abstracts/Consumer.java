package com.geradordearquivo.servicepro.abstracts;

import com.geradordearquivo.servicepro.templates.TemplateMSG;

public abstract class Consumer {
    public void consumeMessage(TemplateMSG message){}
    public void geradorDeRelatorio(TemplateMSG msg){}

}
