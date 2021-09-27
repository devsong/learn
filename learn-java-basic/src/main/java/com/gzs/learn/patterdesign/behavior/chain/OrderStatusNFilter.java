package com.gzs.learn.patterdesign.behavior.chain;

import org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusNFilter implements Filter {

    private Filter next;

    @Override
    public void doFilter(FilterContext ctx) {
        if (StringUtils.equalsIgnoreCase(ctx.getOrderStatus(), "N")) {
            System.out.println("i am order status [N] filter");
        }
        if (next != null) {
            next.doFilter(ctx);
        }
    }

}
