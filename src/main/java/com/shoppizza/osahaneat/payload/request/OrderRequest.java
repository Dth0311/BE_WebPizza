package com.shoppizza.osahaneat.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private int userId;
    private int restId;
    private int[] foodIds;
}
