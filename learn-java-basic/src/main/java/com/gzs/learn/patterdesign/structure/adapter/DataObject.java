package com.gzs.learn.patterdesign.structure.adapter;

import com.thoughtworks.xstream.annotations.XStreamAliasType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XStreamAliasType("DataObject")
public class DataObject {
    private Integer id;
    private String name;

}
