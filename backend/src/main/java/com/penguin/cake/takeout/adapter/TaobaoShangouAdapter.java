package com.penguin.cake.takeout.adapter;
import com.penguin.cake.takeout.TakeoutPlatform;
import org.springframework.stereotype.Component;
@Component
public class TaobaoShangouAdapter extends AbstractDemoTakeoutAdapter {
    public TakeoutPlatform platform() { return TakeoutPlatform.TAOBAO_SHANGOU; }
}
