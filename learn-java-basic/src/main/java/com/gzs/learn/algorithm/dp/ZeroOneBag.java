package com.gzs.learn.algorithm.dp;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 0-1背包问题
 * 
 * @author guanzhisong
 */
public class ZeroOneBag {
    static int[] v = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    static int[] w = {2, 1, 5, 6, 8, 12, 35, 62, 18};
    static List<ZeroOneModel> models = Lists.newArrayList();
    static Map<Integer, List<ZeroOneModel>> result = Maps.newHashMap();
    static {
        for (int i = 0; i < v.length; i++) {
            models.add(new ZeroOneModel(v[i], w[i]));
        }
    }

    public List<ZeroOneBag> getMaxWeight(int capacity, int index) {
        return null;
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class ZeroOneModel {
    // 容量
    private Integer vol;
    // 价值
    private Integer weight;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ZeroOneModel) {
            ZeroOneModel model = (ZeroOneModel) obj;
            if (Objects.equals(this.vol, model.getVol()) && Objects.equals(this.weight, model.getWeight())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = vol.hashCode();
        result = 31 * result + weight.hashCode();
        return result;
    }
}
