package com.xuefei.feign.fallback;

import com.xuefei.dto.BaseResp;
import com.xuefei.dto.BaseRespEumn;
import com.xuefei.feign.OpenFeignOrderClient;
import com.xuefei.pojo.Payment;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class OpenFeignOrderClientFactory implements FallbackFactory<OpenFeignOrderClient> {
    @Override
    public OpenFeignOrderClient create(Throwable throwable) {
        return new OpenFeignOrderClient() {
            @Override
            public BaseResp<Payment> getById(Long id) {
                return null;
            }

            @Override
            public BaseResp<String> hystrixOk(Integer id) {
                return null;
            }

            @Override
            public BaseResp<String> hystrixTimeOut(Integer id) {
                BaseResp<String> baseResp = new BaseResp<>();
                baseResp.setSuccess(false);
                baseResp.setCode(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getCode());
                baseResp.setMessage("消费者调用接口失败：id = " + id + "，失败原因：" + throwable.getMessage());
                return baseResp;
            }
        };
    }
}
