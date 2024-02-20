package com.shoppizza.osahaneat.service.imp;

import com.shoppizza.osahaneat.payload.request.OrderRequest;

public interface IOrderService {
    boolean insertOrder(OrderRequest orderRequest);
}
