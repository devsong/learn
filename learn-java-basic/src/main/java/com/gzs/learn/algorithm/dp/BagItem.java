package com.gzs.learn.algorithm.dp;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 背包条目对象
 * 
 * @author guanzhisong
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BagItem {
    /**
     * 容量
     */
    private int vol;
    /**
     * 数量
     */
    private int count;
    /**
     * 价值
     */
    private int value;

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof BagItem) {
            return ((BagItem) o).getVol() == this.vol;
        }
        return false;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.vol);
        return builder.build();
    }

}
