package com.urjc.iagroup.bikesurbanfloats.history;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface HistoryReference {
    Class<? extends HistoricEntity> value();
}